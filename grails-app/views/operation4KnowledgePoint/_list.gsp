<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'knowledgePoint.label', default: 'KnowledgePoint')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-knowledgePoint" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div id="list-knowledgePoint" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <f:table collection="${knowledgePointList}"/>
    <table>
        <g:if test="${knowledgePointList.size() > 0}">
            <thead>
            <th>编号</th>
            <th>名称</th>
            <th>目标</th>
            <th>知识点</th>
            </thead>
        </g:if>
        <tbody>
        <g:each var="item" status="i" in="${knowledgePointList}">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="javascript: showKnowledgePoint(${item.id})">请替换</a>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>