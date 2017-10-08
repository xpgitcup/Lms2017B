/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4AmbientTemperature() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listAmbientTemperatureDiv = $("#listAmbientTemperatureDiv");
    paginationListAmbientTemperatureDiv = $("#paginationListAmbientTemperatureDiv");

    //获取当前页
    var currentPgaeAmbientTemperature = readCookie("currentPgaeAmbientTemperature", 1);
    var pageSizeAmbientTemperature = readCookie("pageSizeAmbientTemperature", pageSize);
    var totalAmbientTemperature = countAmbientTemperature();
    //console.info("记录总数：" + totalAmbientTemperature);


    //分页
    paginationListAmbientTemperatureDiv.pagination({
        pageSize: pageSizeAmbientTemperature,
        total: totalAmbientTemperature,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listAmbientTemperature(pageNumber, pageSize);
            $.cookie("currentPgaeAmbientTemperature", pageNumber);
        }
    });
    paginationListAmbientTemperatureDiv.pagination("select", currentPgaeAmbientTemperature);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 准备导入地温
* */
function prepareImportAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4AmbientTemperature/prepareImportAmbientTemperature", id, "prepareImportAmbientTemperatureDiv");
}

/*
 * 统计记录总数
 * */
function countAmbientTemperature() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4AmbientTemperature/countAmbientTemperature");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listAmbientTemperature(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4AmbientTemperature/listAmbientTemperature" + getParams(pageNumber, pageSize), 0, "listAmbientTemperatureDiv");
}

/*
 * 新建
 * */
function createAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4AmbientTemperature/createAmbientTemperature", id, "editAmbientTemperatureDiv");
}

/*
 * 编辑
 * */
function editAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑AmbientTemperature." + id);
    ajaxRun("operation4AmbientTemperature/editAmbientTemperature", id, "editAmbientTemperatureDiv");
}
