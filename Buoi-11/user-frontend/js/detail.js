
// lay id tren url
let param = new URLSearchParams(window.location.search);
let id = param.get("id");
console.log(id);
let imgs = [];

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
const imageContainerEl = document.querySelector(".image-container");
const imageItemEl = document.querySelector(".image-item");
const listImg = document.getElementById("img-list");
const btnModalImage = document.getElementById("btn-modal-image");

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
        // avatarPreEl.src = res.data.avatar;
        // console.log(avatarPreEl);

    } catch (error) {
        console.log(error);
    }
}



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
        let res = await axios.get("http://localhost:8080/api/v1/users/" + id + "/files");
        imgs = res.data;
        // B2: hiển thị len giao diện
        renderImage(imgs);


    } catch (error) {
        console.log(error);
    }
}

const renderImage = arr => {
    imageContainerEl.innerHTML = "";
    let html = "";
    for (let index = 0; index < arr.length; index++) {
        const imagePath = arr[index];
        html += `<div class="image-item" onclick="clickImage(this)" dataAttr="${imagePath}">
        <img src="http://localhost:8080/${imagePath}" alt="ảnh">
    </div>`
    }

    imageContainerEl.innerHTML = html;
}


btnModalImage.addEventListener("click", getImage(id));
const btnChooseImage = document.getElementById("btn-chose-image");
const btnDeleteImage = document.getElementById("btn-delete-image");
let imageUrl = "";

function clickImage(img) {
    let selectedImg = document.querySelector(".border-danger");
    if (selectedImg != null) {
        selectedImg.classList.remove("border-danger", "selected");
    }
    if (img.classList.length > 1) {
        img.classList.remove("border-danger", "selected");
    } else {
        img.classList.add("border-danger", "selected");
    }
    console.log(img.firstChild.nextSibling.src);
    imageUrl = img.firstChild.nextSibling.src;
    chooseImage();
    if (imageUrl) {
        btnChooseImage.removeAttribute("disabled");
    }
}

// chọn ảnh
function chooseImage() {
    if (imageUrl) {
        btnDeleteImage.removeAttribute("disabled");
    }
    return imageUrl;
}



async function deleteImage() {
    let url = chooseImage();
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
        console.log(url);
        if (isConfirm) {
            await axios.delete(url);
            for (let index = 0; index < imgs.length; index++) {
                if (imgs[index] == url.substring(22, url.length)) {
                    console.log(imgs[index]);
                    imgs.splice(index, 1);
                }
            }
            console.log(imgs);
            renderImage(imgs);
        }
    } catch (error) {
        console.log(error);
    }
}

const avatar = document.getElementById("avatar");

const uploadImage = async (image) => {
    console.log("uploading image");
    const formData = new FormData();
    formData.append("file", image);
    let newImagePath = await axios.post(`
        http://localhost:8080/api/v1/users/${id}/upload-file`,
        formData
    );

    console.log(newImagePath.data);
    imgs.push(newImagePath.data);
    console.log(imgs);
}






const init = async () => {
    await getProvinces();
    await getUser(id);
}

init();
