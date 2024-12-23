async function get() {
    const response = await fetch(`http://localhost:8080/livros`, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch(`http://localhost:8080/livros`+ id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let livros = await get()
    console.log(livros)
    for (livro of livros){
        addNewRow(livro)
    }
})
function addNewRow(livro){
    const tabela = document.getElementById("tabela-livro")
    const tr = document.createElement("tr")

    tr.setAttribute("data-id", livro.id);

    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="Put_livros.html?id=${livro.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: #1f2f3a;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px; color: #e70d0d"></i>'+
   ' </td>'+
    `<td style="padding-left: 20px;">${livro.titulo}</td>` +
    `<td>${livro.isbn}</td>` +
    `<td>${livro.autor}</td>` +
    `<td>${livro.genero}</td>` +
    `<td>${livro.ano_publicacao}</td>` + 
    `<td class="no-hover">` + 
               ` <span class="status-tag ${livro.status === 'DISPONIVEL' ? 'available' : 'borrowed'}" style="cursor: pointer"> ` +
                    `${livro.status === 'DISPONIVEL' ? 'DISPONÍVEL' : 'EMPRESTADO'} ` +
               ` </span>` +
           ` </td> `

    tabela.appendChild(tr)

}


