/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.core.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;


/**
 * 
 * Persistable Business Object which holds a sub-categorization of employee.
 */

@Entity
@Table(name="SH_EMP_TYP_T")
public class EmployeeType extends KualiCodeBase {
    @Column(name="ROW_ACTV_IND")
	private boolean rowActiveIndicator;
    
    /**
     * public no-arg constructor, which accomplishes basic initialization
     */
    public EmployeeType() {
        super();
    }
    
    /**
     * @return Getter for the active field.
     */
    public boolean isRowActiveIndicator() {
        return rowActiveIndicator;
    }

    
    /**
     * @param name - Setter for the active field.
     */
    public void setRowActiveIndicator(boolean a) {
        this.rowActiveIndicator = a;
    }
}

