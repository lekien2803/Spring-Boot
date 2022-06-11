const btn = document.querySelector(".btn");
const imgEL = document.querySelector(".image img");

btn.addEventListener("click", async function () {

    try {
        // b1: gọi api
        let res = await axios.get("https://dog.ceo/api/breeds/image/random");
        console.log(res);

        // b2: lấy kết quả trả về từ API -> hiển thị
        imgEL.src = res.data.message;
    } catch (error) {
        console.log(error);
    }

})