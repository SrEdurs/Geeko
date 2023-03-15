function seguir(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/seguir/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("seguir" + id).innerText = resp
                window.location.reload();
            }
        }
    )
}
