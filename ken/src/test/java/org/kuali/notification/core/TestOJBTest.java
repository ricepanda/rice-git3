/*
 * Copyright (c) 2004, 2005 The National Association of College and University Business Officers,
 * Cornell University, Trustees of Indiana University, Michigan State University Board of Trustees,
 * Trustees of San Joaquin Delta College, University of Hawai'i, The Arizona Board of Regents on
 * behalf of the University of Arizona, and the r*smart group.
 * 
 * Licensed under the Educational Community License Version 1.0 (the "License"); By obtaining,
 * using and/or copying this Original Work, you agree that you have read, understand, and will
 * comply with the terms and conditions of the Educational Community License.
 * 
 * You may obtain a copy of the License at:
 * 
 * http://kualiproject.org/license.html
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.kuali.notification.core;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.junit.Test;
import org.kuali.notification.bo.Notification;
import org.kuali.notification.bo.NotificationChannel;
import org.kuali.notification.bo.NotificationProducer;
import org.kuali.notification.dao.BusinessObjectDaoTestCaseBase;
import org.kuali.notification.test.util.MockObjectsUtil;
import org.kuali.notification.util.NotificationConstants;

/**
 * Scratch test case for testing aspects of OJB under test harness
 * @author Aaron Hamid (arh14 at cornell dot edu)
 */
public class TestOJBTest extends BusinessObjectDaoTestCaseBase {
    /**
     * Just prints the SQL generated by a query criteria 
     */
    @Test
    public void testCriteria() {
        Criteria criteria_STATUS = new Criteria();
        criteria_STATUS.addEqualTo(NotificationConstants.BO_PROPERTY_NAMES.MESSAGE_DELIVERY_STATUS, NotificationConstants.MESSAGE_DELIVERY_STATUS.DELIVERED);

        Criteria criteria_UNDELIVERED = new Criteria();
        criteria_UNDELIVERED.addEqualTo(NotificationConstants.BO_PROPERTY_NAMES.MESSAGE_DELIVERY_STATUS, NotificationConstants.MESSAGE_DELIVERY_STATUS.UNDELIVERED);

        // now OR the above two together
        criteria_STATUS.addOrCriteria(criteria_UNDELIVERED);

        Criteria criteria_NOTLOCKED = new Criteria();
        criteria_NOTLOCKED.addIsNull(NotificationConstants.BO_PROPERTY_NAMES.LOCKED_DATE);

        Criteria fullQueryCriteria = new Criteria();
        fullQueryCriteria.addAndCriteria(criteria_NOTLOCKED);
        fullQueryCriteria.addLessOrEqualThan(NotificationConstants.BO_PROPERTY_NAMES.NOTIFICATION_AUTO_REMOVE_DATE_TIME, new Timestamp(System.currentTimeMillis()));
        // now add in the STATUS check
        fullQueryCriteria.addAndCriteria(criteria_STATUS);
        
        
        System.err.println(fullQueryCriteria.toString());
        
        Query q = QueryFactory.newQuery(Notification.class, fullQueryCriteria);
        System.err.println(q.toString());
        
    }
    
    @Test
    public void testUpdateRelationships() {
	NotificationChannel channel1 = MockObjectsUtil.getTestChannel1();
	NotificationChannel channel2 = MockObjectsUtil.getTestChannel2();
	NotificationProducer mockProducer1 = MockObjectsUtil.getTestProducer1();

	businessObjectDao.save(mockProducer1);
	assertEquals(0, mockProducer1.getChannels().size());
	
	// add in a notification channel producer join object
	channel1.getProducers().add(mockProducer1);
	
	businessObjectDao.save(channel1);
	
	assertEquals(1, channel1.getProducers().size());
	
	// ojb doesn't update the collections of the child in the relationship on save, despite auto-update...
	// so I'm forced to load it again
	mockProducer1 = (NotificationProducer) businessObjectDao.findById(NotificationProducer.class, mockProducer1.getId());
	assertEquals(1, mockProducer1.getChannels().size());

	channel2.getProducers().add(mockProducer1);	
	businessObjectDao.save(channel2);

	assertEquals(1, channel2.getProducers().size());

	mockProducer1 = (NotificationProducer) businessObjectDao.findById(NotificationProducer.class, mockProducer1.getId());

	assertEquals(2, mockProducer1.getChannels().size());
    }
}