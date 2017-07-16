<?php
$key = strtolower($_GET["key"]);
if ($key == "") return;

$fp = fopen("COURSE09.csv", "r");
$table = array();
for ($i = 0; $row = fgetcsv($fp, 1000, ","); $i++){
	$table[$i] = $row;
}
fclose($fp);

$cnt = 0;
$len = strlen($key);
for ($i = 1; $i < count($table); $i++){
	//echo $table[$i][2];
	if (strtolower(substr($table[$i][2], 0, $len)) == $key) {
		echo $table[$i][2];
		break;
	}
}

?>
