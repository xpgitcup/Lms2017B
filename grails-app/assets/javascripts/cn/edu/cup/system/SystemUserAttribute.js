
var operation4SystemUserAttributeDiv;
var currentTabSystemUserAttributeDiv;

$(function () {
    console.info("用户权限维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4SystemUserAttributeDiv = $("#operation4SystemUserAttributeDiv");
    currentTabSystemUserAttributeDiv = readCookie("currentTabSystemUserAttributeDiv", "用户列表");

    //设置页面跳转函数
    operation4SystemUserAttributeDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "权限编辑":
                    break;
                default:
                    $.cookie("currentTabSystemUserAttributeDiv", title, {path: '/'});
                    break;
            }
        }
    })

    tabAndPage4SystemUser();
    //tabAndPage4DataKeyA();
    //tabAndPage4DataItemA();
    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4SystemUserAttributeDiv.tabs("select", currentTabSystemUserAttributeDiv);

    //var currentTab = checkSessionDataDictionary(currentTabSystemUserAttributeDiv);
    //operation4SystemUserAttributeDiv.tabs("select", currentTab);

});

