/*
 * Copyright 2005-2007 The Kuali Foundation.
 *
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
package org.kuali.rice.kew.doctype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.kew.doctype.DocumentType;
import org.kuali.rice.kew.doctype.service.DocumentTypeService;
import org.kuali.rice.kew.doctype.service.impl.DocumentTypeServiceImpl;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.engine.node.NodeType;
import org.kuali.rice.kew.engine.node.Process;
import org.kuali.rice.kew.engine.node.RouteNode;
import org.kuali.rice.kew.export.ExportDataSet;
import org.kuali.rice.kew.export.ExportFormat;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.test.KEWTestCase;
import org.kuali.rice.kew.test.TestUtilities;
import org.kuali.rice.kew.util.XmlHelper;
import org.kuali.rice.kew.workgroup.GroupNameId;
import org.kuali.rice.kew.workgroup.Workgroup;
import org.kuali.rice.kew.xml.export.DocumentTypeXmlExporter;


public class DocumentTypeTest extends KEWTestCase {
    private static final Logger LOG = Logger.getLogger(DocumentTypeTest.class);

    protected void loadTestData() throws Exception {
        ConfigContext.getCurrentContextConfig().overrideProperty("test.doctype.workgroup", "TestWorkgroup");
        loadXmlFile("DoctypeConfig.xml");
    }

    /**
     * Verify that enroute documents are not affected if you edit their document type.
     * @throws Exception
     */
    @Test public void testChangingDocumentTypeOnEnrouteDocument() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdDTO("user1"), "DocumentType");
        document.setTitle("");
        document.routeDocument("");

        document = new WorkflowDocument(new NetworkIdDTO("rkirkend"), document.getRouteHeaderId());
        assertTrue("rkirkend should have an approve request", document.isApprovalRequested());

        WorkflowInfo info = new WorkflowInfo();
        Integer version1 = info.getDocType(document.getDocumentType()).getDocTypeVersion();

        //update the document type
        loadXmlFile("DoctypeSecondVersion.xml");

        //verify that we have a new documenttypeid and its a newer version
        Integer version2 = info.getDocType(document.getDocumentType()).getDocTypeVersion();

        assertTrue("Version2 should be larger than verison1", version2.intValue() > version1.intValue());

        //the new version would take the document final
        document = new WorkflowDocument(new NetworkIdDTO("rkirkend"), document.getRouteHeaderId());
        assertTrue("rkirkend should have an approve request", document.isApprovalRequested());

        document.approve("");

        document = new WorkflowDocument(new NetworkIdDTO("user2"), document.getRouteHeaderId());
        Integer versionDocument = info.getDocType(document.getRouteHeader().getDocTypeId()).getDocTypeVersion();

        assertTrue("user2 should have an approve request", document.isApprovalRequested());
        //make sure our document still represents the accurate version
        assertEquals("Document has the wrong document type version", version1, versionDocument);
    }

    /**
     * this test will verify that finalapprover node policies work
     *
     * @throws Exception
     */
    @Test public void testFinalApproverRouting() throws Exception {

        WorkflowDocument document = new WorkflowDocument(new NetworkIdDTO("user1"), "FinalApproverDocumentType");
        document.setTitle("");
        document.routeDocument("");
        document = new WorkflowDocument(new NetworkIdDTO("rkirkend"), document.getRouteHeaderId());
        try {
            document.approve("");
            fail("document should have thrown routing exception");
        } catch (Exception e) {
            //deal with single transaction issue in test.
        	TestUtilities.getExceptionThreader().join();
        	document = new WorkflowDocument(new NetworkIdDTO("rkirkend"), document.getRouteHeaderId());
            assertTrue("Document should be in exception routing", document.stateIsException());
        }
    }

    /**
     * Tests that route nodes mark as mandatory send out approve requests
     * @throws Exception
     */
    @Test public void testMandatoryRoute() throws Exception {
        WorkflowDocument document = new WorkflowDocument(new NetworkIdDTO("user1"), "MandatoryRouteDocumentType");
        document.setTitle("");
        try {
            document.routeDocument("");
        } catch (Exception e) {
            //deal with single transaction issue in test.
        	TestUtilities.getExceptionThreader().join();
        	document = new WorkflowDocument(new NetworkIdDTO("user1"), document.getRouteHeaderId());
            assertTrue("Document should be in exception routing", document.stateIsException());
        }
    }

    /**
     * Makes sure the DocumentTypeXmlParser is working.  Compare the parsed 'DocumentType' doctype to it's expected values.
     * This test does not include multiple processes.
     *
     * @throws Exception
     */
    @Test public void testDocumentTypeXmlParser() throws Exception {
        ConfigContext.getCurrentContextConfig().overrideProperty("test.base.url", "http://someurl/path");
        DocumentType parsedDocument = KEWServiceLocator.getDocumentTypeService().findByName("DocumentType");
        assertEquals("Wrong name", "DocumentType", parsedDocument.getName());
        assertEquals("Wrong description", "TestDocumentType", parsedDocument.getDescription());
        assertEquals("Wrong label", "TestDocumentType", parsedDocument.getLabel());
        assertEquals("Wrong postprocessor", "org.kuali.rice.kew.postprocessor.DefaultPostProcessor", parsedDocument.getPostProcessorName());
        assertEquals("Wrong su workgroup", "TestWorkgroup", parsedDocument.getSuperUserWorkgroup().getDisplayName());
        // roundabout way of testing to see if the exception workgroup has been processed properly
        DocumentTypeXmlExporter exporter = new DocumentTypeXmlExporter();
        ExportDataSet dataSet = new ExportDataSet(ExportFormat.XML);
        dataSet.getDocumentTypes().add(parsedDocument);
        assertTrue(XmlHelper.jotNode(exporter.export(dataSet)).matches("(?s).*<defaultExceptionWorkgroupName>TestWorkgroup</defaultExceptionWorkgroupName>.*"));
        //assertNotNull(parsedDocument.getDefaultExceptionWorkgroup());
        //assertEquals("Wrong default exception workgroup", "TestWorkgroup", parsedDocument.getDefaultExceptionWorkgroup().getDisplayName());
        assertEquals("Wrong doc handler url", "http://someurl/path/_blank", parsedDocument.getDocHandlerUrl());
        assertEquals("Wrong blanketApprover workgroup", "TestWorkgroup", parsedDocument.getBlanketApproveWorkgroup().getDisplayName());
        assertEquals("Wrong blanketApprove policy", null, parsedDocument.getBlanketApprovePolicy());
        assertEquals("Wrong PRE_APPROVE policy value", Boolean.FALSE, parsedDocument.getPreApprovePolicy().getPolicyValue());
        assertEquals("Wrong DEFAULT_APPROVE policy value", Boolean.FALSE, parsedDocument.getDefaultApprovePolicy().getPolicyValue());
        assertEquals("Wrong LOOK_FUTURE", Boolean.TRUE, parsedDocument.getLookIntoFuturePolicy().getPolicyValue());

        List processes = parsedDocument.getProcesses();
        assertEquals("Should only be 1 process", 1, processes.size());

        //this is going against the intended structure and is very brittle

        Process process = (Process)processes.get(0);
        List flattenedNodeList = KEWServiceLocator.getRouteNodeService().getFlattenedNodes(process);
        assertEquals("Should be 6 total route nodes", 6, flattenedNodeList.size());

        RouteNode adHocNode = process.getInitialRouteNode();
        assertEquals("Wrong node name should be 'AdHoc'", "AdHoc",adHocNode.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.START.isAssignableFrom(Class.forName(adHocNode.getNodeType())));
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", adHocNode.getExceptionWorkgroup().getDisplayName());

        RouteNode split = (RouteNode)adHocNode.getNextNodes().get(0);
        assertEquals("Wrong node name", "Split", split.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.SPLIT.isAssignableFrom(Class.forName(split.getNodeType())));
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", split.getExceptionWorkgroup().getDisplayName());

        RouteNode ruleTemplate1 = (RouteNode)split.getNextNodes().get(0);
        assertEquals("Wrong node name", "RuleTemplate1", ruleTemplate1.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.REQUESTS.isAssignableFrom(Class.forName(ruleTemplate1.getNodeType())));
        assertEquals("Wrong branch name", "B1", ruleTemplate1.getBranch().getName());
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", ruleTemplate1.getExceptionWorkgroup().getDisplayName());

        RouteNode ruleTemplate2 = (RouteNode)split.getNextNodes().get(1);
        assertEquals("Wrong node name", "RuleTemplate2", ruleTemplate2.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.REQUESTS.isAssignableFrom(Class.forName(ruleTemplate2.getNodeType())));
        assertEquals("Wrong branch name", "B2", ruleTemplate2.getBranch().getName());
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", ruleTemplate2.getExceptionWorkgroup().getDisplayName());

        RouteNode join = (RouteNode)ruleTemplate2.getNextNodes().get(0);
        assertEquals("Wrong node name", "Join", join.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.JOIN.isAssignableFrom(Class.forName(join.getNodeType())));
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", join.getExceptionWorkgroup().getDisplayName());

        RouteNode ruleTemplate3 = (RouteNode)join.getNextNodes().get(0);
        assertEquals("Wrong node name", "RuleTemplate3", ruleTemplate3.getRouteNodeName());
        assertTrue("Wrong node type", NodeType.REQUESTS.isAssignableFrom(Class.forName(ruleTemplate3.getNodeType())));
        assertEquals("Default Exception workgroup not propagated", "TestWorkgroup", ruleTemplate3.getExceptionWorkgroup().getDisplayName());
    }

    /**
     * verify that saved documents are impacting the documents in the rest of they're hierarhy.
     * This means cache notification by document type will work.
     *
     * @throws Exception
     */
    @Test public void testDocumentTypeServiceCacheInteractions() throws Exception {
    	DocumentType child = KEWServiceLocator.getDocumentTypeService().findByName("SaveTestDocumentTypeChild1");
    	DocumentType childDeux = KEWServiceLocator.getDocumentTypeService().findById(child.getDocumentTypeId());
    	System.out.println(child == childDeux);
    	assertEquals("Names should be the same.", child.getName(), childDeux.getName());
    	assertEquals("Versions should be the same.", child.getVersion(), childDeux.getVersion());

    	// verify that the entry exists in OSCache
    	String childIdKey = DocumentTypeServiceImpl.DOCUMENT_TYPE_ID_CACHE_PREFIX+child.getDocumentTypeId().toString();
    	String childNameKey = DocumentTypeServiceImpl.DOCUMENT_TYPE_NAME_CACHE_PREFIX+child.getName();
    	assertNotNull("No entry was found in the id cache.", KEWServiceLocator.getCacheAdministrator().getFromCache(childIdKey));
    	assertNotNull("No entry was found in the name cache.", KEWServiceLocator.getCacheAdministrator().getFromCache(childNameKey));

    	// the parent document type should not be in the cache yet
    	String parentIdKey = DocumentTypeServiceImpl.DOCUMENT_TYPE_ID_CACHE_PREFIX+child.getDocTypeParentId();
    	assertNull("Entry for parent should not have found in the id cache.", KEWServiceLocator.getCacheAdministrator().getFromCache(parentIdKey));

    	DocumentType parent = child.getParentDocType();
    	// the act of fetching the parent from the child should result in the parent being cached
    	assertNotNull("Entry for parent should have been found in the id cache.", KEWServiceLocator.getCacheAdministrator().getFromCache(parentIdKey));

    	// now flush the cache, there should be no entries
    	KEWServiceLocator.getCacheAdministrator().flushAll();
    	assertNull(KEWServiceLocator.getCacheAdministrator().getFromCache(childIdKey));
    	assertNull(KEWServiceLocator.getCacheAdministrator().getFromCache(childNameKey));

    	DocumentType child2 = null;
    	for (Iterator iter = parent.getChildrenDocTypes().iterator(); iter.hasNext();) {
			DocumentType childTmp = (DocumentType) iter.next();
			if (childTmp.getName().equals("SaveTestDocumentTypeChild2")) {
				child2 = childTmp;
			}
		}

    	//update the first child and verify that everything we fetch is a different object - ie it's been refetched
    	//from the db
    	DocumentType childEdit = new DocumentType();
    	childEdit.setName(child.getName());
    	childEdit.setActiveInd(Boolean.TRUE);
    	Workgroup workflowAdmin = KEWServiceLocator.getWorkgroupService().getWorkgroup(new GroupNameId("WorkflowAdmin"));
    	childEdit.setBlanketApproveWorkgroup(workflowAdmin);
    	childEdit.setDefaultExceptionWorkgroup(workflowAdmin);
    	childEdit.setDescription("desc");
    	childEdit.setDocHandlerUrl("url");
    	childEdit.setLabel("lable");
    	childEdit.setPolicies(new ArrayList());
    	childEdit.setRoutingVersion("1");
    	childEdit.setDocTypeParentId(child.getDocTypeParentId());
    	childEdit.setPostProcessorName("somename");
    	childEdit.setSuperUserWorkgroupNoInheritence(workflowAdmin);

    	KEWServiceLocator.getDocumentTypeService().versionAndSave(childEdit);

    	DocumentType child1Ver2 = KEWServiceLocator.getDocumentTypeService().findByName("SaveTestDocumentTypeChild1");
    	assertTrue("the fetched document should a document type version number 1 larger than the previous version", child.getVersion().intValue() + 1 == child1Ver2.getVersion().intValue());
    	DocumentType previousVersion = child1Ver2.getPreviousVersion();
    	//refetch to make sure the previous version diddn't get put in our name catch
    	DocumentType child1Ver2_2 = KEWServiceLocator.getDocumentTypeService().findByName("SaveTestDocumentTypeChild1");
    	assertEquals("DocumentType should have been fetched from cache", child1Ver2, child1Ver2_2);
    	assertFalse("Previous Version should not be an object already fetched from cache", previousVersion.equals(child1Ver2));
    	assertEquals("Fetched wrong previous document type ", previousVersion.getDocumentTypeId(), child.getDocumentTypeId());


    	DocumentType parentV2 = child1Ver2.getParentDocType();
    	assertFalse("These should be different objects", parentV2.equals(parent));

    	DocumentType child2V2 = null;
    	for (Iterator iter = parentV2.getChildrenDocTypes().iterator(); iter.hasNext();) {
			DocumentType childTmp = (DocumentType) iter.next();
			if (! childTmp.equals(child1Ver2)) {
				child2V2 = childTmp;
				assertFalse("These should be different objects", child2V2.equals(child2));
			}
		}
    }

    @Test public void testAttributeSaveClearsCache() throws Exception {
    	super.loadXmlFile("DocTypeWithSearchableAttributes.xml");
    	//fetch our document types out of the document service and therefore the cache
    	DocumentType shouldClearedFromCache = KEWServiceLocator.getDocumentTypeService().findByName("DocumentType");
    	DocumentType clearedFromCacheDuex = KEWServiceLocator.getDocumentTypeService().findByName("DocumentTypeDuex");

    	//upload the new xml
    	super.loadXmlFile("DocTypeRelatedAttributes.xml");
    	DocumentType documentType = KEWServiceLocator.getDocumentTypeService().findByName("DocumentType");
    	DocumentType documentTypeDuex = KEWServiceLocator.getDocumentTypeService().findByName("DocumentTypeDuex");
    	assertFalse("This documenttype should have been cleared from the cache", shouldClearedFromCache.equals(documentType));
    	assertFalse("This documenttype should have been cleared from the cache", clearedFromCacheDuex.equals(documentTypeDuex));
    }

    //verifies the documenttype hierarchy is intact after multiple uploads
    @Test public void testHierarchyUpload() throws Exception {
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration.xml");
    	DocumentType parent = KEWServiceLocator.getDocumentTypeService().findByName("UGSDocumentType");
    	boolean foundRemonstrance = false;
    	boolean foundNewCourse = false;
    	boolean foundDelete = false;
    	for (Iterator iter = parent.getChildrenDocTypes().iterator(); iter.hasNext();) {
			DocumentType childDocType = (DocumentType) iter.next();
			assertTrue("child documenttype should be current", childDocType.getCurrentInd().booleanValue());
			if(childDocType.getName().equals("CourseRemonstranceProcess")) {
				foundRemonstrance = true;
			} else if (childDocType.getName().equals("NewCourseRequest")) {
				foundNewCourse = true;
			} else if (childDocType.getName().equals("DeleteCourseRequest")) {
				foundDelete = true;
			}
		}

    	assertTrue("Didn't find CourseRemonstraneProcess", foundRemonstrance);
    	assertTrue("Didn't find NewCourseRequest", foundNewCourse);
    	assertTrue("Didn't find DeleteCourseRequest", foundDelete);

    	//reload and verify that the structure looks the same - the below is missing one of the children document types
    	//to verify that a partial upload of the hierarchy doesn't kill the entire hierarchy
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration2.xml");

    	parent = KEWServiceLocator.getDocumentTypeService().findByName("UGSDocumentType");
    	foundRemonstrance = false;
    	foundNewCourse = false;
    	foundDelete = false;
    	int i = 0;
    	for (Iterator iter = parent.getChildrenDocTypes().iterator(); iter.hasNext(); i++) {
			DocumentType childDocType = (DocumentType) iter.next();
			assertTrue("child documenttype should be current", childDocType.getCurrentInd().booleanValue());
			if(childDocType.getName().equals("CourseRemonstranceProcess")) {
				foundRemonstrance = true;
			} else if (childDocType.getName().equals("NewCourseRequest")) {
				foundNewCourse = true;
			} else if (childDocType.getName().equals("DeleteCourseRequest")) {
				foundDelete = true;
			}
		}
    	assertTrue("Didn't find CourseRemonstranceProcess", foundRemonstrance);
    	assertTrue("Didn't find NewCourseRequest", foundNewCourse);
    	assertTrue("Didn't find DeleteCourseRequest", foundDelete);
    }

    //verifies documenttype hierarchy is intact after uploading a series of documenttypes and then
    //uploading a parent onto those document types
    @Test public void testHierarchyUpload2() throws Exception {
    	super.loadXmlFile("DocTypesWithoutParent.xml");
    	//Verify that the document types are there
    	DocumentType courseRemonstrance1 = KEWServiceLocator.getDocumentTypeService().findByName("CourseRemonstranceProcess");
    	DocumentType newCourseRequest1 = KEWServiceLocator.getDocumentTypeService().findByName("NewCourseRequest");
    	DocumentType deleteCourse1 = KEWServiceLocator.getDocumentTypeService().findByName("DeleteCourseRequest");

    	//upload the new config with the parent and verify we are getting new document types with new versions
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration.xml");
    	DocumentType courseRemonstrance2 = null;
    	DocumentType newCourseRequest2 = null;
    	DocumentType deleteCourse2 = null;

    	DocumentType ugsDocumentType = KEWServiceLocator.getDocumentTypeService().findByName("UGSDocumentType");
    	for (Iterator iter = ugsDocumentType.getChildrenDocTypes().iterator(); iter.hasNext();) {
			DocumentType childDocType = (DocumentType) iter.next();
			if(childDocType.getName().equals("CourseRemonstranceProcess")) {
				courseRemonstrance2 = childDocType;
			} else if (childDocType.getName().equals("NewCourseRequest")) {
				newCourseRequest2 = childDocType;
			} else if (childDocType.getName().equals("DeleteCourseRequest")) {
				deleteCourse2 = childDocType;
			}
		}

    	assertNotNull(courseRemonstrance2);
    	assertNotNull(newCourseRequest2);
    	assertNotNull(deleteCourse2);

    	assertTrue("Version didn't get incremented", courseRemonstrance1.getVersion().intValue() < courseRemonstrance2.getVersion().intValue());
    	assertTrue("Version didn't increment", newCourseRequest1.getVersion().intValue() < newCourseRequest2.getVersion().intValue());
    	assertTrue("Version didn't increment", deleteCourse1.getVersion().intValue() < deleteCourse2.getVersion().intValue());
    }

    /**
     * Tests that the document type ingestion will not create a brand new
     * document when only label or description field changes.  Relates to
     * JIRA's EN-318 and KULOWF-147.
     *
     * @throws Exception
     */
    @Test public void testDocumentTypeIngestion() throws Exception {
        // first ingestion
        super.loadXmlFile("DocTypeIngestTestConfig1.xml");  // original document
        super.loadXmlFile("DocTypeIngestTestConfig2.xml");  // document with changed label and description fields

        DocumentType secondIngestDoc = KEWServiceLocator.getDocumentTypeService().findByName("IngestTestDocumentType");
        assertNotNull("Second ingested document has empty Previous Version ID after first ingest", secondIngestDoc.getPreviousVersionId());
        DocumentType firstIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(secondIngestDoc.getPreviousVersionId());

        // the second ingested document should now be set to Current with the first ingested document should no longer be set to Current
        assertEquals("First ingested document is still set to Current after first ingest", Boolean.FALSE, firstIngestDoc.getCurrentInd());
        assertEquals("Second ingested document is not set to Current after first ingest", Boolean.TRUE, secondIngestDoc.getCurrentInd());

        // second ingestion
        super.loadXmlFile("DocTypeIngestTestConfig3.xml");  // document setting active to false

        firstIngestDoc = null;
        secondIngestDoc = null;
        DocumentType thirdIngestDoc = KEWServiceLocator.getDocumentTypeService().findByName("IngestTestDocumentType");
        assertNotNull("Third ingested document has empty Previous Version ID after second ingest", thirdIngestDoc.getPreviousVersionId());
        secondIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(thirdIngestDoc.getPreviousVersionId());
        assertNotNull("Second ingested document has empty Previous Version ID after second ingest", secondIngestDoc.getPreviousVersionId());
        firstIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(secondIngestDoc.getPreviousVersionId());

        // the third ingested document should now be set to Current and Inactive... all others should not be set to Current
        assertEquals("First ingested document is set to Current after second ingest", Boolean.FALSE, firstIngestDoc.getCurrentInd());
        assertEquals("Second ingested document is set to Current after second ingest", Boolean.FALSE, secondIngestDoc.getCurrentInd());
        assertEquals("Third ingested document is not set to Inactive after second ingest", Boolean.FALSE, thirdIngestDoc.getActiveInd());
        assertEquals("Third ingested document is not set to Current after second ingest", Boolean.TRUE, thirdIngestDoc.getCurrentInd());

        // third ingestion
        super.loadXmlFile("DocTypeIngestTestConfig4.xml");  // document setting active to true

        firstIngestDoc = null;
        secondIngestDoc = null;
        thirdIngestDoc = null;
        DocumentType fourthIngestDoc = KEWServiceLocator.getDocumentTypeService().findByName("IngestTestDocumentType");
        assertNotNull("Fourth ingested document has empty Previous Version ID after third ingest", fourthIngestDoc.getPreviousVersionId());
        thirdIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(fourthIngestDoc.getPreviousVersionId());
        assertNotNull("Third ingested document has empty Previous Version ID after third ingest", thirdIngestDoc.getPreviousVersionId());
        secondIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(thirdIngestDoc.getPreviousVersionId());
        assertNotNull("Second ingested document has empty Previous Version ID after third ingest", secondIngestDoc.getPreviousVersionId());
        firstIngestDoc = KEWServiceLocator.getDocumentTypeService().findById(secondIngestDoc.getPreviousVersionId());

        // the fourth ingested document should now be set to Current and Active... all others should not be set to Current
        assertEquals("First ingested document is set to Current after third ingest", Boolean.FALSE, firstIngestDoc.getCurrentInd());
        assertEquals("Second ingested document is set to Current after third ingest", Boolean.FALSE, secondIngestDoc.getCurrentInd());
        assertEquals("Third ingested document is set to Current after third ingest", Boolean.FALSE, thirdIngestDoc.getCurrentInd());
        assertEquals("Fourth ingested document is not set to Active after third ingest", Boolean.TRUE, fourthIngestDoc.getActiveInd());
        assertEquals("Fourth ingested document is not set to Current after third ingest", Boolean.TRUE, fourthIngestDoc.getCurrentInd());
    }

    @Test public void testDocumentTypeParentChildLinking() throws Exception {
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration.xml");
    	verifyDocumentTypeLinking();

    	// scenario 1, update the parent document type and verify that all linking is correct
    	super.loadXmlFile("ParentWithChildrenDocTypeConfigurationUpdate1.xml");
    	verifyDocumentTypeLinking();

    	// scenario 2, update a child document type and verify that all linking is correct
    	super.loadXmlFile("ParentWithChildrenDocTypeConfigurationUpdate2.xml");
    	verifyDocumentTypeLinking();

    	// let's reimport from the beginning as well
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration.xml");
    	verifyDocumentTypeLinking();

    	// scenario 3, try an xml file with child doctype listed first
    	super.loadXmlFile("ParentWithChildrenDocTypeConfigurationUpdate3.xml");
    	verifyDocumentTypeLinking();

    	// try loading each of these in parallel threads to verify caching can
    	// handle concurrency situations
    	int num = 7;
    	Thread[] threads = new Thread[num];
    	Callback[] callbacks = new Callback[num];
    	for (int i = 0; i < num; i++) {
    		callbacks[i] = new Callback();
}    	threads[0] = new Thread(new LoadXml("ParentWithChildrenDocTypeConfiguration.xml", callbacks[0]));
    	threads[1] = new Thread(new LoadXml("DocTypeIngestTestConfig1.xml", callbacks[1]));
    	threads[2] = new Thread(new LoadXml("DocumentTypeAttributeFetchTest.xml", callbacks[2]));
    	threads[3] = new Thread(new LoadXml("ChildDocType1.xml", callbacks[3]));
    	threads[4] = new Thread(new LoadXml("ChildDocType2.xml", callbacks[4]));
    	threads[5] = new Thread(new LoadXml("ChildDocType3.xml", callbacks[5]));
    	threads[6] = new Thread(new LoadXml("ChildDocType4.xml", callbacks[6]));
    	for (Thread thread : threads) {
    		thread.start();
    	}
    	for (Thread thread : threads) {
    		thread.join(2*60*1000);
    	}
    	// what should have happened here was an optimistic lock being thrown from the
    	// document type XML import.  Currently that code is catching and just logging
    	// those errors (not rethrowing) so there's no way for us to check that the
    	// optimistic lock was thrown however the verifyDocumentTypeLinking should pass
    	// because the update was never made
    	for (Callback callback : callbacks) {
    		callback.assertXmlLoaded();
    	}
    	verifyDocumentTypeLinking();

    	// reload again for good measure
    	super.loadXmlFile("ParentWithChildrenDocTypeConfiguration.xml");
    	verifyDocumentTypeLinking();

    }

    protected void verifyDocumentTypeLinking() throws Exception {
    	DocumentTypeService service = KEWServiceLocator.getDocumentTypeService();
    	List rootDocs = service.findAllCurrentRootDocuments();
    	int numRoots = rootDocs.size();
    	List documentTypes = service.findAllCurrent();
    	List<DocumentType> leafs = new ArrayList<DocumentType>();
    	for (Iterator iterator = documentTypes.iterator(); iterator.hasNext();) {
			DocumentType documentType = (DocumentType) iterator.next();
			List children = service.getChildDocumentTypes(documentType);
			if (children.isEmpty()) {
				leafs.add(documentType);
			}
		}
    	Set<Long> rootDocIds = new HashSet<Long>();
    	// verify the hierarchy
    	for (DocumentType leaf : leafs) {
			verifyHierarchy(leaf, rootDocIds);
		}
    	for (DocumentType leaf : leafs) {
			verifyHierarchy(leaf, rootDocIds);
		}
    	// we should have the same number of roots as we did from the original roots query
    	assertEquals("Should have the same number of roots", numRoots, rootDocIds.size());
    }

    protected void verifyHierarchy(DocumentType docType, Set<Long> rootDocIds) {
    	assertTrue("DocumentType " + docType.getName() + " should be current.", docType.getCurrentInd().booleanValue());
    	if (docType.getParentDocType() == null) {
    		rootDocIds.add(docType.getDocumentTypeId());
    	} else {
    		verifyHierarchy(docType.getParentDocType(), rootDocIds);
    	}
    }

    protected void flushCache() {
    	// invalidate locally because if we're doing an upload of a document hierarchy we can't wait the 5 secs for this nodes cache
		//to be accurate-the data going in the db depends on it being accurate now.  This means the cache will be cleared multiple times
    	//over during an upload and the subsequent notification to this node.
    	LOG.info("clearing DocumentType cache because of local update");
    	KEWServiceLocator.getCacheAdministrator().flushGroup(DocumentTypeServiceImpl.DOCUMENT_TYPE_ID_CACHE_GROUP);
    	KEWServiceLocator.getCacheAdministrator().flushGroup(DocumentTypeServiceImpl.DOCUMENT_TYPE_NAME_CACHE_GROUP);
    	KEWServiceLocator.getCacheAdministrator().flushEntry(DocumentTypeServiceImpl.CURRENT_ROOTS_IN_CACHE_KEY);
    }

    private class LoadXml implements Runnable {

    	private String xmlFile;
    	private Callback callback;

    	public LoadXml(String xmlFile, Callback callback) {
    		this.xmlFile = xmlFile;
    		this.callback = callback;
    	}

		public void run() {
			try {
				loadXmlFile(xmlFile);
			} catch (Throwable t) {
				callback.record(xmlFile, t);
			}
		}

    }

    private class Callback {
    	private String xmlFile;
    	private Throwable t;
    	public void record(String xmlFile, Throwable t) {
    		this.xmlFile = xmlFile;
    		this.t = t;
    	}
    	public void assertXmlLoaded() {
    		if (t != null) {
    			t.printStackTrace();
    			fail("Failed to load xml file " + xmlFile);
    		}
    	}
    }
}