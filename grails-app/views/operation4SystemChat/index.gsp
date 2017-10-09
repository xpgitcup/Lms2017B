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
    <g:set var="entityName" value="SystemChat"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>
<div id="operation4SystemChatDiv" class="easyui-tabs">
    <div title="我在听">
        <div id="listSystemChatListeningDiv">??????</div>
        <div id="paginationListSystemChatListeningDiv" class="easyui-pagination"></div>
    </div>

    <div title="我的发言">
        <div class="nav">
            <ul>
                <li>
                    <a href="javascript: createSystemChat(0)">发言</a>
                </li>
            </ul>
        </div>
        <div id="listSystemChatISayDiv"></div>
        <div id="paginationListSystemChatISayDiv" class="easyui-pagination"></div>
    </div>

    <div title="整体显示" class="container">
        <div class="row-fluid">
            <div class="col-md-12 column">
                <div class="panel panel-default">
                    <div class="nav">
                        <ul>
                            <li>
                                <a href="javascript: createSystemChat(0)">发言</a>
                            </li>
                        </ul>
                    </div>

                    <div class="easyui-panel">
                        <div id="listSystemChatDiv" class="easyui-tree"></div>

                        <div id="paginationSystemChatDiv" class="easyui-pagination"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div title="对话">
        <div class="easyui-panel">
            <div id="showSystemChatDiv"></div>
        </div>
    </div>
</div>
</body>
</html>
