function noseguir(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/noseguir/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("likes" + id).innerText = resp
                /*$("#megusta"+id).setAttribute("width", "50") // cambiar el icono de color según corresponda*/
            }
        }
    )
    window.location.reload();
}