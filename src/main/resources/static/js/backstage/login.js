/*
*
* 后台登录功能
* */


function login_pwd(pwd){
    var aa=pwd.value;
    if(aa.trim().length<32){
        var aa=$.md5(aa);
    }
    pwd.value=aa;
}

function error_hide(){
    $(".errorsss").html("");
}