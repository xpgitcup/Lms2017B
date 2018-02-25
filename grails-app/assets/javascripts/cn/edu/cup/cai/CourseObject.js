/*
* Create by 李晓平 2018.02.24
* */

var listCourseObjectDiv;
var paginationListCourseObjectDiv;
var currentPgaeCourseObject;
var totalCourseObject;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4CourseObject() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listCourseObjectDiv = $("#listCourseObjectDiv");
    paginationListCourseObjectDiv = $("#paginationListCourseObjectDiv");

    //获取当前页
    currentPgaeCourseObject = readCookie("currentPgaeCourseObject", 1);
    pageSizeCourseObject = readCookie("pageSizeCourseObject", 5);
    totalCourseObject = countCourseObject();
    //console.info("记录总数：" + totalCourseObject);
    //readSearchOptions();


    //分页
    paginationListCourseObjectDiv.pagination({
        pageSize: pageSizeCourseObject,
        total: totalCourseObject,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listCourseObject(pageNumber, pageSize);
            $.cookie("currentPgaeCourseObject", pageNumber);
        }
    });
    paginationListCourseObjectDiv.pagination("select", currentPgaeCourseObject);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 编辑
* */
function editCourseObject(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4CourseObject/edit", id, "editCourseObjectDiv");
}


/*
* 显示
* */
function showCourseObject(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4CourseObject/show", id, "editCourseObjectDiv");
}

/*
 * 新建
 * */
function createCourseObject(id) {
    operation4CourseDiv.tabs("select", "数据编辑")
    ajaxRun("operation4CourseObject/create", id, "editCourseObjectDiv");
}


/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listCourseObject(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4CourseObject/list" + getParams(pageNumber, pageSize), 0, "listCourseObjectDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4CourseObject/searchCourseObject"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listCourseObjectDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countCourseObject() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4CourseObject/count");
    console.info("CourseObject统计结果：" + total);
    return total;
}
