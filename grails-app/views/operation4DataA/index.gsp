<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!--为了处理cookie工作不正常的问题-->
    <g:javascript>
    function checkSessionDataDictionary(currentTab) {
        console.info("处理前的：" + currentTab);
        var tab = currentTab;
        <% if (session.getAttribute("currentDataDictionary") != null) { %>
        tab = "模型列表";
        <% if (session.getAttribute("currentDataKeyA") != null) { %>
        tab = "数据显示";
        <% } %>
        <% } %>
        console.info("处理后的：" + tab);
        return tab;
    }
    </g:javascript>
<!-- end 实现可定制的布局 -->
    <g:set var="entityName" value="DataA"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataDictionary.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataKeyAA.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataItemA.js"/>
</head>

<body>
<div class="nav">
    <ul>
        <g:if test="${session.currentDataDictionary}">
            <li>
                <a href="#">
                    当前字典：${session.currentDataDictionary}
                </a>
            </li>
            <li>
                <a class="create" href="operation4DataDictionary/clearCurrentDataDictionary/?data=1">清除当前选择</a>
            </li>
        </g:if>
        <g:if test="${session.currentDataKeyA}">
            <li>
                <a href="#">
                    当前模型：${session.currentDataKeyA}
                </a>
            </li>
            <li>
                <a class="title" href="operation4DataKeyA/clearCurrentDataKeyA">清除</a>
            </li>
        </g:if>
    </ul>
</div>

<div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>

<div id="operation4DataADiv" class="easyui-tabs">
    <div title="数据字典">
        <div id="listDataDictionaryDiv"></div>

        <div id="paginationListDataDictionaryDiv"></div>
    </div>

    <div title="模型列表">
        <div id="listDataKeyADiv"></div>

        <div id="paginationListDataKeyADiv"></div>
    </div>

    <div title="数据显示">
        <div id="listDataItemADiv"></div>

        <div id="paginationListDataItemADiv"></div>
    </div>

    <div title="图形显示">
        <div id="dataKeyAChartDiv" style="width: 800px; height: 600px"></div>
    </div>

    <div title="数据编辑">
        <div id="editDataItemADiv"></div>
    </div>
</div>
</body>
</html>
