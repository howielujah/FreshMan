<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="css/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
<link type="text/css" href="css/index.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>Main Page - Web Site Practice</title>
</head>
<body>
<div id="welcome">
Hi, ${name}. Welcome to Web Site Practice. <a href="logout.do">登出</a>
</div>
<div id="menu">
    <div id="accordion">
    <c:if test="${Authorise == 1}">
        <div>
            <h3><a href="#">選單參數管理</a></h3>
            <div>
                <ul>
                    <li><a href="#" onclick="goIndustry()">產業維護</a></li>
                    <li><a href="#">品項維護</a></li>
                    <li><a href="#">幣別維護</a></li>
                    <li><a href="#">國家城市維護</a></li>
               </ul>
            </div>
        </div>
    </c:if>
        <div>
            <h3><a href="#">員工維護管理</a></h3>
            <div>客戶資料維護</div>
        </div>
        <div>
            <h3><a href="#">客戶廠商維護</a></h3>
            <div>Nothing Here.</div>
        </div>
        <div>
            <h3><a href="#">訂單維護</a></h3>
            <div>Nothing Here.</div>
        </div>
        <div>
            <h3><a href="#">採購單維護</a></h3>
            <div>Nothing Here.</div>
        </div>
        <div>
            <h3><a href="#">報表作業</a></h3>
            <div>Nothing Here.</div>
        </div>
    </div>
</div>
<div id="mainPage">
</div>
</body>
</html>