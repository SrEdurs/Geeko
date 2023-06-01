var selectedElementId;

    function setSelectedElementId(elementId) {
        selectedElementId = elementId;
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