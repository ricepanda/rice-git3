/*
 * Copyright 2005-2007 The Kuali Foundation
 *
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
package org.kuali.rice.kew.batch.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.kns.web.struts.form.KualiForm;

/**
 * Struts form that accepts uploaded files (in the form of <code>FormFile</code>s)
 * @see org.kuali.rice.kew.batch.web.IngesterAction
 * @see org.apache.struts.upload.FormFile
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class IngesterForm extends KualiForm {

	private static final long serialVersionUID = -2847217233600977960L;
	// this is sort of weak but the alternative is to linearly
    // pad a standard List will null items to make the accessors/mutators happy
    // on get(index)/set(index, value) so they don't throw IndexOutOfBoundsException
    private Map files = new HashMap();

    public Collection getFiles() {
        return files.values();
    }

    public void setFile(int index, Object value) {
        files.put(new Integer(index), value);
    }

    public Object getFile(int index) {
        return files.get(new Integer(index));
    }

    public void reset() {
        files.clear();
    }
}
