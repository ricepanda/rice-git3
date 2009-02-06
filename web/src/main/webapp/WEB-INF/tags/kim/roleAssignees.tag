<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<script language="JavaScript" src="scripts/en-common.js"></script>

<c:set var="roleMemberAttributes" value="${DataDictionary.KimDocumentRoleMember.attributes}" />
<c:set var="roleQualifierAttributes" value="${DataDictionary.KimDocumentRoleQualifier.attributes}" />
<c:set var="kimAttributes" value="${DataDictionary.KimAttributeImpl.attributes}" />

<c:set var="readOnly" value="${empty KualiForm.editingMode['fullEntry']}" />

<script language="javaScript">
function changeMemberTypeCode(){
	document.getElementsByTagName("command").value="changeMemberTypeCode";
	javaScript:document.forms[0].submit();
}
</script>
<kul:tab tabTitle="Assignees" defaultOpen="true">
	<div class="tab-container" align="center">
    <h3>
    	<span class="subhead-left">Assignees</span>
    </h3>
    
    <table cellpadding=0 cellspacing=0 summary="">
        	<tr>
        		<th><div align="left">&nbsp</div></th> 
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${roleMemberAttributes.memberTypeCode}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${roleMemberAttributes.memberName}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${roleMemberAttributes.activeFromDate}" noColon="true" /></div></th>
        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${roleMemberAttributes.activeToDate}" noColon="true" /></div></th>
				<c:forEach var="attrDefn" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="status">
        			<c:set var="fieldName" value="${attrDefn.kimAttribute.attributeName}" />
<!-- >	        		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${KualiForm.document.attributeEntry[fieldName]}" noColon="true" /></div></th> -->
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
         		    <kul:htmlAttributeHeaderCell attributeEntry="${attrEntry}" useShortLabel="false" />
		        </c:forEach>
				<c:if test="${not inquiry}">	
            		<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
				</c:if>	
        	</tr>     
          <c:if test="${not inquiry}">	
             <tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<input type="hidden" name="command"/>
                	<kul:htmlControlAttribute property="member.memberTypeCode" 
                	attributeEntry="${roleMemberAttributes.memberTypeCode}" 
                	onchange="changeMemberTypeCode()" />
					<NOSCRIPT>
   						<html:submit value="select" alt="press this button to refresh the page after changing the delegation type." />
					</NOSCRIPT>                
	            </div>
            	<c:set var="bo" value="${KualiForm.memberBusinessObjectName}"/>
            	<c:set var="fc" value="${KualiForm.memberFieldConversions}"/>
				</td>
                <td class="infoline">   
                <div align="center">             	
					<kul:htmlControlAttribute property="member.memberName" attributeEntry="${roleMemberAttributes.memberName}" />
	               	<kul:lookup boClassName="${bo}" fieldConversions="${fc}" anchor="${tabKey}" />
					<html:hidden property="member.memberId" />
				</div>
				</td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="member.activeFromDate" attributeEntry="${roleMemberAttributes.activeFromDate}" datePicker="true" />
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                	<kul:htmlControlAttribute property="member.activeToDate" attributeEntry="${roleMemberAttributes.activeToDate}" datePicker="true" />
                </div>
                </td>
                <c:set var="numberOfQualifiers2" value="${ KualiForm.member.numberOfQualifiers}"/>
				<c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
					<c:set var="fieldName" value="${qualifier.kimAttribute.attributeName}" />
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
		            <td align="left" valign="middle">
		               	<div align="center"> <kul:htmlControlAttribute property="member.qualifiers[${statusQualifier.index}].attrVal"  attributeEntry="${attrEntry}" readOnly="${inquiry}"  />
						</div>
					</td>
		        </c:forEach>
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addMember.anchor${tabKey}"
						src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>
    	   </tr>         
		</c:if>

      	<c:forEach var="member" items="${KualiForm.document.members}" varStatus="statusMember">
       	    <c:set var="rows" value="2"/>
       		<c:if test="${fn:length(member.roleRspActions) < 1}">	
        	       <c:set var="rows" value="1"/>       		
       		</c:if>        	
            <tr>
				<th rowspan="${rows}" class="infoline" valign="top">
					<c:out value="${statusMember.index+1}" />
				</th>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberTypeCode"  attributeEntry="${roleMemberAttributes.memberTypeCode}" disabled="true" readOnly="false" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].memberName"  attributeEntry="${roleMemberAttributes.memberName}" readOnly="true"  />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].activeFromDate"  attributeEntry="${roleMemberAttributes.activeFromDate}" readOnly="${inquiry}"  datePicker="true" />
					</div>
				</td>
	            <td align="left" valign="middle">
	               	<div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].activeToDate"  attributeEntry="${roleMemberAttributes.activeToDate}" readOnly="${inquiry}"  datePicker="true" />
					</div>
				</td>
				<c:set var="numberOfQualifiers" value="${KualiForm.document.members[statusMember.index].numberOfQualifiers}" />
				<c:forEach var="qualifier" items="${KualiForm.document.kimType.attributeDefinitions}" varStatus="statusQualifier">
					<c:set var="fieldName" value="${qualifier.kimAttribute.attributeName}" />
        			<c:set var="attrEntry" value="${KualiForm.document.attributeEntry[fieldName]}" />
        			<c:choose>
        				<c:when test="${numberOfQualifiers>statusQualifier.index}">
				            <td align="left" valign="middle">
				               	<div align="center"> <kul:htmlControlAttribute property="document.members[${statusMember.index}].qualifiers[${statusQualifier.index}].attrVal"  attributeEntry="${attrEntry}" readOnly="${inquiry}"  />
								</div>
							</td>
						</c:when>
        				<c:otherwise>
				            <td align="left" valign="middle">
				               	<div align="center">
								</div>
							</td>
						</c:otherwise>
					</c:choose>
		        </c:forEach>
			<c:if test="${not inquiry}">	
				<td>
					<div align=center>&nbsp;
						<c:choose>
							<c:when test="${role.edit}">
	        	          		<img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
							</c:when>
	        	       		<c:otherwise>
	        	        		<html:image property='methodToCall.deleteMember.line${statusMember.index}.anchor${currentTabIndex}'
								src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
		        	       	</c:otherwise>
	        	     	</c:choose>  
					</div>
				</td>
			</c:if>    
			</tr>
	        <c:if test="${fn:length(member.roleRspActions) > 0}">	
    			    <tr>
	              <td colspan="7" style="padding:0px;">
	              	<kim:responsibilityActions roleIdx="${status.index}" mbrIdx="0" />
		          </td>
		        </tr>
			</c:if>	 
		</c:forEach>        
	</table>
	</div>
</kul:tab>