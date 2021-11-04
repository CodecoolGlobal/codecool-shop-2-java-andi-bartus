function addEventListeners() {
    let addToCartBttns = document.querySelectorAll(".change-quantity-button_plus");

    let removeFromCartBttns = document.querySelectorAll(".change-quantity-button_minus")

    for (let button of addToCartBttns) {
        button.addEventListener("click", getButtonData);
    }

    for (let button of removeFromCartBttns) {
        button.addEventListener("click", sendRemoveRequest);
    }
}
addEventListeners()

function sendRemoveRequest(e) {
    const bttnData = e.currentTarget.dataset
    let id = bttnData.id
    let number = document.getElementById(`${id}`).innerHTML
    if (parseInt(number) === 1){
        let container = document.getElementById(`card${id}`)
        container.parentNode.parentNode.removeChild(container.parentNode)
        console.log(container.parentElement)
    }
    manageData(id, "/removeFromBasket", -1)
}

function getButtonData(e){
    const bttnData = e.currentTarget.dataset
    let id = bttnData.id
    manageData(id, "/addToBasket", 1);
}

function manageData(id, url, value){
    fetch(url + `?id=${id}`)
        .then(response => response.json())
        .then(data => changeBasketBttn(data, id, value))
}

function changeBasketBttn(data, id, value){
    console.log(data)
    const number = document.getElementById(`${id}`)
    if(number !== null){
        number.innerHTML = (parseInt(number.innerHTML) + value).toString()
    }

    const totalPrice = document.querySelector(".totalprice")
    console.log(totalPrice)
    let totalPriceValue = 0;
    for (const product of data) {
        const price = parseFloat(product[3]) * parseInt(product[4])
        totalPriceValue += price
    }
    totalPrice.innerHTML = "Total price: " + totalPriceValue + " USD"
}

