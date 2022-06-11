// một số công việc thực hiện 
//đi làm: 4s
// nghỉ trưa: 2s
// đi nhậu : 3s




// 3 cách viết function
// function sum(a, b) {
//     return a + b;
// }

// const sum1 = function (a, b) {
//     return a + b;
// }

// const sum2 = (a, b) => a + b;

function doTask1(taskName, callback){
    console.log("bat dau thuc hien cong viec hang ngay");
    console.log(`thuc hien cong viec ${taskName}`);
    setTimeout(function(){
        console.log(`thuc hien xong cong viec ${taskName}`);
        callback();
    }, 4000)
}

function doTask2(taskName, callback){
    console.log(`thuc hien cong viec ${taskName}`);
    setTimeout(function(){
        console.log(`thuc hien xong cong viec ${taskName}`);
        callback();
    }, 2000)
}

function doTask3(taskName, callback){
    console.log(`thuc hien cong viec ${taskName}`);
    setTimeout(function(){
        console.log(`thuc hien xong cong viec ${taskName}`);
        callback();
    }, 3000)
}

function finish(){
    console.log(`ket thuc cong viec`);
}

doTask1("đi làm", function() {
    doTask2("nghỉ trưa", function(){
        doTask3("đi nhậu", finish)
    })
})


// gọi api lấy danh sách user
// lấy thông tin của user theo id
// lấy danh sách bài viết
// lấy chi tiết bài viết
//