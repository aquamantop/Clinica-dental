window.addEventListener('load', () => {
    // Constantes
    const tbody = document.querySelector("#tbody")
    const url = '/odontologos/listar'

    // GET
    fetch(url)
    .then(response => {
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            tbody.innerHTML += `<tr id="fila-${e.id}">
                                <th class="datos">
                                    <button onclick="borrar(${e.id}, '${e.nombre}', '${e.apellido}')" class="borrar">X</button>
                                    <button onclick="editar(${e.id})" class="editar">Editar</button>
                                </th>
                                <th class="datos">${e.id}</th>
                                <td class="datos">${e.nombre}</td>
                                <td class="datos">${e.apellido}</td>
                                <td class="datos">${e.matricula}</td>
                            </tr>`
        })
    })
    .catch(e=>console.log(e))
})