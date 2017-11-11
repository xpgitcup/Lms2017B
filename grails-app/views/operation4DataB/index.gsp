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
<!-- end 实现可定制的布局 -->
    <g:set var="entityName" value="DataB"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataDictionaryB.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataKeyAB.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataItemAB.js"/>
</head>

<body>
<div class="nav">
    <ul id="options4Data">
        <li>
            <a href="javascript: clearFilter4Data()">清除</a>
        </li>
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

    <div title="数据表头">
        <div id="showHeadsDiv"></div>
    </div>
</div>
</body>
</html>
