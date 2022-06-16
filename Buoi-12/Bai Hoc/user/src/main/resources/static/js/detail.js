
// lay id tren url
const API_URL = "/api/v1/users/";
let id = user.id;

const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");

const btnSave = document.getElementById("btn-save");
const btnBack = document.querySelector(".btn-back");
// Đổi mật khẩu
const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");




//Lấy thông tin user
const getUser = async (id) => { 
    try {
        // B1: goi API
        let res = await axios.get(`${API_URL}` + id);
        // console.log(res.data);
        // B2: hiển thị len giao diện
        nameEl.value = res.data.name;
        emailEl.value = res.data.email;
        phoneEl.value = res.data.phone;
        addressEl.value = res.data.address;
        

    } catch (error) {
        console.log(error);
    }
}
getUser(id);

const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}
getProvinces();


// Thay đổi mật khẩu
btnChangePassword.addEventListener("click", async function () {
    try {
        // 1. Lấy 2 giá trị oldPassword, newPassword
        let oldPassword = oldPasswordEl.value;
        let newPassword = newPasswordEl.value;
        console.log(oldPassword);                           
        console.log(newPassword);                           
        // 2. Kiểm tra giá trị có rỗng hay không
        if (oldPassword == "" || newPassword == "") {
            alert("không được để trống");
        }                    
        // 3. Gửi API để cập nhật lại password
        let res = axios.put(`${API_URL}` + id + "/update-password", {
            "oldPassword" : oldPassword,
            "newPassword" : newPassword
        });    
        console.log(res);                       
        // alert("Đổi mật khẩu thành công");
        // 4. Đóng modal và clear lại giá trị trong các ô input 
        // oldPasswordEl.value = "";
        // newPasswordEl.value = "";
        window.location.href = "/detail/" + id;
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        // alert(error.response.data.message);
        console.log(error);
    }
});


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