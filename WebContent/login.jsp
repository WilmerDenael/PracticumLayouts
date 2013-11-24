<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/loginStyle.css" rel="stylesheet" type="text/css">
<title>Bienvenido a Practicum UV</title>
</head>
<body>
	<!-- Version 3.0 - CRUD funcional de alumnos y empresas -->
	<div id="main">
		<header>
			<div id="logo">
				<img alt="Universidad de Valparaiso" src="resources/css/img/menu_marca.gif" />
			</div>
			<h1>Portal Practicum UV</h1>
		</header>
		<div id="form1">
			<h2>Sistema de autentificación</h2>
			<form action="/Practicum/login">
				<label>Ingrese su rut.</label></br>
				<input type="text" name="rut" minlength=1 maxlength=10 title="12.345.678 o 12345678" pattern="[.0-9]{1,10}" autofocus required></br>
				<label>Ingrese su contraseña.</label></br>
				<input type="password" name="pass" title="Se necesita su contraseña" required></br>
				<input type="submit" name="boton" value="Iniciar sesión" class="button"/>
			</form>
		</div>
		<div id="form2">
			<h2>Registro</h2>
			<form action="/Practicum/registrar" method="get">
				<label>Ingrese su rut.</label></br>
				<input type="text" name="rut" minlength=1 maxlength=10 title="12.345.678 o 12345678" pattern="[.0-9]{1,10}" required></br>
				<label>Ingrese su correo electrónico.</label></br>
				<input type="email" name="email" title="Se necesita su correo electrónico" required></br>
				<label>Ingrese su contraseña.</label></br>
				<input type="password" name="pass" title="Se necesita su contraseña" required></br>
				<label>Ingrese su tipo de usuario.</label></br>
				<select name="type">
				  <option value="1">Alumno</option>
				  <option value="2">Empresa</option>
				</select></br>
				<input type="submit" name="boton" value="Registrarse" class="button"/>
			</form>
		</div>
	</div>
</body>
</html>