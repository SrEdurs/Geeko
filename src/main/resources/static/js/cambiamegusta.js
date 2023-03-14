function cambiamegusta(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/cambiamegusta/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("likes" + id).innerText = resp
                document.getElementById("megusta" + id).classList.replace("bi-hand-thumbs-up","bi-hand-thumbs-up-fill")
                document.getElementById("megusta" + id).setAttribute("style","margin-bottom: 10px; color: #29cb30;")
                /*$("#megusta"+id).setAttribute("width", "50") // cambiar el icono de color según corresponda*/
            }
        }
    )
}
