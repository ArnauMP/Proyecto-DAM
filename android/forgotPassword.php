<?php
    require 'send.php';

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $email = $_POST['email'];

        if(sendForgotPassword($email)){
            echo json_encode(array("success" => true, "message" => "Correo de cambio de contraseña enviado correctamente"));
        }
    }
?>