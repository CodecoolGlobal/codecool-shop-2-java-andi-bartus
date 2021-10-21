let addToCartBttns2 = document.querySelectorAll(".change-quantity-button_minus")

for(let bttn of addToCartBttns2){
    bttn.addEventListener("click", getData)
}

function getData(e){

    const bttnData = e.currentTarget.dataset
    let id = bttnData.id

    sendData(id, e)
}

function sendData(id){
    fetch(`/removeFromBasket?id=${id}`)
        .then(response => response.json())
        .then(data => changeBasketBttn2(data, id))
}

function changeBasketBttn2(data, id){
    //console.log(data)
    const number2 = document.getElementById(`${id}`)
    number2.innerHTML = (parseInt(number2.innerHTML) - 1).toString()


}


