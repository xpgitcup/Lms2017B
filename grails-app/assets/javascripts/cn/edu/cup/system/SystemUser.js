/**
 * Created by LiXiaoping on 2017/3/27.
 */
var listSystemUserDiv;
var paginationSystemUserDiv;
var filterSystemUser;
var currentPgaeSystemUser;
var pageSizeSystemUser;
var totalSystemUser;

function tabAndPage4SystemUesr() {

    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    listSystemUserDiv = $("#listSystemUserDiv");
    paginationSystemUserDiv = $("#paginationSystemUserDiv");

    //获取当前的过滤
    filterSystemUser = readCookie("filterSystemUser", "");
    //获取当前页
    currentPgaeSystemUser = readCookie("currentPgaeSystemUser", 1);
    pageSizeSystemUser = readCookie("pageSizeSystemUser", pageSize);
    totalSystemUser = countSystemUser();
    console.info("记录总数：" + totalSystemUser);

    //加载数据
    listSystemUser(currentPgaeSystemUser, pageSizeSystemUser);

    //分页
    paginationSystemUserDiv.pagination({
        pageSize: pageSizeSystemUser,
        total: totalSystemUser,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            listSystemUser(pageNumber, pageSize);
        }
    });
}

/*
* 清除过滤条件
* */
function clearFilter() {
    var roleAttribute4Search = document.getElementById("roleAttribute4Search");
    roleAttribute4Search.value = "";
    $.cookie("filterSystemUser", "");
    var filterKey = document.getElementById("filterKey");
    filterKey.innerText = "";
    location.reload();
}

/*
* 搜索属性
* */
function filter4SystemUser() {
    var filterKey = document.getElementById("filterKey");
    var roleAttribute4Search = document.getElementById("roleAttribute4Search");
    var roleAttribute = roleAttribute4Search.value;
    if (roleAttribute) {
        console.info("过滤：" + roleAttribute);
        filterKey.innerText = "过滤：" + roleAttribute;
        //过滤条件
        $.cookie("filterSystemUser", "roleAttribute-" + roleAttribute);
        /*
        ajaxRun("operation4SystemUser/filter4SystemUser"
            + getParams(currentPgaeSystemUser, pageSizeSystemUser)
            + "&roleAttribute=" + roleAttribute,
            0, "listSystemUserDiv");
            */
        location.reload();
    }
}

/*
* 搜索用户名
* */
function search4UserName() {
    var userName4Search = document.getElementById("userName4Search");
    var userName = userName4Search.value;
    if (userName) {
        console.info("搜索：" + userName);
        userName4Search.value = "";
        ajaxRun("operation4SystemUser/findAllByUserName?userName=" + userName, 0, "listSystemUserDiv");
    }
}


/*
 * 新建
 * */
function createSystemUser(id) {
    console.info("创建SystemUser. 上级节点：" + id);
    ajaxRun("operation4SystemUser/createSystemUser", id, "showSystemUserDiv");
}

/*
 * 编辑
 * */
function editSystemUser(id) {
    console.info("编辑SystemUser." + id);
    ajaxRun("operation4SystemUser/editSystemUser", id, "showSystemUserDiv");
}

/*
 * 统计记录总数
 * */
function countSystemUser() {
    //console.info("开始统计...");
    var total = 0;
    if (filterSystemUser) {
        var filterKey = document.getElementById("filterKey");
        console.info(filterSystemUser);
        var s = filterSystemUser.split('-');
        filterKey.innerText = "过滤：" + s[1];
        console.info(s);
        total = ajaxCalculate("operation4SystemUser/countSystemUserWithFilter?" + s[0] + "=" + s[1]);
    } else {
        total = ajaxCalculate("operation4SystemUser/countSystemUser");
    }
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showSystemUser(id) {
    console.info("显示当前" + id);
    if (id) {
        ajaxRun("operation4SystemUser/getSystemUser", id, "showSystemUserDiv");
    }
}

/*
* 列表显示当前所有对象
* */
function listSystemUser(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    if (filterSystemUser)
    {
        var s = filterSystemUser.split('-');
        ajaxRun("operation4SystemUser/filter4SystemUser"
            + getParams(pageNumber, pageSize)
            + "&"
            + s[0] + "=" + s[1],
            0, "listSystemUserDiv");
    } else {
        ajaxRun("operation4SystemUser/listSystemUser" + getParams(pageNumber, pageSize), 0, "listSystemUserDiv");
    }
}