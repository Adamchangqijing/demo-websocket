// import md5 from './md5.min';
document.write("<script language=javascript src='./md5.min'></script>");
document.write("<script language=javascript src='./jquery-2.2.3.min'></script>");

var packetMsg = null;

function resetSocket(){
    packetMsg.socket.close();
    packetMsg.cacheToken = null;
    packetMsg.socket.state = false;
    packetMsg.socket.isLogin = false;
}
window.onload=function () {
    // var ws = new WebSocket("ws://127.0.0.1:28093/socket");
    // new SockJS("http://127.0.0.1:28093/socket", null, { transports: "websocket" });
    packetMsg = new PacketMsg();
    // packetMsg.
    //获取url
    // var sendNoticeUrl = null;
    // sendNoticeUrl = data.data;
    var socket = new WebsocketLink({url:"ws://127.0.0.1:28093", packetMsg:packetMsg, isBolt:true});
    packetMsg.socket = socket;
    packetMsg.businessProcess = businessProcess(packetMsg);
    socket.start();


    $("#sendId").click(function(){
        var text = $("#msgContent").val();
        console.log(text)
        // var parse = JSON.parse(text);
        // console.log(parse);
        packetMsg.businessProcess[0x10005](text);
    });


};
// websocket 链接
function WebsocketLink(config) {
    var _this = this;
    this.websocket_url = config.url;
    this.socket = null;
    this.state = false;
    this.isLogin = false;
    this.isBolt = false;
    if (config.isBolt!=null){
        this.isBolt = true;
    }
    this.packetMsg = config.packetMsg;
    //断线重连
    this.boltConnection = function () {
        // _this.socket = new SockJS(_this.websocket_url + '/broadcast', null, { transports: "websocket" });
        // _this.socket = new SockJS(_this.websocket_url+"/broadcast",null,{transports:"websocket"});
        if (!("WebSocket" in window)){
            console.log("浏览器不支持web socket ");
            return
        }
        _this.socket = new WebSocket(_this.websocket_url);
        _this.socket.onopen = function () {
            _this.state = true;
            _this.packetMsg.socket = _this;
            //鉴权
            // _this.packetMsg.businessProcess[0x10001]();
        };
        _this.socket.onmessage = function(evt){
            _this.onmessage(evt);
        };
        _this.socket.onclose = function () {
            _this.isLogin = false;
            _this.state = false;
            // _this.close();
            console.log("websocket .... close");
        };
        _this.socket.onerror = function (evt) {
            console.log("websocket .... error");
        }
    };
    //监控开启
    this.timer = function () {
        if (_this.isBolt){
            setInterval(function () {
                if (_this.socket.readyState != 0 &&  _this.socket.readyState !=1){
                    _this.isLogin = false;
                    _this.state = false;
                    _this.boltConnection();
                }
                if (!_this.isLogin && _this.state){
                    _this.packetMsg.businessProcess[0x10001]();
                }
            },3000)
        }
    };
    //启动
    this.start = function () {
        this.boltConnection();
        //定时器 断线重连监控

    };
    //消息响应
    this.onmessage = function (evt) {
        _this.packetMsg.dispose(evt.data);
    };
    //发送消息
    this.send = function (msg,isLogin) {
        if (this.state ){
            if (isLogin && this.isLogin){
                if (!this.isLogin) {
                    console.log("websocket 登录未完成 ");
                    return
                }
            }
            this.socket.send(msg);
        }
    };
    //链接已关闭
    this.close = function () {
        _this.socket.close();
    }

    //开启监控
    this.timer();
}


//用于生成uuid
function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}
function guid() {
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

//包内容
var Header = function (packetMsg) {
    if (packetMsg){
        var _header = packetMsg.header;
        this.msgSn = _header.msgSn;
        this.msgId = _header.msgId;
        this.msgVn = _header.msgVn;
        return
    }
    this.msgSn = null;
    this.msgId = null;
    this.msgVn = null;
};
var AppendBody = function (packetMsg) {
    if (packetMsg) {
        var _appendBody = packetMsg.appendBody;
        if (!_appendBody){
            return ;
        }
        this.sysNo = _appendBody.sysNo;
        this.token = _appendBody.token;
        this.format = _appendBody.format;
        return
    }
    this.sysNo = null;
    this.token = null;
    this.format = "json";
};
function Packet(packetMsg) {
    if (packetMsg){
        this.header = new Header(packetMsg);
        this.appendBody = new AppendBody(packetMsg);
        if (packetMsg.body){
            this.bodyStr = packetMsg.body;
            if (this.appendBody.format == "json"){
                this.body = JSON.parse(this.bodyStr);
            }else {
                this.body = this.bodyStr;
            }
        }
        return
    }
    this.header = null;
    this.appendBody = null;
    this.body = null;
}
//通信协议
function PacketMsg(WebsocketLink) {
    var _this = this;
    // socket
    _this.socket = WebsocketLink;
    _this.cacheToken = "ff";
    //获取token
    _this.token = function () {
        if(_this.cacheToken == null){
            // $.ajax({
            //     url: "getCookie",sync:false,
            //     success: function (data) {
            //         if(data.code!== 100){
            //             _this.cacheToken = null;
            //         }else{
            //             _this.cacheToken = data.data;
            //         }
            //     }
            // });
            _this.cacheToken = "ff";
        }
        return _this.cacheToken;
    };
    _this.sysNo = "TYPE";

    _this.businessProcess =

        //解码操作
        _this.StringDecode = function (packetMsg) {
            var decode = packetMsg.substr(1,packetMsg.length);
            decode = decode.replace(new RegExp("\\$\\$","gm"), "#");
            var checkCode = decode.substr(decode.length - 16,decode.length);
            var MD5Str = md5(decode.length-16).substr(8,16);
            if (checkCode == MD5Str){
                return JSON.parse(decode.substr(0,decode.length-16));
            }
        };
    _this.sendMsg = function(msg,isLogin){
        _this.socket.send(_this.StringEncode(msg),isLogin);
    };
    //发送json消息
    _this.sendJsonMsg = function(number,msg,isLogin){
        var msz =  _this.response(number, msg, "json");
        _this.socket.send(_this.StringEncode(msz),isLogin);
    };
    //编码操作
    _this.StringEncode = function (packetMsg) {
        var msg = JSON.stringify(packetMsg);
        var msg = (msg+md5(msg.length).substr(8,16)).replace("#","$$");
        return "#"+msg;
    };
    //业务处理
    _this.dispose = function (packetMsg) {
        // 解码
        var pkMsg = _this.StringDecode(packetMsg);
        var packet = new Packet(pkMsg);
        _this.businessProcess[packet.header.msgId](packet);
    };
//发送包组成
    _this.response = function(businessNo,body,format){
        var header = new Header();
        header.msgId = businessNo;
        header.msgSn = guid();
        header.msgVn = "1.0";
        var appendBody = new AppendBody();
        appendBody.format = format;
        appendBody.sysNo = _this.sysNo;
        appendBody.token = _this.token();
        var packet = new Packet();
        packet.header = header;
        packet.appendBody = appendBody;
        if ("json" == format){
            packet.body = JSON.stringify(body);
        } else {
            packet.body = body;
        }
        return packet;
    };
}
