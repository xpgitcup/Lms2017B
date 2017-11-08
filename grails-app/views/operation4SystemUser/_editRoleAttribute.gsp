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
<g:form>
    <fieldset class="form">
        <g:each in="${roles}" var="item" status="i">
            <g:checkBox name="${item.key}" value="${item.key}">${item.key}</g:checkBox>
        </g:each>
    </fieldset>
</g:form>
</body>
</html>
