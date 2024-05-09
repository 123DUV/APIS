let contentBotones = document.getElementById('contentBotones');
let nombrePokemon = document.getElementById('nombrePokemon');
let imagenPokemon = document.getElementById('imagenPokemon');

function consumoAPI() {
    let endPoint = 'https://pokeapi.co/api/v2/pokemon';
    //consumo AJAX es un procedimiento Asincrono javaScript and xml
    //Hay q especificar el metodo pq el fetch siempre ejecuta el metodo get
    //el json de la api esta en respuesta, y el procesado esta en data
    //el resultado de respuesta se asigna a data
    fetch(endPoint)
        .then(respuesta => respuesta.json())
        .then(data => {
            console.log('La API responde con: \n');
            for (var i = 0; i < data.results.length; i++) {
                let temp = `<button class="btn btn-info col-12" onclick="cargarDetalle('${data.results[i].url}')">
                <span class="text-uppercase">
                ${data.results[i].name}
                </span>
                </button>`;

                contentBotones.innerHTML += temp;

            }
        })
}

function cargarDetalle(endPoint) {
    fetch(endPoint)
        .then(respuesta => respuesta.json())
        .then(data => {
            
            
            nombrePokemon.innerText = data.name;
            imagenPokemon.src = data.sprites.other.showdown.front_shiny;
        });
}