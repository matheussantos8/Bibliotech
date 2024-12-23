document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formulario");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const titulo = document.getElementById("titulo").value;
        const isbn = document.getElementById("isbn").value;
        const id_autor = document.getElementById("autor").value;
        const id_genero = document.getElementById("genero").value;
        const anoCompleto = document.getElementById("ano").value;
        const foto = document.getElementById("fotoBase64").value;

        const ano_publicacao = anoCompleto ? anoCompleto.split("-")[0] : null;

        const livro = {
            titulo: titulo,
            isbn: isbn,
            ano_publicacao: ano_publicacao,
            id_autor: id_autor,
            id_genero: id_genero,
            foto: foto,
        };

        try {
            const response = await fetch(`http://localhost:8080/livros`, {
                method: "POST",
                headers: {
                    /*"ngrok-skip-browser-warning" : "true",*/
                    "Content-Type": "application/json",
                    Accept: "application/json",
                },
                body: JSON.stringify(livro),
                mode: "cors",
            });

            const contentType = response.headers.get("content-type");
            let responseData = null;

            if (contentType && contentType.includes("application/json")) {
                responseData = await response.json();
            }

            if (response.ok) {
                console.log("Livro cadastrado com sucesso:", responseData);
                showConfirmation();
                form.reset();
            } else {
                console.error("Erro ao cadastrar o livro:", responseData);
                showError();
            }
        } catch (error) {
            console.error("Erro ao fazer a requisição POST:", error);
            showError();
        }
    });
});
