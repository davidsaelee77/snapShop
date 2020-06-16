<?php require_once('database.php'); ?>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>

<body>
<h1>
  <?php  
   if (isset($_GET['id'])) {
    // id has been found
    $id = $_GET['id'];
    echo "Profile database for ", $id;
  } else {
  // id has not been found
  $id = 0;
  echo "No profile found for the scanned QR code";
  }
  ?>
  </h1>



	<table>
		<tr>
			<th>Profile ID</th>
			<th>Waste Type</th>
			<th>Subcategory</th>
			<th>Address</th>
			<th>Expected Weight Range</th>
		</tr>
<?php
$connection = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME);
if (mysqli_connect_errno()) {
    die(mysqli_connect_error());
}

$sql = "select * from Profile WHERE ProfileID = '{$id}' ";
if ($result = mysqli_query($connection, $sql)) {
    // loop through the data
    while ($row = mysqli_fetch_assoc($result)) {
        ?>
            <tr>
			<td><?php echo $row['ProfileID'] ?></td>
			<td><?php echo $row['Waste_type'] ?></td>
			<td><?php echo $row['Subcategory'] ?></td>
			<td><?php echo $row['Address'] ?></td>
			<td><?php echo $row['Expected_weight_range'] ?></td>
		</tr>
<?php
    }
    // release the memory used by the result set
    mysqli_free_result($result);
}
// close the database connection
mysqli_close($connection);

?>
</table>
</body>
</html>
