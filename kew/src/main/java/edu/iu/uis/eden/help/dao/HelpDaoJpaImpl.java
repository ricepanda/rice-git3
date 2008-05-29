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
package edu.iu.uis.eden.help.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kuali.rice.jpa.criteria.Criteria;
import org.kuali.rice.jpa.criteria.QueryByCriteria;
import org.kuali.rice.util.OrmUtils;

import edu.iu.uis.eden.help.HelpEntry;

public class HelpDaoJpaImpl implements HelpDAO {
	
    @PersistenceContext
    private EntityManager entityManager;
    
    public void save(HelpEntry helpEntry) {
        if (helpEntry.getHelpId() == null) {
        	OrmUtils.populateAutoIncValue(helpEntry);
            entityManager.persist(helpEntry);
        } else {
            entityManager.merge(helpEntry);
        }
    }
    
    public void deleteEntry(HelpEntry helpEntry) {
        HelpEntry reattatched = entityManager.merge(helpEntry);
        entityManager.remove(reattatched);
    }
    
    public HelpEntry findById(Long helpId) {
        return (HelpEntry) entityManager.createNamedQuery("HelpEntry.FindById").setParameter("helpId", helpId).getSingleResult();
    }
    
    public List search(HelpEntry helpEntry) {
        Criteria crit = new Criteria("HelpEntry", "he");
       
        if (helpEntry.getHelpId() != null && helpEntry.getHelpId().longValue() != 0) {
            crit.eq("helpId", helpEntry.getHelpId());
        }

        if (!this.isStringEmpty(helpEntry.getHelpName())) {
            crit.rawJpql("UPPER(he.helpName) like '%" + helpEntry.getHelpName().toUpperCase() + "%'");
        }

        if (!this.isStringEmpty(helpEntry.getHelpText())) {
            crit.rawJpql("UPPER(he.helpText) like '%" + helpEntry.getHelpText().toUpperCase() + "%'");
        }
        
        if (!this.isStringEmpty(helpEntry.getHelpKey())) {
            crit.rawJpql("UPPER(he.helpKey) like '%" + helpEntry.getHelpKey().toUpperCase() + "%'");
        }
        
        return new QueryByCriteria(entityManager, crit).toQuery().getResultList();
    }
    
    private boolean isStringEmpty(String string) {
        if ((string == null) || string.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public HelpEntry findByKey(String helpKey){
        return (HelpEntry) entityManager.createNamedQuery("HelpEntry.FindByKey").setParameter("helpKey", helpKey).getSingleResult();
    }

}
