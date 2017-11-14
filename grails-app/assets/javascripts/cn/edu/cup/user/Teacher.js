/*
* 数据显示的过滤条件：无过滤、项目、人员
*
* 无过滤：----- 显示上一次的标签
*   数据字典：显示所有
*   模型列表：显示所有
*   数据显示：无
*
* 数据字典：----模型列表
*   数据字典：显示所有
*   模型列表：显示当前数据字典下的模型
*   数据显示：无
*
*  关键字： ----数据显示
*   数据字典：显示所有
*   模型列表：显示当前关键字所属的数据字典的所有模型
*   数据显示：当前关键字下的所有数据
*
*  数据项： ----数据显示
*   数据字典：显示所有
*   模型列表：当前数据项--所属关键字--所属数据字典下的所有模型
*   数据显示：当前数据项对应的所有数据
* */

var currentFilterKey4Teacher;
var currentFilterValue4Teacher;
var currentFilterAppendValue4Teacher;
var options4Teacher;

var tabs4TeacherDiv;


$(function() {

    console.info("数据字典A维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    tabs4TeacherDiv = $("#tabs4TeacherDiv");
    currentTabTeacherADiv = readCookie("currentTabTeacherADiv", "模型列表");
    options4Teacher = $("#options4Teacher");

    //------------------------------------------------------------------------------------------------------------------
    //当前过滤条件
    loadFilter4Teacher();

    //设置页面跳转函数
    tabs4TeacherDiv.tabs({
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

    tabAndPage4Project4Teacher();
    //tabAndPage4DataKeyA();
    //tabAndPage4DataItemA();
    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    switch (currentFilterKey4Teacher) {
        case "无":
            currentTabDataADiv = "项目管理";
            tabs4TeacherDiv.tabs("select", currentTabDataADiv);
            break;
        case "人员情况":
            currentTabDataADiv = "人员情况";
            tabs4TeacherDiv.tabs("select", currentTabDataADiv);
            break;
        case "项目进度":
            currentTabDataADiv = "项目进度情况";
            tabs4TeacherDiv.tabs("select", currentTabDataADiv);
            break;
        case "人员进度":
            currentTabDataADiv = "项目人员进度";
            tabs4TeacherDiv.tabs("select", currentTabDataADiv);
            break;
        default:
            tabs4TeacherDiv.tabs("select", currentTabDataADiv);
    }

    //------------------------------------------------------------------------------------------------------------------
    //var currentTab = checkSessionDataDictionary(currentTabDataADiv);
    //operation4DataADiv.tabs("select", currentTab);

    //------------------------------------------------------------------------------------------------------------------
    setupOptions4Teacher();

});


function setupOptions4Teacher() {
    var li;
    //设置 li 属性，如 id
    //li.setAttribute("class", "a");
    if (currentFilterKey4Teacher == "无") {
    } else {
    }
    switch (currentFilterKey4Teacher) {
        case "数据字典":
            li = document.createElement("li");
            li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Teacher
                + "|" + currentFilterValue4Teacher
                + "=" + currentFilterAppendValue4Teacher + "</a>";
            options4Teacher.append(li);
            break;
        case "关键字":
            li = document.createElement("li");
            li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Teacher
                + "|" + currentFilterValue4Teacher
                + "=" + currentFilterAppendValue4Teacher + "</a>";
            options4Teacher.append(li);
            break;
        case "数据项":
            li = document.createElement("li");
            li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Teacher
                + "|" + currentFilterValue4Teacher
                + "=" + currentFilterAppendValue4Teacher + "</a>";
            options4Teacher.append(li);
            break;
        default:
            li = document.createElement("li");
            li.innerHTML = "<a>当前过滤条件：" + currentFilterKey4Teacher + "</a>";
            options4Teacher.append(li);
    }
}

function loadFilter4Teacher() {
    currentFilterKey4Teacher = readCookie("currentFilterKey4Teacher", "无");
    if (currentFilterKey4Teacher != "无") {
        currentFilterValue4Teacher = readCookie("currentFilterValue4Teacher", "0");
        currentFilterAppendValue4Teacher = readCookie("currentFilterAppendValue4Teacher", "0");
    } else {
        currentFilterValue4Teacher = 0;
        currentFilterAppendValue4Teacher = 0;
    }
}

function saveFilter4Teacher() {
    $.cookie("currentFilterKey4Teacher", currentFilterKey4Teacher);
    $.cookie("currentFilterValue4Teacher", currentFilterValue4Teacher);
    $.cookie("currentFilterAppendValue4Teacher", currentFilterAppendValue4Teacher);
}

function clearFilter4Teacher() {
    currentFilterKey4Teacher = "无";
    saveFilter4Teacher();
    location.reload();
}