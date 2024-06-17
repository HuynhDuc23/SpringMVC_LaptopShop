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
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
          $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
              const imgURL = URL.createObjectURL(e.target.files[0]);
              $("#avatarPreview").attr("src", imgURL);
              $("#avatarPreview").css({ "display": "block" });
            });
          }); 
        </script>
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
              <div class="row g-3">
                <div class="col-md-6 col-12 mx-auto">
                  <div>
                    <p>Create User</p>
                    <hr>
                  </div>
                  <form:form method="post" action="/createUser" modelAttribute="newUser" enctype="multipart/form-data">
                    <div class="row">
                      <div class="col-md-6">
                        <c:set var="errorEmail">
                          <form:errors path="email" cssClass="invalid-feedback" />
                        </c:set>
                        <label for="email" class="form-label">Email address</label>
                        <form:input type="email" class="form-control ${not empty errorEmail ? 'is-invalid':'' }"
                          path="email" id="email" />
                        ${errorEmail}
                      </div>
                      <div class="col-md-6">
                        <c:set var="errorPassword">
                          <form:errors path="password" cssClass="invalid-feedback" />
                        </c:set>
                        <label for="password" class="form-label">Password</label>
                        <form:input type="password" class="form-control ${not empty errorPassword ? 'is-invalid':'' }"
                          path="password" id="password" />
                        ${errorPassword}
                      </div>
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
                    <div class="row">
                      <div class="col-md-6">
                        <label for="inputState" class="form-label">Role</label>
                        <form:select class="form-select" path="role.name">
                          <form:option value="ADMIN">ADMIN</form:option>
                          <form:option value="USER">USER</form:option>
                        </form:select>
                      </div>
                      <div class="col-md-6">
                        <label for="avatarFile" class="form-label">Default file input example</label>
                        <input class="form-control" type="file" id="avatarFile" accept=".jpg , .png"
                          name="hoidanitFile">
                      </div>
                      <div class="col-12 mb-3">
                        <img style="max-height:250px ; display: none;" alt="avatar preview" id="avatarPreview">
                      </div>
                    </div>
                    <form:button type="submit" class="btn btn-primary mt-3">Create</form:button>
                  </form:form>
                </div>
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