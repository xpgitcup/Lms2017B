
var operation4SystemUserDiv;
var currentTabSystemUserDiv;

$(function () {
    console.info("用户权限维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4SystemUserDiv = $("#operation4SystemUserDiv");
    currentTabSystemUserDiv = readCookie("currentTabSystemUserDiv", "用户管理");

    //设置页面跳转函数
    operation4SystemUserDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "权限编辑":
                    break;
                default:
                    $.cookie("currentTabSystemUserDiv", title, {path: '/'});
                    break;
            }
        }
    })

    tabAndPage4SystemUesr();
    //tabAndPage4DataKeyA();
    //tabAndPage4DataItemA();
    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4SystemUserDiv.tabs("select", currentTabSystemUserDiv);

    //var currentTab = checkSessionDataDictionary(currentTabSystemUserDiv);
    //operation4SystemUserDiv.tabs("select", currentTab);

});

/*
* 编辑用户权限
* */
function editSystemUserRoleAttribute(id) {
    operation4SystemUserDiv.tabs("select", "权限编辑");
    ajaxRunWith("operation4SystemUser/editRoleAttribute", id, "editRoleAttribute", "editRoleAttributeDiv");
}

