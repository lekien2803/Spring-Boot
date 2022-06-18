
// lay id tren url
let param = new URLSearchParams(window.location.search);
let id = param.get("id");
console.log(id);

const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const avatarPreEl = document.getElementById("avatar-preview");

const btnSave = document.getElementById("btn-save");

// Đổi mật khẩu
const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");
const modalChangePasswordEl = document.getElementById('modal-change-password');
const modalChangePasswordConfig = new bootstrap.Modal(modalChangePasswordEl, {
    keyboard: false
});
const btnForgotPasswordEl = document.getElementById("btn-forgot-password");


// Ảnh
const imageContainerEl = document.getElementsByClassName(".image-container");

// lấy province     -------------------------------------------------------------------------------------------------
// lấy api province
const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/");
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

// in ra màn hình
const renderProvince = arr => {
    let html = "";
    arr.forEach(provinces => {
        html += `<option value="${provinces.name}">${provinces.name}</option>`;
        addressEl.innerHTML = html;
    });
}


//Lấy thông tin user    -------------------------------------------------------------------------------------------------
const getUser = async (id) => {
    try {
        // B1: goi API
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id)
        // B2: hiển thị len giao diện
        nameEl.value = res.data.name;
        emailEl.value = res.data.email;
        phoneEl.value = res.data.phone;
        addressEl.value = res.data.address;
        avatarPreEl.src = res.data.avatar;

    } catch (error) {
        console.log(error);
    }
}

const init = async () => {
    await getProvinces();
    await getUser(id);
}

init();


// Xử lý mật khẩu -------------------------------------------------------------------------------------------------
//Thay đổi mật khẩu
btnChangePassword.addEventListener("click", async function () {
    try {
        // 1. Lấy 2 giá trị oldPassword, newPassword
        let oldPasswordValue = oldPasswordEl.value;
        let newPasswordValue = newPasswordEl.value;
        // 2. Kiểm tra giá trị có rỗng hay không
        if (oldPasswordValue == "" || newPasswordValue == "") {
            alert("không được để trống");
            return;
        }
        // 3. Gửi API để cập nhật lại password
        await axios.put(`http://localhost:8080/api/v1/users/${id}/update-password`, {
            oldPassword: oldPasswordValue,
            newPassword: newPasswordValue
        });

        alert("Đổi mật khẩu thành công");

        // 4. Đóng modal và clear lại giá trị trong các ô input 
        modalChangePasswordConfig.hide();
        oldPasswordEl.value = "";
        newPasswordEl.value = "";
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        alert(error.response.data.message);
    }
});

btnForgotPasswordEl.addEventListener("click", async function () {
    try {
        let res = await axios.post(`http://localhost:8080/api/v1/users/${id}/forgot-password`);
        alert(`Mật khẩu mới của bạn là ${res.data}`);
    } catch (error) {
        console.log(error);
    }
});


// Xử lý ảnh -------------------------------------------------------------------------------------------------
// lay image
const getImage = async (id) => {
    try {
        // B1: goi API
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id + "/files")
        let data = res.data;
        // B2: hiển thị len giao diện
        renderImage(data);


    } catch (error) {
        console.log(error);
    }
}
getImage(id);

const renderImage = arr => {
    imageContainerEl.innerHTML = "";
    let html = "";
    arr.forEach(image => {
        html += `<div class="image-item">
                    <img src="http://localhost:8080/${image}" alt="ảnh">
                </div>`
        console.log(image);
    });

    imageContainerEl.innerHTML = html;
}



