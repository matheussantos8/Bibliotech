document.addEventListener("DOMContentLoaded", function(){
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const id_livro = document.getElementById("livroInput").value;
        const id_pessoa = document.getElementById("pessoaInput").value;
        const data_reserva = document.getElementById("dataReservaInput").value;
        const data_validade = document.getElementById("dataValidadeInput").value;


        const reserva = {
            data_reserva : data_reserva,
            data_validade : data_validade,
            id_livro : id_livro,
            id_pessoa : id_pessoa
        };

        try {
            const response = await fetch(`http://localhost:8080/reservas`, {
                method: "POST",
                headers:{
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type" : "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify(reserva),
                mode : "cors",
            })

            const contentType = response.headers.get("content-type");
            let responseData = null;

            if (contentType && contentType.includes("application/json")) {
                responseData = await response.json();
            }  

            if(response.ok){
                console.log("Reserva cadastrada com sucesso:", responseData);
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