/*
* 20171126
* 设置标签页
* 分成：浏览、编辑了个标签
*
* 20171123---撤销---没有用
* 1.DataKeyA 的编辑中增加生成界面选项，用于设置
* 2.params中设置commonUIA = ??
* 3.如果没有，提示设置即可
* 4.如果有，设置到session中
*
* */

var operation4CommonUIADiv;
var currentTabCommonUIADiv;

var listCommonUIADiv;
var paginationListCommonUIADiv;

$(function () {

    // 初始化如全局变量
    operation4CommonUIADiv = $("#operation4CommonUIADiv");
    currentTabCommonUIADiv = readCookie("currentTabCommonUIADiv", "数据浏览");

    //设置页面跳转函数
    operation4CommonUIADiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "数据编辑":
                    break;
                default:
                    $.cookie("currentTabCommonUIADiv", title, {path: '/'});
                    break;
            }
        }
    })

    //--针对CommonUIA的页面处理
    listCommonUIADiv = $("#listCommonUIADiv");
    paginationListCommonUIADiv = $("#paginationListCommonUIADiv");

    //获取当前页
    var currentPgaeCommonUIA = readCookie("currentPgaeCommonUIA", 1);
    var pageSizeCommonUIA = readCookie("pageSizeCommonUIA", pageSize);
    var totalCommonUIA = countCommonUIA();

    //分页
    paginationListCommonUIADiv.pagination({
        pageSize: pageSizeCommonUIA,
        total: totalCommonUIA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listCommonUIA(pageNumber, pageSize);
            $.cookie("currentPgaeCommonUIA", pageNumber);
        }
    });
    paginationListCommonUIADiv.pagination("select", currentPgaeCommonUIA);
    //------------------------------------------------------------------------------------------------------------------
});


/*
 * 统计记录总数
 * */
function countCommonUIA() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4CommonUIA/count");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 数据列表
* */
function listCommonUIA(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4CommonUIA/list" + getParams(pageNumber, pageSize), 0, "listCommonUIADiv");
}

/*
* 显示记录信息
* */
function showCommonUIA(id) {
    operation4CommonUIADiv.tabs("select","数据编辑");
    ajaxRun("operation4CommonUIA/show/" + id, 0, "showCommonUIADiv");
}

/*
* 编辑记录
* */
function editCommonUIA(id) {
    operation4CommonUIADiv.tabs("select","数据编辑");
    ajaxRun("operation4CommonUIA/edit/" + id, 0, "editCommonUIADiv");
}