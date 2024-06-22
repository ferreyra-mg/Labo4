<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/styles.css">
<title>Login</title>
</head>
<body>
<div class="page-login">
    <div class="container">
        <div class="screen">
            <div class="screen__content">
                <form id="login" method="post" action="ServletBanco">
                    <div class="login__field">
                        <i class="login__icon fas fa-user"></i>
                        <input type="text" class="login__input" placeholder="Ingrese su nombre" name="txt_user" required>
                    </div>
                    
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input" placeholder="Contrasenia" name="psw1" required>
                    </div>
                    
                    <div class="login__field">
                        <i class="login__icon fas fa-lock"></i>
                        <input type="password" class="login__input" placeholder="Repita su contrasenia" name="psw2" required>
                    </div>

                    <div class="error-message">
                        <%= request.getAttribute("msj_error") != null ? request.getAttribute("msj_error") : "" %>
                    </div>


                    <button type="submit" class="button login__submit">
                        <span class="button__text">Iniciar sesion</span>
                        <i class="button__icon fas fa-chevron-right"></i>
                    </button>
                </form>
            </div>
            <div class="screen__background">
                <span class="screen__background__shape screen__background__shape4"></span>
                <span class="screen__background__shape screen__background__shape3"></span>
                <span class="screen__background__shape screen__background__shape2"></span>
                <span class="screen__background__shape screen__background__shape1"></span>
            </div>
        </div>
    </div>
</div>

</body>
</html>