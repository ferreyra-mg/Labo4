<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Login</title>
<script>
function TextoPass() {
    var passwordFields = document.querySelectorAll('.password-field');
    passwordFields.forEach(function(passwordField) {
	    if (passwordField.type === "password") {
	        passwordField.type = "text";
	    } else {
	        passwordField.type = "password";
	    }
    });
}

function onSubmit() {
	const usuario = document.querySelector("input[name='txt_user']");
	const checkRecordarPass = document.querySelector("input[name='recordar']");
	
	if (checkRecordarPass.checked) {
		window.localStorage.setItem('usuario', usuario.value);
	}else{
		window.localStorage.removeItem('usuario');
	}
	
	return true;
}

document.addEventListener("DOMContentLoaded", function() {
	const input_usuario = document.querySelector("input[name='txt_user']");
	const usuario = window.localStorage.getItem('usuario');
	
	console.log(usuario)
	
	if (usuario) {
		const checkRecordarPass = document.querySelector("input[name='recordar']");
		checkRecordarPass.checked = true;
		input_usuario.value = usuario;
	}
	
});

</script>
<style>
	
	.login-checkboxes {
		display: grid;
	}
	
	.login__submit {
		display: flex;
		justify-content: center;
	}
	
</style>
</head>
<body>
<div class="page-login">
    <div class="container">
        <div class="screen">
            <div class="screen__content">
                <form id="login" method="post" action="ServletBanco" onsubmit="onSubmit()" >
                    <div class="login__field">
                        <i class="login__icon fas fa-user"></i>
                        <input type="text" class="login__input" placeholder="Ingrese su usuario" name="txt_user" required>
                    </div>
                    
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input password-field" placeholder="Contrasenia" name="psw1" required>
                    </div>
                    
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input password-field" placeholder="Repita su contrasenia" name="psw2" required>
                    </div>
                    
					<div class="login__field login-checkboxes">
						<span>
                        	<input type="checkbox" name="recordar" onclick=""> Recordar usuario
						</span>
						<span>
	                        <input type="checkbox" onclick="TextoPass()"> Mostrar contraseñas
						</span>
                    </div>
					
                    <div class="error-message">
                        <%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
                    </div>

                    <button type="submit" class="button login__submit" name="btn_logear">
                        <span class="button__text">Iniciar sesion</span>
                    </button>
                </form>
            </div>
            <div class="screen__background">
                <span class="screen_background_shape screen_background_shape4"></span>
                <span class="screen_background_shape screen_background_shape3"></span>
                <span class="screen_background_shape screen_background_shape2"></span>
                <span class="screen_background_shape screen_background_shape1"></span>
            </div>
        </div>
    </div>
</div>

</body>
</html>
