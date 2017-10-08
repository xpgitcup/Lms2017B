var operation4PipeSimulationDiv;
var currentTabPipeSimulationDiv;

var listHydraulicProjectDiv;
var paginationListHydraulicProjectDiv;

var listPipeNetworkDiv;
var paginationListPipeNetworkDiv;

var listHydraulicVertexDiv;
var paginationListHydraulicVertexDiv;

var listMileageAndElevationDiv;
var paginationListMileageAndElevationDiv;

var pipeNetworkDiv;

var listAmbientTemperatureDiv;
var paginationListAmbientTemperatureDiv;

var listOverallHeatTransferCoefficientDiv;
var paginationListOverallHeatTransferCoefficientDiv;

$(function () {
    console.info("管道模拟...");

    //------------------------------------------------------------------------------------------------------------------
    //管道标签下的子标签
    pipeNetworkDiv = $("#pipeNetworkDiv");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4PipeSimulationDiv = $("#operation4PipeSimulationDiv");
    currentTabPipeSimulationDiv = readCookie("currentTabPipeSimulationDiv", "模拟工程");

    //设置页面跳转函数
    operation4PipeSimulationDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "编辑":
                case "导入管道":
                case "拓扑结构":
                    break;
                default:
                    $.cookie("currentTabPipeSimulationDiv", title, {path: '/'});
                    break;
            }
        }
    })

    tabAndPage4HydraulicProject();

    tabAndPage4PipeNetwork();

    tabAndPage4HydraulicVertex();

    tabAndPage4MileageAndElevation();

    tabAndPage4AmbientTemperature();

    tabAndPage4OverallHeatTransferCoefficient();

    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4PipeSimulationDiv.tabs("select", currentTabPipeSimulationDiv);

});

