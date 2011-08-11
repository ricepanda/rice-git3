/*
 * Copyright 2007-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.ksb.messaging.threadpool;

import java.sql.Timestamp;

import junit.framework.TestCase;

import org.junit.Test;
import org.kuali.rice.ksb.messaging.MessageServiceInvoker;
import org.kuali.rice.ksb.messaging.PersistedMessage;
import org.kuali.rice.ksb.messaging.threadpool.PriorityBlockingQueuePersistedMessageComparator;


/**
 * Tests that the {@link PriorityBlockingQueuePersistedMessageComparator} is sorting by 
 * priority and date. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class PriorityBlockingQueuePersistedMessageComparatorTest extends TestCase {

    @Test public void testSortingByPriorityAndDate() throws Exception {
	PersistedMessage message1 = new PersistedMessage();
	message1.setQueuePriority(1);
	message1.setQueueDate(new Timestamp(System.currentTimeMillis()));
	
	PersistedMessage message2 = new PersistedMessage();
	message2.setQueuePriority(5);
	message2.setQueueDate(new Timestamp(System.currentTimeMillis()));
	
	PersistedMessage message3 = new PersistedMessage();
	message3.setQueuePriority(5);
	message3.setQueueDate(new Timestamp(System.currentTimeMillis() + 5));
	
	assertEquals(PriorityBlockingQueuePersistedMessageComparator.class.getName() + " is sorting incorrectly", -1, new PriorityBlockingQueuePersistedMessageComparator().compare(new MessageServiceInvoker(message1), new MessageServiceInvoker(message2)));
	assertEquals(PriorityBlockingQueuePersistedMessageComparator.class.getName() + " is sorting incorrectly", 1, new PriorityBlockingQueuePersistedMessageComparator().compare(new MessageServiceInvoker(message2), new MessageServiceInvoker(message1)));
	assertEquals(PriorityBlockingQueuePersistedMessageComparator.class.getName() + " is sorting incorrectly", -1, new PriorityBlockingQueuePersistedMessageComparator().compare(new MessageServiceInvoker(message2), new MessageServiceInvoker(message3)));
    }   
}
