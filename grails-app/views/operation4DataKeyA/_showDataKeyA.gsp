<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataKeyA.label', default: 'DataKeyA')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-dataKeyA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div id="show-dataKeyA" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="dataKeyA"/>
<!--g:form resource="${this.dataKeyA}" method="DELETE"-->
    <g:form controller="operation4DataKeyA" action="deleteDataKeyA" id="${this.dataKeyA.id}">
        <fieldset class="buttons">
            <!--g:link class="edit" action="edit" resource="${this.dataKeyA}"-->
            <a href="javascript: editDataKeyA(${this.dataKeyA.id})">
                <g:message code="default.button.edit.label" default="Edit"/>
            </a>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
