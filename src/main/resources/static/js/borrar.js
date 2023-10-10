var selectedElementId;

    function setSelectedElementId(elementId) {
        selectedElementId = elementId;
        console.log("Holaa el id de lo seleccionado es: " + selectedElementId)
    }

    function borrar() {
        if (selectedElementId) {
            $.ajax({
                url: "/borrar/" + selectedElementId,
                method: "DELETE",
                success: function (resp) {
                    window.location.reload();                   
                }
            });
        }
    }