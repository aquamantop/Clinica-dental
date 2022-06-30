window.addEventListener('load', () => {
    // Constantes
    const form = document.querySelector("#form")
    const paciente = document.querySelector("#paciente")
    const odontologo = document.querySelector("#odontologo")
    const fecha = document.querySelector("#fecha")
    const enviar = document.querySelector("#enviar")
    const date = new Date()
    const urlT = '/turnos/guardar'
    const urlP = '/pacientes/listar'
    const urlO = '/odontologos/listar'

    // Dia
    let d = date.getDate()
    // Mes
    let m = date.getMonth()+1
    // Hora
    let h = date.getHours()
    // Agregamos un 0 si es un solo digito
    let dia = () => {
        if(d < 10){
            return '0'+d
        } else return d
    }
    let mes = () => {
        if(m < 9){
            return '0'+m
        } else return d
    }
    let hora = () => {
        if(h < 10){
            return '0'+h
        } else return h
    }

    // Damos formato
    formato = [date.getFullYear(), mes(), dia()].join('-') +
    'T' + [hora(),date.getMinutes()].join(':');
    // Insertamos la fecha en el valor y el minimo
    fecha.value = formato
    fecha.min = formato

    // Listar pacientes
    fetch(urlP)
    .then(response => {
            return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            paciente.innerHTML += `<option id="" value="${e.id}">
                                        ${e.nombre} ${e.apellido}. Email: ${e.email}
                                   </option>`
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
            odontologo.innerHTML += `<option value="${e.id}">
                                        ${e.nombre} ${e.apellido}. Matricula: ${e.matricula}.
                                     </option>`
        })
    })
    .catch(e=>console.log(e))

    form.addEventListener('submit', (e) => {
        e.preventDefault()
        let fechaELegida = new Date(fecha.value)
        let fechaActual = new Date()
        let valorFecha = fecha.value

        if(fechaELegida < fechaActual){
            alert("Elegir fecha correctamente")
        } else {
            const formData = {
                paciente: {
                    id: paciente.value,
                },
                odontologo: {
                    id: odontologo.value,
                },
                fechaHora: fecha.value
            }
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            }
            fetch(urlT, settings)
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
        }
    })

    function resetearForm(){
        form.reset()
        fecha.value = formato
        fecha.min = formato
    }

})