async function get() {
    const response = await fetch(`http://localhost:8080/generos`, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch(`http://localhost:8080/generos`+ id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let generos = await get()
    console.log(generos)
    for (genero of generos){
        addNewRow(genero)
    }
})
function addNewRow(genero){
    const tabela = document.getElementById("tabela-genero")
    const tr = document.createElement("tr")

    tr.setAttribute("data-id", genero.id);


    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="Put_generos.html?id=${genero.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: #1f2f3a;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px; color: #e70d0d"></i>'+
   ' </td>'+
    `<td>${genero.nome}</td>`
    tabela.appendChild(tr)

}


