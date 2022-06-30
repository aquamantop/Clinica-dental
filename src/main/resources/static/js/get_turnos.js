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
            tbody.innerHTML += `<tr id="fila-${e.id}">
                                <th class="datos">
                                <button onclick="borrar(${e.id}, '${e.paciente}', '${e.odontologo}')" class="borrar">X</button>
                                <button onclick="editar(${e.id})" class="editar">Editar</button>
                                </th>
                                <th class="datos">${e.id}</th>
                                <td class="datos">${e.paciente.nombre} ${e.paciente.apellido}</td>
                                <td class="datos">${e.odontologo.nombre} ${e.odontologo.apellido}</td>
                                <td class="datos">${fecha_hora}</td>
                             </tr>`
        })
    })
    .catch(e=>console.log(e))
})