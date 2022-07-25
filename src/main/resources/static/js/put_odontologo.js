function editar(id) {
    // Constantes
    const tabla = document.querySelector("#tabla")
    const url = '/odontologos/'
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const matricula = document.querySelector("#matricula")
    const volver = document.querySelector("#volver")
    const actualizar = document.querySelector("#actualizar")
    const form = document.querySelector("#form")
    const agregar = document.querySelector("#btnAgregar")
    const p = document.querySelector("#subTitulo")

    tabla.classList.add("hide")
    p.classList.add("hide")
    agregar.classList.add("hide")
    form.classList.remove("hide")

    volver.addEventListener('click', () =>{
        tabla.classList.remove("hide")
        p.classList.remove("hide")
        agregar.classList.remove("hide")
        form.classList.add("hide")
    })

    // Rellenar form con datos del odontologo
    fetch(url + 'buscar/' + id)
    .then(response => response.json())
    .then(data => {
        let odontologo = data
        nombre.value = odontologo.nombre
        apellido.value = odontologo.apellido
        matricula.value = odontologo.matricula
    })
    .catch(e => {
        console.log(e)
        Swal.fire(
            'Odontologo no encontrado',
            'info'
        )
    })

    // PUT
    actualizar.addEventListener('click', (e) => {
        e.preventDefault()
        const formData = {
            id: id,
            nombre: nombre.value,
            apellido: apellido.value,
            matricula: matricula.value
        }
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url + 'actualizar',settings)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            Swal.fire(
                'Odontologo actualizado',
                'success'
            )
            tabla.classList.remove("hide")
            p.classList.remove("hide")
            agregar.classList.remove("hide")
            form.classList.add("hide")
            location.reload()
        })
        .catch(e => console.log(e))

    })

}