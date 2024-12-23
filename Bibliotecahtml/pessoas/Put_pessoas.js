async function getById(id) {
    const response = await fetch(`http://localhost:8080/pessoas/` + id, {
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
    let pessoas = await getById(id);
    console.log(document.getElementById("nomeInput").children)
    document.getElementById("pessoa-id").value = pessoas.id
    document.getElementById("nomeInput").placeholder = pessoas.nome
    document.getElementById("emailInput").placeholder = pessoas.email
    document.getElementById("telefoneInput").placeholder = pessoas.telefone
   
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        
        const nome = document.getElementById("nomeInput").value;
        const email = document.getElementById("emailInput").value;
        const telefone = document.getElementById("telefoneInput").value;
    

        const pessoa = {
            id : id,
            nome: nome,
            email : email,
            telefone : telefone
        };

        try {
            const response = await fetch(`http://localhost:8080/pessoas`, {
                method: "PUT",
                headers: {
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type": "application/json",
                    "Origin": "http://localhost:5500"
                },
                body: JSON.stringify(pessoa),
            });

            if (response.ok) {
                console.log("Pessoa alterada com sucesso!");
                showConfirmation()
                        
            } else {
                const errorData = await response.json();
                showError();
            }
        } catch (error) {
            console.error("Erro ao fazer a requisição PUT: ", error);
            alert("Erro ao se conectar com a API.");
            showError();
        }
    });

});
