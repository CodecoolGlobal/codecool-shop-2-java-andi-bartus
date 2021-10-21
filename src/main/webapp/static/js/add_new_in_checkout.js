let addToCartBttns = document.querySelectorAll(".change-quantity-button_plus");
let removeFromCartBttns = document.querySelectorAll(".change-quantity-button_minus")

for(let button of addToCartBttns){
    button.addEventListener("click", getData);
}

for(let button of removeFromCartBttns) {
    button.addEventListener("click", sendRemoveRequest);
}

function sendRemoveRequest(e) {
    const bttnData = e.currentTarget.dataset
    let id = bttnData.id
    let number = document.getElementById(`${id}`).innerHTML
    if (parseInt(number) === 1){
        let container = document.getElementById(`card${id}`)
        container.parentNode.parentNode.removeChild(container.parentNode)
        console.log(container.parentElement)
    }
    sendData(id, "/removeFromBasket", -1)
}

function getData(e){

    const bttnData = e.currentTarget.dataset
    let id = bttnData.id

    sendData(id, "/addToBasket", 1);
}

function sendData(id, where, value){
    fetch(where + `?id=${id}`)
        .then(response => response.json())
        .then(data => changeBasketBttn(data, id, value))
}

function changeBasketBttn(data, id, value){
    console.log(data)
    const number = document.getElementById(`${id}`)
    if(number !== null){
        number.innerHTML = (parseInt(number.innerHTML) + value).toString()
    }
    console.log(data)
}