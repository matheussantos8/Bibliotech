
async function getById(id) {
    const response = await fetch(`http://localhost:8080/generos/` + id, {
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
    let generos = await getById(id);
    console.log(document.getElementById("generoInput").children)
    document.getElementById("genero-id").value = generos.id
    document.getElementById("generoInput").placeholder = generos.nome
   
    
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        
        const nomeGenero = document.getElementById("generoInput").value;
        
       
        

        const genero = {
            id : id,
            nome: nomeGenero,
        };


        try {
            const response = await fetch("http://localhost:8080/generos", {
                method: "PUT",
                headers: {
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type": "application/json",
                    "Origin": "http://localhost:5500"
                },
                body: JSON.stringify(genero),
            });

            if (response.ok) {
                console.log("Gênero alterado com sucesso!");
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
    });

});
