-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2023 at 03:54 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_mobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `id` int(11) NOT NULL,
  `nomor_plat` varchar(20) NOT NULL,
  `merk` varchar(20) NOT NULL,
  `tahun_pembuatan` date NOT NULL,
  `warna` varchar(30) NOT NULL,
  `stok` int(4) NOT NULL,
  `harga_sewa_perhari` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`id`, `nomor_plat`, `merk`, `tahun_pembuatan`, `warna`, `stok`, `harga_sewa_perhari`) VALUES
(12, 'B 1235 CDE ', 'Toyota', '2022-01-21', 'Putih', 0, '120000'),
(13, 'B 1236 CDE', 'Toyota', '2017-01-01', 'Biru', 1, '140000'),
(14, 'B 1237 CDE ', 'Honda', '2018-01-01', 'Merah', 3, '110000'),
(15, 'B 1238 CDE', 'Honda', '2019-01-01', 'Hijau', 4, '130000'),
(16, 'B 1239 CDE', 'Honda', '2020-01-01', 'Kuning', 1, '150000'),
(17, 'B 1240 CDE', 'Suzuki', '2021-01-01', 'Coklat', 6, '120000'),
(18, 'B 1241 CDE', 'Suzuki', '2021-09-17', 'Abu-abu', 4, '40000'),
(19, 'B 1242 CDE', 'Suzuki', '2023-01-01', 'Biru muda', 0, '100000'),
(20, 'B 1243 CDE', 'Mitsubishi', '2024-01-01', 'Ungu', 1, '110000'),
(52, 'B 1234 CDE', 'Toyota', '2022-01-01', 'Merah', 5, '10000'),
(53, 'B 5678 FGH', 'Honda', '2020-06-01', 'Putih', 3, '12000'),
(54, 'B 9012 JKL', 'Suzuki', '2019-03-01', 'Hitam', 4, '8000'),
(55, 'B 3456 MNO', 'Mitsubishi', '2018-05-01', 'Abu-abu', 2, '9000'),
(56, 'B 7890 PQR', 'Nissan', '2022-08-01', 'Biru', 5, '11000'),
(57, 'B 1234 STU', 'Daihatsu', '2021-02-01', 'Hijau', 4, '7000'),
(58, 'B 5678 VWX', 'Mazda', '2020-09-01', 'Kuning', 3, '10000'),
(59, 'B 9012 YZ', 'Hyundai', '2019-04-01', 'Orange', 5, '9000'),
(60, 'B 3456 ABC', 'Ford', '2018-07-01', 'Coklat', 4, '10000'),
(61, 'B 7890 DEF', 'Chevrolet', '2022-11-01', 'Emas', 3, '12000'),
(63, 'B 1189 TSL', 'Tesla', '2021-01-06', 'Hitam', 2, '100000'),
(64, 'DA 1122 KLM', 'Astrea', '2023-01-08', 'Hitam', 2, '10000'),
(65, 'DA 1113 MLM', 'Supra Edit', '2023-01-05', 'Hjau', 1, '20000');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `no_telepon` varchar(13) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama`, `no_telepon`, `alamat`) VALUES
(1, 'munadi', '082148161129', 'Martapura'),
(4, 'jamal paring', '08214781728', 'jalan sungai deras'),
(5, 'Andi', '081234567890', 'Jl. Merdeka No. 1'),
(6, 'Budi', '089876543210', 'Jl. Pahlawan No. 2'),
(7, 'Cindy', '081234512345', 'Jl. Raya No. 3'),
(8, 'David', '085678901234', 'Jl. Setia Budi No. 4'),
(9, 'Ella', '084567891230', 'Jl. Pahlawan No. 5'),
(10, 'Fandi', '085678901235', 'Jl. Merdeka No. 6'),
(11, 'Gina', '085678901236', 'Jl. Setia Budi No. 7'),
(12, 'Hendi', '085678901237', 'Jl. Raya No. 8'),
(13, 'Indra', '085678901238', 'Jl. Pahlawan No. 9'),
(14, 'Joko', '085678901239', 'Jl. Merdeka No. 10'),
(17, 'Mansur', '0812345678', 'Jalan Martapura'),
(18, 'Mansuri', '0821478982', 'Jalan Durian ');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_mobil` int(11) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `tgl_sewa` date DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `harga_sewa` varchar(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `status` enum('Sudah Di Kembalikan','Belum di Kembalikan') DEFAULT 'Belum di Kembalikan'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_mobil`, `id_pelanggan`, `tgl_sewa`, `tgl_kembali`, `harga_sewa`, `id_user`, `status`) VALUES
(8, 20, 12, '2023-01-07', '2023-01-12', '550000', 3, 'Sudah Di Kembalikan'),
(9, 13, 13, '2023-01-04', '2023-01-31', '3780000', 3, 'Sudah Di Kembalikan'),
(11, 14, 5, '2023-01-04', '2023-01-05', '110000', 7, 'Sudah Di Kembalikan'),
(12, 19, 5, '2023-01-04', '2023-01-05', '110000', 7, 'Sudah Di Kembalikan'),
(13, 19, 5, '2023-01-04', '2023-01-05', '110000', 7, 'Sudah Di Kembalikan'),
(15, 13, 9, '2023-01-05', '2023-01-13', '1120000', 3, 'Sudah Di Kembalikan'),
(16, 13, 8, '2023-01-06', '2023-01-07', '140000', 3, 'Sudah Di Kembalikan'),
(17, 16, 10, '2023-01-05', '2023-01-07', '300000', 3, 'Sudah Di Kembalikan'),
(38, 63, 1, '2023-01-07', '2023-01-08', '111111', 7, 'Sudah Di Kembalikan'),
(39, 63, 1, '2023-01-07', '2023-01-08', '111111', 3, 'Sudah Di Kembalikan'),
(40, 14, 1, '2023-01-13', '2023-01-14', '110000', 3, 'Sudah Di Kembalikan'),
(41, 65, 1, '2023-01-14', '2023-01-16', '40000', 3, 'Sudah Di Kembalikan');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','user') NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `username`, `password`, `role`) VALUES
(3, 'admin1', '6c7ca345f63f835cb353ff15bd6c5e052ec08e7a', 'admin'),
(7, 'tes', 'd1c056a983786a38ca76a05cda240c7b86d77136', 'user'),
(8, 'user', '12dea96fec20593566ab75692c9949596833adc9', 'user'),
(9, 'munadi1', '35edb58fb16da4dd72282bef1eb5798a95df48fa', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_mobil` (`id_mobil`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mobil`
--
ALTER TABLE `mobil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_mobil`) REFERENCES `mobil` (`id`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_users`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
