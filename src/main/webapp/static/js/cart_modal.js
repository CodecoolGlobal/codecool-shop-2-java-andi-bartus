const basketBttn = document.getElementById("basket");

basketBttn.addEventListener("click", getDataForModal)

function getDataForModal() {
    fetch("/basket?id=1")
        .then(response => response.json())
        .then(data => displayModal(data))
}

function displayModal(data) {
    const modal = document.querySelector(".modal-body");
    if (data[0] == null) {
        modal.innerHTML = "Cart is empty";
    } else {
        modal.innerHTML = "";
        let innerHtmlContent = "";
        let totalPrice = 0;
        for (const modalElement of data) {
            const price = parseFloat(modalElement[3]) * parseInt(modalElement[4])
            totalPrice += price
            const separator = "  ,   "
            innerHtmlContent = "⚫ " + modalElement[1] + separator + "QTY : " + modalElement[4] +
                separator + "Price : " + price.toString() + " USD"
            modal.innerHTML += innerHtmlContent;
            modal.innerHTML += "<br>";
        }
        modal.innerHTML += "<br>Total Price: " + totalPrice + " USD"
    }
}