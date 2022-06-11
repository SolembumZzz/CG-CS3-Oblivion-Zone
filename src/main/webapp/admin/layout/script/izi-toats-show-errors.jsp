<c:if test="${errorMessages.size() > 0}">
    <c:forEach var="error" items="${errorMessages}">
        <script>
            iziToast.error({
                title: 'Error',
                message: '${error}',
                timeout: 10000,
                position: "topRight",
                transitionIn:  'bounceInDown',
                transitionOut: 'fadeOutRight',
            });
        </script>
    </c:forEach>
</c:if>