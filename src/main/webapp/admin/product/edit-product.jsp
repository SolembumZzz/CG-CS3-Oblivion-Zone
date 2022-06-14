<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit product</title>
    <%@ include file="/admin/layout/head/head.jsp" %>
    <%@ include file="/admin/layout/head/izi-toast-css.jsp" %>
    <%@ include file="/admin/layout/head/datatable-css.jsp" %>
    <%@ include file="/admin/layout/head/app-css.jsp" %>
</head>
<body data-layout="horizontal">
<div id="wrapper">
    <header id="topnav">
        <div class="navbar-custom">
            <%@ include file="/admin/layout/navbar-custom.jsp" %>
        </div>
        <div class="topbar-menu">
            <%@ include file="/admin/layout/topbar-menu.jsp" %>
        </div>
    </header>

    <!-- ============================================================== -->
    <!-- PAGE CONTENT -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- PAGE TITLE -->
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box">
                        <h4 class="page-title">Edit product</h4>
                    </div>
                </div>
            </div>
            <!-- END PAGE TITLE -->

            <!--START WIZARD -->
            <div class="row">
                <div class="col-md-12">
                    <div class="card-box">
                        <h4 class="header-title">Product Information</h4>
                        <p class="sub-header">
                            Etiam consequat enim et ligula tincidunt gravida. Sed semper vitae erat sagittis tristique.
                            Ut ornare quam quis ante dignissim, sit amet efficitur erat pulvinar. Ut neque ligula,
                            bibendum a lacinia sed, cursus ac lorem.
                        </p>

                        <form method="post">
                            <div>
                                <h3>Product details</h3>

                                <div class="form-group row">
                                    <label class="col-lg-12 control-label">(*) Mandatory</label>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="productName">Product Name*</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 6, max 20 chars"
                                               name="productName" id="productName" required
                                               value="${name}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="productPrice">Price*</label>
                                    <div class="col-lg-10">
                                        <input type="number" class="form-control"
                                               placeholder="min 20.000vnd, max 100.000.000vnd"
                                               name="productPrice" id="productPrice" required
                                               value="${price}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="productQuantity">Quantity*</label>
                                    <div class="col-lg-10">
                                        <input type="number" class="form-control" placeholder="min 1, max 5.000"
                                               name="productQuantity" id="productQuantity" required
                                               value="${quantity}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="productCategory">Category*</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 3, max 10 chars"
                                               name="productCategory" id="productCategory" required
                                               value="${category}">
                                    </div>
                                </div>

                                <h3>Specifications</h3>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="brand">Brand</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="brand" id="brand"
                                               value="${brand}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="mainboard">Main Board</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="mainboard" id="mainboard"
                                               value="${mainboard}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="cpu">CPU</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="cpu" id="cpu" value="${CPU}">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-lg-2" for="ram">RAM</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="ram" id="ram" value="${RAM}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="vga">VGA</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="vga" id="vga" value="${VGA}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="harddrive">Hard Drive</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" placeholder="min 4, max 20 chars"
                                               name="harddrive" id="harddrive"
                                               value="${harddrive}">
                                    </div>
                                </div>

                                <div class="form-group row align-items-center">
                                    <label class="col-lg-2" for="insurance">Insurance</label>
                                    <div class="col-lg-10">
                                        <input type="number" class="form-control" placeholder="min 0, max 120 months"
                                               name="insurance" id="insurance"
                                               value="${insurance}">
                                    </div>
                                </div>

                                <div class="form-group row justify-content-sm-center justify-content-md-end">
                                    <div class="col-auto">
                                        <a class="btn btn-secondary waves-effect waves-light" href="/products">
                                            Cancel
                                        </a>
                                        <button class="btn btn-primary waves-effect waves-light" type="submit">
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!-- END WIZARD -->

            </div>

        </div>
        <!-- end content -->

        <!-- Footer Start -->
        <footer class="footer">
            <%@ include file="/admin/layout/footer.jsp" %>
        </footer>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- END PAGE CONTENT -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->

<%@ include file="/admin/layout/script/izi-toasts.jsp" %>
<%@ include file="/admin/layout/script/izi.toasts-operation-success.jsp" %>
<%@ include file="/admin/layout/script/izi-toats-show-errors.jsp" %>
<!-- Vendor js -->
<script src="${pageContext.request.contextPath}/admin/assets/js/vendor.min.js"></script>

<!-- App js -->
<script src="${pageContext.request.contextPath}/admin/assets/js/app.min.js"></script>
</body>

</html>