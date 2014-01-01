/**
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.rice.krad.uif.lifecycle.initialize;

import org.kuali.rice.krad.uif.lifecycle.ViewLifecycleTaskBase;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecyclePhase;

/**
 * Perform custom initialization for the component defined by the helper.
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class HelperCustomInitializeTask extends ViewLifecycleTaskBase {

    /**
     * Create a task to assign component IDs during the initialize phase.
     * 
     * @param phase The initialize phase for the component.
     */
    public HelperCustomInitializeTask(ViewLifecyclePhase phase) {
        super(phase);
    }

    /**
     * @see org.kuali.rice.krad.uif.lifecycle.ViewLifecycleTaskBase#performLifecycleTask()
     */
    @Override
    protected void performLifecycleTask() {
        // invoke initialize service hook
        ViewLifecycle.getHelper().performCustomInitialization(getPhase().getComponent());
    }

}
