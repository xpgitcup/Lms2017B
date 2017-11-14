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
    <g:set var="entityName" value="Teacher"/>
    <title>${entityName}服务</title>
    <asset:javascript src="cn/edu/cup/user/Teacher.js"/>
    <asset:javascript src="cn/edu/cup/user/project4Teacher.js"/>

</head>

<body>

<div class="nav">

</div>

<div id="tabs4TeacherDiv" class="easyui-tabs">
    <div title="项目管理">
        <div class="easyui-panel">
            <div id="listProject4TeacherDiv"></div>
            <div id="paginationListProject4TeacherDiv"></div>
        </div>
    </div>

    <div title="人员情况"></div>
    <div title="项目进度情况"></div>
    <div title="项目人员进度"></div>
    <div title="数据编辑">
        <div id="editDataItemADiv"></div>
    </div>
</div>

</body>
</html>
