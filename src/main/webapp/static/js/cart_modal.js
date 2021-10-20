
function getDataForModal(){
    fetch("/add_to_cart")
        .then(response => response.json())
        .then(data => displayModal(data))
}

function displayModal(data){
    const modal = document.querySelector(".modal-body")
    modal.innerHTML = ""


}