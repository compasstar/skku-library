document.addEventListener("DOMContentLoaded", function() {
    var message = document.getElementById("message");
    console.log("debug1");
    if (message.innerText.length > 0) {
        console.log("debug2");
        alert(message.innerText);
    }
})