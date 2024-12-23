function showConfirmation() {
  const modal = document.getElementById("confirmation-modal");
  modal.classList.add("show");
  setTimeout(() => modal.classList.remove("show"), 2000);
}

function showError() {
  const modal = document.getElementById("error-modal")
  modal.classList.add("show");
  setTimeout(() => 
    modal.classList.remove("show"), 2000)
  
}


