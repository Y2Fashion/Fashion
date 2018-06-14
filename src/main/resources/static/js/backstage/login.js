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



var validation=false;

$(function(){
    /*验证码判断*/
    $(".inner").mousedown(function(e){
        var el = $(".inner"),os = el.offset(),dx,$span=$(".outer>span"),$filter=$(".filter-box"),_differ=$(".outer").width()-el.width();
        $(document).mousemove(function(e){
            dx = e.pageX - os.left;
            if(dx<0){
                dx=0;
            }else if(dx>_differ){
                dx=_differ;
            }
            $filter.css('width',dx);
            el.css("left",dx);
        });
        $(document).mouseup(function(e){
            $(document).off('mousemove');
            $(document).off('mouseup');
            dx = e.pageX - os.left;
            if(dx<_differ){
                dx=0;
                $span.html("滑动解锁");
            }else if(dx>=_differ){
                dx=_differ;
                $(".outer").addClass("act");
                $span.html("验证通过！");
                el.html('&radic;');
                validation=true;
            }
            $filter.css('width',dx);
            el.css("left",dx);
        })
    });
})

/**
 * 上传，，判断
 * @returns {boolean}
 */
function xxx() {
    if (validation) {
        return true;
    }
    return false;
}