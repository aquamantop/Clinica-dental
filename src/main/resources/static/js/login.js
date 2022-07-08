/* -------------------------------------------------------------------------- */
/*                   logica aplicada en la pantalla de LOGIN                  */
/* -------------------------------------------------------------------------- */

const apiUrl = 'http://localhost:8080/authenticate';

window.addEventListener('load', function(){
    console.log("Entramos en el EventListener");
    //asociamos el formulario para tener una referencia al mismo
    const formulario =  this.document.forms[0];
    //creamos una referencia a la entrada del mail, que utilizaremos para constatar contra la base de datos
    const inputEmail = this.document.querySelector('#inputEmail');
    //creamos una referencia a la entrada del password que utilizaremos para constatar contra la base de datos
    const inputPassword =  this.document.querySelector('#inputPassword');

    formulario.addEventListener('submit', function(event){
        event.preventDefault();
        /* utilizamos la funcion "validacionNoVacio" para verificar que los datos del mail y password no son vacíos
        y lo guardamos en la constante "validacion". */
        const validacion = validacionNoVacio(inputEmail.value) && validacionNoVacio(inputPassword.value);
        // si "validacion" es igual a TRUE, osea, no está vacío
        if(validacion){
            console.log("Entramos en validación");
            const datosUsuario = normalizacionLogin(inputEmail.value, inputPassword.value);
            console.log(datosUsuario);
            //consultamos al servidor y esperamos su respuesta
            fetchApiLogin(apiUrl, datosUsuario);
        }else{
            // si "validacion" es igual a FALSE, osea, está vacío.
            console.log("algun dato no es correcto");
        }
        formulario.reset();
    });
});

/* -------------------------------------------------------------------------- */
/*                      seccion de funciones disponibles                      */
/* -------------------------------------------------------------------------- */

function validacionNoVacio(texto) {
    let resultado = true;

    if(texto === ""){
        resultado = false;
    }

    return resultado
}

function normalizacionLogin(email, password) {
    const usuario = {
        nombre: email.trim(),
        clave: password.trim()
    }

    return usuario;
}

function fetchApiLogin(url,payload) {

    const configuraciones = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(payload)
    }

    fetch(url, configuraciones)
    .then( respuesta => {
        console.log("las configuraciones son: ");
        console.log(configuraciones);
        console.log(respuesta);
        return respuesta.json()
    })
    .then( data => {
        console.log(data);
        console.log(data.jwt);
        //si llega correctamente un token
        console.log("TOKEN desde login.js: ", data.jwt);
        //console.log(data.jwt);
        if(data.jwt){
            localStorage.setItem('jwt', data.jwt);

            location.href = '/html/bienvenido.html'
        }
    }).catch( error => console.log(error))
}