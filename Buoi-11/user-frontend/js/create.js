//API : https://provinces.open-api.vn/api/p/
// truy cap
const addressEl = document.getElementById("address");
const btnSave = document.getElementById("btn-save");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passEl = document.getElementById("password");

// tao user
btnSave.addEventListener("click", async function () {
    try {
        // Lay thong tin o input

        // Goi API
        let res = await axios.post("http://localhost:8080/api/v1/users", {
            address : addressEl.value,
            name : nameEl.value,
            email : emailEl.value,
            phone : phoneEl.value,
            password : passEl.value
        } );

        // Neu thanh cong tra ve trang index
        if(res.data){
            window.location.href = "/user-frontend";
        }
    } catch (error) {
        console.log(error);
    }

})

const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

getProvinces();

const renderProvince = arr => {
    let html = "";
    arr.forEach(provinces => {
        html += `<option value="${provinces.name}">${provinces.name}</option>`;
        addressEl.innerHTML = html;
    });
}