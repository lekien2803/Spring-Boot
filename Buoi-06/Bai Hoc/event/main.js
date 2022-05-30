const btn1 = document.getElementById("btn-1");
const btn2 = document.getElementById("btn-2");
const btn3 = document.getElementById("btn-3");
//su dung html
function sayHello(){
    alert("xin chao cac ban 1");
}

//su dung dom property
btn2.onclick = function() {
    alert("xin chao cac ban 2");
}

//su dung addEventListner
function sayHello3(){
    alert("xin chao cac ban 3");
}
btn3.addEventListener("click", sayHello3);