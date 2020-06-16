<?php


	require 'database.php';

	$Profile_ID = $_POST['ProfileID'];
	$Date_string = $_POST['Date'];
	$Inbound_date = strtotime($Date_string);
	$Actual_weight = $_POST['Weight'];


	$Inbound_res = $conn->query("SELECT InboundID FROM Inbound");
	$Inbound_data = [];
	if ($Inbound_res->num_rows > 0) {
		while ($Inbound_row = $Inbound_res->fetch_assoc()) {
			$Inbound_data[$Inbound_row['InboundID']] = $Inbound_row['InboundID'];
		}
	}
	$InboundNo = sizeof($Inbound_data) + 1;
	$str = "Inbound";
	$conc = $str.$InboundNo;
	
	$queryBuild1 = "SELECT Price_per_lb FROM subwastecode S, profile P WHERE P.profileID = ";
	$queryBuild2 = $queryBuild1.$Profile_ID;
	$queryBuild3 = $queryBuild2." and S.Waste_type = P.Waste_type and S.Subcategory = P.Subcategory";

	$Ppu_res = $conn->query($queryBuild3);
	$Ppu_data = [];
	if ($Ppu_res->num_rows > 0) {
		while ($Ppu_row = $Ppu_res->fetch_assoc()) {
			$Ppu_data[$Ppu_row['Price_per_lb']] = $Ppu_row['Price_per_lb'];
		}
	}
	if (empty($Ppu_data)) {
		print("AHHHHJH");
	} else {
		exit;
	}
	$Ppu = $Ppu_data[sizeof($Ppu_data)- 1];


	$Amount = $Actual_weight * $Ppu;


	$Vehicle_res = $conn->query("SELECT VehicleID FROM Vehicle V, Profile P WHERE V.Waste_type = P.Waste_type");
	$Vehicle_data = [];
	if ($Vehicle_res->num_rows > 0) {
		while ($Vehicle_row = $Vehicle_res->fetch_assoc()) {
			$Vehicle_data[$Vehicle_row['VehicleID']] = $Vehicle_row['Vehicle'];
		}
	}
	$VehicleID = $Vehicle_data[sizeof($Vehicle_data)-1];

	$Ssn_res = $conn->query("SELECT Essn FROM Vehicle V, Employee E WHERE V.VehicleID = E.VehicleID");
	$Ssn_data = [];
	if ($Ssn_res->num_rows > 0) {
		while ($Ssn_row = $Ssn_res->fetch_assoc()) {
			$Ssn_data[$Ssn_row['Essn']] = $Ssn_row['Essn'];
		}
	}
	$Ssn = $Ssn_data[sizeof($Ssn_data)-1];


	$sql = "INSERT INTO Inbound(InboundID, Actual_weight, ProfileID, Inbound_date, Amount, Essn)
				values ('$InboundID', '$Actual_weight', '$ProfileID', '$Inbound_date', '$Amount', '$Ssn')";


	if(!$conn->query($sql)) {
		echo '<br>Not Inserted';
		echo "<br>".$conn->error;
	}
	else {
		echo '<br>Inserted';
	}

	//header("refresh:2; url=index.html");

?>
