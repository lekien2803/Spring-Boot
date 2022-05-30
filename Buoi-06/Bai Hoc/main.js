const heading = document.getElementById("heading");
console.log(heading);

const para2 = document.querySelector(".para");
console.log(para2);

const box1 = document.querySelector(".box");


// const para3 = document.querySelector(".para:nth-child(4)");
// console.log(para3);

const para3 = document.querySelector(".para + .para");
console.log(para3);

// const para3 = para2.nextElementSibling;
// console.log(para3);

const ul1 = document.querySelector(".box ul");
console.log(ul1);

const ul2 = document.querySelector("body > ul");
console.log(ul2);

const paras = document.querySelectorAll("p");
console.log(paras);

//su dung for qua nodelist
// for (let i = 0; i < paras.length; i++) {
//     paras[i].style.color = "red";
//     paras[i].style.backgroundColor = "black";
// }

//su dung array
Array.from(paras).map(e => {
    e.style.color = "red";
    e.style.backgroundColor = "black";
})

// Lấy ra nội dung của phần tử
console.log(heading.innerHTML);
console.log(heading.innerText);
console.log(heading.textContent);

console.log(ul1.innerHTML);
console.log(ul1.innerText);
console.log(ul1.textContent);

heading.innerHTML = "xin chào";
heading.innerHTML = "<span>xin Chào chao xìn</span>";

// Tạo phần tử dom
const newPara = document.createElement("p");
newPara.innerText = "New Para";
console.log(newPara);

//chèn vị trí đầu
document.body.prepend(newPara);

//chèn cuối
document.body.appendChild(newPara);

//chèn vào bất cứ vị trí trong thẻ cha
document.body.insertBefore(newPara, para2);
document.body.insertBefore(newPara, box1);

// para3.insertAdjacentElement("afterend",newPara);
// box1.insertAdjacentElement("beforebegin",newPara);
box1.insertAdjacentHTML("beforebegin", "<p>the para moi</p>");

//xóa phần tử
//xóa heading
// document.body.removeChild(heading);
// heading.parentElement.removeChild(heading);

//thay thế
//ví dụ: thay thế para1 bằng thẻ a
const link = document.createElement("a");
link.innerText = "link google";
link.href = "https://google.com";
para2.parentElement.replaceChild(link, para2);


//copy
const boxCopy1 = box1.cloneNode(true);
const boxCopy2 = box1.cloneNode(false);

console.log(boxCopy1);
console.log(boxCopy2);

document.body.appendChild(boxCopy1);


//ClassList
console.log(box1.classList);

//thêm class
box1.classList.add("textbig","bold");
box1.classList.remove("bold");

console.log(box1.classList.contains("textbig"));
console.log(box1.classList.toggle("bold"));


//lặp đi lặp lại 1 cv sau 1 khoảng tgian nhất định
setInterval(function(){
    box1.classList.toggle("textbig")
}, 1500)