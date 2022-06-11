const sctEl1 = document.querySelector("#province");
const sctEl2 = document.querySelector("#district");
const sctEl3 = document.querySelector("#commune");

async function getAllProvinces() {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res.data);
        displayProvinces(res.data);
    } catch (error) {
        console.log(error);
    }
}


function displayProvinces(array) {
    let html = "";

    for (let i = 0; i < array.length; i++) {
        html += `<option value="${array[i].code}">${array[i].name}</option>`

    }
    sctEl1.innerHTML = html;
}

sctEl1.addEventListener("click", async function () {
    try {

    } catch (error) {
        console.log(error);
    }
})

window.onload = getAllProvinces();