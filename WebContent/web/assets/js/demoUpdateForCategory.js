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
    
    initCategoryMonthly: function(actualValues,LimitValues){    
   
  
    
       
        var budget = [];
        budget[0] =LimitValues.split(",");
        budget[1] = actualValues.split(",");
        
    	
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
    
       
    }
    

    
	

    
}

