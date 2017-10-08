
/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4MileageAndElevation() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listMileageAndElevationDiv = $("#listMileageAndElevationDiv");
    paginationListMileageAndElevationDiv = $("#paginationListMileageAndElevationDiv");

    //获取当前页
    var currentPgaeMileageAndElevation = readCookie("currentPgaeMileageAndElevation", 1);
    var pageSizeMileageAndElevation = readCookie("pageSizeMileageAndElevation", pageSize);
    var totalMileageAndElevation = countMileageAndElevation();
    //console.info("记录总数：" + totalMileageAndElevation);


    //分页
    paginationListMileageAndElevationDiv.pagination({
        pageSize: pageSizeMileageAndElevation,
        total: totalMileageAndElevation,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listMileageAndElevation(pageNumber, pageSize);
            $.cookie("currentPgaeMileageAndElevation", pageNumber);
        }
    });
    paginationListMileageAndElevationDiv.pagination("select", currentPgaeMileageAndElevation);
    //------------------------------------------------------------------------------------------------------------------
}

/*
 * 统计记录总数
 * */
function countMileageAndElevation() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4MileageAndElevation/countMileageAndElevation");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listMileageAndElevation(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4MileageAndElevation/listMileageAndElevation" + getParams(pageNumber, pageSize), 0, "listMileageAndElevationDiv");
}

/*
 * 新建
 * */
function createMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4MileageAndElevation/createMileageAndElevation", id, "editMileageAndElevationDiv");
}

/*
 * 编辑
 * */
function editMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑MileageAndElevation." + id);
    ajaxRun("operation4MileageAndElevation/editMileageAndElevation", id, "editMileageAndElevationDiv");
}

/*
* 准备导入高程里程
* */
function prepareImportMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4MileageAndElevation/prepareImportMileageAndElevation", id, "prepareImportMileageAndElevationDiv");
}

