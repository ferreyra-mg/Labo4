<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<nav class="mask">
		[nombre del usuario]
		<ul class="list">
			<li><a href="Cliente_Transferencia.jsp">Transferencia</a></li>
			<li><a href="Cliente_Home.jsp">Historial</a></li>
			<li><a href="prestamos">Prestamo</a></li>
			<li><a href="Cliente_Perfil.jsp">Perfil</a></li>
		</ul>
	</nav>

	<div class="container-cperfil">
		<div class="perfil">
			Nombre: [APELLIDO, NOMBRE] <br><br>
			DNI: [VALOR] <br><br>
			Cuil: [VALOR] <br><br>
			Sexo: [VALOR] <br><br>
			Ubicación: [LOCALIDAD, PROCINVIA, NACIONALIDAD] <br><br>
			Nacimiento: [VALOR] <br><br>
			Correo Electronico: [VALOR] <br><br>
			Telefono: [VALOR] <br><br>
			
		</div>
		
		
		<div class = "btn-c1">
		    <img src="img/cuenta_bg.jpg" alt="">
		    <div class="card-content">
		      <h2>
		        [CUENTA 1]
		      </h2>
		      <p>
				USUARIO: [VALOR] <br>
				CBU: [VALOR] <br>
				FECHA DE CREACION: [VALOR] <br>
				TIPO DE CUENTA: [VALOR] <br>
				SALDO: [VALOR]
		      </p>
		    </div>
		  </div>
		
		<div class = "btn-c2">
		    <img src="img/cuenta_bg.jpg" alt="">
		    <div class="card-content">
		      <h2>
		        [CUENTA 2]
		      </h2>
		      <p>
				USUARIO: [VALOR] <br>
				CBU: [VALOR] <br>
				FECHA DE CREACION: [VALOR] <br>
				TIPO DE CUENTA: [VALOR] <br>
				SALDO: [VALOR]
		      </p>
		    </div>
		  </div>
		  
		  <div class = "btn-c3">
		    <img src="img/cuenta_bg.jpg" alt="">
		    <div class="card-content">
		      <h2>
		        [CUENTA 3]
		      </h2>
		      <p>
				USUARIO: [VALOR] <br>
				CBU: [VALOR] <br>
				FECHA DE CREACION: [VALOR] <br>
				TIPO DE CUENTA: [VALOR] <br>
				SALDO: [VALOR]
		      </p>
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