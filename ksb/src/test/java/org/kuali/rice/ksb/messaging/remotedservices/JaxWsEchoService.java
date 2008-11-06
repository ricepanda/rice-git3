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
package org.kuali.rice.ksb.messaging.remotedservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This is a jaxws annotated web service, used for testing web services on the ksb. 
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
@WebService(targetNamespace = "http://rice.kuali.org/", name = "JaxWsEchoService")
public interface JaxWsEchoService {

    @WebResult(name = "outMsg")
    @RequestWrapper(localName = "Echo")
    @ResponseWrapper(localName = "EchoResponse")
    @WebMethod
    public String doEcho(
        @WebParam(name = "inMsg") String inMsg
    );	
}
