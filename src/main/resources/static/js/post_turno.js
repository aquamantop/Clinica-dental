window.addEventListener('load', () => {
    // Constantes
    const form = document.querySelector("#form")
    const paciente = document.querySelector("#paciente")
    const odontologo = document.querySelector("#odontologo")
    const fecha = document.querySelector("#fecha")
    const regex = /^[0-9]+$/
    const url = '/odontologos/guardar'
    const array = []

    matricula.addEventListener("keydown", (e) => {
        if (e.code === "Backspace") {
            array.pop()
        }
    })
    matricula.addEventListener("keypress", (e) => {
        if (!validarMatricula(e.key)) {
            e.preventDefault()
        }
    })
    function validarMatricula (cant) {
        let p = cant.match(regex)

        if (p && array.length < 10) {
            array.push(cant)
            return true
        } else return false
    }

    form.addEventListener('submit', (e) => {
        e.preventDefault()
        const formData = {
            nombre: nombre.value,
            apellido: apellido.value,
            matricula: matricula.value,
        }
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }

        if(paciente.innerHTML != "Seleccionar un paciente"
            && odontologo.innerHTML != "Seleccionar un paciente"
            && fecha.value.trim() != ""){
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

    function resetearForm(){
        nombre.value = ""
        apellido.value = ""
        matricula.value = ""
        for (let i = array.length; i > 0; i--) {
            array.pop();
          }
    }

})