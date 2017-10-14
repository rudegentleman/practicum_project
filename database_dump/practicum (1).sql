-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2017 at 11:28 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `practicum`
--

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `name` varchar(100) DEFAULT NULL,
  `secondName` varchar(100) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `Hash` varchar(200) DEFAULT NULL,
  `access code` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`name`, `secondName`, `userName`, `Hash`, `access code`) VALUES
(NULL, NULL, NULL, NULL, NULL),
('denis', 'denis', 'denis', 'denis', NULL),
('denis', 'denis', 'denis', '79aef731091472c4395b63b32b2c00c919b9d9538dc1c990381cc8c4609fe9f8', NULL),
('lohan', 'lohan', 'lohan', '2461d663220e18597d0994bf219f2da9f0ab07a4bd2c722b227bad654ce9c855', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `element_readings`
--

CREATE TABLE `element_readings` (
  `Id` int(12) NOT NULL,
  `oxygen` double NOT NULL,
  `carbon` double NOT NULL,
  `hydrogen` double NOT NULL,
  `temperature` double NOT NULL,
  `humidity` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `my_table`
--

CREATE TABLE `my_table` (
  `Date` date NOT NULL,
  `oxygen` int(45) NOT NULL,
  `mean_oxygen` varchar(255) NOT NULL,
  `carbon` int(45) NOT NULL,
  `mean_carbon` varchar(255) NOT NULL,
  `hydrogen` int(45) NOT NULL,
  `mean_hydrogen` varchar(255) NOT NULL,
  `temperature` int(45) NOT NULL,
  `mean_temperature` varchar(255) NOT NULL,
  `humidity` int(45) NOT NULL,
  `mean_humidity` int(45) NOT NULL,
  `through_num` int(45) NOT NULL,
  `Best record` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `element_readings`
--
ALTER TABLE `element_readings`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `element_readings`
--
ALTER TABLE `element_readings`
  MODIFY `Id` int(12) NOT NULL AUTO_INCREMENT;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
