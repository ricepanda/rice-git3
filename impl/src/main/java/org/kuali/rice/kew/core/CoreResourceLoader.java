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
package org.kuali.rice.kew.core;

import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.reflect.ObjectDefinition;
import org.kuali.rice.core.resourceloader.BaseWrappingResourceLoader;
import org.kuali.rice.core.resourceloader.ServiceLocator;
import org.kuali.rice.kew.KEWServiceLocator;
import org.kuali.rice.kew.plugin.Plugin;
import org.kuali.rice.kew.plugin.PluginRegistry;
import org.kuali.rice.kew.util.KEWConstants;


/**
 * A resource loader which is responsible for loading resources from the Workflow ConfigContext.  It is responsible for
 * searching for service overrides in the Institutional Plugin before falling back to default services
 * from the core.
 *
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class CoreResourceLoader extends BaseWrappingResourceLoader {

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(CoreResourceLoader.class);

	public static final QName NAME = new QName(ConfigContext.getCurrentContextConfig().getMessageEntity(), "KEW_SPRING+PLUGIN_REGISTRY_CONTAINER_RESOURCE_LOADER");

	private final PluginRegistry registry;

	public CoreResourceLoader(ServiceLocator serviceLocator, PluginRegistry registry) {
		super(CoreResourceLoader.NAME, serviceLocator);
		this.registry = registry;
	}

	/**
	 * Overrides the standard getService method to first look in the institutional plugin for the
	 * service with the given name and then fall back to the default implementation in the
	 * core (if it exists).
	 */
	public Object getService(QName serviceName) {
	    	if (isRemoteService(serviceName)) {
	    	    return null;
	    	}
		if (getRegistry() != null) {
			Plugin institutionalPlugin = getRegistry().getInstitutionalPlugin();
			if (institutionalPlugin != null) {
				Object service = institutionalPlugin.getService(serviceName);
				if (service != null) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Retrieved service override for '" + serviceName + "' from the institutional plugin.");
					}
					return postProcessService(serviceName, service);
				}
			}
		}
		Object service = super.getService(serviceName);
		if (service == null && getRegistry() != null) {
		    service = getRegistry().getService(serviceName);
		}
		return service;
	}



	@Override
	public Object getObject(ObjectDefinition objectDefinition) {
	    Object object = super.getObject(objectDefinition);
	    if (object == null && getRegistry() != null) {
		object = getRegistry().getObject(objectDefinition);
	    }
	    return object;
	}

	@Override
	protected boolean shouldWrapService(QName serviceName, Object service) {
		// transaction template is not wrappable because it does not implement an interface
		if (serviceName.getLocalPart().equals("transactionTemplate")) {
			return false;
		}
		return super.shouldWrapService(serviceName, service);
	}
	
	/**
	 * Returns true if the given service name is one which should be loaded from the service bus.  This is used
	 * primarily for embedded clients that want to reference the workgroup and user services on a standalone
	 * server.
	 */
	protected boolean isRemoteService(QName serviceName) {
	    return (useRemoteIdentityServices() &&
	    	(serviceName.getLocalPart().equals(KEWServiceLocator.USER_SERVICE) ||
		    serviceName.getLocalPart().equals(KEWServiceLocator.WORKGROUP_SRV))) ||
		    (useRemoteEmailServices() &&
			    serviceName.getLocalPart().equals(KEWServiceLocator.IMMEDIATE_EMAIL_REMINDER_SERVICE));
	}
	
	protected boolean useRemoteIdentityServices() {
	    String useRemoteIdentityServicesValue = ConfigContext.getCurrentContextConfig().getProperty(KEWConstants.USE_REMOTE_IDENTITY_SERVICES);
	    if (!StringUtils.isBlank(useRemoteIdentityServicesValue)) {
	        return new Boolean(useRemoteIdentityServicesValue.trim());
	    }
	    return false;
	}
	
	protected boolean useRemoteEmailServices() {
	    String useRemoteEmailServicesValue = ConfigContext.getCurrentContextConfig().getProperty(KEWConstants.USE_REMOTE_EMAIL_SERVICES);
	    if (!StringUtils.isBlank(useRemoteEmailServicesValue)) {
	        return new Boolean(useRemoteEmailServicesValue.trim());
	    }
	    return false;
	}

	public PluginRegistry getRegistry() {
		return registry;
	}
}