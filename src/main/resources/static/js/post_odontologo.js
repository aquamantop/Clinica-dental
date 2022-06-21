window.addEventListener('load', () => {
    // Constantes
    const form = document.querySelector("#form")
    const nombre = document.querySelector("#nombre")
    const apellido = document.querySelector("#apellido")
    const matricula = document.querySelector("#matricula")
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
        } else false
    }

    form.addEventListener('submit', (e) => {
        const formData = {
            nombre: nombre.value,
            apellido: apellido.value,
            matricula: matricula.value,
        }
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        if(nombre.value != null && apellido.value != null && matricula.value != null){
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
                alert("ERROR")
                resetearForm()
                console.log(e)
            })
        }
    })

    function resetearForm(){
        document.querySelector('#nombre').value = ""
        document.querySelector('#apellido').value = ""
        document.querySelector('#matricula').value = ""
        for (let i = array.length; i > 0; i--) {
            array.pop();
          }
    }

})