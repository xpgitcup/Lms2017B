<%@ page import="jxl.write.DateTime" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-dataItemA" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataItemA}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataItemA}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.dataItemA}" method="POST"-->
    <g:uploadForm controller="operation4DataItemA" action="saveDataItemA">
        <fieldset class="form">
            <!--f:all bean="dataItemA"/-->
            <table>
                <f:with bean="dataItemA">
                    <tr>
                        <td>
                            <!--f:field property="dataKeyA"/-->
                            ${dataItemA.dataKeyA}
                            <g:hiddenField name="dataKeyA.id" value="${dataItemA.dataKeyA.id}"/>
                        </td>
                        <td>
                            <!--f:field property="dataValue"/-->
                        </td>
                    </tr>
                </f:with>
            </table>
            <table>
                <g:each in="${dataItemA.subDataItems}" var="subItem" status="i">
                    <tr>
                        <td>
                            ${dataItemA.subDataItems[i].dataKeyA.dataTag}
                            <g:hiddenField name="subDataItems[${i}].dataKeyA.id"
                                           value="${dataItemA.subDataItems[i].dataKeyA.id}"/>
                            <g:hiddenField name="subDataItems[${i}].upDataItem.id"
                                           value="${dataItemA.id}"></g:hiddenField>
                        </td>
                    <!--针对普通类型的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyNormal}">
                            <td>
                                <ul>
                                    <li>
                                        ${subItem.dataKeyA.appendParameter}
                                    </li>
                                    <li>
                                        <g:textField name="subDataItems[${i}].dataValue" id="dataValue_${i}"/>
                                    </li>
                                </ul>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对普通类型的多行文本-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyText}">
                            <td>
                                <ul>
                                    <li>
                                        ${subItem.dataKeyA.appendParameter}
                                    </li>
                                    <li>
                                        <g:textArea name="subDataItems[${i}].dataValue" id="dataValue_${i}"/>
                                    </li>
                                </ul>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对时间日期类型的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyDate}">
                            <td>
                                <!--g:textField name="subDataItems[${i}].dataValue" id=""/-->
                                <!--g:formatDate format="yyyy-MM-dd" date="${date}"/-->
                                <!--g:datePicker name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                                  dateFmt="yyyy-MM-dd"
                                                  value="${new Date()}"/-->
                                <!--input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}" value="${new java.util.Date()}" class="datePicker"/-->
                                <input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}" class="datePicker"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对时间日期类型的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyDateTime}">
                            <td>
                                <!--g:textField name="subDataItems[${i}].dataValue" id=""/-->
                                <!--g:formatDate format="yyyy-MM-dd" date="${date}"/-->
                                <!--g:datePicker name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                                  dateFmt="yyyy-MM-dd"
                                                  value="${new Date()}"/-->
                                <!--input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}" value="${new java.util.Date()}" class="dateTimePicker"/-->
                                <input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}" class="dateTimePicker"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对枚举类型的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyEnum}">
                            <td>
                                <!--g:textField name="" id="dataValue_${i}"/-->
                                <g:select name="subDataItems[${i}].dataValue" from="${subItem.dataKeyA?.enumItems()}"
                                          noSelection="['': '-Choose-']"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对文件的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyFile}">
                            <td>
                                <g:textField name="subDataItems[${i}].dataValue" id="file_${i}"/>
                            </td>
                            <td>
                                <g:hiddenField name="uploadFilePath" value="${subItem.dataKeyA.appendParameter}"/>
                                <g:hiddenField name="uploadFileDataKeyId" value="${subItem.dataKeyA.id}"/>
                                <g:hiddenField name="uploadFileIndex" value="${i}"/>
                                <input type="file" name="uploadFile" id="input_${i}"
                                       onchange="updateUploadFileName(${i})"/>
                            </td>
                        </g:if>
                    <!--针对引用类型的-->
                        <g:if test="${subItem.dataKeyA.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyRef}">
                            <td>
                                <!--g:textField name="subDataItems[${i}].dataValue" id="dataValue_${i}"/-->
                                <g:select name="subDataItems[${i}].dataValue"
                                          from="${cn.edu.cup.dictionary.DataItemA.findAllByDataKeyA(cn.edu.cup.dictionary.DataKeyA.get(Integer.parseInt(subItem.dataKeyA.appendParameter)))}"
                                          optionKey="id"
                                          noSelection="${['null': 'Select One...']}"/>
                            </td>
                            <td>
                            </td>
                        </g:if>

                    </tr>
                </g:each>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
<!--/g:form-->
</div>
<g:javascript>
    $("input.datePicker").datetimepicker({
        timepicker: false,
        format: 'Y/m/d',
        mask: true
        //defaultDate: true
    });

    $("input.dateTimePicker").datetimepicker({
        format: 'Y/m/d H:i',
        mask: true,
        defaultDate: new Date()
    });
</g:javascript>

</body>
</html>
