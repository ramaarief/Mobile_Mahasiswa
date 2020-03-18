<?php

include("config.php");

	$id_data = $_POST['id_data'];
	$nama_mahasiswa = $_POST['nama_mahasiswa'];
	$nomer_mahasiswa = $_POST['nomer_mahasiswa'];
	$alamat_mahasiswa = $_POST['alamat_mahasiswa'];

	$sql = "INSERT INTO data_mahasiswa (id_data, nama_mahasiswa, nomer_mahasiswa, alamat_mahasiswa) VALUES ('$id_data', '$nama_mahasiswa', '$nomer_mahasiswa', '$alamat_mahasiswa') ";
	$query = mysqli_query($db, $sql);

	if ($query) {
		# code...
	}else{
		die("Gagal menyimpan perubahan...");
	}

?>