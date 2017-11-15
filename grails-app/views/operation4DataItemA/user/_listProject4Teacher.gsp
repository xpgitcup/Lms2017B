<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div id="list-dataItemA" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="nav">
        <table>
            <thead>
            <tr>
                <th>
                    <a href="javascript: createProject4Teacher()">新项目</a>
                </th>
                <th>
                    <g:form>
                        <span>
                            <label>请选择项目</label>
                            <g:if test="${dataItemAList?.size() > 0}">
                                <select id="searchKey4DataItem" name="searchKey4DataItem">
                                    <g:each in="${dataItemAList[0].subDataItems}" var="item">
                                        <option value="${item.dataKeyA.id}">${item.dataKeyA.dataTag}</option>
                                    </g:each>
                                </select>
                            </g:if>
                            <g:textField name="searchValue4DataItem" id="searchValue4DataItem"></g:textField>
                            <input type="button" value="搜索" onclick="search4DataItem()"/>
                        </span>
                    </g:form>
                </th>
            </tr>
            </thead>
        </table>
    </div>


    <!--f:table collection="${dataItemAList}" /-->
    <table>
        <thead>
        <th>项目名称</th>
        <th>项目性质</th>
        <th>操作</th>
        </thead>
        <tbody>
        <g:each var="item" status="i" in="${dataItemAList}">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <g:each in="${item.subDataItems}" var="e" status="j">
                    <td>${e.dataValue}</td>
                </g:each>
                <td>
                    <a href="javascript: staffManager(${item.id})">人员管理</a>
                </td>
                <td>
                    <a href="javascript: processManager(${item.id})">进度查询</a>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

</body>
</html>