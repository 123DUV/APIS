const tbody = document.querySelector('.tbody');
const form = document.getElementById('formInsertar');
const act = document.getElementById('formAct');


consumoAPI();
function consumoAPI() {
    let endPoint = 'http://codetesthub.com/API/Obtener.php';
    //consumo AJAX es un procedimiento Asincrono javaScript and xml
    //Hay q especificar el metodo pq el fetch siempre ejecuta el metodo get
    //el json de la api esta en respuesta, y el procesado esta en data
    //el resultado de respuesta se asigna a data
    fetch(endPoint)
        .then(respuesta => respuesta.json())
        .then(data => {
            console.log(data);
            tbody.innerHTML ='';
            for (let i = 0; i < data.length; i++) {


                
                
                tbody.innerHTML += `
                <tr>
                <td>${i}</td>
                <td>${data[i].cedula}</td>
                <td>${data[i].nombres}</td>
                <td>${data[i].apellidos}</td>
                <td>${data[i].telefono}</td>
                <td>${data[i].direccion}</td>
                <td>${data[i].email}</td>
                <td>
                <button onclick="eliminarPersona(${data[i].cedula})"> Elim... </button>
                </td>
                <td>
                <button onclick="actualizar('${data[i].cedula}', '${data[i].nombres}', '${data[i].apellidos}', '${data[i].telefono}', '${data[i].direccion}', '${data[i].email}')">Act...</button>
                </td>
                </tr>
                `

                // const numero = document.createElement('td');
                // numero.innerHTML = i;
                // fila.appendChild(numero);
                // const cedula = document.createElement('td');
                // cedula.innerHTML = data[i].cedula;
                // fila.appendChild(cedula);

                // const nombres = document.createElement('td');
                // nombres.innerHTML = data[i].nombres;
                // fila.appendChild(nombres);
                // const apellidos = document.createElement('td');
                // apellidos.innerHTML = data[i].apellidos;
                // fila.appendChild(apellidos);
                // const telefono = document.createElement('td');
                // telefono.innerHTML = data[i].telefono;
                // fila.appendChild(telefono);
                // const direccion = document.createElement('td');
                // direccion.innerHTML = data[i].direccion;
                // fila.appendChild(direccion);

                // const email = document.createElement('td');
                // email.innerHTML = data[i].email;
                // fila.appendChild(email);

                // tbody.appendChild(fila);

            }


        })
        .catch(e => {
            console.log('Error', e);
        })
}

form.addEventListener('submit', function (event) {
    event.preventDefault();//detiene el envio del formulario
    insertar();
});

function insertar() {

    let datos = new FormData(form);//funciona solo si los names coinciden con los datos de la api, si no hay formulario puedo llenarlo con el .append
    let configuracion = {
        method: 'POST',
        headers: { 'Accept': 'application/json' },
        body: datos
    };
    fetch('http://codetesthub.com/API/Insertar.php', configuracion)
        .then(resp => resp.json())
        .then(data => {
            console.log('la api responde con: ', data);
            
            if (data.status) {
                let myModal = new bootstrap.Modal(document.getElementById('modalInsertar'));
                myModal.hide();
                form.reset();
                consumoAPI();
            } else {
                alert('error al insertar');
            }
        });
}

act.addEventListener('submit', function (event) {
    event.preventDefault();//detiene el envio del formulario
    actualizar();
});
function actualizar(cedula, nombres, apellidos, telefono, direccion, email){
    let form = new FormData();
    
    form.append('cedula', cedula);
    form.append('nombres', nombres);
    form.append('apellidos', apellidos);
    form.append('telefono', telefono);
    form.append('direccion', direccion);
    form.append('email', email);
    let configuracion = {
        method: 'POST',
        headers: { 'Accept': 'application/json' },
        body: form
    };
    fetch('http://codetesthub.com/API/Actualizar.php', configuracion)
        .then(resp => resp.json())
        .then(data => {
           
            console.log('la api muestra',data);
            let myModal = new bootstrap.Modal(document.getElementById('modalAct'));
            myModal.show();
            consumoAPI();
        });
}

function eliminarPersona(cedula) {
    let form = new FormData();
    form.append('cedula', cedula);
    
    
    
    let configuracion = {
        method: 'POST',
        headers: { 'Accept': 'application/json' },
        body: form
    };

    fetch('http://codetesthub.com/API/Eliminar.php', configuracion)
        .then(resp => resp.json())
        .then(data => {
            console.log('la api muestra',data);

        });


}

