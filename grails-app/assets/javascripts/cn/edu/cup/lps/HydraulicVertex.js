/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4HydraulicVertex() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listHydraulicVertexDiv = $("#listHydraulicVertexDiv");
    paginationListHydraulicVertexDiv = $("#paginationListHydraulicVertexDiv");

    //获取当前页
    var currentPgaeHydraulicVertex = readCookie("currentPgaeHydraulicVertex", 1);
    var pageSizeHydraulicVertex = readCookie("pageSizeHydraulicVertex", pageSize);
    var totalHydraulicVertex = countHydraulicVertex();
    //console.info("记录总数：" + totalHydraulicVertex);


    //分页
    paginationListHydraulicVertexDiv.pagination({
        pageSize: pageSizeHydraulicVertex,
        total: totalHydraulicVertex,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listHydraulicVertex(pageNumber, pageSize);
            $.cookie("currentPgaeHydraulicVertex", pageNumber);
        }
    });
    paginationListHydraulicVertexDiv.pagination("select", currentPgaeHydraulicVertex);
    //------------------------------------------------------------------------------------------------------------------
}


/*
 * 统计记录总数
 * */
function countHydraulicVertex() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4HydraulicVertex/countHydraulicVertex");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listHydraulicVertex(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4HydraulicVertex/listHydraulicVertex" + getParams(pageNumber, pageSize), 0, "listHydraulicVertexDiv");
}

/*
 * 新建
 * */
function createHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4HydraulicVertex/createHydraulicVertex", id, "editHydraulicVertexDiv");
}

/*
 * 编辑
 * */
function editHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑HydraulicVertex." + id);
    ajaxRun("operation4HydraulicVertex/editHydraulicVertex", id, "editHydraulicVertexDiv");
}
