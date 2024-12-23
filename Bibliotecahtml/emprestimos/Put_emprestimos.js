async function getById(id) {
    const response = await fetch(`http://localhost:8080/emprestimos/` + id, {
        method:"GET",
        headers:{
            /*"ngrok-skip-browser-warning": "true",*/
            "Origin": "http://localhost:5500"
        }
    })
    return await response.json()

    
}

document.addEventListener("DOMContentLoaded", async function(){
    let params = this.location.search
    const id = params.split("=")[1]
    let emprestimos = await getById(id);
    document.getElementById("emprestimo-id").value = emprestimos.id
   
    
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        
        const id_livro = document.getElementById("livroInput").value;
        const id_pessoa = document.getElementById("pessoaInput").value;
        const data_emprestimo = document.getElementById("dataEmprestimoInput").value;


    
    const emprestimo = {
        id_livro : id_livro,
        id_pessoa : id_pessoa,
        data_emprestimo : data_emprestimo
    };


    try {
        const response = await fetch(`http://localhost:8080/emprestimos`, {
            method: "PUT",
            headers: {
                /*"ngrok-skip-browser-warning": "true",*/
                "Content-Type": "application/json",
                "Origin": "http://localhost:5500"
            },
            body: JSON.stringify(emprestimo),
        });

        if (response.ok) {
           showConfirmation();
                    
        } else {
            const errorData = await response.json();
            showError();
        }
    } catch (error) {
        console.error("Erro ao fazer a requisição PUT: ", error);
        alert("Erro ao se conectar com a API.");
        showError();
    }




})
})
