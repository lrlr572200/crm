$(function(){
    year();//获取年
    month();//获取月
    //异步显示数据
    deptMonthlyPage();
});
//异步显示数据
function deptMonthlyPage() {
    var year=$("#year").val();
    var month=$("#month").val();
    var data="year="+year+"&month="+month;
    $.post("/sys/commonthlys.html",data,function (relData) {
        if(relData[0]!=null)
        {
            $("#sumMoney").html(relData[0].sumMoney);
            $("#years").html(relData[0].year);
            $("#months").html(relData[0].month);
            $("#basis").html(relData[0].basis);
            $("#ratio").html(relData[0].ratio);
        }
        names=new Array(relData[1].length);
        sums=new Array(relData[2].length);
        for(var i=0;i<relData[1].length;i++)
        {
            names[i]=relData[1][i];
            sums[i]=relData[2][i];
        }
        pic(names,sums);
    },"json");

}

function pic(n,s) {
    var myChart = echarts.init($("#chart")[0]);
    option = {
        title: {
            x: 'center',
            text: '销售金额统计图',

        },
        tooltip: {
            trigger: 'item'
        },

        calculable: true,
        grid: {
            borderWidth: 0,
            y: 80,
            y2: 60
        },
        xAxis: [
            {
                type: 'category',
                show: true,
                data: n
            }
        ],
        yAxis: [
            {
                type: 'value',
                show: true
            }
        ],
        series: [
            {
                name: '酒店统计图',
                type: 'bar',
                barWidth : 30,//柱图协议
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{b}\n{c}'
                        }
                    }
                },
                /*data: [22,12,25,11,23],*/
                data: s,
                markPoint: {
                    tooltip: {
                        trigger: 'item',
                        backgroundColor: 'rgba(0,0,0,0)',
                        formatter: function(params){
                            return '<img src="'
                                + params.data.symbol.replace('image://', '')
                                + '"/>';
                        }
                    },
                    data: [
                        {xAxis:0, y: 20, name:'Line', symbolSize:20, symbol: 'image://../asset/ico/折线图.png'},
                        {xAxis:1, y: 20, name:'Bar', symbolSize:20, symbol: 'image://../asset/ico/柱状图.png'},
                        {xAxis:2, y: 20, name:'Scatter', symbolSize:20, symbol: 'image://../asset/ico/散点图.png'},
                        {xAxis:3, y: 20, name:'K', symbolSize:20, symbol: 'image://../asset/ico/K线图.png'},
                        {xAxis:4, y: 20, name:'Pie', symbolSize:20, symbol: 'image://../asset/ico/饼状图.png'},
                        {xAxis:5, y: 20, name:'Radar', symbolSize:20, symbol: 'image://../asset/ico/雷达图.png'},
                        {xAxis:6, y: 20, name:'Chord', symbolSize:20, symbol: 'image://../asset/ico/和弦图.png'},
                        {xAxis:7, y: 20, name:'Force', symbolSize:20, symbol: 'image://../asset/ico/力导向图.png'},
                        {xAxis:8, y: 20, name:'Map', symbolSize:20, symbol: 'image://../asset/ico/地图.png'},
                        {xAxis:9, y: 20, name:'Gauge', symbolSize:20, symbol: 'image://../asset/ico/仪表盘.png'},
                        {xAxis:10, y: 20, name:'Funnel', symbolSize:20, symbol: 'image://../asset/ico/漏斗图.png'},
                    ]
                }
            }
        ]
    };
    myChart.setOption(option);

}

function year() {
    var day=new Date();
    day.setTime(day.getTime());
    var years=day.getFullYear();
    var year =years-50;
    var month=day.getMonth()+1;
    var str="";
    for (var i=0;i<100;i++){
        if(year!=years)
        {
            str+="<option value='"+year+"'>"+year+"</option>";
            year++;
        }else{
            str+="<option value='"+year+"' selected='selected'>"+year+"</option>";
            year++;
        }
    }
    $("#year").html(str);

}

function month() {
    var day=new Date();
    day.setTime(day.getTime());
    var month=day.getMonth()+1;
    var str="";
    for (var i=1;i<13;i++){
        if(i!=month)
        {
            str+="<option value='"+i+"'>"+i+"</option>";
        }else{
            str+="<option value='"+i+"' selected='selected'>"+i+"</option>";
        }
    }
    $("#month").html(str)
}

function button() {
    $("#sumMoney").html("");
    $("#years").html("");
    $("#months").html("");
    $("#basis").html("");
    $("#ratio").html("");
    deptMonthlyPage();
}