let users = [];

const userListEl = document.getElementById("users-list");
const searchEl = document.getElementById("search");
const API_URL = "http://localhost:8080/api/v1";

function getUsersAPI(term = "") {
    let url = `${API_URL}/users`;
    if (term) {
        url = `${API_URL}/search?name=${term}`;
    }

    return axios.get(url);
}

async function getUsers(term = "") {
    try {
        // 1. Gọi API lấy danh sách users
        let res = await getUsersAPI();
        // 2. Lưu danh sách users từ API trả về vào biến users (để phục vụ chức năng thêm, xóa, hiển thị danh sách user)
        users = res.data
        console.log(users);
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
            <a href="./detail.html?id=2" class="btn btn-success">Xem chi tiết</a>
            <button class="btn btn-danger">Xóa</button>
        </td>
        </tr>
        `
        userListEl.innerHTML = html;
    }
}
getUsers();

// Lắng nghe sự kiện trong ô input
searchEl.addEventListener("keydown", function (e) {
    // B1 : Kiểm tra nếu người dùng bấm vào phím Enter

    // B2 : Lấy dữ liệu trong ô input

    // B3 : Gọi API tương ứng
    let user = "";
    // B4 : Hiển thị kết quả tương ứng bằng cách gọi renderUser() và truyền kết quả từ API vào để hiển thị
});

const renderUser ()



