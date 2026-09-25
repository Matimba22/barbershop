const modal = document.getElementById("welcomeModal");
const closeModal = document.getElementById("closeModal");

window.addEventListener("load", function () {
    modal.style.display = "flex";
});

closeModal.addEventListener("click", function () {
    modal.style.display = "none";
});

window.addEventListener("click", function (event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});