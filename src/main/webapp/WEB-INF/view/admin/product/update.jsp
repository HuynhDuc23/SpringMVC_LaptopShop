<%@ page contentType="text/html" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

      <!DOCTYPE html>
      <html lang="en">

      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="Dev IT - Dự án laptopshop" />
        <meta name="author" content="Hỏi Dân IT" />
        <title>Dashboard - Hỏi Dân IT</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/css/styles.css" rel="stylesheet" />
        <link href="/css/errors.css" rel="stylesheet" />

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
                Dev IT
              </div>
            </nav>
          </div>
          <div id="layoutSidenav_content">
            <main>
              <div class="row g-3">
                <div class="col-md-6 col-12 mx-auto">
                  <div>
                    <p>Update Product</p>
                    <hr>
                  </div>
                  <form:form method="post" action="/admin/product/update/${product.id}" modelAttribute="product"
                    enctype="multipart/form-data">
                    <div class="row">
                      <div class="col-md-6">
                        <label for="nameProduct" class="form-label">Name</label>
                        <form:input type="text"
                          class="form-control ${bindingResult.hasFieldErrors('name') ? 'input-error' : ''}" path="name"
                          id="nameProduct" />
                        <form:errors path="name" cssClass="error" />
                      </div>
                      <div class="col-md-6">
                        <label for="price" class="form-label">Price</label>
                        <form:input type="number"
                          class="form-control ${bindingResult.hasFieldErrors('price') ? 'input-error' : ''}"
                          path="price" id="price" />
                        <form:errors path="price" cssClass="error" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <label for="quantity" class="form-label">Quantity</label>
                        <form:input type="number"
                          class="form-control ${bindingResult.hasFieldErrors('quantity') ? 'input-error' : ''}"
                          path="quantity" id="quantity" />
                        <form:errors path="quantity" cssClass="error" />
                      </div>
                      <div class="col-md-6">
                        <label for="detailDesc" class="form-label">Detail Description</label>
                        <form:input type="text"
                          class="form-control ${bindingResult.hasFieldErrors('detailDesc') ? 'input-error' : ''}"
                          path="detailDesc" id="detailDesc" />
                        <form:errors path="detailDesc" cssClass="error" />
                      </div>
                    </div>
                    <div class="mb-3">
                      <label for="shortDesc" class="form-label">Short Description</label>
                      <form:input type="text"
                        class="form-control ${bindingResult.hasFieldErrors('shortDesc') ? 'input-error' : ''}"
                        path="shortDesc" id="shortDesc" />
                      <form:errors path="shortDesc" cssClass="error" />
                    </div>
                    <div class="mb-3">
                      <label for="sold" class="form-label">Sold</label>
                      <form:input type="text"
                        class="form-control ${bindingResult.hasFieldErrors('sold') ? 'input-error' : ''}" path="sold"
                        id="sold" />
                      <form:errors path="sold" cssClass="error" />
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <label for="inputState" class="form-label">Factory</label>
                        <form:select class="form-select" path="factory">
                          <form:option value="LENOVO">LENOVO</form:option>
                          <form:option value="ASUS">ASUS</form:option>
                          <form:option value="IPAD">IPAD</form:option>
                        </form:select>
                      </div>
                      <div class="col-md-6">
                        <label for="inputState" class="form-label">Target</label>
                        <form:select class="form-select" path="target">
                          <form:option value="GAMING">GAMING</form:option>
                          <form:option value="VANPHONG">VANPHONG</form:option>
                        </form:select>
                      </div>
                      <div class="col-md-6">
                        <label for="avatarFile" class="form-label">Default file input example</label>
                        <input class="form-control" type="file" id="avatarFile" accept=".jpg, .png" name="hoidanitFile">
                      </div>
                      <div class="col-12 mb-3">
                        <img style="max-height:250px; display: none;" alt="avatar preview" id="avatarPreview">
                      </div>
                    </div>
                    <form:button type="submit" class="btn btn-primary mt-3">Update</form:button>
                    <a href="/admin/product" class="btn btn-success mt-3">Home</a>
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