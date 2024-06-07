-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-06-2024 a las 23:07:58
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `android`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `diets`
--

CREATE TABLE `diets` (
  `meal1` varchar(50) NOT NULL,
  `meal2` varchar(50) NOT NULL,
  `meal3` varchar(50) NOT NULL,
  `meal4` varchar(50) NOT NULL,
  `meal5` varchar(50) NOT NULL,
  `pre_workout` varchar(50) NOT NULL,
  `post_workout` varchar(50) NOT NULL,
  `diet_id` int(11) NOT NULL,
  `type` enum('DEFINITION','VOLUME','NEUTRAL','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `diets`
--

INSERT INTO `diets` (`meal1`, `meal2`, `meal3`, `meal4`, `meal5`, `pre_workout`, `post_workout`, `diet_id`, `type`) VALUES
('Bocadillo, Huevos, Plátano', 'Cereales, Kiwi, Tortilla', 'Tostadas con mermelada, pizza', 'Lentejas2', 'Hamburguesita buena', 'Yogur', 'Batidito de proteínas', 4, 'VOLUME'),
('Bocadillo, Huevos, Plátano', 'Cereales, Kiwi, Tortilla', 'Tostadas con mermelada, pizza', 'Lentejas', 'Hamburguesita buena', 'Yogur', 'Batidito de proteínas', 5, 'DEFINITION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trainings`
--

CREATE TABLE `trainings` (
  `exercise1` varchar(50) NOT NULL,
  `exercise2` varchar(50) NOT NULL,
  `exercise3` varchar(50) NOT NULL,
  `exercise4` varchar(50) NOT NULL,
  `exercise5` varchar(50) NOT NULL,
  `exercise6` varchar(50) NOT NULL,
  `training_id` int(11) NOT NULL,
  `type` enum('HYPERTROPHY','STRENGTH','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `trainings`
--

INSERT INTO `trainings` (`exercise1`, `exercise2`, `exercise3`, `exercise4`, `exercise5`, `exercise6`, `training_id`, `type`) VALUES
('sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x4 reps', 2, 'HYPERTROPHY'),
('sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 'sentadilla 15x3 reps', 3, 'STRENGTH');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `verification_code` varchar(10) NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  `telf` varchar(20) DEFAULT NULL,
  `role` enum('ADMIN','TRAINER','VIP','FREE') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `weight` int(10) DEFAULT NULL,
  `sportFrecuency` enum('NEVER','MONTHLY','WEEKLY','DAILY') NOT NULL,
  `diet` int(11) DEFAULT NULL,
  `training` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`name`, `password`, `email`, `verification_code`, `is_verified`, `telf`, `role`, `birthday`, `weight`, `sportFrecuency`, `diet`, `training`) VALUES
('Arnau', '1234', 'ejemplo2@gmail.com', '1234', 1, '1234568789', 'VIP', '0000-00-00', 50, 'DAILY', NULL, NULL),
('Arnau', '1234', 'ejemplo@gmail.com', '1234', 1, '1234568789', 'VIP', '0000-00-00', 50, 'DAILY', 4, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `diets`
--
ALTER TABLE `diets`
  ADD PRIMARY KEY (`diet_id`);

--
-- Indices de la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD PRIMARY KEY (`training_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`),
  ADD KEY `fk_diet` (`diet`),
  ADD KEY `fk_training` (`training`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `diets`
--
ALTER TABLE `diets`
  MODIFY `diet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `trainings`
--
ALTER TABLE `trainings`
  MODIFY `training_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_diet` FOREIGN KEY (`diet`) REFERENCES `diets` (`diet_id`),
  ADD CONSTRAINT `fk_training` FOREIGN KEY (`training`) REFERENCES `trainings` (`training_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
