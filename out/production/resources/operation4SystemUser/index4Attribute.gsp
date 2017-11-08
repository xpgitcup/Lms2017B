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
    <g:set var="entityName" value="SystemUserAttribute"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/system/${entityName}_SystemUser.js"/>
    <asset:javascript src="cn/edu/cup/system/SystemUser.js"/>
</head>

<body>
<div id="operation4SystemUserAttribute" class="easyui-tabs">
    <div title="用户列表">
        <div class="panel panel-default">
            <div class="nav">
                <ul>
                    <li>
                        <a class="list" id="filterKey">当前过滤条件：</a>
                    </li>
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
                <div id="listSystemUserAttribDiv" class="easyui-tree"></div>

                <div id="paginationSystemUserDiv" class="easyui-pagination"></div>
            </div>
        </div>
    </div>

    <div title="属性列表">
        <div class="nav">
            <ul>
                <li>
                    <a class="list" >统计属性</a>
                </li>
            </ul>
        </div>

    </div>
    <div title="权限编辑">

    </div>
</div>

</body>
</html>
