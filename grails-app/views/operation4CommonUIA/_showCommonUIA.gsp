<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'commonUIA.label', default: 'CommonUIA')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-commonUIA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div id="show-commonUIA" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="commonUIA"/>
<!--g:form resource="${this.commonUIA}" method="DELETE"-->
    <g:form controller="operation4CommonUIA" id="${this.commonUIA.id}" action="delete">
        <fieldset class="buttons">
            <!--g:link class="edit" action="edit" resource="${this.commonUIA}"-->
            <a href="javascript: editCommonUIA(${this.commonUIA.id})">
                <g:message code="default.button.edit.label" default="Edit"/>
            </a>
            <!--/g:link-->
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
