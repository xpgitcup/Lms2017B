<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-dataItemA" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--f:table collection="${dataItemAList}" /-->
            <div>
                <g:form>
                    <span>
                        <label>请选择项目</label>
                        <g:if test="${dataItemAList.size()>0}">
                            <select id="search4DataItem" name="search4DataItem">
                                <g:each in="${dataItemAList[0].dataKeyA.subDataKeys}" var="item">
                                    <option value="${item.dataTag}">${item.dataTag}</option>
                                </g:each>
                            </select>
                            <g:textField name="searchValue"></g:textField>
                        </g:if>
                        <label></label>
                    </span>
                </g:form>
            </div>
            <table>
                <thead>
                <th>数据项</th>
                <th>操作</th>
                </thead>
                <tbody>
                <g:each var="item" status="i" in="${dataItemAList}">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${item.dataKeyA.dataTag}</td>
                        <td>
                            <a href="operation4DataItemA/exportToExcelFile/${item.id}">导出</a>
                        </td>
                    </tr>
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <g:each in="${item.subDataItems}" var="e" status="j">
                            <td>${e.dataKeyA.dataTag}</td>
                        </g:each>
                    </tr>
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <g:each in="${item.subDataItems}" var="e" status="j">
                            <td>${e.dataValue}</td>
                        </g:each>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>