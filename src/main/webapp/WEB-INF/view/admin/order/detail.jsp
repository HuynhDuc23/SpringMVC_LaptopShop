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
          <meta name="description" content="Dự án laptopshop" />
          <meta name="author" content=" IT" />
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
                <!-- table -->
                <div class="container mt-5">
                  <table class="table table-bordered grocery-crud-table table-hover mt-3">
                    <thead>
                      <tr>
                        <th>Id</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="orderDetail" items="${orderDetails}">
                        <tr>
                          <td>
                            ${orderDetail.id}
                          </td>
                          <td>
                            <img src="/images/product/${orderDetail.product.image}" alt="error">
                          </td>
                          <td>
                            ${orderDetail.product.name}
                          </td>
                          <td>
                            ${orderDetail.quantity}
                          </td>
                          <td>
                            <fmt:formatNumber type="number" value=" ${orderDetail.price}" /> <span>đ</span>

                          </td>
                        </tr>
                      </c:forEach>

                    </tbody>

                </div>
                <a href="/admin/order" class="btn btn-success mt-3">Home</a>
              </main>
              <!-- <jsp:include page="../layout/footer.jsp" /> -->
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