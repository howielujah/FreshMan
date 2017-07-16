<?php
$ser_no = strtolower($_GET["ser_no"]);
if ($ser_no == "") return;

$fp = fopen("COURSE09.csv", "r");
$table = array();
for ($i = 0; $row = fgetcsv($fp, 1000, ","); $i++){
	$table[$i] = $row;
}
fclose($fp);

$len = strlen($key);
for ($i = 1; $i < count($table); $i++){
	//echo $table[$i][2];
	if ($table[$i][0] == $ser_no) {
		$arr = array(
			"ser_no" => $table[$i][0],
			"credit" => $table[$i][1],
			"cou_cname" => $table[$i][2],
			"tea_cname" => $table[$i][3],
			"daytime" => $table[$i][4],
		);
		break;
	}
}
$json_str = json_encode($arr);
echo "$json_str";

?>
