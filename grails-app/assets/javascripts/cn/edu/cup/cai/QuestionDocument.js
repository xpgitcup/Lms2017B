/*
* Create by 李晓平 2018.02.24
* */

var listQuestionDocumentDiv;
var paginationListQuestionDocumentDiv;
var currentPgaeQuestionDocument;
var totalQuestionDocument;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4QuestionDocument() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listQuestionDocumentDiv = $("#listQuestionDocumentDiv");
    paginationListQuestionDocumentDiv = $("#paginationListQuestionDocumentDiv");

    //获取当前页
    currentPgaeQuestionDocument = readCookie("currentPgaeQuestionDocument", 1);
    pageSizeQuestionDocument = readCookie("pageSizeQuestionDocument", 10);
    totalQuestionDocument = countQuestionDocument();
    //console.info("记录总数：" + totalQuestionDocument);
    //readSearchOptions();


    //分页
    paginationListQuestionDocumentDiv.pagination({
        pageSize: pageSizeQuestionDocument,
        total: totalQuestionDocument,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listQuestionDocument(pageNumber, pageSize);
            $.cookie("currentPgaeQuestionDocument", pageNumber);
        }
    });
    paginationListQuestionDocumentDiv.pagination("select", currentPgaeQuestionDocument);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editQuestionDocument(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionDocument/edit", id, "editQuestionDocumentDiv");
}


/*
* 显示
* */
function showQuestionDocument(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionDocument/show", id, "editQuestionDocumentDiv");
}

/*
 * 新建
 * */
function createQuestionDocument(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4QuestionDocument/create", id, "editQuestionDocumentDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listQuestionDocument(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4QuestionDocument/list" + getParams(pageNumber, pageSize), 0, "listQuestionDocumentDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4QuestionDocument/searchQuestionDocument"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listQuestionDocumentDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countQuestionDocument() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4QuestionDocument/count");
    console.info("QuestionDocument统计结果：" + total);
    return total;
}
