<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <!--meta name="layout" content="main"/-->
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
    <g:set var="entityName" value="Operation4SystemUser"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/system/SystemUser.js"/>

</head>

<body>

<div id="operation4SystemUserDiv" class="easyui-tabs">
    <div title="用户管理">
        <div class="container">
            <div class="row-fluid">
                <div class="col-md-4 column">
                    <div class="panel panel-default">
                        <div class="nav">
                            <ul>
                                <li>
                                    <a class="list">
                                        用户维护
                                    </a>
                                </li>
                                <li>
                                    <a class="list" id="filterKey">过滤：</a>
                                </li>
                            </ul>
                        </div>

                        <div class="easyui-panel">
                            <div id="listSystemUserDiv" class="easyui-tree"></div>

                            <div id="paginationSystemUserDiv" class="easyui-pagination"></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-8 column">
                    <div class="panel panel-default">
                        <div class="nav" role="navigation">
                            <ul>
                                <li><a class="create" href="javascript: createSystemUser(0)">新建</a></li>
                                <li>
                                    <span>
                                        <form id="searchFormA">
                                            <label>用户名：</label><input type="text" name="userName4Search" id="userName4Search"/>
                                            <input type="button" onclick="search4UserName()" value="搜索">
                                        </form>
                                    </span>
                                </li>
                                <li>
                                    <span>
                                        <form id="searchFormB">
                                            <label>用户属性：</label><input type="text" name="roleAttribute4Search" id="roleAttribute4Search"/>
                                            <input type="button" onclick="filter4SystemUser()" value="过滤">
                                            <input type="button" onclick="clearFilter()" value="清除过滤">
                                        </form>
                                    </span>
                                </li>
                            </ul>
                        </div>

                        <div class="easyui-panel">
                            <div id="showSystemUserDiv"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div title="权限列表"></div>

    <div title="权限编辑">
        <div id="editRoleAttributeDiv"></div>
    </div>

</div>

</body>
</html>
