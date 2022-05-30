const text = document.getElementById("text");
const btn1 = document.getElementById("btn-1");
const btn2 = document.getElementById("btn-2");
const btn3 = document.getElementById("btn-3");

function randomQuote(){
    let quotes = ["qoute1","qoute2","qoute3","qoute4"];
    let randomIndex = Math.floor(Math.random() * quotes.length);
    let quoteRandom = quotes[randomIndex];

    text.innerText = quoteRandom;
}   
btn1.addEventListener("click",randomQuote);


function randomColor(){
    let colors = randomHexColor();
    
}

function randomHexColor(){
    //Tạo mảng bảo gồm 16 kí tự 0 -> 9, a -> f
    let alp = ["a","b","c","d","f",0,1,2,3,4,5,6,7,8,9];
    let a = "";
    // Sử dụng vòng lặp 6 lần, mỗi vòng lặp random ra 1 kí tự (cộng chuỗi)
    for (let i = 0; i < 7; i++) {
        let randomI = Math.floor(Math.random() * alp.length);
        a += alp[randomI];
    }
    //Trả về mã Hex + dấu # ở đầu
    return "#" + a;
}

// btn2.addEventListener("click", randomColor);

