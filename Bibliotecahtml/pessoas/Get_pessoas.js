async function get() {
    const response = await fetch(`http://localhost:8080/pessoas`, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch( `http://localhost:8080/pessoas`+ id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let pessoas = await get()
    console.log(pessoas)
    for (pessoa of pessoas){
        addNewRow(pessoa)
    }
})
function addNewRow(pessoa){
    const tabela = document.getElementById("tabela-pessoa")
    const tr = document.createElement("tr")

    tr.setAttribute("data-id", pessoa.id);


    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="Put_pessoas.html?id=${pessoa.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: #1f2f3a;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px; color: #e70d0d"></i>'+
   ' </td>'+
    `<td>${pessoa.nome}</td>` +
    `<td>${pessoa.email}</td>` +
    `<td>${pessoa.telefone}</td>` 
   


    tabela.appendChild(tr)

}


