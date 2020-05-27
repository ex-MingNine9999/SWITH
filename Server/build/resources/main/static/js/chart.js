var chartDrowFun = {

    chartDrow : function(){
        var chartData = '';

        //날짜형식 변경하고 싶으시면 이 부분 수정하세요.
        var chartDateformat     = 'HH시mm분ss초';
        //라인차트의 라인 수
        var chartLineCount    = 10;
        //컨트롤러 바 차트의 라인 수
        var controlLineCount    = 10;

        // 이 부분 수정 필요함 (방법을 몰라서 하드코딩함)
        var url = document.location.href;
        var kbSplit = url.split("/");
        var k = 0

        while(kbSplit[k] != null){
            k++;
        }
        /////////////////////////////////
        $.ajax({
            type: 'GET',
            url: '/api/v3/dataload/' + kbSplit[k-1],
            dataType: "text",
            contentType: 'application/json; charset=utf-8'
        }).done(function(receiveData) {
            if(receiveData == "null"){
                alert("기록된 집중도 데이터가 없습니다.");
            }
            else {
                chartData = receiveData;
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

        function drawDashboard() {

            var data = new google.visualization.DataTable();
            //그래프에 표시할 컬럼 추가
            data.addColumn('datetime' , '시간');
            data.addColumn('number'   , '전체평균 집중도');
            data.addColumn('number'   , '집중도');

            //그래프에 표시할 데이터
            var dataRow = [];

            var hours = 0;
            var minute = 0;
            var second = 0;

            var jbSplit = chartData.split(':');

            var i = 0;

            while(jbSplit[i] != null){
                if(second >= 60) {
                    minute++;
                    second = 0;
                }
                if(minute >= 60){
                    hours++;
                    minute = 0;
                }
                dataRow = [new Date("2020", "00", "00", hours, minute, second), 50, parseInt(jbSplit[i])];
                data.addRow(dataRow);
                i++;
                second += 10;
            }

            var chart = new google.visualization.ChartWrapper({
                chartType   : 'LineChart',
                containerId : 'lineChartArea', //라인 차트 생성할 영역
                options     : {
                    isStacked   : 'percent',
                    focusTarget : 'category',
                    height          : 500,
                    width              : '100%',
                    legend          : { position: "top", textStyle: {fontSize: 13}},
                    pointSize        : 5,
                    tooltip          : {textStyle : {fontSize:12}, showColorCode : true,trigger: 'both'},
                    hAxis              : {title: "시간", format: "HH:mm:ss", gridlines:{count:chartLineCount,units: {
                                hours : {format: ['HH시']},
                                minute: {format: ['mm분']},
                                second: {format: ['ss초']}}
                        },textStyle: {fontSize:12}},
                    vAxis              : {minValue: 100,viewWindow:{min:0},gridlines:{count:chartDateformat},textStyle:{fontSize:12}},
                    animation        : {startup: true,duration: 1000,easing: 'in' },
                    annotations    : {pattern: chartDateformat,
                        textStyle: {
                            fontSize: 15,
                            bold: true,
                            italic: true,
                            color: '#871b47',
                            auraColor: '#d799ae',
                            opacity: 0.8,
                            pattern: chartDateformat
                        }
                    }
                }
            });

            var control = new google.visualization.ControlWrapper({
                controlType: 'ChartRangeFilter',
                containerId: 'controlsArea',  //control bar를 생성할 영역
                options: {
                    ui:{
                        chartType: 'LineChart',
                        chartOptions: {
                            chartArea: {'width': '60%','height' : 80},
                            hAxis: {'baselineColor': 'none', format: chartDateformat, textStyle: {fontSize:12},
                                gridlines:{count:controlLineCount,units: {
                                        hours : {format: ['HH시']},
                                        minute: {format: ['mm분']},
                                        second: {format: ['ss']}}
                                }}
                        }
                    },
                    filterColumnIndex: 0
                }
            });

            var date_formatter = new google.visualization.DateFormat({ pattern: chartDateformat});
            date_formatter.format(data, 0);

            var dashboard = new google.visualization.Dashboard(document.getElementById('Line_Controls_Chart'));
            window.addEventListener('resize', function() { dashboard.draw(data); }, false); //화면 크기에 따라 그래프 크기 변경
            dashboard.bind([control], [chart]);
            dashboard.draw(data);

        }
        google.charts.setOnLoadCallback(drawDashboard);

    }
}

$(document).ready(function(){

    var content_id = "${content_id}";

    google.charts.load('current', {'packages':['line','controls']});
    chartDrowFun.chartDrow(content_id); //chartDrow() 실행
});