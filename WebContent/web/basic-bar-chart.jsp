<!DOCTYPE HTML>
<html>
<head>
	<script type="text/javascript">
		window.onload = function () {
			var chart = new CanvasJS.Chart("chartContainer",
			{
				title:{
					text: "Bar chart"
				},

				data: [
				{
					type: "bar",

					dataPoints: [
					{ y: 90, label:"Apple" },
					{  y: 70, label:"Mango" },
					{ y: 50, label:"Orange" },
					{y: 60, label:"Banana" },
					{  y: 20, label:"Pineapple" },
					{ y: 30, label:"Pears" },
					{  y: 35, label:"Grapes" },
					{ y: 40, label:"Lychee" },
					{  y: 30, label:"Jackfruit" }
					]
				}
				]
			});

			chart.render();
		}
	</script>

 <script src="assets/js/canvasjs.min.js"></script>
 
	<title>CanvasJS Example</title>
</head>
<body>
	<div id="chartContainer" style="height: 400px; width: 100%;">
	</div>
</body>
</html>