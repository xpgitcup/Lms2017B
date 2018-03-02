<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
    <title>课程维护</title>

    <g:set var="entityName" value="Operation4QuestionType"/>
    <asset:javascript src="cn/edu/cup/cai/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/cai/Operation4QuestionTypeTabs.js"/>
    <asset:javascript src="cn/edu/cup/cai/QuestionType.js"/>
    <asset:javascript src="cn/edu/cup/cai/Question.js"/>
    <asset:javascript src="cn/edu/cup/cai/QuestionDocument.js"/>
    <asset:javascript src="cn/edu/cup/cai/QuestionByUser.js"/>

</head>

<body>

<div class="nav">
    <ul>
        <li>
            <a>当前试题类型</a>
        </li>
        <li>
            <a>选择试题类型->编辑维护试题</a>
        </li>
        <li>
            <a href="javascript: prepareToImportQuestion()">导入试题</a>
        </li>
    </ul>
</div>

<div id="operation4QuestionTypeDiv" class="easyui-tabs">
    <div title="试题类型列表">
        <div class="nav">
            <ul>
                <li>
                    <a class="create" href="javascript: createQuestionType()">新增试题类型</a>
                </li>
            </ul>
        </div>

        <div id="listQuestionTypeDiv"></div>
        <div id="paginationListQuestionTypeDiv" class="easyui-pagination"></div>
    </div>

    <div title="试题列表">
        <div class="nav">
            <ul>
                <li>
                    <a class="create" href="javascript: createQuestion()">新增试题</a>
                </li>
            </ul>
        </div>

        <div id="listQuestionDiv"></div>
        <div id="paginationListQuestionDiv" class="easyui-pagination"></div>
    </div>

    <div title="试题文档">
        <div class="nav">
            <ul>
                <li>
                    <a class="create" href="javascript: createQuestionDocument()">新增试题</a>
                </li>
            </ul>
        </div>

        <div id="listQuestionDocumentDiv"></div>
        <div id="paginationListQuestionDocumentDiv" class="easyui-pagination"></div>
    </div>

    <div title="数据编辑">
        <div id="editQuestionTypeDiv"></div>
        <div id="editQuestionDiv"></div>
        <div id="editQuestionDocumentDiv"></div>
    </div>
</div>
</body>
</html>
