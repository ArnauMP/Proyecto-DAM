<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'phpmailer/src/Exception.php';
require 'phpmailer/src/PHPMailer.php';
require 'phpmailer/src/SMTP.php';

function sendVerificationEmail($email, $verification_code){
    $mail = new PHPMailer(true);

    try{
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
        $mail->Body = "Verifica que eres tú usando el siguiente código: \n " . $verification_code;
        
        $mail->send();
        return true;
    }
    catch (Exception $e){
        return false;
    }
}
