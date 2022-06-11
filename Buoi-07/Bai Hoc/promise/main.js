// Promise : Lời hứa

// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max

// let promise = new Promise(function (resolve, reject){
//     // resolve("thực hiện thành công")
//     reject("thực hiện thất bại")
// })

let money = 40;

const buyIphone = () => {
    return new Promise(function(resolve, reject){
        if(money >= 33){
            resolve("mua iphone");
        }
        else{
            reject("không đủ tiền mua iphone");
        }
    })
}

const buyAirpod = () => {
    return new Promise(function(resolve, reject){
        if(money >= 33 + 5){
            resolve("mua thêm airpod");
        }
        else{
            reject("không đủ tiền mua airpod");
        }
    })
}

// buyIphone()
//     .then(res => {
//         console.log(res);
//         return buyAirpod();
//     })
//     .then(res => {
//         console.log(res);
//     })
//     .catch(error => {
//         console.log(error)
//     })
//     .finally(() => {
//         console.log("về nhà");
//     })

Promise.all([buyIphone(), buyAirpod()])
    .then(res => console.log(res))
    .catch(error => console.log(error))