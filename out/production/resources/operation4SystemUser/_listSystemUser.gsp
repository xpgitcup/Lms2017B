<table>
    <thead>
    <th>用户名</th>
    <th>用户属性</th>
    <th>编辑</th>
    </thead>
    <tbody>
    <g:each in="${systemUserList}" status="i" var="item">
        <tr>
            <td>
                <a href="javascript: showSystemUser(${item.id})">
                    ${item.userName}
                </a>
            </td>
            <td>${item.roleAttribute}</td>
            <td>
                <a href="javascript: editSystemUserRoleAttribute(${item.id})">编辑</a>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
