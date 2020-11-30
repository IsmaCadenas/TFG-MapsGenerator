
   
   /*<![CDATA[*/
	   var colorsLegend = {'colores': {} }; 
	   var labelsLegend = {'labels': {  } }; 
//Variables globales
//Border color
var borderColor = [[${mapForm.borderColor}]];
//Background color
var backgroundColor = [[${mapForm.background}]];
//Color inicial de las areas
var areaInitColor= "#c2c1c1";
//Variable donde se cargan los datos del fill de las areas 
var data = {'areas': {  } }; 
//Variables leyenda
var legendTitleColor = [[${mapForm.legendTitleColor}]] == null ? 'black' : [[${mapForm.legendTitleColor}]];
var legendTitleSize = [[${mapForm.legendTitleSize}]] == null ? '20' : [[${mapForm.legendTitleSize}]];
var legendTitleFont = [[${mapForm.legendTitleFont}]] == null ? 'Arial' : [[${mapForm.legendTitleFont}]];
  
var legendLabelColor = [[${mapForm.legendLabelColor}]] == null ? 'black' : [[${mapForm.legendLabelColor}]];
var legendLabelSize = [[${mapForm.legendLabelSize}]] == null ? '12' : [[${mapForm.legendLabelSize}]];
var legendLabelFont = [[${mapForm.legendLabelFont}]] == null ? 'Arial' : [[${mapForm.legendLabelFont}]];
//Variable leyenda
var leyenda = { 'legend' : {	'area' : { 'mode' : "vertical", 'display' : true, 'hideElemsOnClick': {'enabled':false, 'opacity' : 1, 'animDuration': 0}, 'titleAttrs': { 'font-family':legendTitleFont, 'fill' : legendTitleColor, 'font-size' : legendTitleSize}, 'labelAttrs': { 'font-family':legendLabelFont , 'fill' : legendLabelColor , 'font-size' : legendLabelSize }, 'title' : [[${mapForm.legendTitle}]],'slices': []}}};
   
   //Algoritmo para procesar los datos y poder presentarlos correctamente para que el mapa los represente
   if([[${drawAreas}]]){
	   var a=[[${areas}]];  
		var regiones = a.split(",");
		for(i=0; i < regiones.length-1 ; i++){
			var regiones1 = regiones[i].split(":");
			var color = regiones1[3].split(" ");
			var region1 = regiones1[0].replace(/['"]+/g, '');
		    var region = region1.replace(" ", '');
			data.areas[region] = {
			           attrs: {
			               fill: color[0].replace(/['" }]+/g, '')
			           }
			       };
			colorsLegend.colores[region]=color[0].replace(/['" }]+/g, '');
		  }
   }
   
 //Algoritmo para procesar los datos de la leyenda y poder presentarlos correctamente 
   if([[${drawLegend}]]){
	  
	   	var legends=[[${legends}]];
		var leyendas = legends.split("}, {");
		
		for(i=0; i < leyendas.length ; i++){
			
			var datos = leyendas[i].split(":");
			var fill0 = datos[2].replace("}", '');
			var fills = fill0.split(",");
			var fill = fills[0].replace(/['"]+/g, '').trim() ;
			var label =datos[4].replace("}]", '').replace(/['"]+/g, '').trim() ;
			
			leyenda.legend.area.slices[i]={'attrs': {'fill': fill, 'stroke': fill },'label': label};
		
			$("#sliceColor"+i).val(fill);
			$("#sliceText"+i).val(label);
			
		  }
		
   }
   
   //Variable en la que se cargan las actulizaciones del fill de las areas. Se le cargan las areas con un fill establecido previamente
   var newData = data;
   //Procesar las tooltips
   var areasTooltip = {'areas': {  } }; 
   var tooltips = [[${tooltipsText}]];
   var regiones = tooltips.split(",");
   for(i=0; i < regiones.length-1 ; i++){
	   var regiones1 = regiones[i].split(":");
	   var region1 = regiones1[0].replace(/['"{]+/g, '');
	   var region = region1.replace(" ", '');
	   var tooltips = regiones1[3].replace(/['"}]+/g, '');
	   areasTooltip.areas[region] = {
	           tooltip: {
	               content: "<span style=\"font-weight:bold;\">" + tooltips  + "</span><br />"
	           }
	       };
	  }
   
   function changeSelectedColor(value) {
	   areaInitColor=value;
	   var color = {
	           'map': {'defaultArea' : {'attrsHover' : { 'fill' : areaInitColor}}}
       };
	   $(".mapcontainer").trigger('update', [{mapOptions: color}]);
   }
   
   function changeBackground(value) {
	   backgroundColor=value;
	   $("#contenedor").css("background-color", backgroundColor);
	   $("#mapa").css("background-color", backgroundColor);
       return value;
   }
   
   function changeBordersColor(value) {
	   borderColor=value;
	   var color = {
	           'map': {'defaultArea' : {'attrs' : { 'stroke' : borderColor}}}
       };
      
       $(".mapcontainer").trigger('update', [{mapOptions: color}]);
   }
   
   function changeLegendTitle(value) {
	   leyenda.legend.area.title=value;
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   function changeLegendPosition(value) {
	   leyenda.legend.area.mode=value;
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
	   if(value=="vertical"){
		   $(".mapcontainer").css("display","flex");
		   $(".mapcontainer").css("flex-direction","row");
	   }else{
		   $(".mapcontainer").css("flex-direction","column");
	   }
	   
   }
   
   function showLegend(value) {
	   if(value=="true"){
		   $(".areaLegend").css("visibility", "visible");
		   $("#areaLegend").css("visibility", "visible");
	   }else if (value=="false"){
		   $(".areaLegend").css("visibility", "hidden");
		   $("#areaLegend").css("visibility", "hidden");
	   }
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   
   
   
   
   
   function changeLegendTitleColor(value){
	   leyenda.legend.area.titleAttrs={ 'fill' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   
   function changeLegendTitleFont(value){
	   leyenda.legend.area.titleAttrs={ 'font-family' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   
   function changeLegendTitleSize(value){
	   leyenda.legend.area.titleAttrs={ 'font-size' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   function changeLegendLabelColor(value){
	   leyenda.legend.area.labelAttrs={ 'fill' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   
   function changeLegendLabelFont(value){
	   leyenda.legend.area.labelAttrs={ 'font-family' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }
   
   function changeLegendLabelSize(value){
	   leyenda.legend.area.labelAttrs={ 'font-size' : value};
	   $(".mapcontainer").trigger('update', [{mapOptions: leyenda}]);
   }

   
   
   function updateLegend(){
		    var map = {map: { name: [[${map}]],zoom: { enabled: false },  defaultArea: {  attrs: { fill: "#c2c1c1" ,stroke: borderColor, cursor: "pointer" }, attrsHover: { animDuration: 50, fill: document.getElementById("selectedColor").value , cursor: "pointer" }, eventHandlers: { click: function (e, id, mapElem, textElem) { newData.areas[id] = { attrs: { fill: document.getElementById("selectedColor").value      } }; 
		   var l = JSON.stringify(newData.areas);	
			   $(".area1").val(l); 
			   $(".edited").val("true");
			   $(".mapcontainer").trigger('update', [{mapOptions: newData}]);
	           colorsLegend.colores[id]=newData.areas[id].attrs.fill;
	
	           addSliceColor();
	           updateLegend();
		    } }  }} };
		    
		   leyenda.legend.area.slices=[];
		 	//Actualizará la leyenda con los datos guardados
	       $(".mapcontainer").trigger('update', [{ mapOptions: leyenda , replaceOptions:true}]);
	       //Actualizara con la variable de los datos de las áreas
	       $(".mapcontainer").trigger('update', [{ mapOptions: newData }]);
	       //Actuliza con los tooltips de las areas
	       $(".mapcontainer").trigger('update', [{ mapOptions: areasTooltip }]);
	       $(".mapcontainer").trigger('update', [{ mapOptions: map }]);
	       changeBordersColor($("#selectedBorderColor").val());
	       changeBackground($("#selectedBackgroundColor").val());	
	       changeLegend();
   }
   
	var counterSlices= 0;
   
   function changeLegend(){
	   var leyendaActualizada = { 'legend' : {	'area' : { 'slices': [] }}};
	   
	   for(i=0 ; i< counterSlices; i++){
		   leyendaActualizada.legend.area.slices[i]={'attrs': {'fill': $("#sliceColor"+i).val(), 'stroke': $("#sliceColor"+i).val() },'label': $("#sliceText"+i).val()};
		  
			   labelsLegend.labels[$("#sliceColor"+i).val()] = {
	                   label: $("#sliceText"+i).val(),
	                   color: $("#sliceColor"+i).val()
	           };
		   
			   
	   }
	   $(".legendInput").val(JSON.stringify(leyendaActualizada.legend.area.slices));
	   $(".mapcontainer").trigger('update', [{mapOptions: leyendaActualizada}]);
	   $(".editedLegend").val("true");
   }
   
   function addSlice(color){
	   var label = labelsLegend.labels[color]!=null ? labelsLegend.labels[color].label : '&nbsp;';
	   var color1 = "<input value=" + color +" id='sliceColor"+counterSlices +"' type='color' onChange=changeLegend()> ";
	   var input = "<input value='"+ label + "' id='sliceText"+counterSlices +"' type='text' onChange=changeLegend()><br>";
	   var div = "<div id=slice"+counterSlices+">"+ color1 + input + "</div>"
	   counterSlices++;
	   $("#slicesLegend").append(div);

   }
   
   
   function addSliceColor(){
	   for(i=0;i<counterSlices;i++){
		   var legend =document.getElementById("slice" + i);
		   legend.remove(legend);
	   }
	   counterSlices=0;
	  
  	 var coloresLeg=[];
  	 var colores = JSON.stringify(colorsLegend.colores).split(",");
     for(i=0; i < colores.length ; i++){
    	 var color = colores[i].split(":");
    	 coloresLeg[i]=color[1].replace(/['"}]+/g, '');
     }
     //updateLegend();
     
     const coloresFinal = []
     const myObj = {};
     coloresLeg.forEach(el => { if(!(el in myObj)){ myObj[el] = true; coloresFinal.push(el); } });

     
     for(i=0; i < coloresFinal.length ; i++){
    	 addSlice(coloresFinal[i]);
     }
   }
     
   
   
   //Establezco el background del mapa cargado. Si es un mapa nuevo toma el valor que le paso en el controlador
  function load() {
	   changeBackground(backgroundColor);
	   if([[${drawLegend}]]){
		   var legends=[[${legends}]];
			var leyendas = legends.split("}, {");
			for(i=0; i < leyendas.length ; i++){
				var datos = leyendas[i].split(":");
				var fill0 = datos[2].replace("}", '').replace(/['"]+/g, '');
				var fills = fill0.split(",");
				var fill = fills[0].trim() ;
				var label =datos[4].replace("}]", '').replace(/['"]+/g, '').trim() ;
				leyenda.legend.area.slices[i]={'attrs': {'fill': fill, 'stroke': fill },'label': label};
				addSlice(fill);
				$("#sliceColor"+i).val(fill);
				$("#sliceText"+i).val(label);
				labelsLegend.labels[fill] = {
		                   label: label,
		                   color: fill
		           };
			  }
			console.log(labelsLegend);
	   }
  }
  window.onload = load;
  
        $(function () {
            $(".mapcontainer").mapael({
            	map: {
                    name: [[${map}]],
                    zoom: {
                        enabled: false
                    },
                    defaultArea: {
                        attrs: {
                            fill: areaInitColor,
                            stroke: borderColor,
                            "stroke-width": 1,
                            cursor: "pointer"
                        },
                        attrsHover: {
                            animDuration: 50,
                            fill: document.getElementById("selectedColor").value ,
                            cursor: "pointer"
                        },
                        eventHandlers: {
                            click: function (e, id, mapElem, textElem) {
                            	
                                    newData.areas[id] = {
                                        attrs: {
                                            fill: document.getElementById("selectedColor").value                                            
                                        }
                                    };
                                var l = JSON.stringify(newData.areas);
                            	$(".area1").val(l);
                                $(".mapcontainer").trigger('update', [{mapOptions: newData}]);
                                $(".edited").val("true");
                                
                                colorsLegend.colores[id]=newData.areas[id].attrs.fill;
                                addSliceColor();
                            	updateLegend();
                                
                            }
                        }
                    }
                },
                legend : {
                	area : { 
                			}
                },
                areas: {
                    "ES-O": {
                        "text": {"content": "Asturias", attrs: {"font-size" : 9}}
                    }
                }
            });
            //Actualizará la leyenda con los datos guardados
            $(".mapcontainer").trigger('update', [{ mapOptions: leyenda }]);
            //Actualizara con la variable de los datos de las áreas
            $(".mapcontainer").trigger('update', [{ mapOptions: newData }]);
            //Actuliza con los tooltips de las areas
            $(".mapcontainer").trigger('update', [{ mapOptions: areasTooltip }]);
            
            $(".namecode").val([[${map}]]);
        });
        /*]]>*/
