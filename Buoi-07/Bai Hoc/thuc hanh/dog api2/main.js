const btn = document.querySelector("#btn");
const imgEl = document.querySelector("#image img");
const selectEl = document.querySelector("#breed-list");

// // API lấy giống loài chính
// async function getBreedList(){
//     try {
//         let res = await axios.get("https://dog.ceo/api/breeds/list/all");
//         console.log(res.data.message);
//         //hiển thị danh sách
//         displayBreedList(res.data.message);
//     } catch (error) {
//         console.log(error);
//     }
// }

// function displayBreedList(obj){
//     let keys = Object.keys(obj);

//     let html = "";
//     for (let i = 0; i < keys.length; i++) {
//         html += `<option value="${keys[i]}">${keys[i]}</option>`
//     }
//     selectEl.innerHTML = html;



// }

// async function getBreedList() {
//     const res = await fetch("https://dog.ceo/api/breeds/list/all");
//     const data = await res.json();
//     displayBreedList(data.message);
// }

// function displayBreedList(obj) {
//     let keys = Object.keys(obj);

//     let html = "";
//     for (let i = 0; i < keys.length; i++) {
//         html += `<option value="${keys[i]}">${keys[i]}</option>`
//     }
//     selectEl.innerHTML = html;

//     btn.addEventListener("click", async function () {
//         try {
//             let ress = await fetch("https://dog.ceo/api/breed/hound/images");

//             console.log(ress.json().message);
//         } catch (error) {
//             console.log(error);
//         }
//     })
// }

// btn.addEventListener("click", async function () {
//     try {
//         let res = await fetch("https://dog.ceo/api/breed/hound/images");
//         let data = await res.json();
//         console.log(data.message);
//         imgEl.src = data.message;
//     } catch (error) {
//         console.log(error);
//     }
// }
// )

async function exp() {

    let res = await fetch("https://dog.ceo/api/breed/hound/images");
    let data = await res.json();
    let keys = data.message;
     
    console.log(keys);

}

// getBreedList();
exp();