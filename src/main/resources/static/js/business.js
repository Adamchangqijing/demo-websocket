var businessProcess = function (_this) {
    return {
        //登录业务
        0x10001: function () {
            var packet = _this.response(0x10001, {}, "json");
            if (packet.appendBody.token == null || packet.appendBody.token == "" ){
                console.log("该用户还没登录");
                return
            }
            _this.sendMsg(packet, false);
        },
        //登录响应
        0x10002: function (packet) {
            if (packet.body.code === 100){
                _this.socket.state = true;
                _this.socket.isLogin = true;
                //登陆成功 第一次需要渲染的十二个数据 packet.body.data
                console.log("webSocket登录成功:"+packet.body.data);
            }else {
                _this.socket.isLogin = false;
                console.log("webSocket登录失败:"+packet.body.message);
            }
        },
        //群接收的数据响应
        0x10004: function (packet) {
            if (packet.body.code === 100){
                //需要去处理的数据 flag=true表示新增的数据，需要干掉第一个 flag=false 更新的最后一个需要去替换最后一个
                console.log("群接收的数据响应:"+packet.body.data);
            }else {
                console.log("群接收的数据响应:"+packet.body.message);
            }
        },
        //退出通知
        0x10003: function (packet) {
            //将弹出用户退出的消息
            // logOutLayer(packet.body)
            //关闭链接
            _this.socket.close();
        },
        //主动发送消息
        0x10005: function (vars) {
            // _this.sendJsonMsg(0x10005, vars, true);
            _this.sendMsg(_this.response(0x10005, vars, "json"), false);
        },
        //主动发送消息接收的消息
        0x10006: function (packet) {
            if (packet.body.code === 100){
                console.log("主动发送消息后接收的消息:"+packet.body.data);
                // let data = $("#receiveMsg").html();
                var myDate = new Date();
                var mytime = myDate.toLocaleTimeString();     //获取当前时间
                $("#receiveMsg").html(mytime + ":" + packet.body.data)
            }else {
                console.log("主动发送消息后接收的消息:"+packet.body.message);
            }
        },
    };
};
