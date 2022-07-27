const searchEle = document.querySelector(".seach-form .seach-form-input");
console.log(searchEle);
searchEle.addEventListener("keydown", (e) =>{
    if (e.keyCode == 13){
        let url = new URL(window.location.href);
        let keyword = e.target.value.trim();
        if (keyword == ""){
            url.searchParams.delete("keyword");
        } else {
            url.searchParams.set("keyword", keyword);
        }
        window.location.href = url;
    }
});

// function filterByTopic(topicId) {
//     let url = new URL(window.location.href);
//     url.searchParams.set("topicId", topicId);
//     window.location.href = url;
// }

function filterByTopic(topicId) {
    let url = new URL(window.location.href);
    url.searchParams.set("topicId", topicId);
    window.location.href = url;
}

function clearTopic(){
    let url = new URL(window.location.href);
    url.searchParams.delete("topicId");
    window.location.href = url;
}


