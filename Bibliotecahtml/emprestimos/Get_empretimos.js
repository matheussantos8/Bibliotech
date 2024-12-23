async function get() {
    const response = await fetch(`http://localhost:8080/emprestimos`, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning" : "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

async function getById(id) {
    const response = await fetch(`http://localhost:8080/emprestimos`+ id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning" : "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()
}

document.addEventListener("DOMContentLoaded", async function(){
    let emprestimos = await get()
    console.log(emprestimos)
    for (emprestimo of emprestimos){
        addNewRow(emprestimo)
    }
})
function addNewRow(emprestimo){
    const tabela = document.getElementById("tabela-emprestimo")
    const tr = document.createElement("tr")

    tr.setAttribute("data-id", emprestimo.id);


    tr.innerHTML = 
    '<td class="linha" style="text-align: center; ">' +

       ` <a href="Put_emprestimos.html?id=${emprestimo.id}"><i class="fa-solid fa-edit" style=" font-size: x-large; color: #1f2f3a;"></i></a>`+
          ' <i class="fa-solid fa-trash delete-icon" style="font-size: x-large; margin-left: 10px; color: #e70d0d"></i>'+
   ' </td>'+
    `<td>${emprestimo.Livro}</td>` +
    `<td>${emprestimo.Pessoa}</td>` +
    `<td>${formatDate(emprestimo.data_emprestimo)}</td>` +
    `<td>${formatDate(emprestimo.data_devolucao)}</td>` +
    `<td><i class="fa-solid fa-arrow-rotate-right" id="rotate" onclick="confirmDevolucao(${emprestimo.id}, '${emprestimo.Livro}', '${emprestimo.Pessoa}', '${formatDate(emprestimo.data_devolucao)}')" aria-label="Devolver Empréstimo"></i></td>`

    tabela.appendChild(tr)
}

function formatDate(dateString) {
    if (!dateString) {
        return '';
    }
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year}`;
}

async function confirmDevolucao(id, livro, pessoa, dataDevolucao) {
    if (!dataDevolucao || dataDevolucao === null || dataDevolucao === "") {
        const confirmar = confirm(`Deseja devolver o livro "${livro}" emprestado a "${pessoa}"?`);
        if (confirmar) {
            alert("Livro devolvido!");
            await devolverEmprestimo(id);
            window.location.reload(true);
        }
    } else {
        alert("O empréstimo já foi devolvido.");
    }
        
}
     


async function devolverEmprestimo(id) {
    try {
        const response = await fetch(`http://localhost:8080/emprestimos/${id}/devolver`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            get();
        } else {
            throw new Error('Erro ao registrar a devolução.');
        }
    } catch (error) {
        console.error(error)
    }
}



