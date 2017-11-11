/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4DataKeyA() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listDataKeyADiv = $("#listDataKeyADiv");
    paginationListDataKeyADiv = $("#paginationListDataKeyADiv");

    //获取当前页
    var currentPgaeDataKeyA = readCookie("currentPgaeDataKeyA", 1);
    var pageSizeDataKeyA = readCookie("pageSizeDataKeyA", pageSize);
    var totalDataKeyA = countDataKeyA();
    //console.info("记录总数：" + totalDataKeyA);


    //分页
    paginationListDataKeyADiv.pagination({
        pageSize: pageSizeDataKeyA,
        total: totalDataKeyA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listDataKeyA(pageNumber, pageSize);
            $.cookie("currentPgaeDataKeyA", pageNumber);
        }
    });
    paginationListDataKeyADiv.pagination("select", currentPgaeDataKeyA);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 显示当前模型下的数据
* */
function selectCurrentDataKeyA(id) {
    currentFilterKey4Data = "关键字";
    currentFilterValue4Data = id;
    saveFilter4Data();
    location.reload();
}


/*
* 显示标题
* */
function showHeads(id) {
    operation4DataADiv.tabs("select", "数据表头");
    ajaxRun("operation4DataKeyA/showHeads", id, "showHeadsDiv");
}

/*
* 准备导入
* */
function prepareImportDataKeyA(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4DataKeyA/prepareImportDataKeyA", id, "prepareImportDataKeyADiv");
}

/*
 * 统计记录总数
 * */
function countDataKeyA() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4DataKeyA/countDataKeyA4DataModel");
    console.info("数据模型数：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listDataKeyA(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    switch (currentFilterKey4Data) {
        case "数据字典":
            ajaxRun("operation4DataKeyA/listDataKeyA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&dataDictionary=" + currentFilterValue4Data
                + "&view=listDataKeyB",
                0, "listDataKeyADiv");
            break;
        case "关键字":
            ajaxRun("operation4DataKeyA/listDataKeyA4FilterWithView"
                + getParams(pageNumber, pageSize)
                + "&dataKeyA=" + currentFilterValue4Data
                + "&view=listDataKeyB",
                0, "listDataKeyADiv");
            break;
        default:
            ajaxRun("operation4DataKeyA/listDataKeyA4DataModel" + getParams(pageNumber, pageSize), 0, "listDataKeyADiv");
    }
}

