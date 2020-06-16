<?php require 'database.php'; ?>

	<html>
	<body>
	<form action="insertgen.php" method="post">
	<h1>New Generator Entry Form</h1>
		GeneratorID: <input type="text" name="GeneratorID" /><br>
		<br>Company_name: <input type="text" name="Company_name" ><br>
		<br> <input type="submit" />
		</form>
		
		<section class="call-to-action">
		<div class="cta-container cf">
			<p>Astounding call to action? Let's get Started!</p>
			<a class="btn" href="location.php">Create a location→</a>
		</div>
	</section>
</body>
</html>