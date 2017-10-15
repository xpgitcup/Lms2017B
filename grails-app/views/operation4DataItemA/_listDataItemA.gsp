<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-dataItemA" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--f:table collection="${dataItemAList}" /-->
            <table>
                <thead>
                <th>数据项</th>
                <th>操作</th>
                </thead>
                <tbody>
                <g:each var="item" status="i" in="${dataItemAList}">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${item.dataKeyA.dataTag}</td>
                        <td>
                            <a href="operation4DataItemA/exportToExcelFile/${item.id}">导出</a>
                        </td>
                    </tr>
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <g:each in="${item.subDataItems}" var="e" status="j">
                            <td>${e.dataKeyA.dataTag}=${e.dataValue}</td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>