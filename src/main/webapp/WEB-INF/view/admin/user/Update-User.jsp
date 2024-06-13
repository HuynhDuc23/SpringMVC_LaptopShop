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
                    <div class="container mt-5">
                      <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                          <div>
                            <p style="color: red; padding: 10px; margin-left: 300px;">Update User</p>
                            <hr>
                          </div>
                          <form:form method="post" action="/update/${newUser.id}" modelAttribute="newUser">
                            <div class="mb-3">
                              <form:input type="hidden" class="form-control" path="id" id="id" aria-readonly="true" />
                            </div>
                            <div class="mb-3">
                              <label for="email" class="form-label">Email address</label>
                              <form:input type="email" class="form-control" path="email" id="email" />
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
                            <form:button type="submit" class="btn btn-warning">Update</form:button>
                          </form:form>
                        </div>
                      </div>
                    </div>
                  </div>
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