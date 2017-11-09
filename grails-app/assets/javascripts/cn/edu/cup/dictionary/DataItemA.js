var listDataItemADiv;
var paginationListDataItemADiv;

var currentPgaeDataItemA;
var pageSizeDataItemA;
var totalDataItemA;

/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4DataItemA() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listDataItemADiv = $("#listDataItemADiv");
    paginationListDataItemADiv = $("#paginationListDataItemADiv");

    //获取当前页
    currentPgaeDataItemA = readCookie("currentPgaeDataItemA", 1);
    pageSizeDataItemA = readCookie("pageSizeDataItemA", 5);
    totalDataItemA = countDataItemA();
    //console.info("记录总数：" + totalDataItemA);


    //分页
    paginationListDataItemADiv.pagination({
        pageSize: pageSizeDataItemA,
        total: totalDataItemA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listDataItemA(pageNumber, pageSize);
            $.cookie("currentPgaeDataItemA", pageNumber);
        }
    });
    paginationListDataItemADiv.pagination("select", currentPgaeDataItemA);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 搜索某一关键字的记录
* */
function search4DataItem() {
    var searchKey4DataItem = document.getElementById("searchKey4DataItem")
    var searchKey = searchKey4DataItem.value
    console.info(searchKey);
    var searchValue4DataItem = document.getElementById("searchValue4DataItem");
    var searchValue = searchValue4DataItem.value
    console.info(searchValue);

    var total = ajaxCalculate("operation4DataItemA/countDataItemA4SataKey/?dataKey="
        + searchKey + "&searchValue=" + searchValue);
    paginationListDataItemADiv.pagination({total: total});

    ajaxRun("operation4DataItemA/searchDataItemA?dataKey="
        + searchKey + "&searchValue=" + searchValue, 0, "listDataItemADiv");
}

/*
* 处理文件上传
* */
function updateUploadFileName(id) {
    console.info(id);
    console.info("文件上传...");
    var ainput = "input_" + id;
    var afile = "file_" + id;
    var aainput = document.getElementById(ainput);
    var aafile = document.getElementById(afile);
    var fn = aainput.value
    var k = fn.lastIndexOf('/')
    console.info(aainput);
    console.info(aainput.files.length);
    console.info(aafile);
    aafile.value = aainput.files[0].name;
    console.info(aainput.value);
}

/*
* 准备导入
* */
function prepareImportDataItemA(id) {
    paginationListDataItemADiv.tabs("select", "导入管道");
    ajaxRun("operation4DataItemA/prepareImportDataItemA", id, "prepareImportDataItemADiv");
}

/*
 * 统计记录总数
 * */
function countDataItemA() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4DataItemA/countDataItemA");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listDataItemA(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4DataItemA/listDataItemA" + getParams(pageNumber, pageSize), 0, "listDataItemADiv");
}

/*
 * 新建
 * */
function createDataItemA(id) {
    operation4DataADiv.tabs("select", "数据编辑")
    ajaxRun("operation4DataItemA/createDataItemA", id, "editDataItemADiv");
}

/*
 * 编辑
 * */
function editDataItemA(id) {
    operation4DataADiv.tabs("select", "编辑")
    //console.info("编辑DataItemA." + id);
    ajaxRun("operation4DataItemA/editDataItemA", id, "editDataItemADiv");
}
