function borrarchat(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/borrarchat/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("borrarchat" + id).innerText = resp
                window.location.reload();
            }
        }
    )
    
}