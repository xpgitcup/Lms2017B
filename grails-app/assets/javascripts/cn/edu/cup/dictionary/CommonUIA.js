/*
* 20171123
* 1.DataKeyA 的编辑中增加生成界面选项，用于设置
* 2.params中设置commonUIA = ??
* 3.如果没有，提示设置即可
* 4.如果有，设置到session中
*
* */


var operation4CommonUIADiv;
var listCommonUIADiv;
var paginationListCommonUIADiv;

$(function () {

    // 初始化如全局变量
    operation4CommonUIADiv = $("#operation4CommonUIADiv");
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
