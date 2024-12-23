document.addEventListener("DOMContentLoaded", function(){
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
                method: "POST",
                headers:{
                    /*"ngrok-skip-browser-warning" : "true",*/
                    "Content-Type" : "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify(emprestimo),
                mode : "cors",
            })

            const contentType = response.headers.get("content-type");
            let responseData = null;

            if (contentType && contentType.includes("application/json")) {
                responseData = await response.json();
            } 
 

            if(response.ok){
                console.log("Empréstimo cadastrado com sucesso:", responseData);
                showConfirmation();
                form.reset();
            }
            else{
                const errorData = await response.json();
                showError();
            }
        }
        catch(error){
            console.error("Erro ao fazer a requisição POST: ", error);
            showError();
        }

    })
})