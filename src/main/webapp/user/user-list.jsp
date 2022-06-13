<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13/6/2022
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
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
                        <h4 class="page-title">Users</h4>
                    </div>
                </div>
            </div>
            <!-- END PAGE TITLE -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="card-box table-responsive">
                        <div class="row">
                            <div class="col-10">
                                <h4 class="header-title"><b>All Users</b></h4>
                                <p class="sub-header">
                                    This is but a list.
                                </p>
                            </div>
<%--                            <div class="col-2">--%>
<%--                                <div style="float: right">--%>
<%--                                    <a class="btn btn-primary waves-effect width-md waves-light"--%>
<%--                                       href="${pageContext.request.contextPath}/users?action=create">--%>
<%--                                        <i class="far fa-plus-square"></i>--%>
<%--                                        Add user--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>

                        <table id="datatable-keytable" class="table table-striped table-bordered dt-responsive nowrap"
                               style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                            <thead>
                            <tr>
                                <th class="col-1">ID</th>
                                <th>Username</th>
                                <th>Role</th>
                                <th class="col-1 text-center" id="action">Block</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td class="text-center">${user.getId()}</td>
                                    <td>${user.getUsername()}</td>
                                    <td>${user.getRole()}</td>
                                    <td class="text-center">
                                        <a class="btn btn-outline-danger waves-effect waves-light" title="Block"
                                           href="/users?action=block&id=${user.getId()}">
                                            <i class="fas fa-ban"></i>
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
