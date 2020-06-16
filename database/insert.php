<?php


	require 'database.php';
	
	$Profile_ID = $_POST['profile_id'];
	$Waste_Type = $_POST['type'];
	$Subcategory = $_POST['category'];
	$Address = $_POST['address'];
	$Expected_Weight = $_POST['weight'];
	
	
	$sql = "INSERT INTO Profile(ProfileID, Waste_Type, Subcategory, Address, Expected_Weight_range) 
				values ('$Profile_ID', '$Waste_Type', '$Subcategory', '$Address', '$Expected_Weight')";
				
	
	if(!$conn->query($sql)) {
		echo '<br>Not Inserted';
		echo "<br>".$conn->error;
	}
	else {
		echo '<br>Inserted';
	}
	
	//header("refresh:2; url=index.html");
	
?>
	