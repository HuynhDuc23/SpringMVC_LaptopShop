<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <html lang="en">

      <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
      </head>

      <body>
        <div class="container mt-5">
          <div class="row">
            <div class="col-md-6 col-12 mx-auto">
              <div>
                <p style="color: red; padding: 10px; margin-left: 300px;">Create User</p>
                <hr>
              </div>
              <form:form method="post" action="/createUser" modelAttribute="newUser">
                <div class="mb-3">
                  <label for="email" class="form-label">Email address</label>
                  <form:input type="email" class="form-control" path="email" id="email" />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <form:input type="password" class="form-control" path="password" id="password" />
                </div>
                <div class="mb-3">
                  <label for="fullName" class="form-label">Full Name</label>
                  <form:input type="text" class="form-control" path="fullName" id="fullName" />
                </div>
                <div class="mb-3">
                  <label for="address" class="form-label">Address</label>
                  <form:input type="text" class="form-control" path="address" id="address" />
                </div>
                <div class="mb-3">
                  <label for="phone" class="form-label">Phone</label>
                  <form:input type="text" class="form-control" path="phone" id="phone" />
                </div>
                <form:button type="submit" class="btn btn-primary">Create</form:button>
              </form:form>
            </div>
          </div>
        </div>
      </body>

      </html>