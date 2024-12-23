
async function getById(id) {
    const response = await fetch(`http://localhost:8080/autores/` + id, {
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
    let autores = await getById(id);

    console.log(document.getElementById("autorInput").children)
    document.getElementById("autor-id").value = autores.id
    document.getElementById("autorInput").placeholder = autores.nome
   
    const form = document.getElementById("formulario");
  

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const nomeAutores = document.getElementById("autorInput").value;
        
        const autores = {
            id : id,
            nome: nomeAutores,
        };

        try {
            const response = await fetch(`http://localhost:8080/autores`, {
                method: "PUT",
                headers: {
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type": "application/json",
                    "Origin": "http://localhost:5500"
                },
                body: JSON.stringify(autores),
            });

            if (response.ok) {
                console.log("Autor atualizado com sucesso!");
                showConfirmation();
                        
            } else {
                const errorData = await response.json();
                showError();
            }
        }

        catch (error) {
            console.error("Erro ao fazer a requisição PUT: ", error);
            alert("Erro ao se conectar com a API.");
            showError();
        }
    });

});
