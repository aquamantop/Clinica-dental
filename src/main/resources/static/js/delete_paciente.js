function borrar(id, nombre, apellido){
    // Constantes
    const url = "/pacientes/eliminar/"
    const li = document.querySelector("#linea-"+id)

    let confirmar = confirm("¿Desea eliminar a " + nombre + " " + apellido + "?\n Con id: " + id)

    if(confirmar){
        const settings = {
            method: 'DELETE'
        }
        fetch(url + id,settings)
        .then(response => response.json())
        .catch(e=>console.log(e))
        li.remove();
    }

}