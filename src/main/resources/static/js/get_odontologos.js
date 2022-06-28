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
                ul.innerHTML += `<li>${e.id + ') Odontologo: ' + e.nombre + ' ' +e.apellido + '. Matricula: '
                + e.matricula}</li>
                `
            });
        })

})