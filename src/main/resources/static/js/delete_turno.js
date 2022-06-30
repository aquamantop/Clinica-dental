function borrar(id, paciente, odontologo){
    // Constantes
    const url = "/turnos/eliminar/"
    const fila = document.querySelector("#fila-"+id)

    let confirmar = confirm("¿Desea eliminar el turno de " + paciente + ", con el odontologo " + odontologo + "?\n" + "Turno con id: " + id)

    if(confirmar){
        const settings = {
            method: 'DELETE'
        }
        fetch(url + id,settings)
        .then(response => response.json())
        .catch(e=>console.log(e))
        fila.remove();
    }

}