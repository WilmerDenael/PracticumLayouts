<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.practicum.util.UserBean"%>
    

<% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>

<form action="/Practicum/ingresarCurriculum">

<h2>Datos academicos</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
							<label for="descripcion_da">
								Descipcion:
							</label>
						</td>
						<td>
							<input type="text" id="descripcion_da" name="descripcion_da" />
						</td>
						<td>
							<label for="establecimiento_da">
								Establecimiento:
							</label>
						</td>
						<td>
							<input type="text" id="establecimiento_da" name="establecimiento_da" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fechaInicio_da">
								Fecha Inicio:
							</label>
						</td>
						<td>
							<input type="date" id="fechaInicio_da" name="fechaInicio_da" />
						</td>
						<td>
							<label for="fechaTermino_da">
								Fecha Termino:
							</label>
						</td>
						<td>
							<input type="date" id="fechaTermino_da" name="fechaTermino_da" />
						</td>
					</tr>
				</table>
			</fieldset>
</br>
<h2>Historial laboral</h2>
			<fieldset>
				<span class="tab">
					<a href="#" onclick="cloneMe(this); return false;" class="cloneMe" title="Add">+</a>
					<a href="#" onclick="deleteMe(this); return false;" class="deleteMe" title="Delete">x</a>
				</span>
				<table cellspacing="10">
					<tr>
						<td>
							<label for="descripcion_hl">
								Descipcion:
							</label>
						</td>
						<td>
							<input type="text" id="descripcion_hl" name="descripcion_hl" />
						</td>
						<td>
							<label for="establecimiento_hl">
								Establecimiento:
							</label>
						</td>
						<td>
							<input type="text" id="establecimiento_hl" name="establecimiento_hl" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="cargo_hl">
								Cargo:
							</label>
						</td>
						<td>
							<input type="text" id="cargo_hl" name="cargo_hl" />
						</td>
					</tr>
					<tr>
						<td>
							<label for="fechaInicio_hl">
								Fecha Inicio:
							</label>
						</td>
						<td>
							<input type="date" id="fechaInicio_hl" name="fechaInicio_hl" />
						</td>
						<td>
							<label for="fechaTermino_hl">
								Fecha Termino:
							</label>
						</td>
						<td>
							<input type="date" id="fechaTermino_hl" name="fechaTermino_hl" />
						</td>
					</tr>
				</table>
			</fieldset>
		</br>
      <select multiple class="multiselect" id="areas" name="areas[]">
      <option value="area_interes_1">software</option>
      <option value="area_interes_2">base de datos/option>
      <option value="area_interes_3">redes</option>
      <option value="area_interes_4">asdf</option>
      </br>
      <input type="submit">
      
</form></br>
		
		<a href="index.jsp" target="_self"> <input type="button" name="boton" value="volver" /> </a>


