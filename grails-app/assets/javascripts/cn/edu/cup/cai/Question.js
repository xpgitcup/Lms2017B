/*
* Create by 李晓平 2018.02.24
* */

var listQuestionDiv;
var paginationListQuestionDiv;
var currentPgaeQuestion;
var totalQuestion;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4Question() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listQuestionDiv = $("#listQuestionDiv");
    paginationListQuestionDiv = $("#paginationListQuestionDiv");

    //获取当前页
    currentPgaeQuestion = readCookie("currentPgaeQuestion", 1);
    pageSizeQuestion = readCookie("pageSizeQuestion", 10);
    totalQuestion = countQuestion();
    //console.info("记录总数：" + totalQuestion);
    //readSearchOptions();


    //分页
    paginationListQuestionDiv.pagination({
        pageSize: pageSizeQuestion,
        total: totalQuestion,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listQuestion(pageNumber, pageSize);
            $.cookie("currentPgaeQuestion", pageNumber);
        }
    });
    paginationListQuestionDiv.pagination("select", currentPgaeQuestion);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editQuestion(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4Question/edit", id, "editQuestionDiv");
}


/*
* 显示
* */
function showQuestion(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4Question/show", id, "editQuestionDiv");
}

/*
 * 新建
 * */
function createQuestion(id) {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4Question/create", id, "editQuestionDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listQuestion(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4Question/list" + getParams(pageNumber, pageSize), 0, "listQuestionDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4Question/searchQuestion"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listQuestionDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countQuestion() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4Question/count");
    console.info("Question统计结果：" + total);
    return total;
}
