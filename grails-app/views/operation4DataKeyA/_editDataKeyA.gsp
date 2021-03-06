<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataKeyA.label', default: 'DataKeyA')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-dataKeyA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-dataKeyA" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataKeyA}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataKeyA}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.dataKeyA}" method="PUT"-->
    <g:form controller="operation4DataKeyA" action="updateDataKeyA">
        <div class="container">
            <div class="col-md-6">
                <fieldset class="form">

                    <g:hiddenField name="id" value="${this.dataKeyA?.id}"/>
                    <g:hiddenField name="version" value="${this.dataKeyA?.version}"/>
                    <fieldset class="form">
                        <!--f:all bean="dataKeyA"/-->
                        <g:render template="form4DataKeyA"/>
                    </fieldset>
                    <fieldset class="buttons">
                        <input class="save" type="submit"
                               value="${message(code: 'default.button.update.label', default: 'Update')}"
                               onclick="return formCheck(this.form)"/>
                    </fieldset>
                </fieldset>
            </div>

            <div class="col-md-6">
                <ul>
                    <li>
                        <h2>辅助信息输入</h2>

                        <p>对于枚举类型，请在辅助信息中输入各个分量，然后点击输入按钮。</p>

                        <p>对数组，请在辅助信息中输入各列的标题，然后点击输入按钮。</p>
                    </li>
                    <li>
                        <g:textArea name="appendText" id="appendText"></g:textArea>
                        <input type="button" value="输入" onclick="updateAppendForm4DataKeyA()">
                    </li>
                    <li>
                        <g:select name="dataKeyList" optionKey="id" noSelection="${['null': 'Select One...']}"
                                  from="${cn.edu.cup.dictionary.DataKeyA.findAllByDictionaryAndSubDataKeysIsNotEmpty(session.currentDataDictionary)}"
                                  onchange="onchangeDataKeyList(this.form)"/>

                    </li>
                </ul>
            </div>
        </div>

    </g:form>
</div>
</body>
</html>
