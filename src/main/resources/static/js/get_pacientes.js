window.onload = function(){

    const url = '/odontologos/listar';
    const ul = document.querySelector("ul")
    const config = {
        method: 'GET'
    }

    console.log(ul);

    fetch (url, config)
    .then(function(response){
        console.log(response.body)
        return response.json();
    })
    .then(function(data){
        console.log(data)
    })
    .catch(function(e){
        return e
    })

}