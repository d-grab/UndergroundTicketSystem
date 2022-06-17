-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 16, 2022 at 09:57 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `underg`
--

-- --------------------------------------------------------

--
-- Table structure for table `fares`
--

CREATE TABLE `fares` (
  `fareid` int(11) NOT NULL,
  `fromtime` time DEFAULT NULL,
  `totime` time DEFAULT NULL,
  `fromzone` int(11) NOT NULL,
  `tozone` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fares`
--

INSERT INTO `fares` (`fareid`, `fromtime`, `totime`, `fromzone`, `tozone`, `price`) VALUES
(1, NULL, NULL, 1, 1, 10),
(2, NULL, NULL, 1, 2, 11),
(3, NULL, NULL, 1, 3, 12),
(4, NULL, NULL, 1, 4, 13),
(5, NULL, NULL, 1, 5, 14),
(6, NULL, NULL, 1, 6, 15),
(7, NULL, NULL, 2, 2, 7),
(8, NULL, NULL, 2, 3, 8),
(9, NULL, NULL, 2, 4, 9),
(10, NULL, NULL, 2, 5, 10),
(11, NULL, NULL, 2, 6, 11),
(12, NULL, NULL, 3, 3, 5),
(13, NULL, NULL, 3, 4, 6),
(14, NULL, NULL, 3, 5, 7),
(15, NULL, NULL, 3, 6, 8),
(16, NULL, NULL, 4, 4, 4),
(17, NULL, NULL, 4, 5, 5),
(18, NULL, NULL, 4, 6, 6),
(19, NULL, NULL, 5, 5, 3),
(20, NULL, NULL, 5, 6, 4),
(21, NULL, NULL, 6, 6, 2);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `scheduleid` int(11) NOT NULL,
  `arrivaltime` time NOT NULL,
  `fkstationid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`scheduleid`, `arrivaltime`, `fkstationid`) VALUES
(1, '18:16:00', 1),
(2, '20:06:00', 2),
(3, '10:02:00', 3),
(4, '05:50:00', 4),
(5, '12:21:00', 5),
(6, '11:12:00', 6),
(7, '22:12:00', 6),
(8, '12:30:00', 7);

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `stationid` int(11) NOT NULL,
  `stationname` varchar(50) NOT NULL,
  `zone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stations`
--

INSERT INTO `stations` (`stationid`, `stationname`, `zone`) VALUES
(1, 'London Bridge', 1),
(2, 'Hampstead', 3),
(3, 'Beisize Park', 3),
(4, 'Blackfriars', 1),
(5, 'Brixton', 2),
(6, 'Canada Water', 2),
(7, 'Greenford', 4),
(8, 'Hounslow Central', 4),
(9, 'Stanmore', 5),
(10, 'Ruislip Gardens', 5),
(11, 'Loughton', 6),
(12, 'Hornchurch', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fares`
--
ALTER TABLE `fares`
  ADD PRIMARY KEY (`fareid`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`scheduleid`),
  ADD KEY `fkstationid` (`fkstationid`);

--
-- Indexes for table `stations`
--
ALTER TABLE `stations`
  ADD PRIMARY KEY (`stationid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fares`
--
ALTER TABLE `fares`
  MODIFY `fareid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `scheduleid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `stations`
--
ALTER TABLE `stations`
  MODIFY `stationid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`fkstationid`) REFERENCES `stations` (`stationid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
