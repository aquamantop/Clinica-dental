function borrar(){
    // Constantes
    const borrar = document.querySelectorAll(".borrar")
    const url = "/odontologos/eliminar/"

    borrar.forEach(e => {
        e.addEventListener('click', () => {
            let id = e.id

            const li = document.querySelector("#linea-"+id)

            let confirmar = confirm("¿Desea eliminar el odontologo con id: " + id + "?")

            if(confirmar){
                const settings = {
                    method: 'DELETE'
                }
                fetch(url + id,settings)
                .then(response => response.json())
                .catch(e=>console.log(e))
                li.remove();
            }
        })
    })

}