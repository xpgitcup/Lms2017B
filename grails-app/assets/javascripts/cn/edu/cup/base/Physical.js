/**
 * Created by LiXiaoping on 2017/4/5.
 */

//物理量维护--变量部分

var operation4PhysicalDiv;
var currentTabPhysicalDiv;

//物理量
var listPhysicalQuantityDiv;
var paginationListPhysicalQuantityDiv;

//物理量单位
var QuantityUnitDiv;
var listQuantityUnitDiv;
var paginationListQuantityUnitDiv;

$(function () {
    console.info("单位维护...");

    //总体设置-----------------------------------------------------------------------------------------------------------
    //获取当前tabs的变量
    operation4PhysicalDiv = $("#operation4PhysicalDiv");
    //读取上次所停留的页面
    //currentTabPhysicalDiv = readCookie("currentTabPhysicalDiv", "编辑");//"物理量维护页面");
    currentTabPhysicalDiv = readCookie("currentTabPhysicalDiv", "物理量维护页面");
    console.info("上次停留标签..." + currentTabPhysicalDiv);
    //var currentTab = operation4PhysicalDiv.tabs("getSelected");  //这个得到的不是数值，是一个对象
    //var currentTabIndex = operation4PhysicalDiv.tabs("getTabIndex", currentTab);
    //console.info(currentTab);
    //console.info(currentTabIndex);
    //设置页面跳转函数
    operation4PhysicalDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            if (title !== "编辑") {
                $.cookie("currentTabPhysicalDiv", title, {path:'/'});
                //$.cookie("currentTabPhysicalDiv", title);
            }
            /*
            switch (index) {
                case 0:
                    //加载数据
                    listPhysicalQuantity(currentPgaePhysicalQuantity, pageSizePhysicalQuantity);
                    break;
                case 1:
                    //加载数据
                    listQuantityUnit(currentPgaeQuantityUnit, pageSizeQuantityUnit);
                    break;
                case 2:
                    //加载数据
                    listUnitSystem(currentPgaeUnitSystem, pageSizeUnitSystem);
                    break;
            }
            */
        }
    })

    /*
    if (currentTabIndex !== currentTabPhysicalDiv) {
        operation4PhysicalDiv.tabs("select", currentTabPhysicalDiv);
        console.info("跳转-->" + currentTabPhysicalDiv);
    } else {
        console.info("不用跳转...");
    }
    */
    //------------------------------------------------------------------------------------------------------------------

    //物理量维护---------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listPhysicalQuantityDiv = $("#listPhysicalQuantityDiv");
    paginationListPhysicalQuantityDiv = $("#paginationListPhysicalQuantityDiv");

    //获取当前页
    var currentPgaePhysicalQuantity = readCookie("currentPgaePhysicalQuantity", 1);
    var pageSizePhysicalQuantity = readCookie("pageSizePhysicalQuantity", pageSize);
    var totalPhysicalQuantity = countPhysicalQuantity();
    //console.info("记录总数：" + totalPhysicalQuantity);


    //分页
    paginationListPhysicalQuantityDiv.pagination({
        pageSize: pageSizePhysicalQuantity,
        total: totalPhysicalQuantity,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listPhysicalQuantity(pageNumber, pageSize);
            $.cookie("currentPgaePhysicalQuantity", pageNumber);
        }
    });
    paginationListPhysicalQuantityDiv.pagination("select", currentPgaePhysicalQuantity);
    //------------------------------------------------------------------------------------------------------------------

    //物理量单位维护---------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listQuantityUnitDiv = $("#listQuantityUnitDiv");
    paginationListQuantityUnitDiv = $("#paginationListQuantityUnitDiv");

    //获取当前页
    var currentPgaeQuantityUnit = readCookie("currentPgaeQuantityUnit", 1);
    var pageSizeQuantityUnit = readCookie("pageSizeQuantityUnit", pageSize);
    var totalQuantityUnit = countQuantityUnit();
    //console.info("记录总数：" + totalQuantityUnit);


    //分页
    paginationListQuantityUnitDiv.pagination({
        pageSize: pageSizeQuantityUnit,
        total: totalQuantityUnit,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listQuantityUnit(pageNumber, pageSize);
            $.cookie("currentPgaeQuantityUnit", pageNumber);
        }
    });
    paginationListQuantityUnitDiv.pagination("select", currentPgaeQuantityUnit);
    //------------------------------------------------------------------------------------------------------------------


    //单位制维护---------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listUnitSystemDiv = $("#listUnitSystemDiv");
    paginationListUnitSystemDiv = $("#paginationListUnitSystemDiv");

    //获取当前页
    var currentPgaeUnitSystem = readCookie("currentPgaeUnitSystem", 1);
    var pageSizeUnitSystem = readCookie("pageSizeUnitSystem", pageSize);
    var totalUnitSystem = countUnitSystem();
    //console.info("记录总数：" + totalUnitSystem);


    //分页
    paginationListUnitSystemDiv.pagination({
        pageSize: pageSizeUnitSystem,
        total: totalUnitSystem,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listUnitSystem(pageNumber, pageSize);
            $.cookie("currentPgaeUnitSystem", pageNumber);
        }
    });
    paginationListUnitSystemDiv.pagination("select", currentPgaeUnitSystem);
    //------------------------------------------------------------------------------------------------------------------

    var currentTab = checkSessionPhysicalQuantity(currentTabPhysicalDiv);
    console.info("-->" + currentTab);
    //页面跳转--放到最后，试试看
    operation4PhysicalDiv.tabs("select", currentTab);

});

