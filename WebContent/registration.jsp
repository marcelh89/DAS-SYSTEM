<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<form method="post" action="http://localhost:8080/DAS-SYSTEM-SERVER/doj/webservice/registration">
<p>Vorname: <input type="text" name="forename"  value="Horst" /></p>
<p>Nachname: <input type="text" name="surname"  value="Horstsen" /></p>
<p>Email: <input type="email" name="email"  value="horst123@test.de" /></p>
<p>Passwd: <input type="password" name="passwd" value="123"/></p>
<p>Passwd(wdh) <input type="password" name="passwd_wdh" value="123"/></p>

<input type="submit" value="Login" />
</form>

</body>
</html>