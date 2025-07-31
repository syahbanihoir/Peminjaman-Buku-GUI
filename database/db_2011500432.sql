-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jul 2023 pada 04.03
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_2011500432`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku_2011500432`
--

CREATE TABLE `buku_2011500432` (
  `NoBuku` varchar(4) NOT NULL,
  `Judul` varchar(25) NOT NULL,
  `Tahun` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `buku_2011500432`
--

INSERT INTO `buku_2011500432` (`NoBuku`, `Judul`, `Tahun`) VALUES
('B001', 'Pemrograman Web 1', '2015'),
('B002', 'Manajemen SDM', '2018'),
('B003', 'Web Design', '2015'),
('B004', 'Game Edu', '2022');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detil_2011500432`
--

CREATE TABLE `detil_2011500432` (
  `NoPinjam` varchar(4) NOT NULL,
  `NoBuku` varchar(4) NOT NULL,
  `TglKembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detil_2011500432`
--

INSERT INTO `detil_2011500432` (`NoPinjam`, `NoBuku`, `TglKembali`) VALUES
('P001', 'B001', '2023-07-05'),
('P002', 'B002', '2023-07-05'),
('P003', 'B003', '2023-07-05');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman_2011500432`
--

CREATE TABLE `peminjaman_2011500432` (
  `NoPinjam` varchar(4) NOT NULL,
  `TglPinjam` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `peminjaman_2011500432`
--

INSERT INTO `peminjaman_2011500432` (`NoPinjam`, `TglPinjam`) VALUES
('P001', '2023-06-28'),
('P002', '2023-06-28'),
('P003', '2023-06-28');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku_2011500432`
--
ALTER TABLE `buku_2011500432`
  ADD PRIMARY KEY (`NoBuku`);

--
-- Indeks untuk tabel `detil_2011500432`
--
ALTER TABLE `detil_2011500432`
  ADD PRIMARY KEY (`NoPinjam`,`NoBuku`);

--
-- Indeks untuk tabel `peminjaman_2011500432`
--
ALTER TABLE `peminjaman_2011500432`
  ADD PRIMARY KEY (`NoPinjam`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
