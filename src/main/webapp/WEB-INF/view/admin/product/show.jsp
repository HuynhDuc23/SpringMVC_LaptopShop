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
                  <a class="btn btn-primary btn-nueva" href="/admin/product/create"><b>+</b>Create Product</a>
                  <table class="table table-bordered grocery-crud-table table-hover mt-3">
                    <thead>
                      <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Factory</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="productItem" items="${products}">
                        <tr>
                          <td>
                            ${productItem.id}
                          </td>
                          <td>
                            ${productItem.name}
                          </td>
                          <td>
                            ${productItem.factory}
                          </td>
                          <td>
                            <fmt:formatNumber type="number" value=" ${productItem.price}" />
                          </td>
                          <td>
                            ${productItem.quantity}
                          </td>
                          <td>
                            <a href="/admin/product/edit/${productItem.id}" class="btn btn-success">Detail</a>
                            <a href="/admin/product/update/${productItem.id}" class="btn btn-warning">Edit</a>
                            <a type="button" class="btn btn-danger" href="/admin/product/delete/${productItem.id}"
                              onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                          </td>
                        </tr>
                      </c:forEach>
                      <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                          <li class="page-item <c:if test='${currentPage == 1}'> disabled</c:if>"></li>
                          <a class="page-link"
                            href="<c:if test='${currentPage > 1}'>/admin/product?page=${currentPage - 1}</c:if>"
                            tabindex="<c:if test='${currentPage == 1}'>-1</c:if>">Previous</a>
                          </li>
                          <c:forEach var="i" begin="0" end="${totalPages - 1}">
                            <li class="page-item">
                              <c:choose>
                                <c:when test="${currentPage == (i + 1)}">
                                  <a class="page-link active" href="/admin/product?page=${i + 1}">
                                    <c:out value="${i + 1}" />
                                  </a>
                                </c:when>
                                <c:otherwise>
                                  <a class="page-link" href="/admin/product?page=${i + 1}">
                                    <c:out value="${i + 1}" />
                                  </a>
                                </c:otherwise>
                              </c:choose>
                            </li>
                          </c:forEach>
                          <!-- Next Button -->
                          <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
                            <a class="page-link" href="<c:choose>
          <c:when test='${currentPage < totalPages}'>/admin/product?page=${currentPage + 1}</c:when>
          <c:otherwise>#</c:otherwise>
      </c:choose>" tabindex="<c:if test='${currentPage == totalPages}'>-1</c:if>">Next</a>
                          </li>
                        </ul>
                      </nav>

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