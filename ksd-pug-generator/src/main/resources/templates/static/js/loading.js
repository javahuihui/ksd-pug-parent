// 闭包，起到隔离环境的作用
(function($){
    var stop = 1;
    function loading(msg,time,icon,bgcolor,options){
        stop = stop + 5;
        // 模板对象
        var $template = $('<div class="ksd-loading '+bgcolor+' animate__animated animate__fadeInUp" style="margin-top:'+stop+'px">'+
            '		<span class="icon"><i class="iconfont '+icon+'"></i></span>'+
            '		<span class="msg">'+msg+'</span>'+
            '	</div>');
        // 追加
        $("body").append($template);

        // 覆盖默认样式
        $template.css(options || {})

        // 每个loading事件处理
        $template.off("click").on("click",function(){
            if($template.timer)clearTimeout($template.timer);
            $(this).removeClass("animate__animated animate__fadeInUp")
                .addClass("animate__animated animate__fadeOutDown")
                .fadeOut(1000,function(){
                    $(this).remove();
                    stop = stop - 5;
                })
        });
        // 定时关闭
        $template.timer = setTimeout(function(){
            $template.trigger("click");
        },time || 2000);
    }



    $.success = function(msg,timeout,options){
        loading(msg,timeout,"icon-icon_right","ksd-loading-success",options||{});
    },

    $.warn = function(msg,timeout,options){
        loading(msg,timeout,"icon-28","ksd-loading-warn",options||{});
    }

    $.tip = function(msg,timeout,options){
        loading(msg,timeout,"icon-28","ksd-loading-tip",options||{});
    }

    $.error = function(msg,timeout,options){
        loading(msg,timeout,"icon-shanchu","ksd-loading-error",options||{});
    }

    window.success = $.success;
    window.warn = $.warn;
    window.error = $.error;
    window.tip = $.tip;

})(jQuery);