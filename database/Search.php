<?php require 'database.php'; ?>
<!DOCTYPE html>
<html>
<body>
  <h2>Form Handling in PHP</h2>
  <form method="GET" action="Search.php">
    <div>
      <table style="height: 76px; width: 797px;">
        <tbody>
          <tr>
            <td style="width: 269px;"><span style="color: #0000ff;">Search by employee name:</span></td>
            <td style="width: 514px;"><input type="text" name="last_name"></td>
          </tr>
          <tr>
            <td style="width: 269px;">&nbsp;</td>
            <td style="width: 514px; text-align: right;"><input type="submit" value="Submit"></td>
          </tr>
        </tbody>
      </table>
    </div>
  </form>
</body>
</html>

<?php print_r($_POST);?>

