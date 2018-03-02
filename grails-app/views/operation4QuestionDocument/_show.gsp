<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'questionDocument.label', default: 'QuestionDocument')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-questionDocument" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="show-questionDocument" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="questionDocument" />
            <!--g:form resource="${this.questionDocument}" method="DELETE"-->
            <g:form controller="operation4QuestionDocument" action="delete" id="${this.questionDocument.id}">
                <fieldset class="buttons">
                    <!--
                    <g:link class="edit" action="edit" resource="${this.questionDocument}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                -->
                    <a href="javascript: editQuestionDocument(${this.questionDocument.id})">Edit</a>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
