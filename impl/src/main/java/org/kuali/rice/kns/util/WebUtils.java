/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kns.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServletWrapper;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.kuali.rice.kns.datadictionary.AttributeDefinition;
import org.kuali.rice.kns.datadictionary.AttributeSecurity;
import org.kuali.rice.kns.datadictionary.DataDictionary;
import org.kuali.rice.kns.datadictionary.DataDictionaryEntryBase;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.mask.MaskFormatter;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.exception.FileUploadLimitExceededException;
import org.kuali.rice.kns.exception.ValidationException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.action.KualiMultipartRequestHandler;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.web.struts.pojo.PojoFormBase;

/**
 * General helper methods for handling requests.
 * 
 * 
 */
public class WebUtils {
    private static final Logger LOG = Logger.getLogger(WebUtils.class);

    private static final String IMAGE_COORDINATE_CLICKED_X_EXTENSION = ".x";
    private static final String IMAGE_COORDINATE_CLICKED_Y_EXTENSION = ".y";
    
    /**
     * Checks for methodToCall parameter, and picks off the value using set dot notation. Handles the problem of image submits.
     * 
     * @param request
     * @return methodToCall String
     */
    public static String parseMethodToCall(ActionForm form, HttpServletRequest request) {
        String methodToCall = null;
        
        // check if is specified cleanly
        if (StringUtils.isNotBlank(request.getParameter(KNSConstants.DISPATCH_REQUEST_PARAMETER))) {
        	if (form instanceof KualiForm && !((KualiForm) form).shouldMethodToCallParameterBeUsed(KNSConstants.DISPATCH_REQUEST_PARAMETER,
        				request.getParameter(KNSConstants.DISPATCH_REQUEST_PARAMETER), request)) {
    			throw new RuntimeException("Cannot verify that the methodToCall should be " + request.getParameter(KNSConstants.DISPATCH_REQUEST_PARAMETER));
        	}
    		methodToCall = request.getParameter(KNSConstants.DISPATCH_REQUEST_PARAMETER);
    		// include .x at the end of the parameter to make it consistent w/ other parameters
    		request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, KNSConstants.DISPATCH_REQUEST_PARAMETER + "." + methodToCall + IMAGE_COORDINATE_CLICKED_X_EXTENSION);
        }

