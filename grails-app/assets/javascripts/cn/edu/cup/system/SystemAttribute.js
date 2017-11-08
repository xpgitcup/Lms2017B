/**
 * Created by LiXiaoping on 2017/2/23.
 */


/*
* 新建
* */
function createSystemAttribute(id) {
    console.info("创建SystemAttribute. 上级节点：" + id);
    ajaxRun("operation4SystemAttribute/createSystemAttribute", id, "showSystemAttributeDiv");
}

/*
* 编辑
* */
function editSystemAttribute(id) {
    console.info("编辑SystemAttribute." + id);
    ajaxRun("operation4SystemAttribute/editSystemAttribute", id, "showSystemAttributeDiv");
}

/*
* 统计记录总数
* */
function countSystemAttribute() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4SystemAttribute/countSystemAttribute");
    console.info("统计结果：" + total);
    return total;
}

/*
* 显示当前属性
* */
function showSystemAttribute(node) {
    console.info("显示当前系统属性" + node);
    if (node) {
        var id = node.attributes[0];
        ajaxRun("operation4SystemAttribute/getSystemAttribute", id, "showSystemAttributeDiv");
    }
}
