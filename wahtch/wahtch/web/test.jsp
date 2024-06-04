<%-- 
    Document   : test
    Created on : May 24, 2024, 10:33:31 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
            <table border="1">
                <thead>
                    <tr>
                       <th>Id</th>
                            <th>Product Name </th>
                            <th>Brand</th>
                            <th>Images</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock Quantity</th>
                            <th>Publication Date</th>
                            <th>Funtion</th>
                    </tr>
                </thead>
                <tbody>
                    
                        <c:forEach items="${pro}" var="p">
                    <tr>
                        <td>${p.id}</td>
                            <td>${p.proName}</td>
                            <td > ${p.brand_id} </td>
                            <td > <img src="${p.img}"/></td>
                            <td > ${p.caid} </td>
                            <td > ${p.price} </td>
                            <td > ${p.stockQuantity} </td>
                            <td > ${p.publication_date} </td>
                            <td>
                              <button type="button" class="btn btn-inverse-danger btn-icon">
                            <i class="mdi mdi-delete-forever"></i>
                              </button> &nbsp;
                              <button type="button" class="btn btn-inverse-success btn-icon">
                            <i class="mdi mdi-border-color"></i>
                          </button>
                            </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>

	</body>
</html>
