window.addEventListener('load', () => {
    // Constantes
    const ul = document.querySelector("ul")
    const url = '/odontologos/listar'

    // GET
    fetch(url)
    .then(response => {
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            ul.innerHTML += `<li id="linea-${e.id}">
                                <button onclick="borrar(${e.id}, '${e.nombre}', '${e.apellido}')" class="borrar">X</button>
                                <button onclick="editar(${e.id})" class="editar">Editar</button>
                                ${e.nombre} ${e.apellido}. Matricula: ${e.matricula}.
                             </li>`
        })
    })
    .catch(e=>console.log(e))
})