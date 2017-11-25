<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'commonUIA.label', default: 'CommonUIA')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div id="list-commonUIA" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--f:table collection="${commonUIAList}"/-->
    <table>
        <thead>
        <th>
            关键字
        </th>
        <th>
            视图
        </th>
        <th>
            脚本
        </th>
        <th>
            操作
        </th>
        </thead>
        <tbody>
            <g:each in="${commonUIAList}" var="item" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${item.dataKeyA}</td>
                    <td>${item.viewFileName}</td>
                    <td>${item.jsFileName}</td>
                    <td>
                        <a href="operation4CommonUIA/displayDataKeyUI/${item.id}">
                            测试
                        </a>
                    </td>
                </tr>
            </g:each>
        </tbody>
    </table>

</div>
</body>
</html>