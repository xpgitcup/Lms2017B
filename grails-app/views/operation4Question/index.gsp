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
    <title>Sample title</title>
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
    </ul>
</div>

<div id="operation4QuestionDiv" class="easyui-tabs">
    <div title="课程列表">
        <div class="nav">
            <ul>
                <li>
                    <a class="create" href="javascript: createQuestion()">新增课程</a>
                </li>
            </ul>
        </div>

        <div id="listQuestionDiv"></div>

        <div id="paginationListQuestionDiv" class="easyui-pagination"></div>
    </div>

    <div title="课程目标列表">
        <div class="nav">
            <ul>
                <li>
                    <a class="create" href="javascript: createQuestionObject()">新增课程目标</a>
                </li>
            </ul>
        </div>

        <div id="listQuestionObjectDiv"></div>

        <div id="paginationListQuestionObjectDiv" class="easyui-pagination"></div>
    </div>

    <div title="知识点列表"></div>

    <div title="数据编辑">
        <div id="editQuestionDiv"></div>

        <div id="editQuestionObjectDiv"></div>
    </div>
</div>

</body>
</html>
