document.addEventListener("DOMContentLoaded", function () {
    const tabela = document.getElementById("tabela-reserva");

    tabela.addEventListener("click", async (e) => {
        const target = e.target.closest(".delete-icon");

        if (!target) return; 

        const row = target.closest("tr");
        const id = row.dataset.id; 

        if (!id) {
            console.error("ID da reserva não encontrada.");
            return;
        }

        const confirmar = confirm("Você tem certeza que deseja excluir?");
        if (!confirmar) return;

        try {
            const response = await fetch(`http://localhost:8080/reservas/${id}`, {
                method: "DELETE",
            });

            if (response.ok) {
                alert("Reserva excluída com sucesso.");
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
            alert("Erro ao se conectar com a API.");
        }
    });
});
