<?php

$servername = "localhost";
$username = "testuser";
$password = "mypassword";
$db = "wastebro";

define("DB_HOST", "localhost");
define("DB_USER", "testuser");
define("DB_PASS", "mypassword");
define("DB_NAME", "wastebro");

$conn = new mysqli($servername, $username, $password, $db);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}else{
	echo "Connected successfully";
}


function biowaste_summary($conn){

	$sql =  "SELECT    Company_name, Waste_type, Subcategory, Amount, Inbound_date
			FROM    Generator G, Inbound I, Profile P, Location L
			WHERE    (I.ProfileID = P.ProfileID AND P.Waste_type = 'BIO')
			AND (P.Address = L.Address AND L.GeneratorID = G.GeneratorID);";
			
	$res = $conn->query($sql);
	//print_r($res);
	//exit;
	
	$data = [];
	
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			
			$data[] = $row;
		}
	}
	
	return $data;		
}

function max_profit($conn){

	$sql =  "SELECT I.ProfileID, Waste_type, Pt.Amount AS 'Income Amount', 
            Pt.Cost, Pt.Amount - Pt.Cost AS Profits
			FROM        Inbound I, Profit Pt, Profile Pf
			WHERE        (I.ProfileID = Pf.ProfileID AND I.Amount = Pt.Amount)
			GROUP BY     I.ProfileID, Waste_type, Pt.Amount, Pt.Cost
			HAVING        AVG(Pt.Amount - Pt.Cost) >= ALL (SELECT    AVG(Pt2.Amount - Pt2.Cost)
			FROM    Profit AS Pt2)
			ORDER BY    Profits DESC;";

	$res = $conn->query($sql);
	
	$data = [];
	
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			
			$data[] = $row;
		}
	}
	
	return $data;				
}


function expensive_waste($conn){

	$sql =  "SELECT     *
			FROM     SubWastecode W1
			WHERE    W1.Price_per_lb >= (SELECT     AVG(W2.Price_per_lb)
										FROM    SubWastecode W2
										WHERE    W1.Waste_type = W2.Waste_type)
										ORDER BY W1.Price_per_lb DESC;";

	$res = $conn->query($sql);
	
	$data = [];
	
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			
			$data[] = $row;
		}
	}
	
	return $data;		
}


function get_vehicle($conn){

	$sql =  "SELECT     First_name, Last_name, Ssn, Title, E.VehicleID, Model, License, Waste_type, Max_weight
			FROM        Employee E
			LEFT JOIN    Vehicle V
			ON            E.VehicleID = V.VehicleID
			UNION ALL
			SELECT        First_name, Last_name, Ssn, Title, E.VehicleID, Model, License, Waste_type, Max_weight
			FROM        Employee E
			RIGHT JOIN    Vehicle V
			ON            E.VehicleID = V.VehicleID;";

	$res = $conn->query($sql);
	
	$data = [];
	
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			
			$data[] = $row;
		}
	}
	
	return $data;		
}


function get_addresses($conn) {
	$sql = "Select Address From location";
	$res = $conn->query($sql);
	$data = [];
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			$data[$row['Address']] = $row['Address'];
		}
	}
	return $data;
}


function get_subcategory($conn) {
	$sql = "Select Subcategory From profile";
	$res = $conn->query($sql);
	$data = [];
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			$data[$row['Subcategory']] = $row['Subcategory'];
		}
	}
	return $data;
}

function get_wastetype($conn) {
	$sql = "Select waste_type From profile";
	$res = $conn->query($sql);
	$data = [];
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			$data[$row['waste_type']] = $row['waste_type'];
		}
	}
	return $data;
}

function get_generatorID($conn) {
	$sql = "Select GeneratorID From generator";
	$res = $conn->query($sql);
	$data = [];
	if($res->num_rows > 0){
		while($row = $res->fetch_assoc()){
			$data[$row['GeneratorID']] = $row['GeneratorID'];
		}
	}
	return $data;
}

function get_profile($conn) {
  $sql = "Select ProfileID From Profile";
  $res = $conn->query($sql);
  $data = [];
  if ($res->num_rows > 0) {
    while ($row = $res->fetch_assoc()) {
      $data[$row['ProfileID']] = $row['ProfileID'];
    }
  }
  return $data;
}




