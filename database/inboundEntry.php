<?php require 'database.php'; ?>

<html>
<body>
<h1>New Inbound Entry Form</h1>

	<form action="insertInbound.php" method="post">

		<br> ProfileID:
		<select name="ProfileID">
			<option value="">Please select Profile</option>
			<?php foreach(get_profile($conn) as $profile) { ?>
			<option value="<?=$profile?>"><?=$profile?></option>
			<?php } ?>
		</select>
		<br>
		<br> Date: <input type="text" name="Date" /><br>
		<br> Weight: <input type="number" name="Weight" /><br>
		<br> <input type="submit" />

	</form>
</body>
</html>
