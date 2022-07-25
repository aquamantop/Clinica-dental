function editar(id) {
    // Constantes
    const tabla = document.querySelector("#tablaPacientes")
    const url = '/pacientes/'
    const form = document.querySelector("#form")
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const email = document.querySelector("#email")
    const dni = document.querySelector("#dni")
    const calle = document.querySelector("#calle")
    const numero = document.querySelector("#numero")
    const localidad = document.querySelector("#localidad")
    const provincia = document.querySelector("#provincia")
    const regex = /^[0-9]+$/
    const array = []
    const volver = document.querySelector("#volver")
    const actualizar = document.querySelector("#actualizar")
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

    // Rellenar form con datos del paciente
    fetch(url + 'buscar/' + id)
    .then(response => response.json())
    .then(data => {
        let paciente = data
        nombre.value = paciente.nombre
        apellido.value = paciente.apellido
        email.value = paciente.email
        dni.value = paciente.dni
        calle.value = paciente.domicilio.calle
        numero.value = paciente.domicilio.numero
        localidad.value = paciente.domicilio.localidad
        provincia.value = paciente.domicilio.provincia
    })
    .catch(e => {
        console.log(e)
        Swal.fire(
            'Paciente no encontrado',
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
            email: email.value,
            dni: dni.value,
            domicilio: {
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value}
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
                'Paciente actualziado',
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