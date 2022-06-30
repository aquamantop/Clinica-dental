window.addEventListener('load', () => {
    // Constantes
    const tbody = document.querySelector("#tbody")
    const url = '/turnos/listar'

    // GET
    fetch(url)
    .then(response => {
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            let fecha_hora = e.fechaHora.replace("T", " ")
            let nombreCompleto = e.paciente.nombre + " " + e.paciente.apellido
            let nombreCompletoOdont = e.odontologo.nombre + " " + e.odontologo.apellido

            tbody.innerHTML += `<tr id="fila-${e.id}">
                                <th class="datos">
                                <button onclick="borrar(${e.id}, '${nombreCompleto}', '${nombreCompletoOdont}')" class="borrar">X</button>
                                <button onclick="editar(${e.id})" class="editar">Editar</button>
                                </th>
                                <th class="datos">${e.id}</th>
                                <td class="datos">${nombreCompleto}</td>
                                <td class="datos">${nombreCompletoOdont}</td>
                                <td class="datos">${fecha_hora}</td>
                             </tr>`
        })
    })
    .catch(e=>console.log(e))
})