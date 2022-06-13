<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen</title>
  <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/all.css'>
  <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.2.0/css/fontawesome.css'>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/user/assets/login/style.css">
  <%@ include file="/admin/layout/head/izi-toast-css.jsp" %>

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="screen">
    <div class="screen__content">
      <form method="post" class="login">
        <div class="login__field">
          <i class="login__icon fas fa-user"></i>
          <input type="text" class="login__input" placeholder="User name / Email"
                 id="username" name="username" value="${username}">
        </div>

        <div class="login__field">
          <i class="login__icon fas fa-lock"></i>
          <input type="password" class="login__input" placeholder="Password"
                 id="password" name="password" value="${password}">
        </div>

        <button class="button login__submit">
          <span class="button__text">Log In</span>
          <i class="button__icon fas fa-chevron-right"></i>
        </button>
      </form>
    </div>
    <div class="screen__background">
      <span class="screen__background__shape screen__background__shape4"></span>
      <span class="screen__background__shape screen__background__shape3"></span>
      <span class="screen__background__shape screen__background__shape2"></span>
      <span class="screen__background__shape screen__background__shape1"></span>
    </div>
  </div>
</div>
<!-- partial -->

<%@ include file="/admin/layout/script/izi-toasts.jsp" %>
<%@ include file="/admin/layout/script/izi.toasts-operation-success.jsp" %>
<%@ include file="/admin/layout/script/izi-toats-show-errors.jsp"%>
</body>
</html>
