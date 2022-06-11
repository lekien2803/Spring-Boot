
// lay id tren url
let param = new URLSearchParams(window.location.search);
let id = param.get("id");
console.log(id);

const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");



//Lấy thông tin user
const getUser = async (id) => { 
    try {
        // B1: goi API
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id)
        // B2: hiển thị len giao diện
        nameEl.value = res.data.name;
        emailEl.value = res.data.email;
        phoneEl.value = res.data.phone;
        addressEl.value = res.data.address;
        

    } catch (error) {
        console.log(error);
    }
}

const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

// lay image
const getImage = async (id) => { 
    try {
        // B1: goi API
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id +"/files")
        console.log(res.data);
        // B2: hiển thị len giao diện
     
        

    } catch (error) {
        console.log(error);
    }
}
getImage(id);

getProvinces();
getUser(id);

const renderProvince = arr => {
    let html = "";
    arr.forEach(provinces => {
        html += `<option value="${provinces.name}">${provinces.name}</option>`;
        addressEl.innerHTML = html;
    });
}

const init = async () =>{
    await getProvinces();
    await getUser(id);
}

init();