var operation4UserLibraryDiv;
var currentTabUserLibraryDiv;

var listUserLibraryClassifyDiv;
var paginationListUserLibraryClassifyDiv;

var listUserLibraryDiv;
var paginationListUserLibraryDiv;

var listUserClassDiv;
var paginationListUserClassDiv;

$(function () {
    console.info($("title").text() + "加载成功...");

    //总体设置----------------------------------------------------------------------------------------------------------
    //获取当前tabs的变量
    operation4UserLibraryDiv = $("#operation4UserLibraryDiv");
    //读取上次所停留的页面
    currentTabUserLibraryDiv = readCookie("currentTabUserLibraryDiv", "用户类库分类");
    console.info("上次停留标签..." + currentTabUserLibraryDiv);

    operation4UserLibraryDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            //if (title !== "编辑") {
            $.cookie("currentTabUserLibraryDiv", title, {path: '/'});
            //}
        }
    })

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listUserLibraryClassifyDiv = $("#listUserLibraryClassifyDiv");
    paginationListUserLibraryClassifyDiv = $("#paginationListUserLibraryClassifyDiv");

    //获取当前页
    var currentPgaeUserLibraryClassify = readCookie("currentPgaeUserLibraryClassify", 1);
    var pageSizeUserLibraryClassify = readCookie("pageSizeUserLibraryClassify", pageSize);
    var totalUserLibraryClassify = countUserLibraryClassify();
    //console.info("记录总数：" + totalPhysicalQuantity);


    //分页
    paginationListUserLibraryClassifyDiv.pagination({
        pageSize: pageSizeUserLibraryClassify,
        total: totalUserLibraryClassify,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listUserLibraryClassify(pageNumber, pageSize);
            $.cookie("currentPgaeUserLibraryClassify", pageNumber);
        }
    });
    paginationListUserLibraryClassifyDiv.pagination("select", currentPgaeUserLibraryClassify);
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listUserLibraryDiv = $("#listUserLibraryDiv");
    paginationListUserLibraryDiv = $("#paginationListUserLibraryDiv");

    //获取当前页
    var currentPgaeUserLibrary = readCookie("currentPgaeUserLibrary", 1);
    var pageSizeUserLibrary = readCookie("pageSizeUserLibrary", pageSize);
    var totalUserLibrary = countUserLibrary();
    //console.info("记录总数：" + totalPhysicalQuantity);


    //分页
    paginationListUserLibraryDiv.pagination({
        pageSize: pageSizeUserLibrary,
        total: totalUserLibrary,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listUserLibrary(pageNumber, pageSize);
            $.cookie("currentPgaeUserLibrary", pageNumber);
        }
    });
    paginationListUserLibraryDiv.pagination("select", currentPgaeUserLibrary);
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listUserClassDiv = $("#listUserClassDiv");
    paginationListUserClassDiv = $("#paginationListUserClassDiv");

    //获取当前页
    var currentPgaeUserClass = readCookie("currentPgaeUserClass", 1);
    var pageSizeUserClass = readCookie("pageSizeUserClass", pageSize);
    var totalUserClass = countUserClass();
    //console.info("记录总数：" + totalPhysicalQuantity);


    //分页
    paginationListUserClassDiv.pagination({
        pageSize: pageSizeUserClass,
        total: totalUserClass,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listUserClass(pageNumber, pageSize);
            $.cookie("currentPgaeUserClass", pageNumber);
        }
    });
    paginationListUserClassDiv.pagination("select", currentPgaeUserClass);
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    operation4UserLibraryDiv.tabs("select", currentTabUserLibraryDiv);
})

/*
 * 统计记录总数
 * */
function countUserLibraryClassify() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4UserLibrary/countUserLibraryClassify");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
 * 列表显示当前所有对象
 * */
function listUserLibraryClassify(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4UserLibrary/listUserLibraryClassify" + getParams(pageNumber, pageSize), 0, "listUserLibraryClassifyDiv");
}

/*
 * 统计记录总数
 * */
function countUserLibrary() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4UserLibrary/countUserLibrary");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
 * 列表显示当前所有对象
 * */
function listUserLibrary(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4UserLibrary/listUserLibrary" + getParams(pageNumber, pageSize), 0, "listUserLibraryDiv");
}

/*
 * 统计记录总数
 * */
function countUserClass() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4UserLibrary/countUserClass");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
 * 列表显示当前所有对象
 * */
function listUserClass(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4UserLibrary/listUserClass" + getParams(pageNumber, pageSize), 0, "listUserClassDiv");
}
