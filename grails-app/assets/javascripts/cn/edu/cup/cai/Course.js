/*
* Create by 李晓平 2018.02.24
* */

var listCourseDiv;
var paginationListCourseDiv;
var currentPgaeCourse;
var totalCourse;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4Course() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listCourseDiv = $("#listCourseDiv");
    paginationListCourseDiv = $("#paginationListCourseDiv");

    //获取当前页
    currentPgaeCourse = readCookie("currentPgaeCourse", 1);
    pageSizeCourse = readCookie("pageSizeCourse", 5);
    totalCourse = countCourse();
    //console.info("记录总数：" + totalCourse);
    //readSearchOptions();


    //分页
    paginationListCourseDiv.pagination({
        pageSize: pageSizeCourse,
        total: totalCourse,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listCourse(pageNumber, pageSize);
            $.cookie("currentPgaeCourse", pageNumber);
        }
    });
    paginationListCourseDiv.pagination("select", currentPgaeCourse);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 列表
* */
/*
* 列表显示当前所有对象
* */
function listCourse(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    //readSearchOptions();
    console.info("正常列表...");
    ajaxRun("operation4Course/list" + getParams(pageNumber, pageSize), 0, "listCourseDiv");
    /*
    if (!searchValue4DataItem) {
    } else {
        console.info("搜索结果...");
        ajaxRun("operation4Course/searchCourse"
            + getParams(pageNumber, pageSize)
            + "&dataKey=" + searchKey4DataItem
            + "&searchValue=" + searchValue4DataItem,
            0, "listCourseDiv");
    }
    */
}


/*
 * 统计记录总数
 * */
function countCourse() {
    //readSearchOptions();
    //console.info("开始统计..." + searchKey4DataItem + "=" + searchValue4DataItem)
    var total;
    console.info("正常统计...")
    total = ajaxCalculate("operation4Course/count");
    console.info("Course统计结果：" + total);
    return total;
}
