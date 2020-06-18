<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AIT BlockBuster</title>
    
    <style type="text/css">
      <%@ include file="css/styles.css" %>
    </style>
  </head>
  <body>
    <div>
      <h1>Inventory Management</h1>
      
      <div class="header">
        <a href="${pageContext.request.contextPath}/" class="header-button">VIEW ALL</a>
        <a href="${pageContext.request.contextPath}/add" class="header-button">ADD A MOVIE</a> 
      </div>
    </div>
    <div>
      <table border="1">        
        <tr>
          <th>Title</th>
          <th>Director</th>
          <th>Film Duration</th>
          <th>Copies</th>
          <th>Available</th>
          <th>Actions</th>
        </tr>
        <c:forEach var="movie" items="${movies}">
          <tr>
            <td><c:out value="${movie.title}" /></td>
            <td><c:out value="${movie.director}" /></td>
            <td><c:out value="${movie.filmLength}" /></td>
            <td><c:out value="${movie.copies}" /></td>
            <td><c:out value="${movie.available}" /></td>
            <td>
	      <div>
	        <a href="${pageContext.request.contextPath}/update?action=rent&id=${movie.id}"
            	   class="button"
                >
              	  RENT
              	</a>
              	<a href="${pageContext.request.contextPath}/update?action=return&id=${movie.id}"
              	   class="button"
              	>
              	  RETURN
              	</a>
              	<a href="${pageContext.request.contextPath}/edit?id=${movie.id}"
              	   class="button"
              	>
              	  EDIT
              	</a>
              </div>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>