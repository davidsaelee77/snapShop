<?php


	require 'database.php';
	
	$GeneratorID = $_POST['GeneratorID'];
	$Company_name = $_POST['Company_name'];

	
	$sql = "INSERT INTO generator(GeneratorID, Company_name) 
				values ('$GeneratorID', '$Company_name');";
				
	
	$array = query($sql);
	
	if(!$conn->query($sql)) {
		echo '<br>Not Inserted';
		echo "<br>".$conn->error;
	}
	else {
		echo '<br>Inserted';
	}

	
	//header("refresh:2; url=index.html");
	
?>