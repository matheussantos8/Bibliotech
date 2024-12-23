
document.addEventListener("DOMContentLoaded", function(){
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const nome = document.getElementById("autorInput").value;
        
        const autores = {
            nome : nome,
        };

        try {
            const response = await fetch(`http://localhost:8080/autores`, {
                method: "POST",
                headers:{
                    /*"ngrok-skip-browser-warning" : "true",*/
                    "Content-Type" : "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify(autores),
                mode : "cors",
            })

            const responseData = await response.json();  

            console.log(responseData);  

            if(response.ok){
                console.log("Pessoa cadastrada com sucesso:", responseData);
                showConfirmation();
                form.reset()
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