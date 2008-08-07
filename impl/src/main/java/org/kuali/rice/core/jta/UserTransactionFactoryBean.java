/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.core.jta;

import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.config.Config;
import org.kuali.rice.core.config.ConfigContext;
import org.kuali.rice.core.config.ConfigurationException;
import org.kuali.rice.core.util.RiceConstants;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.jndi.JndiTemplate;


/**
 * Factory bean that supplies a UserTransaction object from the the current context
 * (i.e. plugin, embedding webapp) Config object map if defined therein (under the Config.USER_TRANSACTION key),
 * from JNDI if {@link Config#USER_TRANSACTION_JNDI} is defined,
 * or from a default declaratively assigned in containing bean factory.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class UserTransactionFactoryBean implements FactoryBean {

	private UserTransaction defaultUserTransaction;
	private JndiTemplate jndiTemplate;
	
	public Object getObject() throws Exception {
		
		if (ConfigContext.getCurrentContextConfig().getObject(RiceConstants.SPRING_TRANSACTION_MANAGER) != null) {
			return null;
		}
		
		UserTransaction userTransaction = (UserTransaction)ConfigContext.getCurrentContextConfig().getObject(Config.USER_TRANSACTION_OBJ);
		if (userTransaction == null) {
			String userTransactionJndiName = ConfigContext.getCurrentContextConfig().getProperty(Config.USER_TRANSACTION_JNDI);
			if (!StringUtils.isEmpty(userTransactionJndiName)) {
				if (this.jndiTemplate == null) {
				    this.jndiTemplate = new JndiTemplate();
				}
				try {
					userTransaction = (UserTransaction)this.jndiTemplate.lookup(userTransactionJndiName, UserTransaction.class);
				} catch (NamingException e) {
					throw new ConfigurationException("Could not locate the UserTransaction at the given JNDI location: '" + userTransactionJndiName + "'", e);
				}
			}
			
		}
		if (userTransaction != null) {
			return userTransaction;
		}
		return this.defaultUserTransaction;
	}

	public Class getObjectType() {
		return UserTransaction.class;
	}

	public boolean isSingleton() {
		return true;
	}
	
	public void setDefaultUserTransaction(UserTransaction userTransaction) {
	    this.defaultUserTransaction = userTransaction;
	}

	public JndiTemplate getJndiTemplate() {
		return this.jndiTemplate;
	}

	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		this.jndiTemplate = jndiTemplate;
	}
	
	

}
