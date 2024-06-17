<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
        <meta name="author" content="Hỏi Dân IT" />
        <title>Dashboard - Hỏi Dân IT</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
      </head>

      <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp" />
        <div id="layoutSidenav">
          <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
              <jsp:include page="../layout/sidebar.jsp" />
              <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
                Hỏi Dân IT
              </div>
            </nav>
          </div>
          <div id="layoutSidenav_content">
            <main>
              <div class="container mt-5">
                <a class="btn btn-primary btn-nueva" href="/create"><b>+</b>Create User</a>
                <table class="table table-bordered grocery-crud-table table-hover mt-3">
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Email</th>
                      <th>FullName</th>
                      <th>Role</th>
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
                          ${user.role.name} </td>
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
            </main>
            <jsp:include page="../layout/footer.jsp" />
          </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
          crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
          crossorigin="anonymous"></script>
        <script src="/js/chart-area-demo.js"></script>
        <script src="/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
          crossorigin="anonymous"></script>
        <script src="/js/datatables-simple-demo.js"></script>
      </body>

      </html>