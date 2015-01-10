<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cancel Transaction</title>


</head>
<body>
	<table border="1">
		<tr>
			<th>TransactionID :</th>
			<th>Order ID</th>

		</tr>
		<c:forEach items="${list}" var="respone">
			<tr>
				<td>${response.transaction_id}</td>
				<td>${response.order_id}</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>