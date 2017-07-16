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
		$arr = array(
			"ser_no" => $table[$i][0],
			"credit" => $table[$i][1],
			"cou_cname" => $table[$i][2],
			"tea_cname" => $table[$i][3],
			"daytime" => $table[$i][4],
		);
		$json_arr[$cnt] = $arr;
		$cnt++;
	}
}

$json_obj["CourseList"] = $json_arr;
$json_str = json_encode($json_obj);
echo "$json_str";

?>
