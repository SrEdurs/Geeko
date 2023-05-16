function puntuar(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/puntuar/" + id,
            success: function (resp) {
                //console.log(resp)
                /*document.getElementById("likes" + id).innerText = resp
                document.getElementById("megusta" + id).classList.replace("bi-hand-thumbs-up","bi-hand-thumbs-up-fill")
                document.getElementById("megusta" + id).setAttribute("style","margin-bottom: 10px; color: #29cb30;")
                var boton = document.getElementById("megustas" + id);
                boton.removeAttribute('onclick');
                boton.setAttribute("onclick","nomegusta(this.getAttribute('data-id'))")*/

                var valor = document.getElementById("customRange3").value
                document.getElementById("customRange3").value = resp
                alert("Puntuación realizada con éxito")
                alert("Puntuación actual: " + resp)
            }
        }
    )
}
