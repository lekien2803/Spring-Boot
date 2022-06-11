// Promise : Lời hứa

// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max

// let promise = new Promise(function (resolve, reject){
//     // resolve("thực hiện thành công")
//     reject("thực hiện thất bại")
// })

let money = 35;

const buyIphone = () => {
    return new Promise(function (resolve, reject) {
        if (money >= 33) {
            resolve("mua iphone");
        }
        else {
            reject("không đủ tiền mua iphone");
        }
    })
}

const buyAirpod = () => {
    return new Promise(function (resolve, reject) {
        if (money >= 33 + 5) {
            resolve("mua thêm airpod");
        }
        else {
            reject("không đủ tiền mua airpod");
        }
    })
}

// async function buy(){

// }

const buy = async () => {
    try {
        let res = await buyIphone();
        console.log(res);
        let res1 = await buyAirpod();
        console.log(res1);
    } catch (error) {
        console.log(error);
    }

    return "về nhà";
}

// console.log(buy());
buy()
    .then(res => console.log(res))
    .catch(error => console.log(error))