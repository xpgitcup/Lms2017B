/*
* Create by 李晓平 2018.02.24
* */

var listTestPaperDiv;
var paginationListTestPaperDiv;
var currentPgaeTestPaper;
var totalTestPaper;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4TestPaper() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listTestPaperDiv = $("#listTestPaperDiv");
    paginationListTestPaperDiv = $("#paginationListTestPaperDiv");

    //获取当前页
    currentPgaeTestPaper = readCookie("currentPgaeTestPaper", 1);
    pageSizeTestPaper = readCookie("pageSizeTestPaper", 10);
    totalTestPaper = countTestPaper();
    //console.info("记录总数：" + totalTestPaper);
    //readSearchOptions();


    //分页
    paginationListTestPaperDiv.pagination({
        pageSize: pageSizeTestPaper,
        total: totalTestPaper,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listTestPaper(pageNumber, pageSize);
            $.cookie("currentPgaeTestPaper", pageNumber);
        }
    });
    paginationListTestPaperDiv.pagination("select", currentPgaeTestPaper);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editTestPaper(id) {
    operation4TestPaperDiv.tabs("select", "数据编辑")
    ajaxRun("operation4TestPaper/edit", id, "editTestPaperDiv");
}


/*
* 显示
* */
function showTestPaper(id) {
    operation4TestPaperDiv.tabs("select", "数据编辑")
    ajaxRun("operation4TestPaper/show", id, "editTestPaperDiv");
}

/*
 * 新建
 * */
function createTestPaper(id) {
    operation4TestPaperDiv.tabs("select", "数据编辑")
    ajaxRun("operation4TestPaper/create", id, "editTestPaperDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listTestPaper(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4TestPaper/list" + getParams(pageNumber, pageSize), 0, "listTestPaperDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4TestPaper/searchTestPaper"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listTestPaperDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countTestPaper() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4TestPaper/count");
    console.info("TestPaper统计结果：" + total);
    return total;
}
