<#--

    Copyright 2005-2014 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->
<?xml version="1.0" encoding="UTF-8"?>
<jmeterTestPlan version="1.2" properties="2.4" jmeter="2.9 r1437961">
<hashTree>
<TestPlan guiclass="TestPlanGui" testclass="TestPlan" testname="@{TESTPLANNAME}" enabled="true">
    <stringProp name="TestPlan.comments">JMeter test for @{TESTPLANNAME}</stringProp>
    <boolProp name="TestPlan.functional_mode">false</boolProp>
    <boolProp name="TestPlan.serialize_threadgroups">false</boolProp>
    <elementProp name="TestPlan.user_defined_variables" elementType="Arguments" guiclass="ArgumentsPanel" testclass="Arguments" testname="User Defined Variables" enabled="true">
        <collectionProp name="Arguments.arguments"/>
    </elementProp>
    <stringProp name="TestPlan.user_define_classpath"></stringProp>
</TestPlan>
<hashTree>
<!-- When called from Maven the User Defined Variable default values defined here, which have maven properties, are not
used when running from maven, the jmeter properties from maven override them.  These defaults are used when running from
the JMeter UI or command line script -->
<Arguments guiclass="ArgumentsPanel" testclass="Arguments" testname="User Defined Variables" enabled="true">
    <collectionProp name="Arguments.arguments">
        <elementProp name="SERVER" elementType="Argument">
            <stringProp name="Argument.name">SERVER</stringProp>
            <stringProp name="Argument.value">@{__P(server,localhost)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="PORT" elementType="Argument">
            <stringProp name="Argument.name">PORT</stringProp>
            <stringProp name="Argument.value">@{__P(port,8080)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="PATH" elementType="Argument">
            <stringProp name="Argument.name">PATH</stringProp>
            <stringProp name="Argument.value">@{__P(path,)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="LOOPS" elementType="Argument">
            <stringProp name="Argument.name">LOOPS</stringProp>
            <stringProp name="Argument.value">@{__P(loops,1)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="THREADS" elementType="Argument">
            <stringProp name="Argument.name">THREADS</stringProp>
            <stringProp name="Argument.value">@{__P(threads,1)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="RAMPSECONDS" elementType="Argument">
            <stringProp name="Argument.name">RAMPSECONDS</stringProp>
            <stringProp name="Argument.value">@{__P(rampseconds,1)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="USERPREFIX" elementType="Argument">
            <stringProp name="Argument.name">USERPREFIX</stringProp>
            <stringProp name="Argument.value">@{__P(userprefix,testuser)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="COUNTERCONFIGSTART" elementType="Argument">
            <stringProp name="Argument.name">COUNTERCONFIGSTART</stringProp>
            <stringProp name="Argument.value">@{__P(counterconfigstart,1)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="COUNTERCONFIGEND" elementType="Argument">
            <stringProp name="Argument.name">COUNTERCONFIGEND</stringProp>
            <stringProp name="Argument.value">@{__P(counterconfigend,6)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="COUNTERCONFIGINCR" elementType="Argument">
            <stringProp name="Argument.name">COUNTERCONFIGINCR</stringProp>
            <stringProp name="Argument.value">@{__P(counterconfigincr,1)}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="PAGEUNDERTESTTIMEOUTMS" elementType="Argument">
            <stringProp name="Argument.name">PAGEUNDERTESTTIMEOUTMS</stringProp>
            <stringProp name="Argument.value">@{__P(pageundertesttimeoutms,${pageundertesttimeoutms})}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="TESTPLANNAME" elementType="Argument">
            <stringProp name="Argument.name">TESTPLANNAME</stringProp>
            <stringProp name="Argument.value">@{__P(testplanname,${testplanname})}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="TESTPATH" elementType="Argument">
            <stringProp name="Argument.name">TESTPATH</stringProp>
            <stringProp name="Argument.value">@{__P(testpath,${testpath})}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="VIEWID" elementType="Argument">
            <stringProp name="Argument.name">VIEWID</stringProp>
            <stringProp name="Argument.value">@{__P(viewid,${viewid})}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
        <elementProp name="PAGEID" elementType="Argument">
            <stringProp name="Argument.name">PAGEID</stringProp>
            <stringProp name="Argument.value">@{__P(pageid,${pageid})}</stringProp>
            <stringProp name="Argument.metadata">=</stringProp>
        </elementProp>
    </collectionProp>
</Arguments>
<hashTree/>
<ConfigTestElement guiclass="HttpDefaultsGui" testclass="ConfigTestElement" testname="HTTP Request Defaults" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" testname="User Defined Variables" enabled="true">
        <collectionProp name="Arguments.arguments"/>
    </elementProp>
    <stringProp name="HTTPSampler.domain">@{SERVER}</stringProp>
    <stringProp name="HTTPSampler.port">@{PORT}</stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol"></stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}</stringProp>
    <stringProp name="HTTPSampler.concurrentPool">4</stringProp>
</ConfigTestElement>
<hashTree/>
<ThreadGroup guiclass="ThreadGroupGui" testclass="ThreadGroup" testname="Thread Group" enabled="true">
    <stringProp name="ThreadGroup.on_sample_error">continue</stringProp>
    <elementProp name="ThreadGroup.main_controller" elementType="LoopController" guiclass="LoopControlPanel" testclass="LoopController" testname="Loop Controller" enabled="true">
        <boolProp name="LoopController.continue_forever">false</boolProp>
        <stringProp name="LoopController.loops">1</stringProp>
    </elementProp>
    <stringProp name="ThreadGroup.num_threads">@{THREADS}</stringProp>
    <stringProp name="ThreadGroup.ramp_time">@{RAMPSECONDS}</stringProp>
    <longProp name="ThreadGroup.start_time">1366316314000</longProp>
    <longProp name="ThreadGroup.end_time">1366316314000</longProp>
    <boolProp name="ThreadGroup.scheduler">false</boolProp>
    <stringProp name="ThreadGroup.duration"></stringProp>
    <stringProp name="ThreadGroup.delay"></stringProp>
    <stringProp name="TestPlan.comments">Use recording manager</stringProp>
</ThreadGroup>
<hashTree>
<CookieManager guiclass="CookiePanel" testclass="CookieManager" testname="HTTP Cookie Manager" enabled="true">
    <collectionProp name="CookieManager.cookies"/>
    <boolProp name="CookieManager.clearEachIteration">false</boolProp>
</CookieManager>
<hashTree/>
<RecordingController guiclass="RecordController" testclass="RecordingController" testname="Recording Controller" enabled="true"/>
<hashTree>
<CounterConfig guiclass="CounterConfigGui" testclass="CounterConfig" testname="USER Counter" enabled="true">
    <stringProp name="CounterConfig.start">@{COUNTERCONFIGSTART}</stringProp>
    <stringProp name="CounterConfig.end">@{COUNTERCONFIGEND}</stringProp>
    <stringProp name="CounterConfig.incr">@{COUNTERCONFIGINCR}</stringProp>
    <stringProp name="CounterConfig.name">USER</stringProp>
    <stringProp name="CounterConfig.format">@{USERPREFIX}</stringProp>
    <boolProp name="CounterConfig.per_user">false</boolProp>
</CounterConfig>
<hashTree/>
<UserParameters guiclass="UserParametersGui" testclass="UserParameters" testname="User Parameters" enabled="true">
    <collectionProp name="UserParameters.names">
        <stringProp name="84303">URL</stringProp>
    </collectionProp>
    <collectionProp name="UserParameters.thread_values">
        <collectionProp name="960218966">
            <stringProp name="1330894003">@{SERVER}:@{PORT}@{PATH}</stringProp>
        </collectionProp>
    </collectionProp>
    <boolProp name="UserParameters.per_iteration">false</boolProp>
</UserParameters>
<hashTree/>
<RegexExtractor guiclass="RegexExtractorGui" testclass="RegexExtractor" testname="Regular Expression Extractor" enabled="true">
    <stringProp name="RegexExtractor.useHeaders">false</stringProp>
    <stringProp name="RegexExtractor.refname">FORM_KEY</stringProp>
    <stringProp name="RegexExtractor.regex">type=&quot;hidden&quot; name=&quot;formKey&quot; value=&quot;(.+)&quot;</stringProp>
    <stringProp name="RegexExtractor.template">$1$</stringProp>
    <stringProp name="RegexExtractor.default"></stringProp>
    <stringProp name="RegexExtractor.match_number">0</stringProp>
</RegexExtractor>
<hashTree/>
<RegexExtractor guiclass="RegexExtractorGui" testclass="RegexExtractor" testname="Regular Expression Extractor" enabled="true">
    <stringProp name="RegexExtractor.useHeaders">false</stringProp>
    <stringProp name="RegexExtractor.refname">SESSION_ID</stringProp>
    <stringProp name="RegexExtractor.regex">type=&quot;hidden&quot; name=&quot;sessionId&quot; value=&quot;(.+)&quot;</stringProp>
    <stringProp name="RegexExtractor.template">$1$</stringProp>
    <stringProp name="RegexExtractor.default"></stringProp>
    <stringProp name="RegexExtractor.match_number">0</stringProp>
</RegexExtractor>
<hashTree/>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments"/>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/kradsampleapp" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">KradSampleAppHome</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">start</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/kradsampleapp</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-login/login" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">start</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">DummyLoginView</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="dataObjectClassName" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">dataObjectClassName</stringProp>
                <stringProp name="Argument.value">org.kuali.rice.krad.web.login.DummyLoginForm</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="returnLocation" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">returnLocation</stringProp>
                <stringProp name="Argument.value">%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-login/login</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/listener" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.loading</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.error</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.errorFieldModified</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.warning</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.information</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.details</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.closeDetails</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/1/statuses/user_timeline.json" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="screen_name" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">screen_name</stringProp>
                <stringProp name="Argument.value">kuali</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="count" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">count</stringProp>
                <stringProp name="Argument.value">3</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="include_rts" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">include_rts</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="page" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">page</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="callback" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">callback</stringProp>
                <stringProp name="Argument.value">jQuery18308417981016830758_1370389801212</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="_" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">_</stringProp>
                <stringProp name="Argument.value">1370389801669</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain">api.twitter.com</stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/1/statuses/user_timeline.json</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">*/*</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-login/login" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">DummyLoginView</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="requestedFormKey" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">requestedFormKey</stringProp>
                <stringProp name="Argument.value"></stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="flowKey" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">flowKey</stringProp>
                <stringProp name="Argument.value"></stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="view.applyDirtyCheck" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">view.applyDirtyCheck</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="dirtyForm" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">dirtyForm</stringProp>
                <stringProp name="Argument.value">false</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="renderedInLightBox" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">renderedInLightBox</stringProp>
                <stringProp name="Argument.value">false</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="view.singlePageView" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">view.singlePageView</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="view.disableBrowserCache" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">view.disableBrowserCache</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="login_user" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">login_user</stringProp>
                <stringProp name="Argument.value">@{USER}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="view.currentPageId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">view.currentPageId</stringProp>
                <stringProp name="Argument.value">LoginPage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">submit</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="focusId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">focusId</stringProp>
                <stringProp name="Argument.value">LoginButton</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="jumpToId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">jumpToId</stringProp>
                <stringProp name="Argument.value">LoginButton</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="formKey" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.value">@{FORM_KEY}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
                <stringProp name="Argument.name">formKey</stringProp>
            </elementProp>
            <elementProp name="sessionId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.value">@{SESSION_ID}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
                <stringProp name="Argument.name">sessionId</stringProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">ISO-8859-1</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-login/login</stringProp>
    <stringProp name="HTTPSampler.method">POST</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Content-Type" elementType="Header">
                <stringProp name="Header.name">Content-Type</stringProp>
                <stringProp name="Header.value">application/x-www-form-urlencoded</stringProp>
            </elementProp>
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
    <ResponseAssertion guiclass="AssertionGui" testclass="ResponseAssertion" testname="Response Assertion" enabled="true">
        <collectionProp name="Asserion.test_strings">
            <stringProp name="-1877499968">Invalid Login</stringProp>
        </collectionProp>
        <stringProp name="Assertion.test_field">Assertion.response_data</stringProp>
        <boolProp name="Assertion.assume_success">false</boolProp>
        <intProp name="Assertion.test_type">6</intProp>
    </ResponseAssertion>
    <hashTree/>
</hashTree>
<IfController guiclass="IfControllerPanel" testclass="IfController" testname="If Invalid Login Controller" enabled="true">
    <stringProp name="TestPlan.comments">If Invalid Login, Skip rest of test</stringProp>
    <stringProp name="IfController.condition">@{JMeterThread.last_sample_ok}</stringProp>
    <boolProp name="IfController.evaluateAll">false</boolProp>
</IfController>
<hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/kradsampleapp" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">KradSampleAppHome</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">start</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="__login_user" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">__login_user</stringProp>
                <stringProp name="Argument.value">@{USER}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/kradsampleapp</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/kradsampleapp" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">KradSampleAppHome</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">start</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/kradsampleapp</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-login/login?methodToCall=start&amp;viewId=DummyLoginView&amp;dataObjectClassName=org.kuali.rice.krad.web.login.DummyLoginForm&amp;returnLocation=%2Fkr-krad%2Fkradsampleapp%3FviewId%3DKradSampleAppHome%26methodToCall%3Dstart</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/listener" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.loading</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">utf-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.error</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding"></stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.errorFieldModified</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.warning</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.information</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.details</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/1/statuses/user_timeline.json" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="screen_name" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">screen_name</stringProp>
                <stringProp name="Argument.value">kuali</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="count" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">count</stringProp>
                <stringProp name="Argument.value">3</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="include_rts" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">include_rts</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="page" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">page</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="callback" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">callback</stringProp>
                <stringProp name="Argument.value">jQuery18308659084213754977_1370389813887</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="_" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">_</stringProp>
                <stringProp name="Argument.value">1370389814197</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain">api.twitter.com</stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">utf-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/1/statuses/user_timeline.json</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">*/*</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.closeDetails</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/kradsampleapp?viewId=KradSampleAppHome&amp;methodToCall=start</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<LoopController guiclass="LoopControlPanel" testclass="LoopController" testname="Loop Controller" enabled="true">
    <boolProp name="LoopController.continue_forever">true</boolProp>
    <stringProp name="LoopController.loops">@{LOOPS}</stringProp>
</LoopController>
<hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="@{TESTPATH}?viewId=@{VIEWID}&amp;pageId=@{PAGEID}" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="viewId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">viewId</stringProp>
                <stringProp name="Argument.value">@{VIEWID}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="pageId" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">pageId</stringProp>
                <stringProp name="Argument.value">@{PAGEID}</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}@{TESTPATH}</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
    <DurationAssertion guiclass="DurationAssertionGui" testclass="DurationAssertion" testname="Duration Assertion" enabled="true">
        <stringProp name="DurationAssertion.duration">@{PAGEUNDERTESTTIMEOUTMS}</stringProp>
    </DurationAssertion>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/listener" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.loading</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">utf-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">false</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">true</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/labs?viewId=Lab-PerformanceMedium&amp;pageId=Lab-Performance-Page2</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.expand</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/labs?viewId=Lab-PerformanceMedium&amp;pageId=Lab-Performance-Page2</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/kr-krad/listener" enabled="true">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="methodToCall" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">methodToCall</stringProp>
                <stringProp name="Argument.value">retrieveMessage</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxRequest" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxRequest</stringProp>
                <stringProp name="Argument.value">true</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="ajaxReturnType" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">ajaxReturnType</stringProp>
                <stringProp name="Argument.value">update-none</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="key" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">key</stringProp>
                <stringProp name="Argument.value">message.collapse</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain"></stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">UTF-8</stringProp>
    <stringProp name="HTTPSampler.path">@{PATH}/kr-krad/listener</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">application/json, text/javascript, */*; q=0.01</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/labs?viewId=Lab-PerformanceMedium&amp;pageId=Lab-Performance-Page2</stringProp>
            </elementProp>
            <elementProp name="X-Requested-With" elementType="Header">
                <stringProp name="Header.name">X-Requested-With</stringProp>
                <stringProp name="Header.value">XMLHttpRequest</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
<HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy" testname="/1/statuses/user_timeline.json" enabled="false">
    <elementProp name="HTTPsampler.Arguments" elementType="Arguments" guiclass="HTTPArgumentsPanel" testclass="Arguments" enabled="true">
        <collectionProp name="Arguments.arguments">
            <elementProp name="screen_name" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">screen_name</stringProp>
                <stringProp name="Argument.value">kuali</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="count" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">count</stringProp>
                <stringProp name="Argument.value">3</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="include_rts" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">include_rts</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="page" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">page</stringProp>
                <stringProp name="Argument.value">1</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="callback" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">callback</stringProp>
                <stringProp name="Argument.value">jQuery18303170479459034474_1370465044470</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
            <elementProp name="_" elementType="HTTPArgument">
                <boolProp name="HTTPArgument.always_encode">false</boolProp>
                <stringProp name="Argument.name">_</stringProp>
                <stringProp name="Argument.value">1370465045260</stringProp>
                <stringProp name="Argument.metadata">=</stringProp>
                <boolProp name="HTTPArgument.use_equals">true</boolProp>
            </elementProp>
        </collectionProp>
    </elementProp>
    <stringProp name="HTTPSampler.domain">api.twitter.com</stringProp>
    <stringProp name="HTTPSampler.port"></stringProp>
    <stringProp name="HTTPSampler.connect_timeout"></stringProp>
    <stringProp name="HTTPSampler.response_timeout"></stringProp>
    <stringProp name="HTTPSampler.protocol">http</stringProp>
    <stringProp name="HTTPSampler.contentEncoding">utf-8</stringProp>
    <stringProp name="HTTPSampler.path">/1/statuses/user_timeline.json</stringProp>
    <stringProp name="HTTPSampler.method">GET</stringProp>
    <boolProp name="HTTPSampler.follow_redirects">true</boolProp>
    <boolProp name="HTTPSampler.auto_redirects">false</boolProp>
    <boolProp name="HTTPSampler.use_keepalive">true</boolProp>
    <boolProp name="HTTPSampler.DO_MULTIPART_POST">false</boolProp>
    <boolProp name="HTTPSampler.monitor">false</boolProp>
    <stringProp name="HTTPSampler.embedded_url_re"></stringProp>
</HTTPSamplerProxy>
<hashTree>
    <HeaderManager guiclass="HeaderPanel" testclass="HeaderManager" testname="HTTP Header Manager" enabled="true">
        <collectionProp name="HeaderManager.headers">
            <elementProp name="Accept-Language" elementType="Header">
                <stringProp name="Header.name">Accept-Language</stringProp>
                <stringProp name="Header.value">en-US,en;q=0.5</stringProp>
            </elementProp>
            <elementProp name="Accept" elementType="Header">
                <stringProp name="Header.name">Accept</stringProp>
                <stringProp name="Header.value">*/*</stringProp>
            </elementProp>
            <elementProp name="User-Agent" elementType="Header">
                <stringProp name="Header.name">User-Agent</stringProp>
                <stringProp name="Header.value">Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0</stringProp>
            </elementProp>
            <elementProp name="Referer" elementType="Header">
                <stringProp name="Header.name">Referer</stringProp>
                <stringProp name="Header.value">http://@{URL}/kr-krad/labs?viewId=Lab-PerformanceMedium&amp;pageId=Lab-Performance-Page2</stringProp>
            </elementProp>
            <elementProp name="Accept-Encoding" elementType="Header">
                <stringProp name="Header.name">Accept-Encoding</stringProp>
                <stringProp name="Header.value">gzip, deflate</stringProp>
            </elementProp>
        </collectionProp>
    </HeaderManager>
    <hashTree/>
</hashTree>
</hashTree>
</hashTree>
</hashTree>
</hashTree>
</hashTree>
</hashTree>
</jmeterTestPlan>
