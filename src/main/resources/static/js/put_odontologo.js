window.addEventListener('load', function() {
    // Constantes
    const ul = document.querySelector("ul")
    const url = '/odontologos/'
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const matricula = document.querySelector("#matricula")
    const volver = document.querySelector("#volver")
    const actualizar = document.querySelector("#actualizar")
    const form = document.querySelector("#agregarOdont")
    const agregar = document.querySelector("#agregarOdont")
    const editar = document.querySelectorAll(".editar")

    editar.forEach(e => {
        e.addEventListener('click', () => {
             console.log(e.innerHTML)
            // ID
            let id = e.innerHTML

            document.querySelector("ul").classList.add("hide")
            document.querySelector("#agregarOdont").classList.add("hide")
            document.querySelector("#agregarOdont").classList.remove("hide")

            volver.addEventListener('click', () =>{
                ul.classList.remove("hide")
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
        })
    })



})