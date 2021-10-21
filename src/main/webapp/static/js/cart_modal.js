const basketBttn = document.getElementById("basket");

basketBttn.addEventListener("click", getDataForModal)

function getDataForModal(){
    fetch("/basket?id=1")
        .then(response => response.json())
        .then(data => displayModal(data))
}

function displayModal(data){
    const modal = document.querySelector(".modal-body");
    if (data[0] == null){
        modal.innerHTML = "Cart is empty" }

    modal.innerHTML = "";
    let innerHtmlContent = "";
    for (const modalElement of data) {

        const price = parseFloat(modalElement[3])*parseInt(modalElement[4])
        const separator = "  ,   "
        innerHtmlContent = "⚫ " + modalElement[1] +separator + "QTY : " + modalElement[4] +
            separator + "Total price : " + price.toString() + " USD"
        modal.innerHTML += innerHtmlContent;
        modal.innerHTML += "<br>";
    }

}