<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href=".\display.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        
        <script type="text/javascript">
            $(document).ready( function () {
                $('#myTbl').DataTable( {
                    "paging":   false,
                    "ordering": false,
                    "info":     false,
                    "searching": false,
                    "columns": [
                                null,
                                { "width": "10%" },
                                { "width": "10%" }
                              ]
                } );
            } );
        </script>
        <title>To do list</title>
    </head>
    <body>
       <sql:setDataSource var="ds" driver = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost:3306/to-do-app"
            user = "root"  password = ""/>
        <sql:query dataSource="${ds}" var="rs">
            SELECT username from user_list WHERE uid = <%out.print(session.getAttribute("uid"));%>
        </sql:query>
            <div class="Hform">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-11">
                            <h4>Welcome ${rs.rows[0].username} </h4>           
                        </div>
                        <div class="col-sm-1">
                            <form method="GET" action="Logout">
                                <button type="submit" class="btn btn-danger">Log Out</button>
                            </form>
                        </div>
                    </div>
                </div> 
            </div>
            <div class="Sform">
            <sql:query dataSource="${ds}" var="rs">
            SELECT udata,udid from user_data WHERE uid = <%out.print(session.getAttribute("uid"));%>
            </sql:query>
            <table id="myTbl" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>Tasks</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="table" items="${rs.rows}">  
                    <tr>  
                        <td><c:out value="${table.udata}"/></td>
                        <td>
                            <form method="GET" action="Update">
                                <input type="hidden" value="${table.udid}" name="hiddData">
                                <button type="submit" class="btn btn-primary">Update</button>
                            </form>
                        </td>
                        <td>
                            <form method="GET" action="delete">
                                <input type="hidden" value="${table.udid}" name="delData">
                                <button type="submit" class="btn btn-danger" onclick="alert('Are You Sure You Want To Delete this task?');">Delete</button>
                            </form>
                            
                        </td>
                    </tr>  
                </c:forEach>
            </tbody>
            
        </table>
                <a class="btn btn-success btn-lg btn-block custom" href="add.html">Add Task</a>
                </div>
    </body>
</html>