<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Dashboard</title>
    <%@ include file="/admin/layout/head/head.jsp" %>
    <%@ include file="/admin/layout/head/izi-toast-css.jsp" %>
    <%@ include file="/admin/layout/head/datatable-css.jsp" %>
    <%@ include file="/admin/layout/head/app-css.jsp" %>
</head>
<body data-layout="horizontal">
<!-- Begin page -->
<div id="wrapper">
    <!-- Navigation Bar-->
    <header id="topnav">
        <!-- Topbar Start -->
        <div class="navbar-custom">
            <%@ include file="/admin/layout/navbar-custom.jsp" %>
        </div>
        <!-- end Topbar -->
        <div class="topbar-menu">
            <%@ include file="/admin/layout/topbar-menu.jsp" %>
        </div>
        <!-- end navbar-custom -->
    </header>
    <!-- End Navigation Bar-->

    <!-- ============================================================== -->
    <!-- PAGE CONTENT -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- PAGE TITLE -->
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box">
                        <h4 class="page-title">Products</h4>
                    </div>
                </div>
            </div>
            <!-- END PAGE TITLE -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="card-box table-responsive">
                        <div class="row">
                            <div class="col-10">
                                <h4 class="header-title"><b>All Products</b></h4>
                                <p class="sub-header">
                                    All products including locked ones.
                                </p>
                            </div>
                            <div class="col-2">
                                <div style="float: right">
                                    <a class="btn btn-primary waves-effect width-md waves-light"
                                       href="${pageContext.request.contextPath}/products?action=create">
                                        <i class="far fa-plus-square"></i>
                                        Add product
                                    </a>
                                </div>
                            </div>
                        </div>

                        <table id="datatable-keytable" class="table table-striped table-bordered dt-responsive nowrap"
                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                            <thead>
                            <tr>
                                <th class="col-1">ID</th>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th class="col-1">Quantity</th>
                                <th class="col-1">Locked</th>
                                <th class="col-1" id="action">Action</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="product" items="${productList}">
                                <tr>
                                    <td class="text-center">${product.getId()}</td>
                                    <td>${product.getName()}</td>
                                    <td>${product.getCategory()}</td>
                                    <td class="text-right">
                                        <fmt:formatNumber type="currency" pattern="#,###₫" value="${product.getPrice()}"/>
                                    </td>
                                    <td class="text-center">${product.getQuantity()}</td>
                                    <td>${product.isLocked() ? 'Locked' : 'Not locked'}</td>
                                    <td class="text-center">
                                        <a class="btn btn-outline-secondary waves-effect waves-light" title="Edit"
                                            href="/products?action=edit&id=${product.getId()}">
                                            <i class="fas fa-pen"></i>
                                        </a>
                                        <a class="btn btn-outline-danger waves-effect waves-light" title="${product.isLocked() ? 'Unlock' : 'Lock'}"
                                            href="${product.isLocked() ? '/products?action=unlock&id=' : '/products?action=lock&id='}${product.getId()}">
                                                ${product.isLocked() ? '<i class="fas fa-unlock"></i>' : '<i class="fas fa-lock"></i>'}
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
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
<%@ include file="/admin/layout/script/izi.toasts-operation-success.jsp"%>
<%@ include file="/admin/layout/script/izi-toats-show-errors.jsp"%>

<script src="${pageContext.request.contextPath}/admin/assets/js/vendor.min.js"></script>
<%@ include file="/admin/layout/script/datatable.jsp" %>
<%@ include file="/admin/layout/script/libs-app.jsp" %>

<script src="${pageContext.request.contextPath}/admin/assets/js/app.min.js"></script>\

<script>

    /*document.addEventListener("DOMContentLoaded", function () {
        console.log("ready....");
        document.getElementById("action").classList.remove("sorting");
    });*/
   $(document).ready(function () {
       console.log("ready....");
       document.getElementById("action").classList.remove("sorting");

       // $("action").off("change");
       // document.getElementById("action").removeEventListener("click", ()=>{
       //     console.log("remove click")
       // });
       // document.getElementById("action").removeEventListener("change", ()=>{
       //     console.log("remove click")
       // });
   });
</script>
</body>

</html>