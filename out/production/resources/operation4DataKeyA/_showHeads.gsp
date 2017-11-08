数据关键字的标题
<table>
    <tr>
        <g:each in="${heads}" var="item" status="j">
            <td>${item[0]}</td>
        </g:each>
    </tr>
    <tr>
        <g:each in="${heads}" var="item" status="j">
            <td>${item[1]}</td>
        </g:each>
    </tr>
    <tr>
        <g:each in="${heads}" var="item" status="j">
            <td>${item[2]}</td>
        </g:each>
    </tr>
</table>