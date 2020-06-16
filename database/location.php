<?php require 'database.php'; ?>

	<html>
	<body>
	<form action="insert.php" method="post">
	<h1>New Location Entry Form</h1>
		Address: <input type="text" name="address" /><br>
		<br>GeneratorID:
		<select name="generatorID">
			<option value="">Please select generator ID</option>
			<?php foreach(get_generatorID($conn) as $generatorID) { ?>
			<option value="<?=$generatorID?>"><?=$generatorID?></option>
			<?php } ?>
			<br> <input type="submit" />
		</select>
		</form>
		
		<section class="call-to-action">
		<div class="cta-container cf">
			<a class="btn" href="form.php">Create a profile →</a>
		</div>
	</section>
</body>
</html>