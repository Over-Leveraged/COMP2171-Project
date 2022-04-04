-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2022 at 04:11 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `guardsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `guardsdb`
--

CREATE TABLE `guardsdb` (
  `id` int(11) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `company` varchar(40) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `medical_exp` date DEFAULT NULL,
  `medical_path` varchar(255) DEFAULT NULL,
  `police_rec_exp` date DEFAULT NULL,
  `psra_exp` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE batch(batch_id VARCHAR(50) NOT NULL,training_name VARCHAR(250) ,location VARCHAR(255),date Date ,PRIMARY KEY(batch_id));
INSERT INTO batch VALUE ("bc1000", "Retraining", "kingston", "2022-03-31");

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users VALUE ("1", "tahjay", "thompson", "test@gmail.com","ladnjahvbcdhbd");

--
-- Dumping data for table `guardsdb`
--

INSERT INTO `guardsdb` (`id`, `fname`, `lname`, `company`, `contact`, `medical_exp`, `medical_path`, `police_rec_exp`, `psra_exp`) VALUES
(1, 'DJanae', 'Patterson', 'Marksman', 'dpatterson@test.com', '2022-03-10', NULL, NULL, NULL),
(10, 'Akiel', 'Walsh', 'Guardsman', 'awalsh@test.com', '2022-03-31', NULL, NULL, NULL),
(14, 'Djanae', 'Patterson2', 'Guardsman', 'dwalsh@test.com', '2022-09-07', NULL, NULL, NULL),
(16, 'Akiel', 'Pattersons', 'Guardsman', 'Contact@test.com', '2017-03-08', NULL, NULL, NULL),
(17, 'Jessa', 'James', 'Atlast', 'jj@test.com', '2022-03-24', 'part1.pdf', '2022-03-31', '2022-03-17');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guardsdb`
--
ALTER TABLE `guardsdb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guardsdb`
--
ALTER TABLE `guardsdb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
