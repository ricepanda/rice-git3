/*
 * Copyright 2011 The Kuali Foundation
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
package org.kuali.rice.krms.impl.provider.repository;

import org.kuali.rice.krms.api.repository.AgendaDefinition;
import org.kuali.rice.krms.framework.engine.AgendaTree;
import org.kuali.rice.krms.framework.engine.LazyAgendaTree;

/**
 * This is a description of what this class does - ewestfal don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public final class RepositoryLazyAgendaTree extends LazyAgendaTree {

	private final AgendaDefinition agendaDefinition;
	private final RepositoryToEngineTranslator translator;
	
	public RepositoryLazyAgendaTree(AgendaDefinition agendaDefinition, RepositoryToEngineTranslator translator) {
		this.agendaDefinition = agendaDefinition;
		this.translator = translator;
	}
	
	@Override
	protected AgendaTree materializeAgendaTree() {
		return translator.translateAgendaDefinitionToAgendaTree(agendaDefinition);
	}

}
