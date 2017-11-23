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

    <g:set var="entityName" value="CommonA"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>

    <!--加载js文件-->
    <g:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>

</head>

<body>

<g:if test="${session.commonUIA}">
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>
</g:if>
<g:else>
    <h1>请选择某个commonUIA.</h1>
</g:else>

</body>
</html>