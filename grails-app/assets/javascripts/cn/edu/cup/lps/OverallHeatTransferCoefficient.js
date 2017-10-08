/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4OverallHeatTransferCoefficient() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listOverallHeatTransferCoefficientDiv = $("#listOverallHeatTransferCoefficientDiv");
    paginationListOverallHeatTransferCoefficientDiv = $("#paginationListOverallHeatTransferCoefficientDiv");

    //获取当前页
    var currentPgaeOverallHeatTransferCoefficient = readCookie("currentPgaeOverallHeatTransferCoefficient", 1);
    var pageSizeOverallHeatTransferCoefficient = readCookie("pageSizeOverallHeatTransferCoefficient", pageSize);
    var totalOverallHeatTransferCoefficient = countOverallHeatTransferCoefficient();
    //console.info("记录总数：" + totalOverallHeatTransferCoefficient);


    //分页
    paginationListOverallHeatTransferCoefficientDiv.pagination({
        pageSize: pageSizeOverallHeatTransferCoefficient,
        total: totalOverallHeatTransferCoefficient,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listOverallHeatTransferCoefficient(pageNumber, pageSize);
            $.cookie("currentPgaeOverallHeatTransferCoefficient", pageNumber);
        }
    });
    paginationListOverallHeatTransferCoefficientDiv.pagination("select", currentPgaeOverallHeatTransferCoefficient);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 准备导入
* */
function prepareImportOverallHeatTransferCoefficient(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4OverallHeatTransferCoefficient/prepareImportOverallHeatTransferCoefficient", id, "prepareImportOverallHeatTransferCoefficientDiv");
}

/*
 * 统计记录总数
 * */
function countOverallHeatTransferCoefficient() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4OverallHeatTransferCoefficient/countOverallHeatTransferCoefficient");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listOverallHeatTransferCoefficient(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4OverallHeatTransferCoefficient/listOverallHeatTransferCoefficient" + getParams(pageNumber, pageSize), 0, "listOverallHeatTransferCoefficientDiv");
}

/*
 * 新建
 * */
function createOverallHeatTransferCoefficient(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4OverallHeatTransferCoefficient/createOverallHeatTransferCoefficient", id, "editOverallHeatTransferCoefficientDiv");
}

/*
 * 编辑
 * */
function editOverallHeatTransferCoefficient(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑OverallHeatTransferCoefficient." + id);
    ajaxRun("operation4OverallHeatTransferCoefficient/editOverallHeatTransferCoefficient", id, "editOverallHeatTransferCoefficientDiv");
}
