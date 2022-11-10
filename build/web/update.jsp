<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update task</title>
        <link rel="stylesheet" href=".\login.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <sql:setDataSource var="ds" driver = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/to-do-app"
            user = "root"  password = ""/>
       
        <sql:query dataSource="${ds}" var="rs">
            SELECT udata from user_data WHERE uid = <%out.print(session.getAttribute("uid"));%> and udid = <%out.print(session.getAttribute("udid"));%> 
        </sql:query>
        <form method="get" action="update_query" class="Uform">
            <h2>Update task</h2>
            <p type="Task:"><input type="text" placeholder="Enter new task" name="Task" value="${rs.rows[0].udata}"/></p>
            <input type="submit" name="UPDATE" class="btn">  
        </form>
    </body>
</html>
