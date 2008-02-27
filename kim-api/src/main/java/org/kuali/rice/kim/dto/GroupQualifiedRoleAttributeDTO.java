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
package org.kuali.rice.kim.dto;

/**
 * This is a Data Transfer Object (DTO) that is used by the service layer.
 * 
 * Business object that represents a single qualified role attribute record associated with a group.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class GroupQualifiedRoleAttributeDTO extends QualifiedRoleAttributeDTO {
    private static final long serialVersionUID = -4240285371664750182L;
    

    private Long groupId;

    private GroupDTO groupDto;

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return this.groupId;
    }

    /**
     * @param groupId
     *            the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 
     * This method ...
     * 
     * @return GroupDTO
     */
    public GroupDTO getGroupDTO() {
        return this.groupDto;
    }

    /**
     * 
     * This method ...
     * 
     * @param groupDto
     */
    public void setGroupDTO(GroupDTO groupDto) {
        this.groupDto = groupDto;
    }
}