<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" href="css/jquery-ui-1.8.20.custom.css" rel="stylesheet" />
	<link type="text/css" href="css/login.css" rel="stylesheet" />
	<title>WebSite Login</title>
	<style>
		.errors {
	      color: red;
   		}
	</style>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.20.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>

	<script type="text/javascript">
	$(function(){

		//生成dialog內容
		
		var birthday = $('#birthday').datepicker({ dateFormat: 'yy-mm-dd' });
		var form = $("#dialog");
			
		form.dialog({
				width: '370px',
		        autoOpen: false, 
		        show: "blind", 
		        hide: "explode"
			}); 
 
	    $("#register_button").on('click',function(){
	    	form.dialog("open"); 
	        return ; 
		})	
		
		var inputelements = new Array();
            //把dialog內的input元素放入inputelements陣列
            for (var i = 0; i < 6; i++) {
                inputelements.push($("form[id='dialog'] input")[i]);
            }
		
		//如果dialog內的input輸入長度滿了游標跳到下一個input元素
            function focusnext(inputobj) {
                var user = inputobj.val();
                var max = inputobj.attr('maxlength');
                if (user.length == max) {
                    var index = inputelements.indexOf(inputobj[0]);
                    if (index == (inputelements.length - 1)) return; //最後一個input元素滿了的話不動作
                    inputelements[index + 1].focus();
                }
            }
		
		 $('input[name="account"]').on('keyup', function () {

                focusnext($('input[name="account"]'));

            })
		
            $('input[name="pswd"]').on('keyup', function () {
				
                focusnext($('input[name="pswd"]'));

            })
            
             $('input[name="pswdconfirm"]').on('keyup', function () {

                focusnext($('input[name="pswdconfirm"]'));

            })
            
            $('input[name="username"]').on('keyup', function () {

                focusnext($('input[name="username"]'));

            })
            
            $('input[name="birthday"]').on('change', function () {

                focusnext($('input[name="birthday"]'));

            });
            
            $('input[name="phone"]').on('keyup', function () {

                focusnext($('input[name="phone"]'));

            });
            
//         若註冊輸入有錯誤，自動跳出dialog
        <c:if test = "${errorMsgs.size()>0}">
        	$('input[name="pswd"]').val("");	
        	$('input[name="pswdconfirm"]').val("");
        	form.dialog("open");
      	</c:if>

		$.validator.addMethod("regex", function(value, element, regexp) {
			var re = new RegExp(regexp);
			return re.test(value);
		});

		var usrregex = '^([a-zA-Z])([A-Za-z0-9_\.]){5,11}$'; // (6~12)英文開頭、後面接英文/數字/_
		var passwdregex = '^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[~!@#$%^&*()_+])[0-9A-Za-z~!@#$%^&*()_+]{8,}$'; // (8~)英文/數字/特殊字元
		var unregex = '^[\u4e00-\u9fa5_a-zA-Z]{1,30}$'; // (1~30) 中文/英文/_
		var birthregex = '^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$'; // (YYYY/MM/DD)
		var phoneregex = '^09[0-9]{8}$'; // 09xxxxxxxx

		$("#dialog").validate({
			errorClass: 'errors',
			rules: {
            	account: {
            		regex: usrregex
                },
                pswd: {
                	regex: passwdregex
                },
                pswdconfirm: {
                    equalTo: "#pswd"
                },
                username: {
                	regex: unregex
                },
                birthday: {
                	regex: birthregex
                },
                phone: {
                	regex: phoneregex
                }
            }, messages: {
            	account: "(6~12)英文開頭、後面接英文/數字/_/.",
            	pswd: "(8~12)英文/數字/@",
            	pswdconfirm:"輸入要跟密碼一樣",
            	username:"(1~30) 中文/英文/_",
            	birthday:"(YYYY/MM/DD)",
            	phone:"09xxxxxxxx"
            }
        });
		
		//如果驗證不成功把密碼跟密碼確認清空
		$("#dialog").on( 'submit' ,function () {
			  if ($("#dialog").valid()) {
			    /*驗證成功，post the form data to server*/
			  } else {
				$('#pswd').val("");
				$('#pswdconfirm').val("");
				return false;
			}
			});
		
		//重置輸入
		$("#reset").on('click',function(){
			$('input[type="text"]').val("");
			$('input[type="password"]').val("");
		})
		
		//判斷有沒有cookie叫registered，有則代表註冊成功
		 if ($.cookie('registered') != null) {  
                    alert("註冊成功");
                    $.cookie('registered', '', { expires: -1 });
         }
		
	})
</script>
</head>
<body>
	<form id="login_form" method="POST" action="login.do">
		<table>
				<tr>
					<td><span class="short_label">使用者帳號 : </span></td>
					<td>
						<span class="short_text"><input name="userName" type="text"/></span><span style="color:red">${loginError.userNameError}</span>
					</td>
				</tr>
				<tr>
					<td><span class="short_text">使用者密碼 : </span></td>
					<td>
						<span class="short_text"><input name="password" type="password"/></span><span style="color:red">${loginError.passwordError}</span>
					</td>
				</tr>
				<tr>
					<td><span><input id="register_button" type="button" value="新使用者" /></span></td>
					<td><span><input id="login_button" type="submit" value="登入" /></span></td>
					<td>${errMsg}</td>
				</tr>
			</table>
	</form>
	<form id="dialog" title="新使用者註冊" method="POST" action="register.do">
		<table>
			<tr>
				<td><label>帳號:</label></td>
				<td><input type="text" name="account" maxlength="12" id="account" value = "${param.account }"></td>
				<td><span style="color:red">${errorMsgs.usrError}</span></td>
			<tr>
			<tr>
				<td><label>密碼:</label></td>
				<td><input type="password" name="pswd" maxlength="12" id="pswd" ></td>
				<td><span style="color:red">${errorMsgs.passwdError}</span></td>
			</tr>
			<tr>
				<td><label>再次輸入密碼:</label></td>
				<td><input type="password" name="pswdconfirm" maxlength="12" id="pswdconfirm" ></td>
				<td><span style="color:red">${errorMsgs.pswdconfirmError}</span></td>
			</tr>
			<tr>
				<td><label>姓名:</label></td>
				<td><input type="text" name="username" maxlength="30" id="username" value = "${param.username }"></td>
				<td><span style="color:red">${errorMsgs.unError}</span></td>
			</tr>
			<tr>
				<td><label>生日:</label></td>
				<td><input type="text" name="birthday" maxlength="10" id="birthday" value = "${param.birthday }"></td>
				<td><span style="color:red">${errorMsgs.birthError}</span></td>
			</tr>
			<tr>
				<td><label>電話:</label></td>
				<td><input type="text" name="phone" maxlength="10" id="phone" value = "${param.phone }"></td>
				<td><span style="color:red">${errorMsgs.phoneError}</span></td>
			</tr>
			<tr>
				<td><input type="submit" value="送出"></td>
				<td><input type="button" value="重置" id="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>