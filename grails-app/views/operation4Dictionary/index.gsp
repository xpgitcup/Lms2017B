<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
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

<!--为了处理cookie工作不正常的问题-->
    <g:javascript>
    function checkSessionDataDictionary(currentTab) {
        console.info("处理前的：" + currentTab);
        var tab = currentTab;
        <% if (session.getAttribute("currentDataDictionary") != null) { %>
        tab = "模型维护";
        <% } %>
        console.info("处理后的：" + tab);
        return tab;
    }
    </g:javascript>

    <g:set var="entityName" value="Dictionary"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataDictionary.js"/>
    <asset:javascript src="cn/edu/cup/dictionary/DataKeyA.js"/>
</head>

<body>

<div class="nav">
    <g:if test="${session.currentDataDictionary}">
        <ul>
            <li>
                <a href="#">
                    当前字典：${session.currentDataDictionary}
                </a>
            </li>
            <li>
                <a class="create" href="operation4DataDictionary/clearCurrentDataDictionary">清除当前选择</a>
            </li>
        </ul>
    </g:if>
</div>

<div id="operation4DictionaryDiv" class="easyui-tabs">
    <div title="数据字典">
        <div class="nav" role="navigation">
            <ul>
                <li><a class="create" href="javascript: createDataDictionary(0)">新建数据字典</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="row-fluid">
                <div class="col-md-6 column">
                    <div class="panel panel-default">
                        <div class="easyui-panel">
                            <div id="listDataDictionaryDiv"></div>
                            <div id="paginationListDataDictionaryDiv"></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 column">
                    <div class="panel panel-default">
                        <div>
                            <g:if test="${flash.message}">
                                <div class="message" role="status">${flash.message}</div>
                            </g:if>
                        </div>
                        <div class="easyui-panel">
                            <div id="showDataDictionaryDiv"></div>
                            <div id="editDataDictionaryDiv"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div title="模型维护">
        <div class="container">
            <div class="col-md-6">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="create" href="javascript: createDataKeyA(0)">新建：根/模型节点</a></li>
                        <li><a id="createDataKeyA" class="create" href="#">新建子节点</a></li>
                    </ul>
                </div>

                <div class="panel panel-default">
                    <div class="easyui-panel">
                        <div id="displayTreeDataKeyADiv" class="easyui-tree"></div>
                        <div id="paginationDataKeyADiv" class="easyui-pagination"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                </div>
                <div class="easyui-panel">
                    <div id="showDataKeyADiv"></div>
                </div>
            </div>
        </div>
    </div>

    <div title="模型编辑">
        <div class="easyui-panel">
            <div id="editDataKeyADiv"></div>
        </div>
    </div>
</div>
</body>
</html>
