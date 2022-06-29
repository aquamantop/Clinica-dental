function editar(id) {
    // Constantes
    const ul = document.querySelector("ul")
    const url = '/odontologos/'
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const matricula = document.querySelector("#matricula")
    const volver = document.querySelector("#volver")
    const actualizar = document.querySelector("#actualizar")
    const form = document.querySelector("#form")
    const agregar = document.querySelector("#btnAgregar")
    const p = document.querySelector("#subTitulo")

    ul.classList.add("hide")
    p.classList.add("hide")
    agregar.classList.add("hide")
    form.classList.remove("hide")

    volver.addEventListener('click', () =>{
        ul.classList.remove("hide")
        p.classList.remove("hide")
        agregar.classList.remove("hide")
        form.classList.add("hide")
    })

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
        alert(e)
    })

    actualizar.addEventListener('click', () => {
        // PUT
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
        .catch(e => console.log(e))
    })

}