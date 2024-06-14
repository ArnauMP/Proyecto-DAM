<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'phpmailer/src/Exception.php';
require 'phpmailer/src/PHPMailer.php';
require 'phpmailer/src/SMTP.php';

function sendVerificationEmail($email, $verification_code){
    $mail = new PHPMailer(true);

    try{
        $mail->CharSet = 'UTF-8';
        $mail->isSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'kwt4122003@gmail.com'; // Your gmail
        $mail->Password = 'ilelwxnmiefqirqu'; // Your gmail app password
        $mail->SMTPSecure = 'ssl';
        $mail->Port = 465;
        
        $mail->setFrom('kwt4122003@gmail.com');
        
        $mail->addAddress($email);
        
        $mail->isHTML(true);
        
        $mail->Subject = "Verification Code";
        $mail->Body = "Verifica que eres tú usando el siguiente código:<br>" . $verification_code;
        
        $mail->send();
        return true;
    }
    catch (Exception $e){
        return false;
    }
}

function sendContactEmail($name, $email, $telf, $subject, $message){
    $mail = new PHPMailer(true);

    try{
        $mail->CharSet = 'UTF-8';
        $mail->isSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'kwt4122003@gmail.com'; // Your gmail
        $mail->Password = 'ilelwxnmiefqirqu'; // Your gmail app password
        $mail->SMTPSecure = 'ssl';
        $mail->Port = 465;
        
        $mail->setFrom('kwt4122003@gmail.com');
        
        $mail->addAddress('kwt4122003@gmail.com');
        
        $mail->isHTML(true);
        
        $mail->Subject = $subject;
        $mail->Body = "El usuario " . $name . " con email " . $email . " y número de teléfono " . $telf . " te ha mandado el siguiente mensaje:<br><br>" . $message;
        
        $mail->send();
        return true;
    }
    catch (Exception $e){
        return false;
    }
}

function sendForgotPassword($email){
    $mail = new PHPMailer(true);

    try{
        $mail->CharSet = 'UTF-8';
        $mail->isSMTP();
        $mail->Host = 'smtp.gmail.com';
        $mail->SMTPAuth = true;
        $mail->Username = 'kwt4122003@gmail.com'; // Your gmail
        $mail->Password = 'ilelwxnmiefqirqu'; // Your gmail app password
        $mail->SMTPSecure = 'ssl';
        $mail->Port = 465;
        
        $mail->setFrom('kwt4122003@gmail.com');
        
        $mail->addAddress($email);
        
        $mail->isHTML(true);
        
        $mail->Subject = "Change Password";
        $mail->Body = "Si quieres cambiar de contraseña, haz click en este enlace: http://kwt:8080/ChangePasswd";
        
        $mail->send();
        return true;
    }
    catch (Exception $e){
        return false;
    }
}
