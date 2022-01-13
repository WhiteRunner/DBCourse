<!-- 统计图2 -->
<template>
	<div class="echarts-div" id='pic-chart' ref='pic-chart'></div>
</template>

<script>
	module.exports = {
		data() {
			return {
			}
		},
		methods: {

			// 刷新饼图
			f5PieChart: function() {
				// ===========================================  定义数据
                const that=this
                sa.ajax('/Orders/cnt',function(res) {
                    var list=res.data
                    var dataArray = []
                     var statusArray=['','订单创建','订单进行','订单完成','订单取消']
                    list.forEach((item,index,array)=>{
                        var type=statusArray[item.orderStatus]
                        var cnt=item.priceSum
                        dataArray.push({name:type,value:cnt})
                    })
			
				// ===========================================  开始渲染
				    var myChart = echarts.init(that.$refs['pic-chart']);
				    option = {
                        title: {
                            // text: '订单金额统计',
                            left: 'left',
                            top: 0,
                            textStyle: {
                                color: '#666',
                                fontSize: '14'
                            }
                        },
                        toolbox: {
                            show: true,
                            top: 0,
                            feature: {
                                saveAsImage: {
                                    show: true
                                }
                            }
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series: [{
                            name: '订单金额统计',
                            type: 'pie',
                            radius: '70%', // 半径大小
                            center: ['50%', '60%'],
                            selectedMode: 'single',
                            // roseType: 'radius',
                            data: dataArray.sort(function(a, b) {
                                return a.value - b.value;
                            }),
                            // roseType: 'area', // 半径模式还是面积模式
                            itemStyle: {
                                normal: {
                                    color: function(params) {
                                        // build a color map as your need.
                                        var colorList = [
                                            '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
                                            '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
                                            '#1e90ff','#ff6347','#7b68ee','#00fa9a',
                                            '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'
                                        ];
                                        // '#ffd700',
                                        function GetRandomNum(Min, Max) {
                                            var Range = Max - Min;
                                            var Rand = Math.random();
                                            return (Min + Math.round(Rand * Range));
                                        }
                                        var index = GetRandomNum(0, colorList.length - 1);
                                        return colorList[index];
                                        //return colorList[params.dataIndex]
                                    }
                                }
                            },
                            label: {
                                normal: {
                                    formatter: '{b|{b}：}{c}  {per|{d}%}  ',
                                    rich: {}
                                }
                            },
                            // 弹出动画 
                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }]
                    };
				    myChart.setOption(option);
				    window.myChartList.push(myChart);
                })

			},
		},
		created() {
			// 刷新所有图标数据
			this.$nextTick(function() {
				this.f5PieChart();
			});
		}
	}
</script>

<style scoped>
	
</style>
