document.addEventListener("DOMContentLoaded", function () {
    const tabela = document.getElementById("tabela-emprestimo");

    tabela.addEventListener("click", async (e) => {
        const target = e.target.closest(".delete-icon");

        if (!target) return; 

        const row = target.closest("tr");
        const id = row.dataset.id; 

        if (!id) {
            console.alert("ID do empréstimo não encontrado.");
            return;
        }

        const confirmar = confirm("Você tem certeza que deseja excluir?");
        if (!confirmar) return;

        try {
            const response = await fetch(`http://localhost:8080/emprestimos/${id}`, {
                method: "DELETE",
            });

            if (response.ok) {
                alert("Empréstimo excluído com sucesso.");
                row.remove();
            } else {
                const contentType = response.headers.get("content-type");
                if (contentType && contentType.includes("application/json")) {
                    const errorData = await response.json();
                    alert(`Erro ao excluir: ${errorData.message || response.statusText}`);
                } else {
                    alert(`Erro ao excluir: ${response.statusText}`);
                }
            }
        } catch (error) {
            console.error("Erro ao fazer a requisição DELETE:", error);
            showError();
        }
    });
});
