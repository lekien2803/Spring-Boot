

const userListEl = document.getElementById("users-list");
const searchEl = document.getElementById("search");
const API_URL = "http://localhost:8080/api/v1";


// API function
function getUsersAPI(term = "") {
    let url = "/api/v1/users";
    if (term) {
        url = `/api/v1/users/search?name=${term}`;
    }

    return axios.get(url);
}

const deleteUserAPI = (id) => {
    return axios.delete(`${API_URL}/users/${id}`);
}


//function
async function getUsers(term = "") {
    try {
        // 1. Gọi API lấy danh sách users
        let res = await getUsersAPI(term);
        // 2. Lưu danh sách users từ API trả về vào biến users (để phục vụ chức năng thêm, xóa, hiển thị danh sách user)
        users = res.data
        // 3. Render dữ liệu ra ngoài giao diện
        renderUsers(users);

    } catch (error) {
        console.log(error);
    }
}

//function hien thi users len giao dien
const renderUsers = (arr) => {
    userListEl.innerHTML = "";

    if (arr.length == 0) {
        userListEl.innerHTML = "Không có user nào";
        return;
    }

    let html = "";
    for (let i = 0; i < arr.length; i++) {
        const t = arr[i];
        html += `<tr>
            <td>${i + 1}</td>
            <td>${t.name}</td>
            <td>${t.email}</td>
            <td>${t.phone}</td>
            <td>${t.address}</td>
            <td>
                <a href="./detail/${t.id}" class="btn btn-success">Xem chi tiết</a>
                <button class="btn btn-danger" onclick="deleteUser(${t.id})">Xóa</button>
            </td>
        </tr>
        `
        userListEl.innerHTML = html;
    }
}


// Lắng nghe sự kiện trong ô input
searchEl.addEventListener("keydown", function (event) {
    let term = "";
    // B1 : Kiểm tra nếu người dùng bấm vào phím Enter
    if (event.key == 'Enter') {
        // B2 : Lấy dữ liệu trong ô input
        term = searchEl.value;
        console.log(term);
        // B3 : Gọi API tương ứng
        getUsers(term);
        // B4 : Hiển thị kết quả tương ứng bằng cách gọi renderUser() và truyền kết quả từ API vào để hiển thị
    }
});


const deleteUser = async (id) => {
    try {
        // Xác nhận xem user có đồng ý xóa hay không (sử dụng confirm)
        let isConfirm = confirm("Bạn có muốn xóa không?");
        // Nếu người dùng đồng ý xóa -> Gọi API xóa
        if (isConfirm) {
            await deleteUserAPI(id);
             // Xóa user ứng với id trong mảng users ban đầu
             users.forEach((user, index) => {
                if (user.id == id)  {
                    users.splice(index, 1);
                }
             });
        }
        // Render lại giao diện
        renderUsers(users);
    } catch (error) {
        // Xử lý nếu có lỗi xảy ra
        console.log(error);
    }
};




