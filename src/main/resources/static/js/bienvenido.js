// chequear que exista un usuario loggeado
const jwt = localStorage.getItem('jwt');
// si no existe un token, lo sacamos de la vista
if (!jwt) {
  location.replace('/');
}


const apiBaseUrl = 'http://localhost:8080';

window.addEventListener('load', function () {
  // inicializo las librerias
  AOS.init();
  dayjs().format()
  /* -------------------------------------------------------------------------- */
  /*                             logica de la vista                             */
  /* -------------------------------------------------------------------------- */
  const jwt = localStorage.getItem('jwt')

  const nodoNombreUsuario = document.querySelector('.user-info p');
  const btnCerrar = document.querySelector('#closeApp');

  btnCerrar.addEventListener('click', function () {

    Swal.fire({
      title: '¿Desea cerrar sesión?',
      text: "Para ingresar nuevamente tendrá que introducir sus credenciales.",
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '¡Si!',
      cancelButtonText: '¡Mejor no!'
    }).then((result) => {
      if (result.isConfirmed) {
        //  cerrar sesion del usuario
        localStorage.clear();
        location.href = '/login.html';
      }
    })
    // cerra sesion
    // if (confirm("¿Desea cerrar sesión?")) {
    //   // limpiamos el storage y redireccionamos
    //   localStorage.clear();
    //   location.replace('/');
    // }
  })

  /* --------------- funciones que se disparan al iniciar la app -------------- */
  obtenerNombreUsuario(`${apiBaseUrl}/user`, jwt);

  /* ---------------------- GET: obtener info del usuario --------------------- */
    function obtenerNombreUsuario(url, token) {

      const configuraciones = {
        method: 'GET',
        headers: {
          authorization: 'Bearer ' + token
        }
      }

      fetch(url, configuraciones)
        .then(respuesta => respuesta.json())
        .then(data => {
          console.log(data);
          nodoNombreUsuario.innerText = data.name;
        })
    }


 });