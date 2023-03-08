function cambiareporte(id) {
    // llamar a la funcion del servidor para aumentar el like

    $.ajax(
        {url: "/cambiareporte/"+id,
            success: function(resp) {
                console.log(resp)
                document.getElementById("reporte"+id).innerText=resp
                /*$("#megusta"+id).setAttribute("width", "50") // cambiar el icono de color según corresponda*/
            }}

    )
    window.location.reload();

}