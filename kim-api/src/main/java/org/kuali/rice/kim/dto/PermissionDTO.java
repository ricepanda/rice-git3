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

import java.io.Serializable;

/**
 * This is the Data Transfer Object (DTO) that is used for our service layer.
 * 
 * This business object represents the entity of Permission.  The ability to perform actions in a system/application 
 * is tied to permissions as these are what get checked against in the application.  A permission should describe the ability to 
 * take a certain action within a system. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class PermissionDTO implements Serializable {
	private static final long serialVersionUID = 6742054701723826912L;
	
    private Long id;
	private String name;
	private String description;
	private Long namespaceId;
	
	private NamespaceDTO namespaceDto;
	
	/**
     * @return the namespaceDto
     */
    public NamespaceDTO getNamespaceDto() {
        return this.namespaceDto;
    }

    /**
     * @param namespaceDto the namespaceDto to set
     */
    public void setNamespaceDto(NamespaceDTO namespaceDto) {
        this.namespaceDto = namespaceDto;
    }

    /**
	 * This method returns the description of a permission instance.
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method sets the description of a permission instance.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method returns the unique identifier (primary key) for a permission instance.
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method sets the value of the primary key for a permission instance.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method returns the name of the permission, which is also a unique identifier when combined 
	 * with the associated application.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of the permission instance.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * @return the namespaceId
     */
    public Long getNamespaceId() {
        return this.namespaceId;
    }

    /**
     * @param namespaceId the namespaceId to set
     */
    public void setNamespaceId(Long namespaceId) {
        this.namespaceId = namespaceId;
    }
}
