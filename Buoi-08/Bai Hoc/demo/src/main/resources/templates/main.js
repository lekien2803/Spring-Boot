const btn1 = document.querySelector(".btn-color");
const btn2 = document.querySelector(".btn-hex");
const btn3 = document.querySelector(".btn-RGB");
const body = document.querySelector("body");

const btn_bmi = document.querySelector(".btn-bmi");

btn1.addEventListener("click", async function () {
    try {
        let res = await axios.get("http://localhost:8080/random-color?type=1");
        body.style.backgroundColor = res.data;

    } catch (error) {
        console.log(error);
    }
})
btn2.addEventListener("click", async function () {
    try {
        let res = await axios.get("http://localhost:8080/random-color?type=2");
        body.style.backgroundColor = res.data;

    } catch (error) {
        console.log(error);
    }
})
btn3.addEventListener("click", async function () {
    try {
        let res = await axios.get("http://localhost:8080/random-color?type=3");
        body.style.backgroundColor = res.data;

    } catch (error) {
        console.log(error);
    }
})

btn_bmi.addEventListener("click",async function(){
    try {
        let res = await axios.post("http://localhost:8080/bmi", {
            height : 1.7,
            weight : 60
        });
        console.log(res);
    } catch (error) {
        console.log(error);
    }
})