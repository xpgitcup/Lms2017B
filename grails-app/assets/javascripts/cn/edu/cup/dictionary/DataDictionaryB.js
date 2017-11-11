/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4DataDictionary4Data() {

    console.info("数据维护...B");

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listDataDictionaryDiv = $("#listDataDictionaryDiv");
    paginationListDataDictionaryDiv = $("#paginationListDataDictionaryDiv");

    //获取当前页
    var currentPgaeDataDictionary = readCookie("currentPgaeDataDictionary", 1);
    var pageSizeDataDictionary = readCookie("pageSizeDataDictionary", pageSize);
    var totalDataDictionary = countDataDictionary();
    console.info("记录总数：" + totalDataDictionary);


    //分页
    paginationListDataDictionaryDiv.pagination({
        pageSize: pageSizeDataDictionary,
        total: totalDataDictionary,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listDataDictionary4Data(pageNumber, pageSize);
            $.cookie("currentPgaeDataDictionary", pageNumber);
        }
    });
    paginationListDataDictionaryDiv.pagination("select", currentPgaeDataDictionary);
    //------------------------------------------------------------------------------------------------------------------
}

function tabAndPage4DataDictionary() {

    console.info("DataDictionary...");

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listDataDictionaryDiv = $("#listDataDictionaryDiv");
    paginationListDataDictionaryDiv = $("#paginationListDataDictionaryDiv");

    //获取当前页
    var currentPgaeDataDictionary = readCookie("currentPgaeDataDictionary", 1);
    var pageSizeDataDictionary = readCookie("pageSizeDataDictionary", pageSize);
    var totalDataDictionary = countDataDictionary();
    console.info("记录总数：" + totalDataDictionary);


    //分页
    paginationListDataDictionaryDiv.pagination({
        pageSize: pageSizeDataDictionary,
        total: totalDataDictionary,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listDataDictionary(pageNumber, pageSize);
            $.cookie("currentPgaeDataDictionary", pageNumber);
        }
    });
    paginationListDataDictionaryDiv.pagination("select", currentPgaeDataDictionary);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 维护数据字典
* */
function maintainDataDictionary(id) {
    operation4DictionaryDiv.tabs("select", "模型维护");
    displayTreeDataKeyADiv.tree("reload");
}

/*
 * 统计记录总数
 * */
function countDataDictionary() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4DataDictionary/countDataDictionary");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listDataDictionary4Data(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4DataDictionary/listDataDictionaryWithView"
        + getParams(pageNumber, pageSize)
        + "&view=listDataDictionaryB",
        0,
        "listDataDictionaryDiv");
}

function listDataDictionary(pageNumber, pageSize) {
    console.info("列表显示对象：DataDictionary");
    ajaxRun("operation4DataDictionary/listDataDictionaryWithView"
        + getParams(pageNumber, pageSize)
        + "&view=listDataDictionaryB",
        0,
        "listDataDictionaryDiv");
}

/*
 * 新建
 * */
function createDataDictionary(id) {
    ajaxRun("operation4DataDictionary/createDataDictionary", id, "editDataDictionaryDiv");
}

/*
 * 编辑
 * */
function showDataDictionary(id) {
    //console.info("编辑DataDictionary." + id);
    ajaxRun("operation4DataDictionary/showDataDictionary", id, "editDataDictionaryDiv");
}

/*
 * 编辑
 * */
function editDataDictionary(id) {
    //console.info("编辑DataDictionary." + id);
    ajaxRun("operation4DataDictionary/editDataDictionary", id, "editDataDictionaryDiv");
}
