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
    
    initChartist: function(incomes,expenses){    
        var dataSales = {
          labels: ['9:00AM', '12:00AM', '3:00PM', '6:00PM', '9:00PM', '12:00PM', '3:00AM', '6:00AM'],
          series: [
             [287, 385, 490, 492, 554, 586, 698, 695, 752, 788, 846, 944],
            [67, 152, 143, 240, 287, 335, 435, 437, 539, 542, 544, 647],
            [23, 113, 67, 108, 190, 239, 307, 308, 439, 410, 410, 509]
          ]
        };
        var optionsSales = {
          lineSmooth: false,
          low: 0,
          high: 800,
          showArea: true,
          height: "245px",
          axisX: {
            showGrid: false,
          },
          lineSmooth: Chartist.Interpolation.simple({
            divisor: 3
          }),
          showLine: false,
          showPoint: false,
        };
        
        var responsiveSales = [
          ['screen and (max-width: 640px)', {
            axisX: {
              labelInterpolationFnc: function (value) {
                return value[0];
              }
            }
          }]
        ];
    
        Chartist.Line('#chartHours', dataSales, optionsSales, responsiveSales);
        var budget = [];
        budget[0] = incomes.split(",");
        budget[1] = expenses.split(",");
    	
       var numberOfBudget=budget[0].length;
       switch (numberOfBudget) {
       case 1:
    	   months=['1'];
           break; 
       case 2:
    	   months=['1','2'];
           break; 
       case 3: 
    	   months=['1','2','3'];
           break;
       case 4:
    	   months=['1','2','3','4'];
    	   break;
       case 5:
    	   months=['1','2','3','4','5'];
    	   break;
       case 6:
    	   months=['1','2','3','4','5','6'];
    	   break;
       case 7:
    	   months=['1','2','3','4','5','6','7'];
    	   break;
       case 8:
    	   months=['1','2','3','4','5','6','7','8'];
    	   break;
       case 9:
    	   months=['1','2','3','4','5','6','7','8','9'];
    	   break;
       case 10:
    	   months=['1','2','3','4','5','6','7','8','9','10'];
    	   break;
       case 11:
    	   months=['1','2','3','4','5','6','7','8','9','10','11'];
    	   break;
       case 12:
    	   months=['1','2','3','4','5','6','7','8','9','10','11','12'];
    	   break;
          }
       var months
        var data = {
        		
          labels: months,
          series: [
             budget[0],
             budget[1]
          ]
        };
        
        var options = {
            seriesBarDistance: 10,
            axisX: {
                showGrid: false
            },
            height: "245px"
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
        
        Chartist.Bar('#chartActivity', data, options, responsiveOptions);
    
        var dataPreferences = {
            series: [
                [25, 30, 20, 25]
            ]
        };
        
        var optionsPreferences = {
            donut: true,
            donutWidth: 40,
            startAngle: 0,
            total: 100,
            showLabel: false,
            axisX: {
                showGrid: false
            }
        };
    
        Chartist.Pie('#chartPreferences', dataPreferences, optionsPreferences);
        
        Chartist.Pie('#chartPreferences', {
          labels: ['75%','10%','15%'],
          series: [75, 10, 15]
        });   
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
        	icon: "pe-7s-gift",
        	message: "Welcome to <b>Light Bootstrap Dashboard</b> - a beautiful freebie for every web developer."
        	
        },{
            type: type[color],
            timer: 4000,
            placement: {
                from: from,
                align: align
            }
        });
	},


	validate:function (data,status)
	{
	
		 switch(data.status) {
         case "begin":
        	 color=6;
        	 $.notify({
              	icon: "pe-7s-gift",
              	message: "<b> Creating Transaction.... </b> <img src='assets/img/3.gif' width=\"64\" height=\"64\" /><br></br>."
              	
              },{
                  type: type[color],
                  timer: 2000,
                  placement: {
                 	 from: 'top',
                      align: 'center'
                  }
              });
        	 
             break;
         case "complete":
             break;
         case "success":
        	/* color=2;
	        	 $.notify({
	              	icon: "pe-7s-gift",
	              	message: "<div class=\"alert alert-success\"><button type=\"button\" aria-hidden=\"true\" class=\"close\">×</button><span><b> Success - </b> This is a regular notification made with \".alert-success\" <img src='assets/img/success.png' width=\"64\" height=\"64\" /><br>Loading..M</br></span>.</div>."
	              	
	              },{
	                  type: type[color],
	                  timer: 2000,
	                  placement: {
	                 	 from: 'top',
	                      align: 'center'
	                  }
	              });
	        	 */
        	 break;
         case "serverError":
        	 demo.handleError(data);
        	 break;
        
		
		 }
	
    
    
	},
	showSuccessNotification: function(from, align){
    	//color = Math.floor((Math.random() * 4) + 1);
		  color=2;
    	
    	$.notify({
        	icon: "pe-7s-gift",
        	message: "<div class=\"alert alert-success\"><button type=\"button\" aria-hidden=\"true\" class=\"close\">×</button><span><b> Success - </b> This is a regular notification made with \".alert-success\" <img src='assets/img/3.gif' width=\"64\" height=\"64\" /><br>Loading..M</br></span>.</div>."
        	
        },{
            type: type[color],
            timer: 4000,
            placement: {
                from: from,
                align: align
            }
        });
	},
	handleError:function (data) {
		color=4;
   	
   	 $.notify({
         	icon: "pe-7s-gift",
         	message: "<b> Error -"+data.errorMessage+" </b> "

         },{
             type: type[color],
             timer: 2000,
             placement: {
            	 from: 'top',
                 align: 'center'
             }
         });
   	
   	 
	}
    
}

