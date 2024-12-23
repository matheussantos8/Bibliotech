document.addEventListener("DOMContentLoaded", function(){
    const seta = document.getElementById("closeMenu")
    const sidebar = document.getElementById("sidebar")
    const menu = document.getElementById("openMenu")


    seta.addEventListener("click", function(){
        sidebar.classList.add("closed")
        sidebar.classList.remove("opened")
    })

    menu.addEventListener("click", function(){
        sidebar.classList.add("opened")
        sidebar.classList.remove("closed")
    })
})