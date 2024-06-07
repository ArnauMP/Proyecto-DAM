<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    $host = 'localhost';
    $dbname = 'android';
    $usuario = 'root';
    $contraseña = '';

    // Parámetros recibidos por POST
    $email = isset($_POST['email']) ? $_POST['email'] : '';

    try {
        // Conectar a la base de datos
        $conexion = new PDO("mysql:host=$host;dbname=$dbname", $usuario, $contraseña);
        $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        // Preparar la consulta SQL para actualizar dieta
        $query = "UPDATE users SET diet = :diet WHERE email = :email";
        $stmt = $conexion->prepare($query);

        if (!empty($email)) {
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':diet', $diet);
        }

        // Ejecutar la consulta
        $stmt->execute();

        // Comprobar si algún registro fue actualizado
        if ($stmt->rowCount() > 0) {
            echo json_encode(array("success" => true, "message" => "Dieta actualizada correctamente"));
        } else {
            echo json_encode(array("success" => false, "message" => "No se encontró la dieta o no se necesitaban cambios en los datos"));
        }

    } catch (PDOException $e) {
        echo json_encode(array("success" => false, "message" => "Error: " . $e->getMessage()));
    }
}
?>