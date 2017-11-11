var currentFilter4Data;
var options4Data;

var operation4DataADiv;
var currentTabDataADiv;

var listDataDictionaryDiv;
var paginationListDataDictionaryDiv;

$(function () {
    console.info("数据字典A维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4DataADiv = $("#operation4DataADiv");
    currentTabDataADiv = readCookie("currentTabDataADiv", "模型列表");
    options4Data = $("#options4Data");

    //------------------------------------------------------------------------------------------------------------------
    //当前过滤条件
    currentFilter4Data = readCookie("currentFilter4Data", "无")

    //设置页面跳转函数
    operation4DataADiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "数据编辑":
                case "数据表头":
                    break;
                default:
                    $.cookie("currentTabDataADiv", title, {path: '/'});
                    break;
            }
        }
    })

    tabAndPage4DataDictionary4Data();
    //tabAndPage4DataKeyA();
    //tabAndPage4DataItemA();
    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4DataADiv.tabs("select", currentTabDataADiv);

    //------------------------------------------------------------------------------------------------------------------
    //var currentTab = checkSessionDataDictionary(currentTabDataADiv);
    //operation4DataADiv.tabs("select", currentTab);

    //------------------------------------------------------------------------------------------------------------------
    setupOptions4Data();
});


function setupOptions4Data() {
    var li = document.createElement("li");
    //设置 li 属性，如 id
    //li.setAttribute("id", "newli");
    li.innerHTML = "当前过滤条件：" + currentFilter4Data;
    options4Data.append(li);
}