function nomegusta(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/nomegusta/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("likes" + id).innerText = resp
                document.getElementById("megusta" + id).classList.replace("bi-hand-thumbs-up-fill","bi-hand-thumbs-up")
                document.getElementById("megusta" + id).setAttribute("style","margin-bottom: 10px;")
                var boton = document.getElementById("megustas" + id);
                boton.removeAttribute('onclick');
                boton.setAttribute("onclick","cambiamegusta(this.getAttribute('data-id'))")
            }
        }
    )
}