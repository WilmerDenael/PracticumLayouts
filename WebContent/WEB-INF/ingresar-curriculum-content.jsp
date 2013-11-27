<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>
    
<script type="text/javascript"> 
function crear() { 
	//alert("entra!");
 	papa=document.getElementById('idiomasSelect'); 
  	sel=document.createElement('select'); //creamos el select 
 	opt=document.createElement('option'); //creamos una opcion 
 	opt2=document.createElement('option'); //creamos una opcion
 	
  	opt.value='Ingles';
  	opt.innerHTML='Ingles'; 
  	
  	opt2.value='Frances';
  	opt2.innerHTML='Frances'; 
  	
  	sel.appendChild(opt);
  	sel.appendChild(opt2);
  	papa.appendChild(sel);
  	
  	

}
</script> 

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<div id="backContenido">
	<div id="contenido">
		<h2>Ingreso de curriculum:</h2>
		
		    
		    <form>
		    <div id="idiomasSelect">
		    </div>
		    	<!-- <select name="idiomas">
		    		<option value="Ingles">Ingles</option>
		    		<option value="Frances">Frances</option>
		    		<option value="Koreano">Koreano</option>
		    		<option value="Mapudungun">Mapudungun</option>
		    		
		    	</select> -->
		    	
		    </form>
		    <input type="button" value="Crear" onclick="crear()" /> 
		
		

		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>
  	</div>
</div>

