window.addEventListener('load', () => {
    // Constantes
    const ul = document.querySelector("ul")
    const url = '/odontologos/listar'

    console.log(ul)
        // GET
        fetch(url)
        .then(response => {
            return response.json()
        })
        .then(data => {
            console.log(data)
            data.forEach(e => {
                ul.innerHTML += `<li>
                                 <button class="borrar">X</button>
                                 <button class="editar">${e.id}</button>
                                 ${'Odontologo: ' + e.nombre + ' ' +e.apellido + '. Matricula: '
                                 + e.matricula}
                                 </li>`
            });
        })

})