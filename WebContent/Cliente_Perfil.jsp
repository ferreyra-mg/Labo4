<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.Cliente" %>
<%@ page import="funciones.Clientex" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Lato|Liu+Jian+Mao+Cao&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/2c36e9b7b1.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Perfil</title>
</head>
<body class="cl">
	
	
	<% //revisa que el usuario sea cliente
		if(session.getAttribute("tipoUsuario") != "cliente"  )
		{
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
	%>
	
	<nav class="mask">
		<div class="name-user">
			<%= session.getAttribute("nm_user") != null ? session.getAttribute("nm_user") : "" %>
		</div>
		<ul class="list">
			<li><a href="ServletMovimiento?mostrar=">Transferencia</a></li>
			<li><a href="Cliente_Home.jsp">Historial</a></li>
			<li><a href="Cliente_Prestamo.jsp">Prestamo</a></li>
			<li><a href="Cliente_Perfil.jsp">Perfil</a></li>
			<li><a href="ServletBanco?logout=">Cerrar Sesión</a></li>
		</ul>
	</nav>

	<form action="ServletDescolgable" method="post">
		<input type="submit" name="btn_TCPe" value="Traer Cuentas"> <!--  ABREVIATURA Traer Cliente Home-->
	</form>
	<form action="ServletCliente" method="post">
		<input type="submit" name="btn_traerInfoP" value="Mostar Info. Personal">
	</form>

	<% 
	ArrayList<Cuenta> cuentas = null;
	if(request.getAttribute("cuentas") != null) {
		cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
	}
	
	Cliente clienteLog = null;
	String sexo = "";
	String ubicacion = "";
	if(request.getAttribute("clienteSession")!=null){
		clienteLog = (Cliente)request.getAttribute("clienteSession");
		if(clienteLog.getSexo()){
			   sexo = "masculino";
			} else {
			   sexo = "femenino";
			}
		ubicacion = Clientex.obtenerUbicacion(clienteLog.getLocalidad(), clienteLog.getProvincia(), clienteLog.getNacionalidad());
	}			
	%>

	<div class="container-cperfil">
		<div class="perfil">
		
			Nombre: <%= clienteLog != null ? clienteLog.getApellido() + ", " + clienteLog.getNombre() : "" %> <br><br>
			DNI: <%= clienteLog != null ? clienteLog.getDni() : "" %> <br><br>
			Cuil: <%= clienteLog != null ? clienteLog.getCuil() : "" %> <br><br>
			Sexo: <%= sexo %> <br><br>
			Ubicación: <%= ubicacion %> <br><br>
			Nacimiento: <%= clienteLog != null ? clienteLog.getFechaNacimiento() : "" %> <br><br>
			Correo Electronico: <%= clienteLog != null ? clienteLog.getCorreoElectronico() : "" %> <br><br>
			Telefono: <%= clienteLog != null ? clienteLog.getTelefono() : "" %> <br><br>
			
		</div>
		<div class ="cartass">
		<%
			if (cuentas != null) {
			for (Cuenta cuenta : cuentas) {
				String tipo = Clientex.obtenerTipo(cuenta.getTipo());
		%>
		<div class = "btn-c1">
		    <img src="img/cuenta_bg.jpg" alt="cuenta">
		    <div class="card-content" > 
		      <h2>
		        <%=cuenta.getUsuario() %>
		      </h2>
		      <p>
				CBU: <%=cuenta.getCBU() %> 				<br>
				FECHA DE CREACION: <%=cuenta.getCreacion()%> 	<br>
				TIPO DE CUENTA: <%= cuenta.toString(tipo) %> 	<br>
				SALDO: <%=cuenta.getSaldo()%>
		      </p>
		    </div>
		  </div> <br><br>
		<%
			}
		}
		%>
		</div>
		
		  </div>
		  
		<div class="valores">
		
			<div class="contenedor-t">

				<!-- Tarjeta -->
				<section class="tarjeta" id="tarjeta">
					<div class="delantera">
						<div class="logo-marca" id="logo-marca">

						</div>
						<img src="./img/chip-tarjeta.png" class="chip" alt="">
						<div class="datos">
							<div class="grupo" id="numero">
								<p class="label">Número Tarjeta</p>
								<p class="numero">#### #### #### ####</p>
							</div>
							<div class="flexbox">
								<div class="grupo" id="nombre">
									<p class="label">Nombre Tarjeta</p>
									<p class="nombre">Grupo 9</p>
								</div>
		
								<div class="grupo" id="expiracion">
									<p class="label">Expiracion</p>
									<p class="expiracion"><span class="mes">MM</span> / <span class="year">AA</span></p>
								</div>
							</div>
						</div>
					</div>
		
					<div class="trasera">
						<div class="barra-magnetica"></div>
						<div class="datos">
							<div class="grupo" id="firma">
								<p class="label">Firma</p>
								<div class="firma"><p></p></div>
							</div>
							<div class="grupo" id="ccv">
								<p class="label">CCV</p>
								<p class="ccv"></p>
							</div>
						</div>
						<p class="leyenda">Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus exercitationem, voluptates illo.</p>
						<a href="#" class="link-banco">frgp.cvg.utn.edu.ar</a>
					</div>
				</section>
			</div>
		
		</div>
	
	<script>
		const tarjeta = document.querySelector('#tarjeta'),
		  btnAbrirFormulario = document.querySelector('#btn-abrir-formulario'),
		  formulario = document.querySelector('#formulario-tarjeta'),
		  numeroTarjeta = document.querySelector('#tarjeta .numero'),
		  nombreTarjeta = document.querySelector('#tarjeta .nombre'),
		  logoMarca = document.querySelector('#logo-marca'),
		  firma = document.querySelector('#tarjeta .firma p'),
		  mesExpiracion = document.querySelector('#tarjeta .mes'),
		  yearExpiracion = document.querySelector('#tarjeta .year');
		  ccv = document.querySelector('#tarjeta .ccv');
	
			//* Volteamos la tarjeta para mostrar el frente.
			const mostrarFrente = () => {
				if(tarjeta.classList.contains('active')){
					tarjeta.classList.remove('active');
				}
			}
			
			//* Rotacion de la tarjeta
			tarjeta.addEventListener('click', () => {
				tarjeta.classList.toggle('active');
			});
			
			// * Boton de abrir formulario
			btnAbrirFormulario.addEventListener('click', () => {
				btnAbrirFormulario.classList.toggle('active');
				formulario.classList.toggle('active');
			});

			// * Select del mes generado dinamicamente.
			for(let i = 1; i <= 12; i++){
				let opcion = document.createElement('option');
				opcion.value = i;
				opcion.innerText = i;
				formulario.selectMes.appendChild(opcion);
			}

			// * Select del año generado dinamicamente.
			const yearActual = new Date().getFullYear();
			for(let i = yearActual; i <= yearActual + 8; i++){
				let opcion = document.createElement('option');
				opcion.value = i;
				opcion.innerText = i;
				formulario.selectYear.appendChild(opcion);
			}

			// * Input numero de tarjeta
			formulario.inputNumero.addEventListener('keyup', (e) => {
				let valorInput = e.target.value;

				formulario.inputNumero.value = valorInput
				// Eliminamos espacios en blanco
				.replace(/\s/g, '')
				// Eliminar las letras
				.replace(/\D/g, '')
				// Ponemos espacio cada cuatro numeros
				.replace(/([0-9]{4})/g, '$1 ')
				// Elimina el ultimo espaciado
				.trim();

				numeroTarjeta.textContent = valorInput;

				if(valorInput == ''){
					numeroTarjeta.textContent = '#### #### #### ####';

					logoMarca.innerHTML = '';
				}

				if(valorInput[0] == 4){
					logoMarca.innerHTML = '';
					const imagen = document.createElement('img');
					imagen.src = 'img/logos/visa.png';
					logoMarca.appendChild(imagen);
				} else if(valorInput[0] == 5){
					logoMarca.innerHTML = '';
					const imagen = document.createElement('img');
					imagen.src = 'img/logos/mastercard.png';
					logoMarca.appendChild(imagen);
				}

				// Volteamos la tarjeta para que el usuario vea el frente.
				mostrarFrente();
			});

			// * Input nombre de tarjeta
			formulario.inputNombre.addEventListener('keyup', (e) => {
				let valorInput = e.target.value;

				formulario.inputNombre.value = valorInput.replace(/[0-9]/g, '');
				nombreTarjeta.textContent = valorInput;
				firma.textContent = valorInput;

				if(valorInput == ''){
					nombreTarjeta.textContent = 'Jhon Doe';
				}

				mostrarFrente();
			});

			// * Select mes
			formulario.selectMes.addEventListener('change', (e) => {
				mesExpiracion.textContent = e.target.value;
				mostrarFrente();
			});

			// * Select Año
			formulario.selectYear.addEventListener('change', (e) => {
				yearExpiracion.textContent = e.target.value.slice(2);
				mostrarFrente();
			});

			// * CCV
			formulario.inputCCV.addEventListener('keyup', () => {
				if(!tarjeta.classList.contains('active')){
					tarjeta.classList.toggle('active');
				}

				formulario.inputCCV.value = formulario.inputCCV.value
				// Eliminar los espacios
				.replace(/\s/g, '')
				// Eliminar las letras
				.replace(/\D/g, '');

				ccv.textContent = formulario.inputCCV.value;
			});
	</script>
</body>
</html>