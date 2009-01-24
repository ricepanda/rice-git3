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
package org.kuali.rice.kew.useroptions.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kuali.rice.core.database.platform.Platform;
import org.kuali.rice.core.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kew.useroptions.UserOptions;
import org.kuali.rice.kew.useroptions.dao.UserOptionsDAO;


public class UserOptionsDaoJpaImpl implements UserOptionsDAO {

    @PersistenceContext
    private EntityManager entityManager;

	public Long getNewOptionIdForActionList() {
        return getPlatform().getNextValSQL("SEQ_ACTION_LIST_OPTN", entityManager);
    }

	protected Platform getPlatform() {
    	return (Platform) GlobalResourceLoader.getService(RiceConstants.DB_PLATFORM);
    }

    public List findByUserQualified(String principalId, String likeString) {
        return new ArrayList(entityManager.createNamedQuery("UserOptions.FindByUserQualified").setParameter("workflowId", principalId).setParameter("optionId", likeString).getResultList());
    }

    public void deleteByUserQualified(String principalId, String likeString) {
        List<UserOptions> userOptions = (List<UserOptions>) entityManager.createNamedQuery("UserOptions.FindByUserQualified").setParameter("workflowId", principalId).setParameter("optionId", likeString).getResultList();
        for (UserOptions uo : userOptions) {
            entityManager.remove(uo);
        }
    }

    public Collection findByWorkflowUser(String principalId) {
        return entityManager.createNamedQuery("UserOptions.FindByWorkflowId").setParameter("workflowId", principalId).getResultList();
    }

    public void save(UserOptions userOptions) {
        if (userOptions.getOptionId() == null) {
            entityManager.persist(userOptions);
        } else {
            entityManager.merge(userOptions);
        }
    }

    public void deleteUserOptions(UserOptions userOptions) {
        UserOptions reattatched = entityManager.merge(userOptions);
        entityManager.remove(reattatched);
    }

    public UserOptions findByOptionId(String optionId, String principalId) {
        return (UserOptions) entityManager.createNamedQuery("UserOptions.FindByOptionId").setParameter("optionId", optionId).setParameter("workflowId", principalId).getSingleResult();
    }

    public Collection findByOptionValue(String optionId, String optionValue) {
        return entityManager.createNamedQuery("UserOptions.FindByOptionValue").setParameter("optionId", optionId).setParameter("optionValue", optionValue).getResultList();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}