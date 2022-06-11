<c:if test="${operation != null}">
    <script>
        iziToast.success({
            title: 'Success!',
            message: '${operation}',
            timeout: 4000,
            position: "topRight",
            transitionIn:  'bounceInDown',
            transitionOut: 'fadeOutRight',
        });
    </script>
</c:if>