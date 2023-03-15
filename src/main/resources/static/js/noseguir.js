function noseguir(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/noseguir/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("noseguir" + id).innerText = resp
                window.location.reload();
            }
        }
    )
}