
// lay id tren url
let param = new URLSearchParams(window.location.search);
let id = param.get("id");
console.log(id);

const addressEl = document.getElementById("address");

// Lay thong tin user
const getUser = async (id) => {
    try {
        //b1 : goi api
        let res = await aixos.get(`http://localhost:8080/api/v1/users/${id}`);
        //b2 hien thi tren giao dien
        addressEl.value = res.data.address;
    } catch (error) {
        console.log(error);
    }
}

const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        console.log(res);

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

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