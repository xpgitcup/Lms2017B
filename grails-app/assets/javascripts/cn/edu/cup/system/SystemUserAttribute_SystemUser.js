/**
 * Created by LiXiaoping on 2017/3/27.
 */
var listSystemUserDiv;
var paginationSystemUserDiv;
var filterSystemUser;
var currentPgaeSystemUser;
var pageSizeSystemUser;
var totalSystemUser;


function tabAndPage4SystemUser() {

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
