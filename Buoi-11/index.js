const URL_API = "http://localhost:8080/api/v1";

// Lưu lại danh sách todos sau khi gọi API
let users = [];
const userListEl = document.querySelector("#user-list");
const search = document.querySelector("#search");
const deleteBtn = document.querySelector("#delete");

function getUsersAPI(term = "") {
  let url = `${URL_API}/users`;
  if (term) {
    url = `${URL_API}/users/search?name=${term}`;
  }
  return axios.get(url);
}

const deleteUserAPI = (id) => {
  return axios.delete(`${URL_API}/users/${id}`);
};

async function getUsers(term = "") {
  try {
    let res = await getUsersAPI(term);
    console.log(res);
    users = res.data;
    renderUser(users);
  } catch (error) {
    console.log(error);
  }
}

getUsers();
function renderUser(arr) {
  userListEl.innerHTML = "";

  if (arr.length == 0) {
    userListEl.innerHTML = "Không có ai cả";
    return;
  }

  let html = "";

  for (let index = 0; index < arr.length; index++) {
    const user = arr[index];
    html += `   <tr>
                    <td>${index + 1}</td>
                     <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.address}</td>
                    <td>
                       <a href="./detail.html?id=${user.id}" class="btn btn-success">Xem chi tiết</a>
                         <button class="btn btn-danger" onclick="deleteUser(${
                           user.id
                         })">Xóa</button>
                     </td>
                     </tr>`;
  }
  userListEl.innerHTML = html;
}

function enterKeyPressed(event) {
  let term = "";
  if (event.keyCode == 13) {
    term = search.value;
    console.log(term);
    getUsers(term);
  }
}

const deleteUser= async(id) =>{
  try {
    // Xác nhận xem người dùng có muốn xóa hay không
    let isConfirm = confirm("Bạn có muốn xóa không?");

    if (isConfirm) {
      await deleteUserAPI(id);
      // Xóa trong mảng todos
      users.forEach((user, index) => {
        if (user.id == id) {
          users.splice(index, 1);
        }
      });

      // Render lại giao diện
      renderUser(users);
    }
  } catch (error) {
    console.log(error);
  }
};

