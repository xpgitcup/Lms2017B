<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'questionType.label', default: 'QuestionType')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-questionType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="show-questionType" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="questionType" />
            <!--g:form resource="${this.questionType}" method="DELETE"-->
            <g:form controller="operation4QuestionType" action="delete" id="${this.questionType.id}">
                <fieldset class="buttons">
                    <!--
                    <g:link class="edit" action="edit" resource="${this.questionType}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                -->
                    <a href="javascript: editQuestionType(${this.questionType.id})">Edit</a>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
