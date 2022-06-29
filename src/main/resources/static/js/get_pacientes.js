window.addEventListener('load', () => {
    // Constantes
    const ul = document.querySelector("ul")
    const url = '/pacientes/listar'

    // GET
    fetch(url)
    .then(response => {
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(e => {
            ul.innerHTML += `<li id="linea-${e.id}">
                                <button onclick="borrar(${e.id})" class="borrar">X</button>
                                <button onclick="editar(${e.id})" class="editar">Editar</button>
                                Paciente: ${e.nombre} ${e.apellido}. Email: ${e.email}.
                             </li>`
        })
    })
    .catch(e=>console.log(e))
})