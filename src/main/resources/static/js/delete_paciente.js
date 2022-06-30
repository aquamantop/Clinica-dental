function borrar(id, nombre, apellido){
    // Constantes
    const url = "/pacientes/eliminar/"
    const fila = document.querySelector("#fila-"+id)

    let confirmar = confirm("¿Desea eliminar a " + nombre + " " + apellido + "?\n Con id: " + id)

    if(confirmar){
        const settings = {
            method: 'DELETE'
        }
        fetch(url + id,settings)
        .then(response => console.log(response.json()))
        .catch(e=>console.log(e))
        fila.remove();
    }

}