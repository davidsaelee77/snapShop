<?php


	require 'database.php';
	
	$Address = $_POST['address'];
	$GeneratorID = $_POST['generatorID'];

	
	$sql = "INSERT INTO location(generatorID, company_name) 
				values ('$Address', '$GeneratorID');";
				
	
	if(!$conn->query($sql)) {
		echo '<br>Not Inserted';
		echo "<br>".$conn->error;
	}
	else {
		echo '<br>Inserted';
	}
	
	//header("refresh:2; url=index.html");
	
?>