function borrar(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/borrar/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("borrar" + id).innerText = resp
            }
        }
    )

}