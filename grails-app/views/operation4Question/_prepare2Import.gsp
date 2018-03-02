<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'question.label', default: 'Question')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-question" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div id="create-question" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.question}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.question}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.question}" method="POST"-->
    <g:uploadForm controller="operation4Question" action="importQuestionFromFile">
        <fieldset class="form">
            <ul>
                <li>
                    <label>所属课程</label>
                    <g:select name="course" from="${cn.edu.cup.cai.Course.all}"
                              noSelection="['':'-Choose a course-']" >课程

                    </g:select>
                </li>
                <li>
                    <label>请选择试卷+答案</label>
                    <input type="file" name="uploadedFile">
                </li>
            </ul>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
