function borrar(id, paciente, odontologo){
    // Constantes
    const url = "/turnos/eliminar/"
    const fila = document.querySelector("#fila-"+id)

    Swal.fire({
      title: '¿Está seguro de eliminar turno?',
      text: `Con id ${id}, paciente ${paciente} y odontologo ${odontologo}`,
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
                'Turno eliminado',
                'success'
            )
        )
        .catch(e=>console.log(e))
        fila.remove();
      }
    })

}