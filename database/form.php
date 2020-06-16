<?php require 'database.php'; ?>

<html>
<body>
<h1>New Profile Entry Form</h1>

	<form action="insert.php" method="post">
	
		Profile_ID: <input type="text" name="profile_id" /><br>
		<br> Waste_Type:
		<select name="waste_type">
			<option value="">Please select address</option>
			<?php foreach(get_wastetype($conn) as $waste_type) { ?>
			<option value="<?=$waste_type?>"><?=$waste_type?></option>
			<?php } ?>
		</select>
		<br>
		<br> Subcategory: 
		<select name="Subcategory">
			<option value="">Please select subcategory</option>
			<?php foreach(get_subcategory($conn) as $subcategory) { ?>
			<option value="<?=$subcategory?>"><?=$subcategory?></option>
			<?php } ?>
		</select>
		<br>
		<br> Address: 
		<select name="address">
			<option value="">Please select address</option>
			<?php foreach(get_addresses($conn) as $address) { ?>
			<option value="<?=$address?>"><?=$address?></option>
			<?php } ?>
		</select>
		<br>
		<br> Expected_Weight: <input type="number" name="weight" /><br>
		<br> <input type="submit" />

	</form>
</body>
</html>