//======================================================================================================================
//PhysicalQuantity
/*
 * 统计记录总数
 * */
function countPhysicalQuantity() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4Physical/countPhysicalQuantity");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listPhysicalQuantity(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4Physical/listPhysicalQuantity" + getParams(pageNumber, pageSize), 0, "listPhysicalQuantityDiv");
}

/*
 * 新建
 * */
function createPhysicalQuantity(id) {
    operation4PhysicalDiv.tabs("select", "编辑")
    ajaxRun("operation4Physical/createPhysicalQuantity", id, "editPhysicalQuantityDiv");
}

/*
 * 编辑
 * */
function editPhysicalQuantity(id) {
    operation4PhysicalDiv.tabs("select", "编辑")
    //console.info("编辑PhysicalQuantity." + id);
    ajaxRun("operation4Physical/editPhysicalQuantity", id, "editPhysicalQuantityDiv");
}

/*
 * 显示当前
 * */
function showPhysicalQuantity(id) {
    //console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4PhysicalQuantity/getPhysicalQuantity", id, "showPhysicalQuantityDiv");
    }
}

//======================================================================================================================
//QuantityUnit
/*
 * 统计记录总数
 * */
function countQuantityUnit() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4Physical/countQuantityUnit");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
 * 列表显示当前所有对象
 * */
function listQuantityUnit(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4Physical/listQuantityUnit" + getParams(pageNumber, pageSize), 0, "listQuantityUnitDiv");
}

/*
 * 新建
 * */
function createQuantityUnit(id) {
    operation4PhysicalDiv.tabs("select", "编辑")
    ajaxRun("operation4Physical/createQuantityUnit", id, "editQuantityUnitDiv");
}

/*
 * 编辑
 * */
function editQuantityUnit(id) {
    //console.info("编辑QuantityUnit." + id);
    operation4PhysicalDiv.tabs("select", "编辑")
    ajaxRun("operation4Physical/editQuantityUnit", id, "editQuantityUnitDiv");
}

/*
 * 显示当前
 * */
function showQuantityUnit(id) {
    //console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4QuantityUnit/getQuantityUnit", id, "showQuantityUnitDiv");
    }
}


//======================================================================================================================
//UnitSystem
/*
 * 统计记录总数
 * */
function countUnitSystem() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4Physical/countUnitSystem");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
 * 列表显示当前所有对象
 * */
function listUnitSystem(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4Physical/listUnitSystem" + getParams(pageNumber, pageSize), 0, "listUnitSystemDiv");
}

/*
 * 新建
 * */
function createUnitSystem(id) {
    //UnitSystemDiv.tabs("select", "对话");
    operation4PhysicalDiv.tabs("select", "编辑")
    ajaxRun("operation4Physical/createUnitSystem", id, "editUnitSystemDiv");
}

/*
 * 编辑
 * */
function editUnitSystem(id) {
    //console.info("编辑UnitSystem." + id);
    operation4PhysicalDiv.tabs("select", "编辑")
    ajaxRun("operation4Physical/editUnitSystem", id, "editUnitSystemDiv");
}

/*
 * 显示当前
 * */
function showUnitSystem(id) {
    //console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4Physical/getUnitSystem", id, "showUnitSystemDiv");
    }
}
