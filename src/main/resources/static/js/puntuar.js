function puntuar(id, valor) {
    $.ajax(
        {
            url: "/puntuar/" + id + "/" + valor,
            success: function (resp) {
                //Recargar la página
                window.location.reload();                
            }
        }
    )
}
