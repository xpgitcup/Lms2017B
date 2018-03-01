/*
* Create by 李晓平 2018.02.24
* */

var listQuestionTypeDiv;
var paginationListQuestionTypeDiv;
var currentPgaeQuestionType;
var totalQuestionType;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4QuestionType() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listQuestionTypeDiv = $("#listQuestionTypeDiv");
    paginationListQuestionTypeDiv = $("#paginationListQuestionTypeDiv");

    //获取当前页
    currentPgaeQuestionType = readCookie("currentPgaeQuestionType", 1);
    pageSizeQuestionType = readCookie("pageSizeQuestionType", 10);
    totalQuestionType = countQuestionType();
    //console.info("记录总数：" + totalQuestionType);
    //readSearchOptions();


    //分页
    paginationListQuestionTypeDiv.pagination({
        pageSize: pageSizeQuestionType,
        total: totalQuestionType,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listQuestionType(pageNumber, pageSize);
            $.cookie("currentPgaeQuestionType", pageNumber);
        }
    });
    paginationListQuestionTypeDiv.pagination("select", currentPgaeQuestionType);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editQuestionType(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionType/edit", id, "editQuestionTypeDiv");
}


/*
* 显示
* */
function showQuestionType(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionType/show", id, "editQuestionTypeDiv");
}

/*
 * 新建
 * */
function createQuestionType(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionType/create", id, "editQuestionTypeDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listQuestionType(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4QuestionType/list" + getParams(pageNumber, pageSize), 0, "listQuestionTypeDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4QuestionType/searchQuestionType"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listQuestionTypeDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countQuestionType() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4QuestionType/count");
    console.info("QuestionType统计结果：" + total);
    return total;
}