        /**
         * The reason why we are checking for a ".x" at the end of the parameter name:
         * It is for the image names that in addition to sending the form data, 
         * the web browser sends the x,y coordinate of where the user clicked on the image. 
         * If the image input is not given a name then the browser sends the x and y coordinates as the "x" and "y" input fields. 
         * If the input image does have a name, the x and y coordinates are sent using the format name.x and name.y. 
         */
        if (methodToCall == null) {
            // iterate through parameters looking for methodToCall
            for (Enumeration i = request.getParameterNames(); i.hasMoreElements();) {
                String parameterName = (String) i.nextElement();

                // check if the parameter name is a specifying the methodToCall
                if (parameterName.startsWith(KNSConstants.DISPATCH_REQUEST_PARAMETER) 
                		&& endsWithCoordinates(parameterName)) {
                	if (form instanceof ActionForm && !((KualiForm) form).shouldMethodToCallParameterBeUsed(parameterName, request.getParameter(parameterName), request)) {
            			throw new RuntimeException("Cannot verify that the methodToCall should be " + parameterName);
            		}
                    methodToCall = StringUtils.substringBetween(parameterName, KNSConstants.DISPATCH_REQUEST_PARAMETER + ".", ".");
                    request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, parameterName);
                    // Fix for KRACOEUS-267, KULRICE-1412, KULRICE-1425, and KFSMI-110
                    // Add this to return the method to call once it is matched
                    break; 
                } else { 
                    // KULRICE-1218: Check if the parameter's values match (not just the name)
                    for (String value : request.getParameterValues(parameterName)) {
                        if (value.startsWith(KNSConstants.DISPATCH_REQUEST_PARAMETER) 
                        		&& endsWithCoordinates(value)) {
                        	if (form instanceof ActionForm && !((KualiForm) form).shouldMethodToCallParameterBeUsed(parameterName, value, request)) {
                        		throw new RuntimeException("Cannot verify that the methodToCall should be " + value);
                        	}
                            methodToCall = StringUtils.substringBetween(value, KNSConstants.DISPATCH_REQUEST_PARAMETER + ".", ".");
                            request.setAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE, value);
                        }
                    }
                }
            }
        }
        
        return methodToCall;
    }


    /**
     * Iterates through and logs (at the given level) all attributes and parameters of the given request onto the given Logger
     * 
     * @param request
     * @param logger
     */
    public static void logRequestContents(Logger logger, Level level, HttpServletRequest request) {
        if (logger.isEnabledFor(level)) {
            logger.log(level, "--------------------");
            logger.log(level, "HttpRequest attributes:");
            for (Enumeration e = request.getAttributeNames(); e.hasMoreElements();) {
                String attrName = (String) e.nextElement();
                Object attrValue = request.getAttribute(attrName);

                if (attrValue.getClass().isArray()) {
                    logCollection(logger, level, attrName, Arrays.asList((Object[]) attrValue));
                }
                else if (attrValue instanceof Collection) {
                    logCollection(logger, level, attrName, (Collection) attrValue);
                }
                else if (attrValue instanceof Map) {
                    logMap(logger, level, attrName, (Map) attrValue);
                }
                else {
                    logObject(logger, level, attrName, attrValue);
                }
            }

            logger.log(level, "--------------------");
            logger.log(level, "HttpRequest parameters:");
            for (Enumeration i = request.getParameterNames(); i.hasMoreElements();) {
                String paramName = (String) i.nextElement();
                String[] paramValues = (String[]) request.getParameterValues(paramName);

                logArray(logger, level, paramName, paramValues);
            }

            logger.log(level, "--------------------");
        }
    }


    private static void logArray(Logger logger, Level level, String arrayName, Object[] array) {
        StringBuffer value = new StringBuffer("[");
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                value.append(",");
            }
            value.append(array[i]);
        }
        value.append("]");

        logThing(logger, level, arrayName, value);
    }

    private static void logCollection(Logger logger, Level level, String collectionName, Collection c) {
        StringBuffer value = new StringBuffer("{");
        for (Iterator i = c.iterator(); i.hasNext();) {
            value.append(i.next());
            if (i.hasNext()) {
                value.append(",");
            }
        }
        value.append("}");

        logThing(logger, level, collectionName, value);
    }

    private static void logMap(Logger logger, Level level, String mapName, Map m) {
        StringBuffer value = new StringBuffer("{");
        for (Iterator i = m.entrySet().iterator(); i.hasNext();) {
            Map.Entry e = (Map.Entry) i.next();
            value.append("('" + e.getKey() + "','" + e.getValue() + "')");
        }
        value.append("}");

        logThing(logger, level, mapName, value);
    }

    private static void logObject(Logger logger, Level level, String objectName, Object o) {
        logThing(logger, level, objectName, "'" + o + "'");
    }

    private static void logThing(Logger logger, Level level, String thingName, Object thing) {
        logger.log(level, "    '" + thingName + "' => " + thing);
    }

    /**
     * A file that is not of type text/plain or text/html can be output through the response using this method.
     * 
     * @param response
     * @param contentType
     * @param outStream
     * @param fileName
     */
    public static void saveMimeOutputStreamAsFile(HttpServletResponse response, String contentType, ByteArrayOutputStream byteArrayOutputStream, String fileName) throws IOException {

        // set response
        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentLength(byteArrayOutputStream.size());

        // write to output
        OutputStream outputStream = response.getOutputStream();
        byteArrayOutputStream.writeTo(response.getOutputStream());
        outputStream.flush();
        outputStream.close();
    }

    /**
     * A file that is not of type text/plain or text/html can be output through the response using this method.
     * 
     * @param response
     * @param contentType
     * @param outStream
     * @param fileName
     */
    public static void saveMimeInputStreamAsFile(HttpServletResponse response, String contentType, InputStream inStream, String fileName, int fileSize) throws IOException {

        // set response
        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentLength(fileSize);

        // write to output
        OutputStream out = response.getOutputStream();
        while (inStream.available() > 0) {
            out.write(inStream.read());
        }
        out.flush();
    }
    
    /**
     * JSTL function to return the tab state of the tab from the form. 
     * 
     * @param form
     * @param tabKey
     * @return
     */
    public static String getTabState(KualiForm form, String tabKey) {
        return form.getTabState(tabKey);
    }
    
    public static void incrementTabIndex(KualiForm form, String tabKey) {
    	form.incrementTabIndex();
    }
    
    /**
     * Generates a String from the title that can be used as a Map key.
     * 
     * @param tabTitle
     * @return
     */
    public static String generateTabKey(String tabTitle) {
        String key = "";
        if (!StringUtils.isBlank(tabTitle)) {
            key = tabTitle.replaceAll("\\W", "");
//            if (key.length() > 25) {
//                key = key.substring(0, 24);
//            }
        }
        
        return key;
    }

    public static void getMultipartParameters(HttpServletRequest request, ActionServletWrapper servletWrapper, ActionForm form) {
        Map params = new HashMap();

        // Get the ActionServletWrapper from the form bean
        //ActionServletWrapper servletWrapper = getServletWrapper();
        boolean isMultipart = false;
        try {
            // Obtain a MultipartRequestHandler
            MultipartRequestHandler multipartHandler = getMultipartHandler(request, form);

            if (multipartHandler != null) {
                isMultipart = true;
                // Set servlet and mapping info
                if (servletWrapper != null) {
                    // from pojoformbase
                    // servlet only affects tempdir on local disk
                    servletWrapper.setServletFor(multipartHandler);
                }
                multipartHandler.setMapping((ActionMapping) request.getAttribute(Globals.MAPPING_KEY));
                // Initialize multipart request class handler
                multipartHandler.handleRequest(request);
                // stop here if the maximum length has been exceeded
                Boolean maxLengthExceeded = (Boolean) request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
                if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue())) {
                    throw new FileUploadLimitExceededException("");
                }
                // get file elements for kualirequestprocessor
                if (servletWrapper == null) {
                    request.setAttribute(KNSConstants.UPLOADED_FILE_REQUEST_ATTRIBUTE_KEY,getFileParametersForMultipartRequest(request, multipartHandler));
                }
            }
        }
        catch (ServletException e) {
            throw new ValidationException("unable to handle multipart request " + e.getMessage(),e);
        }
    }

    private static MultipartRequestHandler getMultipartHandler(HttpServletRequest request, ActionForm form) throws ServletException {
        KualiMultipartRequestHandler multipartHandler = new KualiMultipartRequestHandler();
        if (form instanceof PojoFormBase) {
            multipartHandler.setMaxUploadSizeToMaxOfList( ((PojoFormBase) form).getMaxUploadSizes() );
        }
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "Max File Upload Size: " + multipartHandler.getSizeMaxString() );
        }
        return multipartHandler;
    }

    private static Map getFileParametersForMultipartRequest(HttpServletRequest request, MultipartRequestHandler multipartHandler) {
        Map parameters = new HashMap();
        Hashtable elements = multipartHandler.getFileElements();
        Enumeration e = elements.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            parameters.put(key, elements.get(key));
        }

        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
            e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                parameters.put(key, request.getParameterValues(key));
            }
        }
        else {
            LOG.debug("Gathering multipart parameters for unwrapped request");
        }
        return parameters;
    }


    // end multipart
    
    public static void registerEditableProperty(PojoFormBase form, String editablePropertyName){
    	form.registerEditableProperty(editablePropertyName);
    }
    
    public static boolean isDocumentSession(Document document, PojoFormBase docForm){
		boolean sessionDoc = document instanceof org.kuali.rice.kns.document.SessionDocument;
		boolean dataDictionarySessionDoc = false;
		if (!sessionDoc) {
			DocumentEntry documentEntry = null;
			DataDictionary dataDictionary = KNSServiceLocator.getDataDictionaryService().getDataDictionary();
			if (docForm instanceof KualiMaintenanceForm) {
				KualiMaintenanceForm maintenanceForm = (KualiMaintenanceForm) docForm;
				if (dataDictionary != null) {
					documentEntry = dataDictionary.getDocumentEntry(maintenanceForm.getDocTypeName());
					dataDictionarySessionDoc = documentEntry.isSessionDocument();
				}
			} else {
				if (document!=null && dataDictionary != null) {
					documentEntry = dataDictionary.getDocumentEntry(document.getClass().getName());
					dataDictionarySessionDoc = documentEntry.isSessionDocument();
				}
			}
		}
		return sessionDoc || dataDictionarySessionDoc;
    }
    
    public static boolean isFormSessionDocument(PojoFormBase form){
    	Document document = null;
    	if (KualiDocumentFormBase.class.isAssignableFrom(form.getClass())) {
			KualiDocumentFormBase docForm = (KualiDocumentFormBase)form;
			document = docForm.getDocument();
    	}
    	return isDocumentSession(document, form);
    }
    
    public static String KEY_KUALI_FORM_IN_SESSION = "KualiForm";
    public static ActionForm getKualiForm(PageContext pageContext){
    	return getKualiForm((HttpServletRequest)pageContext.getRequest());
    }
    
    public static ActionForm getKualiForm(HttpServletRequest request){
    	if(request.getAttribute(KEY_KUALI_FORM_IN_SESSION)!=null)
    		return (ActionForm)request.getAttribute(KEY_KUALI_FORM_IN_SESSION);
    	else
    		return (ActionForm)request.getSession().getAttribute(KEY_KUALI_FORM_IN_SESSION);
    }
    
    public static boolean isPropertyEditable(Set<String> editableProperties, String propertyName){
    	if (LOG.isDebugEnabled()) {
    		LOG.debug("editableProperties: "+editableProperties);
    	}
    	
    	boolean returnVal =  editableProperties.contains(propertyName) ||
    			(getIndexOfCoordinateExtension(propertyName)==-1?false:
    				editableProperties.contains(
    						propertyName.substring(0, getIndexOfCoordinateExtension(propertyName))));
    	return returnVal;
    }
    
    public static boolean endsWithCoordinates(String parameter){
    	return parameter.endsWith(WebUtils.IMAGE_COORDINATE_CLICKED_X_EXTENSION) ||
    			parameter.endsWith(WebUtils.IMAGE_COORDINATE_CLICKED_Y_EXTENSION);
    }

    public static int getIndexOfCoordinateExtension(String parameter){
		int indexOfCoordinateExtension = parameter.lastIndexOf(WebUtils.IMAGE_COORDINATE_CLICKED_X_EXTENSION);
		if(indexOfCoordinateExtension==-1)
			indexOfCoordinateExtension = parameter.lastIndexOf(WebUtils.IMAGE_COORDINATE_CLICKED_Y_EXTENSION);
		return indexOfCoordinateExtension;
    }
    
    public static String getFullyMaskedValue(String className, String fieldName, Object formObject, String propertyName){
    	String displayMaskValue = null;
    	Object propertyValue = ObjectUtils.getPropertyValue(formObject, propertyName);
    	
    	DataDictionaryEntryBase entry = (DataDictionaryEntryBase) KNSServiceLocator.getDataDictionaryService().getDataDictionary().getDictionaryObjectEntry(className);
    	AttributeDefinition a = entry.getAttributeDefinition(fieldName);
    	
    	AttributeSecurity attributeSecurity = a.getAttributeSecurity();
    	if(attributeSecurity != null && attributeSecurity.isMask()){
    		MaskFormatter maskFormatter = attributeSecurity.getMaskFormatter();
    		displayMaskValue =  maskFormatter.maskValue(propertyValue);
    		
    	}        
    	return displayMaskValue;
    }
    
    public static String getPartiallyMaskedValue(String className, String fieldName, Object formObject, String propertyName){
    	String displayMaskValue = null;
    	Object propertyValue = ObjectUtils.getPropertyValue(formObject, propertyName);
    	
    	DataDictionaryEntryBase entry = (DataDictionaryEntryBase) KNSServiceLocator.getDataDictionaryService().getDataDictionary().getDictionaryObjectEntry(className);
    	AttributeDefinition a = entry.getAttributeDefinition(fieldName);
    	
    	AttributeSecurity attributeSecurity = a.getAttributeSecurity();
    	if(attributeSecurity != null && attributeSecurity.isPartialMask()){
    		MaskFormatter partialMaskFormatter = attributeSecurity.getPartialMaskFormatter();
    		displayMaskValue =  partialMaskFormatter.maskValue(propertyValue);
    		
    	}        
    	return displayMaskValue;
    }
    
    public static boolean canFullyUnmaskField(String businessObjectClassName, String fieldName) {
		    Class businessObjClass = null;
		    try{
		    	businessObjClass = Class.forName(businessObjectClassName);
		    	
		    }catch(Exception e){
		    	throw new RuntimeException("Unable to create instance of the class: " + businessObjClass.getName());
		    }
		    return KNSServiceLocator.getBusinessObjectAuthorizationService().canFullyUnmaskField(GlobalVariables.getUserSession().getPerson(),
		  			   businessObjClass, fieldName);
    }
    
    public static boolean canPartiallyUnmaskField(String businessObjectClassName, String fieldName) {
	    Class businessObjClass = null;
	    try{
	    	businessObjClass = Class.forName(businessObjectClassName);
	    	
	    }catch(Exception e){
	    	throw new RuntimeException("Unable to create instance of the class: " + businessObjClass.getName());
	    }
	    return KNSServiceLocator.getBusinessObjectAuthorizationService().canPartiallyUnmaskField(GlobalVariables.getUserSession().getPerson(),
	  			   businessObjClass, fieldName);
    }
    
    public static boolean canAddNoteAttachment(Document document) {
    	boolean canViewNoteAttachment = false;
    	DocumentAuthorizer documentAuthorizer = KNSServiceLocator.getDocumentHelperService().getDocumentAuthorizer(document);
    	canViewNoteAttachment = documentAuthorizer.canAddNoteAttachment(document, null, GlobalVariables.getUserSession().getPerson());
    	return canViewNoteAttachment;
    }
    
    public static boolean canViewNoteAttachment(Document document, String attachmentTypeCode) {
    	boolean canViewNoteAttachment = false;
    	DocumentAuthorizer documentAuthorizer = KNSServiceLocator.getDocumentHelperService().getDocumentAuthorizer(document);
    	canViewNoteAttachment = documentAuthorizer.canViewNoteAttachment(document, attachmentTypeCode, GlobalVariables.getUserSession().getPerson());
    	return canViewNoteAttachment;
    }
    
    public static boolean canDeleteNoteAttachment(Document document, String attachmentTypeCode, String authorUniversalIdentifier) {
    	boolean canDeleteNoteAttachment = false;
    	DocumentAuthorizer documentAuthorizer = KNSServiceLocator.getDocumentHelperService().getDocumentAuthorizer(document);
    	canDeleteNoteAttachment = documentAuthorizer.canDeleteNoteAttachment(document, attachmentTypeCode, "false", GlobalVariables.getUserSession().getPerson());
    	if(canDeleteNoteAttachment){
    		return canDeleteNoteAttachment;
    	}else{
    		canDeleteNoteAttachment = documentAuthorizer.canDeleteNoteAttachment(document, attachmentTypeCode, "true", GlobalVariables.getUserSession().getPerson());
    		if(canDeleteNoteAttachment && authorUniversalIdentifier.equals(GlobalVariables.getUserSession().getPerson().getPrincipalId())){
    			return true;
    		}
    	}
    	return canDeleteNoteAttachment;
    }
    
    public static void reuseErrorMapFromPreviousRequest(KualiDocumentFormBase kualiDocumentFormBase) {
    	if (kualiDocumentFormBase.getErrorMapFromPreviousRequest() == null) {
    		throw new RuntimeException("Error map from previous request is null!");
    	}
    	ErrorMap errorMapFromGlobalVariables = GlobalVariables.getErrorMap();
    	if (kualiDocumentFormBase.getErrorMapFromPreviousRequest() == errorMapFromGlobalVariables) {
    		// if we've switched them already, then return early and do nothing
    		return;
    	}
    	if (!errorMapFromGlobalVariables.isEmpty()) {
    		throw new RuntimeException("Cannot replace error map because it is not empty");
    	}
    	GlobalVariables.setErrorMap(kualiDocumentFormBase.getErrorMapFromPreviousRequest());
    	GlobalVariables.getErrorMap().clearErrorPath();
    }
    
    /**
     * When a struts action handler method attempts to upload a file to the user's browser, editable properties are cleared out because the JSP is not rendered,
     * but the HTML page is still viewable to the user.  After downloading the file, when the user tries to click on a button on the screen, it will complain that
     * the methodToCall couldn't be verified.  This method will re-register all of the editable properties from the previous request (i.e. the request that rendered the 
     * HTML page) so that the user can use the HTML page. 
     * 
     * @param kualiForm
     */
    public static void reRegisterEditablePropertiesFromPreviousRequest(KualiForm kualiForm) {
    	Set<String> editableProperties = kualiForm.getEditablePropertiesFromPreviousRequest();
        for (String editableProperty : editableProperties) {
            kualiForm.registerEditableProperty(editableProperty);
        }
    }
    
    /**
     * Excapes out HTML to prevent XSS attacks, and replaces the following strings to allow for a limited set of HTML tags
     * 
     * <li> [X] and [/X], where X represents any 1 or 2 letter string may be used to specify the equivalent tag in HTML (i.e. &lt;X&gt; and &lt;/X&gt;)
     * <li> [font COLOR], where COLOR represents any valid html color (i.e. color name or hexcode preceeded by #) will be filtered into &lt;font color="COLOR"/&gt;
     * <li> [/font] will be filtered into &lt;/font&gt;
     *  
     * @param inputString
     * @return
     */
    public static String filterHtmlAndReplaceRiceMarkup(String inputString) {
    	String outputString = StringEscapeUtils.escapeHtml(inputString);
    	// string has been escaped of all <, >, and & (and other characters)
    	
    	Map<String, String> findAndReplacePatterns = new HashMap<String, String>();
    	
    	// now replace our rice custom markup into html

    	// DON'T ALLOW THE SCRIPT TAG OR ARBITRARY IMAGES/URLS/ETC. THROUGH
    	
    	// filter any one character tags
    	findAndReplacePatterns.put("\\[([A-Za-z])\\]", "<$1>");
    	findAndReplacePatterns.put("\\[/([A-Za-z])\\]", "</$1>");    	
    	// filter any two character tags
    	findAndReplacePatterns.put("\\[([A-Za-z]{2})\\]", "<$1>");
    	findAndReplacePatterns.put("\\[/([A-Za-z]{2})\\]", "</$1>");
    	// filter the font tag
    	findAndReplacePatterns.put("\\[font (#[0-9A-Fa-f]{1,6}|[A-Za-z]+)\\]", "<font color=\"$1\">");
    	findAndReplacePatterns.put("\\[/font\\]", "</font>");
    	
    	for (String findPattern : findAndReplacePatterns.keySet()) {
    		Pattern p = Pattern.compile(findPattern);
    		Matcher m = p.matcher(outputString);
    		if (m.find()) {
    			String replacePattern = findAndReplacePatterns.get(findPattern);
    			outputString = m.replaceAll(replacePattern);
    		}
    	}
    	
    	return outputString;
    }
    
    public static boolean containsSensitiveDataPatternMatch(String fieldValue) {
    	if (fieldValue == null) {
    		return false;
    	}
    	String sensitiveDataPatterns = "[0-9]{9};[0-9]{3}-[0-9]{2}-[0-9]{4}";
    	if (StringUtils.isBlank(sensitiveDataPatterns)) {
    		return false;
    	}
    	StringTokenizer tok = new StringTokenizer(sensitiveDataPatterns, ";", false);
    	while (tok.hasMoreTokens()) {
    		String pattern = tok.nextToken();
    		if (Pattern.compile(pattern).matcher(fieldValue).find()) {
    			return true;
    		}
    	}
    	return false;
    }
}