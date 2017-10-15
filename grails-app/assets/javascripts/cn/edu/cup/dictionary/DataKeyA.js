/**
 * Created by LiXiaoping on 2017/2/26.
 */


function tabAndPage4DataKeyA() {
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    displayTreeDataKeyADiv = $("#displayTreeDataKeyADiv");
    paginationDataKeyADiv = $("#paginationDataKeyADiv");

    //获取当前页
    var currentPgaeDataKeyA = readCookie("currentPgaeDataKeyA", 1);
    var pageSizeDataKeyA = readCookie("pageSizeDataKeyA", pageSize);
    var totalDataKeyA = countDataKeyA();
    console.info("记录总数：" + totalDataKeyA);
    currentDataDictionary = $.cookie("currentDataDictionary", 0);

    //加载数据
    displayTreeDataKeyADiv.tree({
        url: "operation4DataKeyA/getTreeDataKeyA" + getParams(currentPgaeDataKeyA, pageSizeDataKeyA),
        onSelect: function (node) {
            showDataKeyA(node);
            $("#currentDictionary").attr('text', currentDataDictionary);
            $("#createDataKeyA").attr('href', 'javascript: createDataKeyA(' + node.attributes[0] + ')');
            console.info(node);
            console.info("当前节点：" + node.target.id);
            $.cookie("currentDataKeyA", node.target.id);
        },
        onLoadSuccess: function () {
            displayTreeDataKeyADiv.tree("collapseAll");
            var nodeid = $.cookie("currentDataKeyA");
            console.info("当初扩展到" + nodeid);
            if (nodeid) {
                var cNode = $("#" + nodeid);
                displayTreeDataKeyADiv.tree("expandTo", cNode);
            }
        }
    });
    //分页
    paginationDataKeyADiv.pagination({
        pageSize: pageSizeDataKeyA,
        total: totalDataKeyA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            displayTreeDataKeyADiv.tree({
                url: "operation4DataKeyA/getTreeDataKeyA" + getParams(pageNumber, pageSize)
            });
            //记录当前页面
            $.cookie("currentPgaeDataKeyA", pageNumber);
            //应该清除掉当前扩展
            //$.cookie("currentDataKeyA", '', { expires: -1 });
        }
    });
    paginationDataKeyADiv.pagination("select", currentPgaeDataKeyA);
}

/*
* 有效性检验...
* */
function formCheck(form) {
    console.info("有效性检验...")
    console.info(form.dataKeyType.value);
    if ((parseInt(form.columnNumber.value)>1) && (form.appendParameter.value == '')) {
        alert("请输入数据列标题：");
        form.appendText.focus();
        return false
    } else {
        return true
    }
    switch (form.dataKeyType.value) {
        case "dataKeyRef":
            if (form.appendParameter.value == '') {
                alert("请选择关键字：");
                form.dataKeyList.focus();
                return false
            } else {
                return true
            }
            break;
        default:
            return true;
            break;
    }
}

/*
* 更新引用关键字
* */
function onchangeDataKeyList(form) {
    form.appendParameter.value = form.dataKeyList.value;
}

/*
 * 新建--更新枚举类型的标题
 * */
function updateAppendForm4DataKeyA() {
    console.info("hi....");
    var appendParameter = $("#appendParameter");
    var appendText = $("#appendText");
    console.info(appendText);
    console.info(appendText.val());
    var reg = new RegExp("\n","g");
    var ss = appendText.val().replace(reg,",");
    console.info(ss.length);
    appendParameter.attr("value", ss);
}

function createDataKeyA(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    operation4DictionaryDiv.tabs('select','模型编辑');
    ajaxRun("operation4DataKeyA/createDataKeyA", id, "editDataKeyADiv");
}


/*
 * 编辑
 * */
function editDataKeyA(id) {
    console.info("编辑DataKeyA." + id);
    operation4DictionaryDiv.tabs('select','模型编辑');
    ajaxRun("operation4DataKeyA/editDataKeyA", id, "editDataKeyADiv");
}

/*
 * 统计记录总数
 * */
function countDataKeyA() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4DataKeyA/countDataKeyA");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showDataKeyA(node) {
    console.info("显示当前系统属性" + node);
    if (node !== null) {
        var id = node.attributes[0];
        console.info("id=" + id);
        ajaxRun("operation4DataKeyA/getDataKeyA", id, "showDataKeyADiv");
    }
    else {
        console.info("error 找不到id!");
        $("#showDataKeyADiv").html("");
    }
}
