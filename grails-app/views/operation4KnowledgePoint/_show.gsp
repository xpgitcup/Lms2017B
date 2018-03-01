<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'knowledgePoint.label', default: 'KnowledgePoint')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-knowledgePoint" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="show-knowledgePoint" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="knowledgePoint" />
            <!--g:form resource="${this.knowledgePoint}" method="DELETE"-->
            <g:form controller="operation4KnowledgePoint" action="delete" id="${this.knowledgePoint.id}">
                <fieldset class="buttons">
                    <!--
                    <g:link class="edit" action="edit" resource="${this.knowledgePoint}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                -->
                    <a href="javascript: editKnowledgePoint(${this.knowledgePoint.id})">Edit</a>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
