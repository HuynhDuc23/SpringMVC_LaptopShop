<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Table</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../../../../resources/css/list.css">
      </head>

      <body>
        <div class="container mt-5">
          <a class="btn btn-primary btn-nueva" href="/create"><b>+</b>Create User</a>
          <table class="table table-bordered grocery-crud-table table-hover mt-3">
            <thead>
              <tr>
                <th>Id</th>
                <th>Email</th>
                <th>FullName</th>
                <th>Email</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="user" items="${users}">
                <tr>
                  <td>
                    ${user.id}
                  </td>
                  <td>
                    ${user.email}
                  </td>
                  <td>
                    ${user.fullName} </td>
                  <td>
                    ${user.phone} </td>
                  <td>
                    <a href="/user-details/${user.id}" class="btn btn-success">Detail</a>
                    <a href="/update/${user.id}" class="btn btn-warning">Edit</a>
                    <a type="button" class="btn btn-danger" href="delete/${user.id}"
                      onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                  </td>
                </tr>
              </c:forEach>

            </tbody>
          </table>


        </div>

      </body>

      </html>