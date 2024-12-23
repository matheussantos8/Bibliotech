
async function getById(id) {
    const response = await fetch(`http://localhost:8080/reservas/` + id, {
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
    let reservas = await getById(id);
    document.getElementById("reserva-id").value = reservas.id
   
     
    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const id_livro = document.getElementById("livroInput").value;
        const id_pessoa = document.getElementById("pessoaInput").value;
        const data_reserva = document.getElementById("dataReservaInput").value;
        const data_validade = document.getElementById("dataValidadeInput").value;


        const reserva = {
            id : id,
            data_reserva : data_reserva,
            data_validade : data_validade,
            id_livro : id_livro,
            id_pessoa : id_pessoa
        };

        try {
            const response = await fetch(`http://localhost:8080/reservas`, {
                method: "PUT",
                headers: {
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type": "application/json",
                    "Origin": "http://localhost:5500"
                },
                body: JSON.stringify(reserva),
            });

            if (response.ok) {
                console.log("Reserva alterada com sucesso!");
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
