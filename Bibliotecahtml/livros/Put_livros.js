
async function getById(id) {
    const response = await fetch(`http://localhost:8080/livros/` + id, {
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
    let livros = await getById(id);
    console.log(document.getElementById("tituloInput").children)

    document.getElementById("titulo-id").value = livros.id
    document.getElementById("tituloInput").placeholder = livros.titulo
    document.getElementById("isbnInput").placeholder = livros.isbn


    const form = document.getElementById("formulario");
    const mensagem = document.getElementById("mensagem");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        
        const titulo = document.getElementById("tituloInput").value;
        const isbn = document.getElementById("isbnInput").value;
        const id_autor = document.getElementById("autorInput").value;
        const id_genero = document.getElementById("generoInput").value;
        const anoCompleto = document.getElementById("anoInput").value;
        const foto = document.getElementById("fotoBase64").value;

        const ano_publicacao = anoCompleto ? anoCompleto.split("-")[0] : null;

        const livro = {
            id : id,
            titulo: titulo,
            isbn: isbn,
            ano_publicacao: ano_publicacao,
            id_autor: id_autor,
            id_genero: id_genero,
            foto: foto,
        };


        try {
            const response = await fetch(`http://localhost:8080/livros`, {
                method: "PUT",
                headers: {
                    /*"ngrok-skip-browser-warning": "true",*/
                    "Content-Type": "application/json",
                    "Origin": "http://localhost:8080 :5500"
                },
                body: JSON.stringify(livro),
            });

            if (response.ok) {
                console.log("Livro cadastrado com sucesso:");
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
