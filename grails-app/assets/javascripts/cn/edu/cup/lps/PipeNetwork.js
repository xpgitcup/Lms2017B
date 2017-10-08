/*
* 标签页--分页
* */
function tabAndPage4PipeNetwork() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listPipeNetworkDiv = $("#listPipeNetworkDiv");
    paginationListPipeNetworkDiv = $("#paginationListPipeNetworkDiv");

    //获取当前页
    var currentPgaePipeNetwork = readCookie("currentPgaePipeNetwork", 1);
    var pageSizePipeNetwork = readCookie("pageSizePipeNetwork", pageSize);
    var totalPipeNetwork = countPipeNetwork();
    //console.info("记录总数：" + totalPipeNetwork);


    //分页
    paginationListPipeNetworkDiv.pagination({
        pageSize: pageSizePipeNetwork,
        total: totalPipeNetwork,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listPipeNetwork(pageNumber, pageSize);
            $.cookie("currentPgaePipeNetwork", pageNumber);
        }
    });
    paginationListPipeNetworkDiv.pagination("select", currentPgaePipeNetwork);
    //------------------------------------------------------------------------------------------------------------------
}

/*
 * 统计记录总数
 * */
function countPipeNetwork() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeNetwork/countPipeNetwork");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 显示拓扑图
* */
function showPipeNetworkTopo(id) {
    console.info("拓扑图:" + id);
    operation4PipeSimulationDiv.tabs("select", "管道");
    pipeNetworkDiv.tabs("select", "纵断面图")

    var topo = ajaxCall("operation4PipeNetwork/showPipeNetworkTopo/" + id, 0);
    var data = topo.data;
    var links = topo.links;

    console.info("下面是数据：");
    console.info(data);
    console.info(links);

    var pipeNetworkProfileDiv = document.getElementById("pipeNetworkProfileDiv");
    var pipeNetworkProfile = echarts.init(pipeNetworkProfileDiv);

    data = [
        {
            name: 'A',
            x: 0,
            y: 0
        },
        {
            name: 'B',
            x: 100,
            y: 100
        }
    ];
    links = [
        {
            source: 'A',
            target: 'B'
        }
    ];

    var option =
        {
            title: {
                text: topo.name
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                min: 0,
                max: 1000,
                type: 'value'
            },
            yAxis: {
                min: 0,
                max: 1000,
                type: 'value',
                scale: true
            },
            series: [
                {
                    id: 'a',
                    type: 'graph',
                    layout: 'none',
                    symbolSize: 50,
                    showSymbol: true,
                    coordinateSystem: 'cartesian2d',
                    data: data,
                    links: links
                }
            ]
        }
    pipeNetworkProfile.setOption(option);
}

/*
* 显示纵断面图
* */
function showPipeNetworkProfile(id) {
    console.info("绘制纵断面图:" + id);
    operation4PipeSimulationDiv.tabs("select", "管道");
    pipeNetworkDiv.tabs("select", "纵断面图")

    var profile = ajaxCall("operation4PipeNetwork/showPipeNetworkProfile/" + id, 0);
    var data = profile.data
    var stations = profile.stations;
    var temperatures = profile.temperatures

    console.info("下面是数据：");
    console.info(data);
    console.info(stations);
    console.info(temperatures);

    var pipeNetworkProfileDiv = document.getElementById("pipeNetworkProfileDiv");
    var pipeNetworkProfile = echarts.init(pipeNetworkProfileDiv);

    var option =
        {
            title: {
                text: profile.name
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '4%',
                containLabel: true
            },
            xAxis: {
                name: '里程/km',
                nameLocation: 'middle',
                nameGap: 20,
                min: 0,
                max: 300,
                type: 'value',
                axisLine: {onZero: true}
            },
            yAxis: [
                {
                    name: '高程/m',
                    nameLocation: 'middle',
                    nameGap: 30,
                    min: 0,
                    max: 1000,
                    type: 'value',
                    axisLine: {onZero: true}
                },
                {
                    name: '地温/℃',
                    nameLocation: 'middle',
                    nameGap: 30,
                    min: -10,
                    max: 90,
                    type: 'value',
                    axisLine: {onZero: true}
                }
            ],
            series: [
                {
                    id: 'a',
                    type: 'line',
                    smooth: true,
                    showSymbol: false,
                    data: data,
                    itemStyle: {normal: {label: {show: false}}}
                },
                {
                    id: 'station',
                    type: 'scatter',
                    data: stations
                },
                {
                    id: '地温',
                    type: 'line',
                    data: temperatures,
                    yAxisIndex: 1
                }
            ]
        }
    pipeNetworkProfile.setOption(option);
}

function showPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "拓扑结构");
    var elements = ajaxCall("operation4PipeNetwork/showPipeNetworkAsJson/" + id, 0);

    console.info(elements);

    drawTopo(elements);
}

/**/
function prepareImportFromExcel(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4PipeNetwork/prepareImportFromExcel", id, "prepareImportFromExcelDiv");
}

/*
* 管道绘图
* */
function drawTopo(items) {
    console.info("开始绘图...");
    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
    var scene = new JTopo.Scene(stage); // 创建一个场景对象

    var cWidth = stage.width
    var cHeight = stage.height
    var xoffset =

        stage.frames = -24;
    scene.clear();

    // 不指定布局的时候，容器的布局为自动(容器边界随元素变化）
    var container = new JTopo.Container(items.name);
    container.textPosition = 'Middle_Center';
    container.fontColor = '128,128,128';
    container.font = '18pt 微软雅黑';
    container.borderColor = '255,0,0';
    container.borderRadius = 10; // 圆角
    container.width = cWidth;
    container.height = cHeight;

    scene.add(container);

    function drawNode(node) {
        var anode = new JTopo.CircleNode(node.name)
        anode.setSize(10, 10);
        var x = node.xLocation * cWidth * 0.8 + 10;
        var y = cHeight - node.yLocation * cHeight * 0.8 + 10;
        anode.setLocation(x, y);
        console.info("(" + x + "," + y + ")");
        node.tnode = anode;
        scene.add(anode);
        container.add(anode);
        return anode;
    }

    for (var i = 0; i < items.nodes.length; i++) {
        drawNode(items.nodes[i])
    }

    console.info("开始连线....")

    for (var i = 0; i < items.links.length; i++) {
        var from = items.links[i].start.id;
        var toId = items.links[i].end.id;
        //console.info(i + ":" + from + '-' + toId);

        var fromNode = arrayFind(items.nodes, 'id', from);
        var toNode = arrayFind(items.nodes, 'id', toId);

        var alink = new JTopo.Link(fromNode.tnode, toNode.tnode);

        scene.add(alink);
    }
}

/*
* 列表显示当前所有对象
* */
function listPipeNetwork(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeNetwork/listPipeNetwork" + getParams(pageNumber, pageSize), 0, "listPipeNetworkDiv");
}

/*
 * 新建
 * */
function createPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeNetwork/createPipeNetwork", id, "editPipeNetworkDiv");
}

/*
 * 编辑
 * */
function editPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑PipeNetwork." + id);
    ajaxRun("operation4PipeNetwork/editPipeNetwork", id, "editPipeNetworkDiv");
}
