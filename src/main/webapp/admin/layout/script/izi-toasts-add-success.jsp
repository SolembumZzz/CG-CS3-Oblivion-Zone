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

        window.onload = function () {
            document.querySelectorAll("input.form-control").forEach(e => {
                e.value = "";
            });
        }
    </script>
</c:if>