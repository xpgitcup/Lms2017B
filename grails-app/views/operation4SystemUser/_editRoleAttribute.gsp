<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户权限编辑</title>
</head>

<body>
<h1>${systemUser} ${roles}</h1>
<g:form controller="operation4SystemUser" action="updateRoleAttribute">
    <fieldset class="form">
        <g:hiddenField name="id" value="${systemUser.id}"></g:hiddenField>
        <ul>
            <g:each in="${roles}" var="item" status="i">
                <li>
                    <g:if test="${item.value}">
                        <input type="checkbox" name="roleAttribute" value="${item.key}" checked="${item.value}">${item.key}
                    </g:if>
                    <g:else>
                        <input type="checkbox" name="roleAttribute" value="${item.key}">${item.key}
                    </g:else>
                </li>
            </g:each>
        </ul>
        <input type="submit" value="OK">
    </fieldset>
</g:form>
</body>
</html>
