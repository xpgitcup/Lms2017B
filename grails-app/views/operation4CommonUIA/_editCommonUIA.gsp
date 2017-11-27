<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'commonUIA.label', default: 'CommonUIA')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div id="edit-commonUIA" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.commonUIA}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.commonUIA}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.commonUIA}" method="PUT"-->
    <g:form controller="operation4CommonUIA" action="update">
        <g:hiddenField name="id" value="${this.commonUIA?.id}"/>
        <g:hiddenField name="version" value="${this.commonUIA?.version}"/>
        <fieldset class="form">
            <f:all bean="commonUIA"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
