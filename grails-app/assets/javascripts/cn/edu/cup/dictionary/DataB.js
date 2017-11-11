var currentFilterKey4Data;
var currentFilterValue4Data;
var currentFilterAppendValue4Data;
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
    loadFilter4Data();

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
    tabAndPage4DataKeyA();
    tabAndPage4DataItemA();
    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    switch (currentFilterKey4Data) {
        case "数据字典":
            currentTabDataADiv = "模型列表";
            operation4DataADiv.tabs("select", currentTabDataADiv);
            break;
        case "关键字":
            currentTabDataADiv = "数据显示";
            operation4DataADiv.tabs("select", currentTabDataADiv);
            break;
        default:
            operation4DataADiv.tabs("select", currentTabDataADiv);
    }

    //------------------------------------------------------------------------------------------------------------------
    //var currentTab = checkSessionDataDictionary(currentTabDataADiv);
    //operation4DataADiv.tabs("select", currentTab);

    //------------------------------------------------------------------------------------------------------------------
    setupOptions4Data();
});


function setupOptions4Data() {
    var li = document.createElement("li");
    //设置 li 属性，如 id
    //li.setAttribute("class", "a");
    if (currentFilterKey4Data == "无") {
        li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Data + "</a>";
    } else {
        li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Data
            + "|" + currentFilterValue4Data
            + "=" + currentFilterAppendValue4Data + "</a>";
    }
    options4Data.append(li);
}

function loadFilter4Data() {
    currentFilterKey4Data = readCookie("currentFilterKey4Data", "无");
    if (currentFilterKey4Data != "无") {
        currentFilterValue4Data = readCookie("currentFilterValue4Data", "0");
        currentFilterAppendValue4Data = readCookie("currentFilterAppendValue4Data", "0");
    } else {
        currentFilterValue4Data = 0;
        currentFilterAppendValue4Data = 0;
    }
}

function saveFilter4Data() {
    $.cookie("currentFilterKey4Data", currentFilterKey4Data);
    $.cookie("currentFilterValue4Data", currentFilterValue4Data);
    $.cookie("currentFilterAppendValue4Data", currentFilterAppendValue4Data);
}

function clearFilter4Data() {
    currentFilterKey4Data = "无";
    saveFilter4Data();
    location.reload();
}