<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
          <meta charset="utf-8" />
          <meta http-equiv="X-UA-Compatible" content="IE=edge" />
          <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
          <meta name="description" content="Dev IT - Dự án laptopshop" />
          <meta name="author" content="Dev IT" />
          <title>Dashboard - Dev IT</title>
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
                  Dev IT
                </div>
              </nav>
            </div>
            <div id="layoutSidenav_content">
              <main>
                <div class="container mt-5">
                  <table class="table table-bordered grocery-crud-table table-hover mt-3">
                    <thead>
                      <tr>
                        <th>Id</th>
                        <th>TotalPrice</th>
                        <th>User</th>
                        <th>Status</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="order" items="${listorder}">
                        <tr>
                          <td>
                            ${order.id}
                          </td>
                          <td>
                            <fmt:formatNumber type="number" value="${order.totalPrice}" /> <span>đ</span>

                          </td>
                          <td>
                            ${order.user.fullName}
                          </td>
                          <td>
                            ${order.status}
                          </td>

                          <td>
                            <a href="/admin/order/detail/${order.id}" class="btn btn-success">Detail</a>
                            <a href="/admin/order/update/${order.id}" class="btn btn-warning">Update</a>
                            <a type="button" class="btn btn-danger" href="/admin/order/delete/${order.id}"
                              onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                          </td>
                        </tr>
                      </c:forEach>

                    </tbody>
                </div>
                <!-- <jsp:include page="../layout/footer.jsp" /> -->
              </main>

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