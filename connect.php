<?php 
header("content-type:text/javascript;charset=utf-8");
define('HOST','localhost'); //ชื่อ host
define('USER','root'); //username
define('PASS',''); //password
define('DB','manganovellist'); // ชื่อ database ที่จะติดต่อ

 if($_SERVER['REQUEST_METHOD']=='GET'){

	if(isset($_GET['sql'])) {
		$sql = $_GET['sql'];
	} else {
		$sql = '';
	}
 
 	$status = $_GET['status'];
	if(isset($_GET['action'])) {
		$action = $_GET['action'];
	} else {
		$action = '';
	}
	
	if(isset($_GET['bookName'])) {
		$bookName = $_GET['bookName'];
	} else {
		$bookName = '';
	}
	
	if(isset($_GET['userId'])) {
		$userId = $_GET['userId'];
	} else {
		$userId = '';
	}

	if($action == 'all'){
 
		$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect'); //ต่อฐานข้อมูล
		
		mysqli_set_charset($con,"utf8");
	 
		$sql = "";
		$sql .= " SELECT b.bid, b.book_name, t.type_name, d.director_name, r.rate_name, s.status_name ";
		$sql .= " FROM books b ";
		$sql .= " LEFT JOIN directors d ON d.director_id = b.director ";
		$sql .= " LEFT JOIN types t ON t.type_id = b.`type` ";
		$sql .= " LEFT JOIN status s ON s.status_id = b.`status` ";
		$sql .= " LEFT JOIN rates r ON r.rate_id = b.rate; ";
	 
		$r = mysqli_query($con,$sql);
	 
		$result = array();

		while($row = mysqli_fetch_array($r))
		  {
			array_push($result,array("book_name"=>$row['book_name']));
		  }

		echo json_encode(array('result'=>$result));
	 
		mysqli_close($con);
			
	}else if($action == 'all_by_user'){
 
		$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect'); //ต่อฐานข้อมูล
		
		mysqli_set_charset($con,"utf8");
	 
		$sql = "";
		$sql .= " SELECT DISTINCT b.bid, b.book_name, t.type_name, d.director_name, r.rate_name, s.status_name ";
		$sql .= " FROM books b ";
		$sql .= " LEFT JOIN directors d ON d.director_id = b.director ";
		$sql .= " LEFT JOIN types t ON t.type_id = b.`type` ";
		$sql .= " LEFT JOIN status s ON s.status_id = b.`status` ";
		$sql .= " LEFT JOIN rates r ON r.rate_id = b.rate ";
		$sql .= " INNER JOIN keep ON keep.bid = b.bid; ";
	 
		$r = mysqli_query($con,$sql);
	 
		$result = array();

		while($row = mysqli_fetch_array($r))
		  {
			array_push($result,array("book_name"=>$row['book_name']));
		  }

		echo json_encode(array('result'=>$result));
	 
		mysqli_close($con);
			
	}else if($action == 'collection'){
		
		$con = new mysqli(HOST,USER,PASS,DB) or die('Unable to Connect'); //ต่อฐานข้อมูล

		if ($con->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}
		
		mysqli_set_charset($con,"utf8");
	 /*
		$sql = "";
		$sql .= " SELECT b.book_name, n.book_no, n.price ";
		$sql .= " FROM books b ";
		$sql .= " left JOIN book_number n ON  n.bid = b.bid ";
		$sql .= " WHERE b.book_name = '". $bookName . "'";
		$sql .= " ORDER BY n.book_no; ";
		$r = mysqli_query($con,$sql);
		$result = array();*/
		
		$sql = "";
		$sql .= " SELECT (case when keep.uid = ".$userId." then 'Y' ELSE 'N' END) AS 'flag',  ";
		$sql .= " book_number.book_name, book_number.book_no , book_number.price, keep.kid , keep.uid ,book_number.bid , book_number.nid, ";
		$sql .= " directors.director_name, types.type_name, status.status_name, books.path_img ";
		$sql .= " FROM book_number  ";
		$sql .= " LEFT JOIN keep ON book_number.nid = keep.nid ";
		$sql .= " LEFT JOIN books ON book_number.bid = books.bid  ";
		$sql .= " LEFT JOIN directors ON directors.director_id = books.director ";
		$sql .= " LEFT JOIN types ON types.type_id = books.`type` ";
		$sql .= " LEFT JOIN status ON status.status_id = books.`status` ";
		$sql .= " WHERE book_number.book_name = '". $bookName . "' ORDER BY book_number.book_no ; ";
		
		$r = $con->query($sql);
		
		$result = [];
		$result2 = array();

		while($row = $r->fetch_assoc())
		  {
			$result2['flag'] 		= $row['flag'];
			$result2['book_name'] 	= $row['book_name'];
			$result2['book_no'] 		= $row['book_no'];
			$result2['price'] 		= $row['price'];
			$result2 = (array) $row;
			array_push($result,$result2);
		  }
	 
		// echo json_encode($result);
		echo json_encode($result);
		mysqli_close($con);
	
	}else if($action == 'save'){
		$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect'); //ต่อฐานข้อมูล
		
		mysqli_set_charset($con,"utf8");

		if ($con->query($sql) === TRUE) {
			echo "New record created successfully";
		} else {
			echo "Error: " . $sql . "<br>" . $con->error;
		}
	 
		mysqli_close($con);
	}
 
 }
