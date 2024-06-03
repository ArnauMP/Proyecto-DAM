<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    // Parámetros recibidos por POST
    $email = isset($_POST['email']) ? $_POST['email'] : '';
    $name = isset($_POST['name']) ? $_POST['name'] : '';
    $telf = isset($_POST['telf']) ? $_POST['telf'] : '';

    try {
        // Conectar a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Preparar la consulta SQL para actualizar nombre y teléfono
        $query = "UPDATE users SET name = :name, telf = :telf WHERE email = :email";
        $stmt = $conexion->prepare($query);

        if (!empty($email)) {
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':telf', $telf);
        }

        // Ejecutar la consulta
        $stmt->execute();

        // Comprobar si algún registro fue actualizado
        if ($stmt->rowCount() > 0) {
            echo json_encode(array("success" => true, "message" => "Usuario actualizado correctamente"));
        } else {
            echo json_encode(array("success" => false, "message" => "No se encontró el usuario o no se necesitaban cambios en los datos"));
        }

    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>
