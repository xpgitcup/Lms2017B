var operation4TestPaperDiv     //tabs
var currentTabTestPaperDiv     //当前tab

function firstRunOperation4TestPaper() {

    //获取当前tabs的变量
    operation4TestPaperDiv = $("#operation4TestPaperDiv");

    //读取上次所停留的页面
    currentTabTestPaperDiv = readCookie("currentTabTestPaperDiv", "课程列表");
    console.info("上次停留标签..." + currentTabTestPaperDiv);
    //设置页面跳转函数
    operation4TestPaperDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            if (title !== "数据编辑") {
                $.cookie("currentTabTestPaperDiv", title, {path:'/'});
                //$.cookie("currentTabTestPaperDiv", title);
            }
            /*
            switch (index) {
                case 0:
                    //加载数据
                    listTestPaperQuantity(currentPgaeTestPaperQuantity, pageSizeTestPaperQuantity);
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

    if (currentTabTestPaperDiv != "数据编辑") {
        operation4TestPaperDiv.tabs("select", currentTabTestPaperDiv);
    } else {
        operation4TestPaperDiv.tabs("select", "课程列表");
    }
}