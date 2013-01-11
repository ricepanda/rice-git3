/**
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.rice.krad.demo.uif.form;

import edu.sampleu.demo.kitchensink.UITestObject;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.web.form.UifFormBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic form for the KRAD sample application
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class KradSampleAppForm extends UifFormBase {
    private static final long serialVersionUID = -7525378097732916418L;
    private String themeName;
    private String exampleShown;
    private String currentExampleIndex;

    //Fields separated by demonstration type below:

    //InputField
    private String inputField1;
    private String inputField2;
    private String inputField3;
    private String inputField4;
    private String inputField5;
    private String inputField6;
    private String inputField7;
    private String inputField8;
    private String inputField9;
    private String inputField10;
    private String inputField11;
    private String inputField12;
    private String inputField13;
    private String inputField14;
    private String inputField15;

    private String testPersonId;
    private Person testPerson;

    private String testGroupId;

    //MessageField
    private String messageField1;

    // Collections
    private List<UITestObject> collection1 = new ArrayList<UITestObject>();
    private List<UITestObject> collection2 = new ArrayList<UITestObject>();
    private List<UITestObject> collection3 = new ArrayList<UITestObject>();
    private List<UITestObject> collection4 = new ArrayList<UITestObject>();
    private List<UITestObject> collection5 = new ArrayList<UITestObject>();

    public KradSampleAppForm() {
        super();

        messageField1 = "fruits";

        getCollection1().add(new UITestObject("13", "14", "15", "16"));
        getCollection1().add(new UITestObject("17", "18", "19", "20"));
        getCollection1().add(new UITestObject("5", "6", "7", "8"));
        getCollection1().add(new UITestObject("1", "2", "3", "4"));
        getCollection1().add(new UITestObject("9", "10", "11", "12"));
        getCollection1().add(new UITestObject("13", "14", "15", "16"));
        getCollection1().add(new UITestObject("213", "143", "151", "126"));
        getCollection1().add(new UITestObject("133", "144", "155", "156"));

        getCollection2().add(new UITestObject("A", "B", "C", "D"));
        getCollection2().add(new UITestObject("1", "2", "3", "4"));
        getCollection2().add(new UITestObject("W", "X", "Y", "Z"));
        collection2.add(new UITestObject("a", "b", "c", "d"));
        collection2.add(new UITestObject("a", "s", "d", "f"));

        collection3.add(new UITestObject("A", "B", "C", "D"));
        collection3.get(0).getSubList().add(new UITestObject("A", "B", "C", "D"));
        collection3.get(0).getSubList().add(new UITestObject("1", "2", "3", "4"));
        collection3.get(0).getSubList().add(new UITestObject("W", "X", "Y", "Z"));
        collection3.add(new UITestObject("1", "2", "3", "4"));
        collection3.get(1).getSubList().add(new UITestObject("A", "B", "C", "D"));
        collection3.get(1).getSubList().add(new UITestObject("1", "2", "3", "4"));
        collection3.add(new UITestObject("W", "X", "Y", "Z"));
        collection3.get(2).getSubList().add(new UITestObject("W", "X", "Y", "Z"));

        collection4.add(new UITestObject("A", "B", "C", "D"));
        collection4.get(0).getSubList().add(new UITestObject("A", "B", "C", "D"));
        collection4.get(0).getSubList().add(new UITestObject("1", "2", "3", "4"));
        collection4.get(0).getSubList().add(new UITestObject("W", "X", "Y", "Z"));
        collection4.add(new UITestObject("1", "2", "3", "4"));
        collection4.get(1).getSubList().add(new UITestObject("a", "b", "C", "D"));
        collection4.get(1).getSubList().add(new UITestObject("a", "s", "D", "F"));

        //triple nesting
        collection5.add(new UITestObject("a", "a", "a", "a"));
        collection5.get(0).getSubList().add(new UITestObject("A", "B", "C", "D"));
        collection5.get(0).getSubList().get(0).getSubList().add(new UITestObject("a3", "3", "3", "3"));
        collection5.get(0).getSubList().get(0).getSubList().add(new UITestObject("a3", "3", "3", "3"));
        collection5.get(0).getSubList().add(new UITestObject("1", "2", "3", "4"));
        collection5.get(0).getSubList().get(1).getSubList().add(new UITestObject("b3", "3", "3", "3"));
        collection5.get(0).getSubList().get(1).getSubList().add(new UITestObject("b3", "3", "3", "3"));
        collection5.get(0).getSubList().get(1).getSubList().add(new UITestObject("b3", "3", "3", "3"));
        collection5.add(new UITestObject("b", "b", "b", "b"));
        collection5.get(1).getSubList().add(new UITestObject("a", "b", "C", "D"));
        collection5.get(1).getSubList().get(0).getSubList().add(new UITestObject("a23", "3", "3", "3"));
        collection5.get(1).getSubList().get(0).getSubList().add(new UITestObject("a23", "3", "3", "3"));
        collection5.get(1).getSubList().add(new UITestObject("a", "s", "D", "F"));
        collection5.get(1).getSubList().get(1).getSubList().add(new UITestObject("b23", "3", "3", "3"));
        collection5.get(1).getSubList().get(1).getSubList().add(new UITestObject("b23", "3", "3", "3"));
    }

    /**
     * Theme by name (id) currently used for the component library view
     *
     * @return
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * @param themeName
     */
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    /**
     * Backing property for the large example dropdown since on is required.  Stores dropdown selection
     *
     * @return
     */
    public String getExampleShown() {
        return exampleShown;
    }

    /**
     * Large example selection
     *
     * @param exampleShown
     */
    public void setExampleShown(String exampleShown) {
        this.exampleShown = exampleShown;
    }

    /**
     * Index of the current example, used to reselect between submit actions
     *
     * @return
     */
    public String getCurrentExampleIndex() {
        return currentExampleIndex;
    }

    /**
     * Index of the current example
     *
     * @param currentExampleIndex
     */
    public void setCurrentExampleIndex(String currentExampleIndex) {
        this.currentExampleIndex = currentExampleIndex;
    }

    /**
     * Below are basic getters and setters for this data object - no javadoc needed *
     */

    public String getInputField1() {
        return inputField1;
    }

    public void setInputField1(String inputField1) {
        this.inputField1 = inputField1;
    }

    public String getInputField2() {
        return inputField2;
    }

    public void setInputField2(String inputField2) {
        this.inputField2 = inputField2;
    }

    public String getInputField3() {

        return inputField3;
    }

    public void setInputField3(String inputField3) {
        this.inputField3 = inputField3;
    }

    public String getInputField4() {
        return inputField4;
    }

    public void setInputField4(String inputField4) {
        this.inputField4 = inputField4;
    }

    public String getInputField5() {
        return inputField5;
    }

    public void setInputField5(String inputField5) {
        this.inputField5 = inputField5;
    }

    public String getInputField6() {
        return inputField6;
    }

    public void setInputField6(String inputField6) {
        this.inputField6 = inputField6;
    }

    public String getInputField7() {
        return inputField7;
    }

    public void setInputField7(String inputField7) {
        this.inputField7 = inputField7;
    }

    public String getInputField8() {
        return inputField8;
    }

    public void setInputField8(String inputField8) {
        this.inputField8 = inputField8;
    }

    public String getInputField9() {
        return inputField9;
    }

    public void setInputField9(String inputField9) {
        this.inputField9 = inputField9;
    }

    public String getInputField10() {
        return inputField10;
    }

    public void setInputField10(String inputField10) {
        this.inputField10 = inputField10;
    }

    public String getInputField11() {
        return inputField11;
    }

    public void setInputField11(String inputField11) {
        this.inputField11 = inputField11;
    }

    public String getInputField12() {
        return inputField12;
    }

    public void setInputField12(String inputField12) {
        this.inputField12 = inputField12;
    }

    public String getInputField13() {
        return inputField13;
    }

    public void setInputField13(String inputField13) {
        this.inputField13 = inputField13;
    }

    public String getInputField14() {
        return inputField14;
    }

    public void setInputField14(String inputField14) {
        this.inputField14 = inputField14;
    }

    public String getInputField15() {
        return inputField15;
    }

    public void setInputField15(String inputField15) {
        this.inputField15 = inputField15;
    }

    public String getMessageField1() {
        return messageField1;
    }

    public void setMessageField1(String messageField1) {
        this.messageField1 = messageField1;
    }

    public String getTestPersonId() {
        return testPersonId;
    }

    public void setTestPersonId(String testPersonId) {
        this.testPersonId = testPersonId;
    }

    public Person getTestPerson() {
        if ((testPerson == null) || !StringUtils.equals(testPerson.getPrincipalId(), getTestPersonId())) {
            testPerson = KimApiServiceLocator.getPersonService().getPerson(getTestPersonId());

            if (testPerson == null) {
                try {
                    testPerson = KimApiServiceLocator.getPersonService().getPersonImplementationClass().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return testPerson;
    }

    public void setTestPerson(Person testPerson) {
        this.testPerson = testPerson;
    }

    public String getTestGroupId() {
        return testGroupId;
    }

    public void setTestGroupId(String testGroupId) {
        this.testGroupId = testGroupId;
    }

    public List<UITestObject> getCollection1() {
        return collection1;
    }

    public void setCollection1(List<UITestObject> collection1) {
        this.collection1 = collection1;
    }

    public List<UITestObject> getCollection2() {
        return collection2;
    }

    public void setCollection2(List<UITestObject> collection2) {
        this.collection2 = collection2;
    }

    public List<UITestObject> getCollection3() {
        return collection3;
    }

    public void setCollection3(List<UITestObject> collection3) {
        this.collection3 = collection3;
    }

    public List<UITestObject> getCollection4() {
        return collection4;
    }

    public void setCollection4(List<UITestObject> collection4) {
        this.collection4 = collection4;
    }

    public List<UITestObject> getCollection5() {
        return collection5;
    }

    public void setCollection5(List<UITestObject> collection5) {
        this.collection5 = collection5;
    }
}
