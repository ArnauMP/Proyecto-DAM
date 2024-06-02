<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    $email = $_POST['email'];
    $verification_code = $_POST['verification_code'];

    try {
        // Conectar a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Verificar el código de verificación
        $stmt = $conexion->prepare("SELECT verification_code, is_verified FROM users WHERE email = :email");
        $stmt->bindParam(':email', $email);
        $stmt->execute();
        $user = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($user && $user['verification_code'] == $verification_code) {
            if ($user['is_verified'] == 0) {
                // Actualizar el estado del usuario a verificado
                $update_stmt = $conexion->prepare("UPDATE users SET is_verified = 1 WHERE email = :email");
                $update_stmt->bindParam(':email', $email);
                $update_stmt->execute();
                echo "La cuenta ha sido verificada exitosamente.";
            } else {
                echo "La cuenta ya ha sido verificada.";
            }
        } else {
            // Eliminar el usuario de la base de datos si el código de verificación es incorrecto
            $delete_stmt = $conexion->prepare("DELETE FROM users WHERE email = :email");
            $delete_stmt->bindParam(':email', $email);
            $delete_stmt->execute();
            echo "Código de verificación incorrecto.";
        }
    } catch (PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
}
?>
