window.addEventListener('load', () => {
    // Constantes
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
    const url = '/pacientes/guardar'
    const array = []

    // Validaciones
    dni.addEventListener("keydown", (e) => {
        if (e.code === "Backspace") {
            array.pop()
        }
    })
    dni.addEventListener("keypress", (e) => {
        if (!validarDNI(e.key)) {
            e.preventDefault()
        }
    })
    function validarDNI (cant) {
        let p = cant.match(regex)

        if (p && array.length < 10) {
            array.push(cant)
            return true
        } else return false
    }

    // POST
    form.addEventListener('submit', (e) => {
        e.preventDefault()
        const formData = {
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
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        if(nombre.value.trim() != "" && apellido.value.trim() != "" && email.value.trim() != "" && dni.value.trim() != ""
            && calle.value.trim() != "" && numero.value.trim() != "" && localidad.value.trim() != "" && provincia.value.trim() != ""){
            fetch(url, settings)
            .then(response => {
                response.json()
            })
            .then(data => {
                console.log(data)
                alert("Datos enviados")
                resetearForm()
            })
            .catch(e => {
                console.log(e)
                alert(e)
                resetearForm()
            })
        } else alert("Completar datos")
    })

    // Resetear form
    function resetearForm(){
        nombre.value = ""
        apellido.value = ""
        email.value = ""
        dni.value = ""
        calle.value = ""
        numero.value = ""
        localidad.value = ""
        provincia.value = ""
        for (let i = array.length; i > 0; i--) {
            array.pop();
        }
    }

})