Class Database{
    private $host = DB_HOST;
    private $user = DB_USER;
    private $pass = DB_PASS;
    private $dbname = DB_NAME;

    private $dbh;
    private $error;

    private $stmt;

    public function __construct(){
        // Set DSN
        $dsn = 'mysql:host=' . $this->host . ';dbname=' . $this->dbname;
        // Set options
        $options = array(
            PDO::ATTR_PERSISTENT    => true,
            PDO::ATTR_ERRMODE       => PDO::ERRMODE_EXCEPTION
        );
        // Create a new PDO instanace
        try{
            $this->dbh = new PDO($dsn, $this->user, $this->pass, $options);
        }
        // Catch any errors
        catch(PDOException $e){
            $this->error = $e->getMessage();
        }
    }

    public function query($query){
		var_dump($query);
        $this->stmt = $this->dbh->prepare($query);
    }

    public function bind($param, $value, $type = null){
        if (is_null($type)) {
            switch (true) {
                case is_int($value):
                    $type = PDO::PARAM_INT;
                    break;
                case is_bool($value):
                    $type = PDO::PARAM_BOOL;
                    break;
                case is_null($value):
                    $type = PDO::PARAM_NULL;
                    break;
                default:
                    $type = PDO::PARAM_STR;
            }
        }
        $this->stmt->bindValue($param, $value, $type);
    }

    public function execute(){
        return $this->stmt->execute();
    }

    public function column(){
        $this->execute();
        return $this->stmt->fetchAll(PDO::FETCH_COLUMN);
    }

    public function resultset(){
        $this->execute();
        return $this->stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function single(){
        $this->execute();
        return $this->stmt->fetch(PDO::FETCH_ASSOC);
    }

    public function rowCount(){
        return $this->stmt->rowCount();
    }

    public function lastInsertId(){
        return $this->dbh->lastInsertId();
    }

    public function beginTransaction(){
        return $this->dbh->beginTransaction();
    }

    public function endTransaction(){
        return $this->dbh->commit();
    }

    public function cancelTransaction(){
        return $this->dbh->rollBack();
    }

    public function debugDumpParams(){
        return $this->stmt->debugDumpParams();
    }
}

function printTable($tbl_name, $db_query){
    //New PDO object
    $pdo = new Database();

    //Get column names
    $pdo->query("DESCRIBE ". $tbl_name);
    $col_names = $pdo->column();

    //Get number of columns
    $col_cnt = count($col_names);

    //Setup table - user css class db-table for design
    echo "<table class='db-table'>";
   // echo "<tr colspan='". $col_cnt ."'>". $tbl_name ."</tr>";
    echo "<tr>";

    //Give each table column same name is db column name
    for($i=0;$i<$col_cnt;$i++){
        echo "<td>". $col_names[$i] ."</td>";
    }

    echo "</tr>";

    //Get db table data
    $pdo->query($db_query);

    $results = $pdo->resultset();
    $res_cnt = count($results);

    //Print out db table data
    for($i=0;$i<$res_cnt;$i++){
        echo "<tr>";
        for($y=0;$y<$col_cnt;$y++){
            echo "<td>". $results[$i][$col_names[$y]] ."</td>";
        }
        echo "</tr>";
    }
	
	echo "</table>";
}

function print_arr($arr) {
	echo "<pre>";
	print_r($arr);
	echo "</pre>";
}

function printGeneralTable($data) {
	if(empty($data)) { return ""; }
	$first_row = $data[0];
	if(!is_array($first_row)) { return ""; }
	$header = array_keys($first_row);
?>
<table>
	<tr>
	<?php foreach($header as $col) { ?>
		<th><?=$col?></th>
	<?php } ?>
	</tr>
	<?php foreach($data as $row) { ?>
	<tr>
		<?php foreach($row as $col) { ?>
		<td><?=$col?></td>
		<?php } ?>
	</tr>
	<?php } ?>
</table>
<?php
}

/*
<table>
 <?php           
           if ( mysqli_connect_errno() ) 
           {
             die( mysqli_connect_error() );  
           }
           $sql = biowaste_summary($conn);
           
           if ($result = mysqli_query($conn, $sql)) 
           {
             // loop through the data
             while($row = mysqli_fetch_assoc($result))
             {  
  ?>
  
	    for($i=0;$i<sizeof($sql);$i++){
        echo "<td>". $col_names[$i] ."</td>";
    }
                <tr>
                  <td><?php echo $row['Company_name'] ?></td>
                  <td><?php echo $row['Waste_type'] ?></td>
                  <td><?php echo $row['Subcategory'] ?></td>
                  <td><?php echo $row['Amount'] ?></td>
                  <td><?php echo $row['Inbound_date'] ?></td>
                </tr>
<?php
            }
             // release the memory used by the result set
             mysqli_free_result($result); 
           } // end if (isset)
			 // end if ($_SERVER)
?>
</table> 

*/

?>