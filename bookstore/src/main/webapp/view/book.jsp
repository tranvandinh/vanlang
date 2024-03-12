<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thông tin Sách</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ page import = "model.Book" %>
	<%@ page import = "dao.Books" %>
    <%  
	    String id=request.getParameter("currBookID"); 
    	System.out.print(id);
		Book book = Books.getBook(id);
		
    %>
	<form action="book" method="post">
		<div class="container" style="width: 50%; justify-content: center;">
			<div >
				<label class="form-label">Mã sách</label> 
				<input type="text" class="form-control" placeholder="Nhập mã sách" name ="id" value="<%= book.getId()%>"/>
			</div>
			<div >
				<label class="form-label">Tên sách</label> 
				<input type="text" class="form-control" placeholder="Nhập tên sách" name ="name" value="<%= book.getName()%>"/>
			</div>
			<div >
				<label class="form-label">Giá bán</label> 
				<input type="text" class="form-control" placeholder="Nhập giá bán" name ="price"  value="<%= book.getPrice()%>"/>
			</div>
			
			<div style="float: right;">
				<button type="submit" class="btn btn-primary" name="button" value="save">Lưu</button>
				<button type="submit" class="btn btn-primary" name="button" value="closebook">Đóng</button>
			</div>
		</div>
	</form>



</body>
</html>