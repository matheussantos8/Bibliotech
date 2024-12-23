async function get() {
    const response = await fetch( "http://localhost:8080/reservas", {
        method:"GET",
        headers:{
            
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch( "http://localhost:8080/reservas"+ id, {
        method:"GET",
        headers:{
            
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let reservas = await get()
    console.log(reservas)
    for (reserva of reservas){
        addNewRow(reserva)
    }
})
function addNewRow(reserva){
    const tabela = document.getElementById("tabela-reserva")
    const tr = document.createElement("tr")
    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="editar-generos.html?id=${reserva.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: black;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px;"></i>'+
   ' </td>'+
    `<td>${reserva.livro}</td>` +
    `<td>${reserva.pessoa}</td>` +
    `<td>${reserva.data_reserva}</td>` +
    `<td>${reserva.data_validade}</td>` 
    


    tabela.appendChild(tr)

}


