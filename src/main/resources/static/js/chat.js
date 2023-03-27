function chat(id) {
    // llamar a la funcion del servidor para aumentar el like
    $.ajax(
        {
            url: "/chat/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("chat" + id).innerText = resp
                window.location.href = "http://localhost:8080/chat/id/" + id;
            }
        }
    )
}
