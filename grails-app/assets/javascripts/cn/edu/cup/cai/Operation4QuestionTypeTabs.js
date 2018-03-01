var operation4QuestionTypeDiv     //tabs
var currentTabQuestionTypeDiv     //当前tab

function firstRunOperation4QuestionType() {

    //获取当前tabs的变量
    operation4QuestionTypeDiv = $("#operation4QuestionTypeDiv");

    //读取上次所停留的页面
    currentTabQuestionTypeDiv = readCookie("currentTabQuestionTypeDiv", "课程列表");
    console.info("上次停留标签..." + currentTabQuestionTypeDiv);
    //设置页面跳转函数
    operation4QuestionTypeDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            if (title !== "数据编辑") {
                $.cookie("currentTabQuestionTypeDiv", title, {path:'/'});
                //$.cookie("currentTabQuestionTypeDiv", title);
            }
            /*
            switch (index) {
                case 0:
                    //加载数据
                    listQuestionTypeQuantity(currentPgaeQuestionTypeQuantity, pageSizeQuestionTypeQuantity);
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

    if (currentTabQuestionTypeDiv != "数据编辑") {
        operation4QuestionTypeDiv.tabs("select", currentTabQuestionTypeDiv);
    } else {
        operation4QuestionTypeDiv.tabs("select", "课程列表");
    }
}