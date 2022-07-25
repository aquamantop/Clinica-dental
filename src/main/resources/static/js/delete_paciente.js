function borrar(id, nombre, apellido){
    // Constantes
    const url = "/pacientes/eliminar/"
    const fila = document.querySelector("#fila-"+id)

    Swal.fire({
        title: '¿Está seguro de eliminar paciente?',
        text: `Con id ${id}, nombre ${nombre} y apellido ${apellido}`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: 'Cancelar',
        confirmButtonText: 'Si, eliminar'
    }).then((result) => {
        if (result.isConfirmed) {
            const settings = {
                method: 'DELETE'
            }
            fetch(url + id,settings)
            .then(response => console.log(response.json()))
            .then(
                Swal.fire(
                    'Paciente eliminado',
                    'success'
                )
            )
            .catch(e=>console.log(e))
            fila.remove();
        }
    })

}