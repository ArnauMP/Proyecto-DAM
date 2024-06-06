<?php
    require 'send.php';

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $host = 'localhost';
        $dbname = 'android';
        $usuario = 'root';
        $contraseña = '';

        $name = $_POST['name'];
        $email = $_POST['email'];
        $telf = $_POST['telf'];
        $subject = $_POST['subject'];
        $message = $_POST['message'];

        try {

            // Enviar respuesta JSON en caso de éxito
            if (sendContactEmail($name, $email, $telf, $subject, $message)){
                echo json_encode(array("success" => true, "message" => "Mensaje de contacto enviado correctamente"));
            }
            else{
                echo json_encode(array("success" => false, "message" => "El envío del mensaje ha fallado"));
            }
        } catch (PDOException $e) {
            // Enviar respuesta JSON en caso de error
            echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
        }
    }
?>