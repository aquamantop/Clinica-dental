const arrayPacientes = []
const arrayOdontologos = []

function editar(id) {
    // Constantes
    const tabla = document.querySelector("#tabla")
    const url = '/turnos/'
    const urlP = '/pacientes/listar'
    const urlO = '/odontologos/listar'
    const paciente = document.querySelector("#paciente")
    const odontologo = document.querySelector("#odontologo")
    const fecha = document.querySelector("#fecha")
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

    // Listar pacientes
    fetch(urlP)
    .then(response => {
            return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            if(arrayPacientes.includes(e.id)){
                console.log("Id: " + e.id + " ya cargado en pantalla")
            } else {
                paciente.innerHTML += `<option value="${e.id}">
                                            ${e.nombre} ${e.apellido}. Email: ${e.email}
                                       </option>`
                arrayPacientes.push(e.id)
                console.log(arrayPacientes)
            }
        })
    })
    .catch(e=>console.log(e))

    // Listar odontologos
    fetch(urlO)
    .then(response => {
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            if(arrayOdontologos.includes(e.id)){
                console.log("Id: " + e.id + " ya cargado en pantalla")
            } else {
                odontologo.innerHTML += `<option value="${e.id}">
                                            ${e.nombre} ${e.apellido}. Matricula: ${e.matricula}.
                                         </option>`
                arrayOdontologos.push(e.id)
                console.log(arrayOdontologos)
            }
        })
    })
    .catch(e=>console.log(e))

    // Rellenar form con datos del turno
    fetch(url + 'buscar/' + id)
    .then(response => response.json())
    .then(data => {
        let turno = data
        paciente.value = turno.paciente.id
        odontologo.value = turno.odontologo.id
        fecha.value = turno.fechaHora
    })
    .catch(e => {
        console.log(e)
        alert("Error al cargar turno"+e)
    })

    // PUT
    actualizar.addEventListener('click', (e) => {
        e.preventDefault()
        const formData = {
            id: id,
            paciente: {id: paciente.value},
            odontologo: {id: odontologo.value},
            fechaHora: fecha.value
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
            alert("Turno actualizado")
            tabla.classList.remove("hide")
            p.classList.remove("hide")
            agregar.classList.remove("hide")
            form.classList.add("hide")
            vaciarArray(arrayOdontologos)
            vaciarArray(arrayPacientes)
            location.reload()
        })
        .catch(e => console.log(e))
    })

}

function vaciarArray(array){
    for (let i = array.length; i > 0; i--) {
        array.pop();
    }
}