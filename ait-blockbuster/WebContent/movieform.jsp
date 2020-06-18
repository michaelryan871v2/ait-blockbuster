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
      <c:if test="${movie != null}">
        <h2>Edit Movie</h2>
        <form action="update" method="post">
          <input type="hidden" name="id" value="<c:out value="${movie.id}" />" />
          
          <label>
            Title
            <input type="text" name="title" value="<c:out value="${movie.title}" />" />
          </label>
          <label>
            Director
            <input type="text" name="director" value="<c:out value="${movie.director}" />" />
          </label>
          <label>
          	Film Duration
          	 <input type="text" name="filmLength" value="<c:out value="${movie.filmLength}" />" />
          </label>
            # of Copies
            <select name="copies">
              <c:forEach begin="1" end="15" varStatus="loop">
                <option value="${loop.index}"
                  <c:if test="${movie.copies == loop.index}">selected</c:if>
                >
                  ${loop.index}
                </option>
              </c:forEach>
            </select>
          </label>
          <div class="form-actions">
            <input type="submit" value="SAVE" name="submit" />
            <input type="submit" value="DELETE" name="submit" />
          </div>
        </form>
      </c:if>
      <c:if test="${movie == null}">
        <h2>Add Movie</h2>
        <form action="insert" method="post">
          <input type="hidden" name="id" />
          
          <label>
            Title
            <input type="text" name="title" />
          </label>
          <label>
            Director
            <input type="text" name="director" />
          </label>
          <label>
            Film Length
            <input type="text" name="filmLength" />
          </label>
          <label>
            # of Copies
            <input type="text" name="copies" />
          </label>
          <input type="submit" value="ADD" name="submit" />
        </form>
      </c:if>
    </div>
  </body>
</html>