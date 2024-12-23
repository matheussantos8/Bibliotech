async function get() {
    const response = await fetch(`http://localhost:8080/autores`, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning" : "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch(`http://localhost:8080/autores`+ id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning" : "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let autores = await get()
    console.log(autores)
    for (autor of autores){
        addNewRow(autor)
    }
})
function addNewRow(autor){
    const tabela = document.getElementById("tabela-autor")
    const tr = document.createElement("tr")

    tr.setAttribute("data-id", autor.id);

    
    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="Put_autores.html?id=${autor.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: #1f2f3a;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px; color: #e70d0d"></i>'+
   ' </td>'+
    `<td>${autor.nome}</td>`
    tabela.appendChild(tr)

}


