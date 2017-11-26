<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
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

    <g:set var="entityName" value="CommonUIA"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>

    <!--加载js文件-->
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>

</head>

<body>

<div class="easyui-tabs" id="operation4CommonUIADiv">
    <div title="数据浏览">
        <div id="listCommonUIADiv"></div>
        <div id="paginationListCommonUIADiv"></div>
    </div>

    <div title="数据编辑">
        <div id="showCommonUIADiv"></div>
        <div id="editCommonUIADiv"></div>
    </div>
</div>

</body>
</html>