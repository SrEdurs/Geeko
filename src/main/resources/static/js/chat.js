function chat(id) {
    $.ajax(
        {
            url: "/chat/" + id,
            success: function (resp) {
                console.log(resp)
                document.getElementById("chat" + id).innerText = resp
                window.location.href = "http://localhost:8080/social"
            }
        }
    )
}
