var operation4CourseDiv     //tabs
var currentTabCourseDiv     //当前tab

function firstRunOperation4Course() {

    //获取当前tabs的变量
    operation4CourseDiv = $("#operation4CourseDiv");

    //读取上次所停留的页面
    currentTabCourseDiv = readCookie("currentTabCourseDiv", "课程列表");
    console.info("上次停留标签..." + currentTabCourseDiv);
    //设置页面跳转函数
    operation4CourseDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            if (title !== "编辑") {
                $.cookie("currentTabCourseDiv", title, {path:'/'});
                //$.cookie("currentTabCourseDiv", title);
            }
            /*
            switch (index) {
                case 0:
                    //加载数据
                    listCourseQuantity(currentPgaeCourseQuantity, pageSizeCourseQuantity);
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

}