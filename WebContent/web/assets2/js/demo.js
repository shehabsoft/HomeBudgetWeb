type = ['','info','success','warning','danger'];


demo = {
    initPickColor: function(){
        $('.pick-class-label').click(function(){
            var new_class = $(this).attr('new-class');
            var old_class = $('#display-buttons').attr('data-class');
            var display_div = $('#display-buttons');
            if(display_div.length) {
            var display_buttons = display_div.find('.btn');
            display_buttons.removeClass(old_class);
            display_buttons.addClass(new_class);
            display_div.attr('data-class', new_class);
            }
        });
    },

    initFormExtendedDatetimepickers: function(){
        $('.datetimepicker').datetimepicker({
            icons: {
                time: "fa fa-clock-o",
                date: "fa fa-calendar",
                up: "fa fa-chevron-up",
                down: "fa fa-chevron-down",
                previous: 'fa fa-chevron-left',
                next: 'fa fa-chevron-right',
                today: 'fa fa-screenshot',
                clear: 'fa fa-trash',
                close: 'fa fa-remove'
            }
         });
    },

    initDocumentationCharts: function(dayString,totalPriceString,expensesLabels,expensesValues,dayStringCategory,totalPriceStringCategory,maxPurchasesPercategory){
        /* ----------==========     Daily Sales Chart initialization For Documentation    ==========---------- */
    	var day = [];
    	var totalPrice=[];
    	day[0] = dayString.split(",");
    	totalPrice[0] = totalPriceString.split(",");
        dataDailySalesChart = {
            labels: day[0],
            series: [
                 [totalPrice[0]]
            ]
        };

        optionsDailySalesChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: 5000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
        }

        var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

        md.startAnimationForLineChart(dailySalesChart);
    },

    initDashboardPageCharts: function(dayString,totalPriceString,expensesLabels,expensesValues,dayStringCategory,totalPriceStringCategory,maxPurchasesPercategory){

        /* ----------==========     Daily Sales Chart initialization    ==========---------- */
    	var day = [];
    	var totalPrice=[];
    	day[0] = dayString.split(",");
    	totalPrice[0] = totalPriceString.split(",");
        dataDailySalesChart = {
           
        		labels:day[0],
            
    
            series: [totalPrice[0]]
                
            
        };

        optionsDailySalesChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: 5000	, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
        }

        var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

        md.startAnimationForLineChart(dailySalesChart);



        /* ----------==========     Completed Tasks Chart initialization    ==========---------- */
        var dayCategory = [];
    	var totalPriceCategory=[];
    	dayCategory[0] = dayStringCategory.split(",");
    	totalPriceCategory[0] = totalPriceStringCategory.split(",");
        dataCompletedTasksChart = {
            labels: dayCategory[0]	,
            series: [
                totalPriceCategory[0]
            ]
        };

        optionsCompletedTasksChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: maxPurchasesPercategory, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0}
        }

        var completedTasksChart = new Chartist.Line('#completedTasksChart', dataCompletedTasksChart, optionsCompletedTasksChart);

        // start animation for the Completed Tasks Chart - Line Chart
        md.startAnimationForLineChart(completedTasksChart);



        /* ----------==========     Emails Subscription Chart initialization    ==========---------- */
        var expensesLab = [];
    	var expensesVal=[];
    	expensesLab[0] = expensesLabels.split(",");
    	var LabelNum ="";
    	var LabelNumArr=[];
    	expensesVal[0] = expensesValues.split(",");
    	for(i=0;i<expensesLab[0].length;i++)
    		{
    		if(i==expensesLab[0].length-1)
    			{
    			LabelNum+=""+i;	
    			}else
    			 {
    			 LabelNum+=""+i+",";	
    			 }
    		
    		}
    	LabelNumArr[0]=LabelNum.split(",");
        var dataEmailsSubscriptionChart = {
          labels:	LabelNumArr[0],
          series: [
                expensesVal[0]

          ]
        };
        var optionsEmailsSubscriptionChart = {
            axisX: {
                showGrid: false
            },
            low: 0,
            high: 3000,
            chartPadding: { top: 0, right: 5, bottom: 0, left: 0}
        };
        var responsiveOptions = [
          ['screen and (max-width: 640px)', {
            seriesBarDistance: 5,
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];
        var emailsSubscriptionChart = Chartist.Bar('#emailsSubscriptionChart', dataEmailsSubscriptionChart, optionsEmailsSubscriptionChart, responsiveOptions);

        //start animation for the Emails Subscription Chart
        md.startAnimationForBarChart(emailsSubscriptionChart);

    },
    initExpensesCategories: function(ActualValues){

        /* ----------==========     Daily Sales Chart initialization    ==========---------- */

    	actualValues[0] = ActualValues.split(",");
    	for(i=0;i<actualValues[0].length;i++)
		{
		if(i==actualValues[0].length-1)
			{
			LabelNum+=""+i;	
			}else
			 {
			 LabelNum+=""+i+",";	
			 }
		
		}
    	LabelNumArr[0]=LabelNum.split(",");;
        dataDailySalesChart = {
           
        		labels:LabelNumArr[0],
            
    
            series: [actualValues[0]]
                
            
        };

        optionsDailySalesChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: 5000	, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
        }

        var dailySalesChart = new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);

        md.startAnimationForLineChart(dailySalesChart);



        /* ----------==========     Completed Tasks Chart initialization    ==========---------- */
        var dayCategory = [];
    	var totalPriceCategory=[];
    	dayCategory[0] = dayStringCategory.split(",");
    	totalPriceCategory[0] = totalPriceStringCategory.split(",");
        dataCompletedTasksChart = {
            labels: dayCategory[0]	,
            series: [
                totalPriceCategory[0]
            ]
        };

        optionsCompletedTasksChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: maxPurchasesPercategory, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0}
        }

        var completedTasksChart = new Chartist.Line('#completedTasksChart', dataCompletedTasksChart, optionsCompletedTasksChart);

        // start animation for the Completed Tasks Chart - Line Chart
        md.startAnimationForLineChart(completedTasksChart);




    }
    ,
    initalizeCategoryHistory:function(actualValues,finalValue,statusDate)
    {
        /* ----------==========     CategoryHistory Chart initialization    ==========---------- */
    	var LabelNumArr=[];
        var actualValuesArr=[];
        actualValuesArr= actualValues.split(",");
        var statusDateArr=[];
        statusDateArr=statusDate.split(",");
    	var LabelNum='';
    	for(i=0;i<actualValuesArr.length;i++)
    	{
    	if(i==actualValuesArr.length-1)
    		{
    		LabelNum+="Current Month ("+actualValuesArr[i]+")";	
    		}else
    		 {
    		 LabelNum+="("+statusDateArr[i]+")"+",";	
    		 }
    	
    	}
    	LabelNumArr[0]=LabelNum.split(",");;
    	var history=[];
    	history[0] = actualValues.split(",");
    	var max=history[0][0];
    	var MaxInt=parseInt(max);
    	for(i=1;i<history[0].length;i++)
    		{
    		if(MaxInt<parseInt(history[0][i]))
    			{
    			MaxInt=parseInt(history[0][i]);
    			}
    		}
    	if(MaxInt<parseInt(finalValue))
    		{
    		max=finalValue;
    		}
    	if(MaxInt>0 &&MaxInt<10)
		{
		MaxInt+=20;
		}
    	else if(MaxInt>10 &&MaxInt<100)
		{
		MaxInt+=10;
		}
    	else if(MaxInt>100 &&MaxInt<1000)
    		{
    		MaxInt+=100;
    		}
    	else if(MaxInt>1000 &&MaxInt <10000)
    		{
    		max+=1000;
    		}
    	else if(MaxInt>10000 &&MaxInt <100000)
    		{
    		MaxInt+=10000;
    		}
    	
    	
        dataCompletedTasksChart = {
            labels:LabelNumArr[0]	,
            series: [
                history[0]
            ]
        };

        optionsCompletedTasksChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: MaxInt, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0}
        }

        var completedTasksChart = new Chartist.Line('#completedTasksChart2', dataCompletedTasksChart, optionsCompletedTasksChart);

        // start animation for the Completed Tasks Chart - Line Chart
        md.startAnimationForLineChart(completedTasksChart);

    	
    },
    initalizePurchaseHistoryChart:function(actualValues,statusDate)
    {
        /* ----------==========     CategoryHistory Chart initialization    ==========---------- */
    	var LabelNumArr=[];
        var actualValuesArr=[];
        actualValuesArr= actualValues.split(",");
        
        var statusDateArr=[];
        statusDateArr=statusDate.split(",");
    	var LabelNum='';
    	for(i=0;i<actualValuesArr.length;i++)
    	{
    	if(i==actualValuesArr.length-1)
    		{
    		LabelNum+="Current Month ("+actualValuesArr[i]+")";	
    		}else
    		 {
    		 LabelNum+="("+statusDateArr[i]+")"+",";	
    		 }
    	
    	
    	}
    	LabelNumArr[0]=LabelNum.split(",");
    	var history=[];
    	history[0] = actualValues.split(",");
    	var max=history[0][0];
    	var MaxInt=parseInt(max);
    	for(i=1;i<history[0].length;i++)
    		{
    		if(MaxInt<parseInt(history[0][i]))
    			{
    			MaxInt=parseInt(history[0][i]);
    			}
    		}
     
    	if(MaxInt>0 &&MaxInt<10)
		{
		MaxInt+=20;
		}
    	else if(MaxInt>10 &&MaxInt<100)
		{
		MaxInt+=10;
		}
    	else if(MaxInt>100 &&MaxInt<1000)
    		{
    		MaxInt+=100;
    		}
    	else if(MaxInt>1000 &&MaxInt <10000)
    		{
    		max+=1000;
    		}
    	else if(MaxInt>10000 &&MaxInt <100000)
    		{
    		MaxInt+=10000;
    		}
    	
    	
        dataCompletedTasksChart = {
            labels:LabelNumArr[0]	,
            series: [
                history[0]
            ]
        };

        optionsCompletedTasksChart = {
            lineSmooth: Chartist.Interpolation.cardinal({
                tension: 0
            }),
            low: 0,
            high: MaxInt, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
            chartPadding: { top: 0, right: 0, bottom: 0, left: 0}
        }

        var completedTasksChart = new Chartist.Line('#completedTasksChart3', dataCompletedTasksChart, optionsCompletedTasksChart);
        var goButton=document.getElementById('go');
    	goButton.click();
        // start animation for the Completed Tasks Chart - Line Chart
        md.startAnimationForLineChart(completedTasksChart);
      

    	
    },
    initGoogleMaps: function(){
        var myLatlng = new google.maps.LatLng(40.748817, -73.985428);
        var mapOptions = {
          zoom: 13,
          center: myLatlng,
          scrollwheel: false, //we disable de scroll over the map, it is a really annoing when you scroll through page
          styles: [{"featureType":"water","stylers":[{"saturation":43},{"lightness":-11},{"hue":"#0088ff"}]},{"featureType":"road","elementType":"geometry.fill","stylers":[{"hue":"#ff0000"},{"saturation":-100},{"lightness":99}]},{"featureType":"road","elementType":"geometry.stroke","stylers":[{"color":"#808080"},{"lightness":54}]},{"featureType":"landscape.man_made","elementType":"geometry.fill","stylers":[{"color":"#ece2d9"}]},{"featureType":"poi.park","elementType":"geometry.fill","stylers":[{"color":"#ccdca1"}]},{"featureType":"road","elementType":"labels.text.fill","stylers":[{"color":"#767676"}]},{"featureType":"road","elementType":"labels.text.stroke","stylers":[{"color":"#ffffff"}]},{"featureType":"poi","stylers":[{"visibility":"off"}]},{"featureType":"landscape.natural","elementType":"geometry.fill","stylers":[{"visibility":"on"},{"color":"#b8cb93"}]},{"featureType":"poi.park","stylers":[{"visibility":"on"}]},{"featureType":"poi.sports_complex","stylers":[{"visibility":"on"}]},{"featureType":"poi.medical","stylers":[{"visibility":"on"}]},{"featureType":"poi.business","stylers":[{"visibility":"simplified"}]}]

        }
        var map = new google.maps.Map(document.getElementById("map"), mapOptions);

        var marker = new google.maps.Marker({
            position: myLatlng,
            title:"Hello World!"
        });

        // To add the marker to the map, call setMap();
        marker.setMap(map);
    },

	showNotification: function(from, align){
    	color = Math.floor((Math.random() * 4) + 1);

    	$.notify({
        	icon: "notifications",
        	message: "Welcome to <b>Material Dashboard</b> - a beautiful freebie for every web developer."

        },{
            type: type[color],
            timer: 4000,
            placement: {
                from: from,
                align: align
            }
        });
	}



}
