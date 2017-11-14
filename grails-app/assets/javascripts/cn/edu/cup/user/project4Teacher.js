
var listProject4TeacherDiv;
var paginationListProject4TeacherDiv;

var currentPgaeProject4Teacher;
var pageSizeProject4Teacher;
var totalProject4Teacher;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4Project4Teacher() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listProject4TeacherDiv = $("#listProject4TeacherDiv");
    paginationListProject4TeacherDiv = $("#paginationListProject4TeacherDiv");

    //获取当前页
    currentPgaeProject4Teacher = readCookie("currentPgaeProject4Teacher", 1);
    pageSizeProject4Teacher = readCookie("pageSizeProject4Teacher", 5);
    totalProject4Teacher = countProject4Teacher();
    //console.info("记录总数：" + totalProject4Teacher);


    //分页
    paginationListProject4TeacherDiv.pagination({
        pageSize: pageSizeProject4Teacher,
        total: totalProject4Teacher,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listProject4Teacher(pageNumber, pageSize);
            $.cookie("currentPgaeProject4Teacher", pageNumber);
        }
    });
    paginationListProject4TeacherDiv.pagination("select", currentPgaeProject4Teacher);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 搜索某一关键字的记录
* */
function search4Project4Teacher() {
    currentFilterKey4Data = "数据项";
    currentFilterValue4Data = document.getElementById("searchKey4Project4Teacher").value;
    currentFilterAppendValue4Data = document.getElementById("searchValue4Project4Teacher").value;
    saveFilter4Data();
    location.reload();
}

/*
 * 统计记录总数
 * */
function countProject4Teacher() {
    console.info("开始统计...")
    var total;
    switch (currentFilterKey4Teacher) {
        case "数据字典":
            total = ajaxCalculate("operation4Project4TeacherA/countProject4TeacherA4Filter");
            break;
        case "关键字":
            total = ajaxCalculate("operation4Project4TeacherA/countProject4TeacherA4Filter"
                + "?dataKey=" + currentFilterValue4Data
            );
            break;
        case "数据项":
            total = ajaxCalculate("operation4Project4TeacherA/countProject4TeacherA4Filter"
                + "?dataKey=" + currentFilterValue4Data
                + "&dataValue=" + currentFilterAppendValue4Data
            );
            console.info("数据项：" + total);
            break;
        default:
            total = ajaxCalculate("operation4DataItemA/countDataItemA4Filter?dataKey=11");
    }
    console.info("Project4TeacherA " + currentFilterKey4Teacher + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listProject4Teacher(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    switch (currentFilterKey4Teacher) {
        case "数据字典":
            console.info("过滤数据字典...");
            ajaxRun("operation4Project4TeacherA/listProject4TeacherA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&view=listProject4TeacherAB",
                0, "listProject4TeacherDiv");
            break;
        case "关键字":
            console.info("过滤关键字...");
            ajaxRun("operation4Project4TeacherA/listProject4TeacherA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&dataKey=" + currentFilterValue4Data
                + "&view=listProject4TeacherAB",
                0, "listProject4TeacherDiv");
            break;
        case "数据项":
            console.info("过滤数据项...");
            ajaxRun("operation4Project4TeacherA/listProject4TeacherA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&dataKey=" + currentFilterValue4Data
                + "&dataValue=" + currentFilterAppendValue4Data
                + "&view=listProject4TeacherAB",
                0, "listProject4TeacherDiv");
            break;
        default:
            console.info("正常列表...");
            ajaxRun("operation4DataItemA/listDataItemA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&dataKey=11"
                + "&view=user/listProject4Teacher",
                0, "listProject4TeacherDiv");
    }
}

/*
 * 新建
 * */
function createProject4Teacher() {
    tabs4TeacherDiv.tabs("select", "数据编辑")
    ajaxRun("operation4DataItemA/createDataItemA"
        + "?view=user/createProject4Teacher",
        11,
        "editDataItemADiv");
}

/*
 * 编辑
 * */
function editProject4Teacher(id) {
    operation4DataDiv.tabs("select", "编辑")
    //console.info("编辑Project4TeacherA." + id);
    ajaxRun("operation4Project4TeacherA/editProject4Teacher", id, "editProject4TeacherDiv");
}
