
function check(){
    let admin_id = document.getElementById("id");

    let id = admin_id.value.trim();
    if(isNaN(id) || id === ""){
        admin_id.value = "0000000";
    }
}

