/*
* Create by 李晓平 2018.02.24
* */

var listKnowledgePointDiv;
var paginationListKnowledgePointDiv;
var currentPgaeKnowledgePoint;
var totalKnowledgePoint;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4KnowledgePoint() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listKnowledgePointDiv = $("#listKnowledgePointDiv");
    paginationListKnowledgePointDiv = $("#paginationListKnowledgePointDiv");

    //获取当前页
    currentPgaeKnowledgePoint = readCookie("currentPgaeKnowledgePoint", 1);
    pageSizeKnowledgePoint = readCookie("pageSizeKnowledgePoint", 10);
    totalKnowledgePoint = countKnowledgePoint();
    //console.info("记录总数：" + totalKnowledgePoint);
    //readSearchOptions();


    //分页
    paginationListKnowledgePointDiv.pagination({
        pageSize: pageSizeKnowledgePoint,
        total: totalKnowledgePoint,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listKnowledgePoint(pageNumber, pageSize);
            $.cookie("currentPgaeKnowledgePoint", pageNumber);
        }
    });
    paginationListKnowledgePointDiv.pagination("select", currentPgaeKnowledgePoint);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editKnowledgePoint(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4KnowledgePoint/edit", id, "editKnowledgePointDiv");
}


/*
* 显示
* */
function showKnowledgePoint(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4KnowledgePoint/show", id, "editKnowledgePointDiv");
}

/*
 * 新建
 * */
function createKnowledgePoint(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4KnowledgePoint/create", id, "editKnowledgePointDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listKnowledgePoint(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4KnowledgePoint/list" + getParams(pageNumber, pageSize), 0, "listKnowledgePointDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4KnowledgePoint/searchKnowledgePoint"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listKnowledgePointDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countKnowledgePoint() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4KnowledgePoint/count");
    console.info("KnowledgePoint统计结果：" + total);
    return total;
}
