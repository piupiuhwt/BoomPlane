package com.hwt.websocket;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    static Logger log = LoggerFactory.getLogger(WebSocket.class);
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<>();
    private static int onlineCount;
    private Session session;
    private String username;


    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session){
        this.username = username;
        this.session = session;

    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.username+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws  IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("username") String username) throws IOException {
        log.info("发送消息到:"+username+"，报文:"+message);
        if(StringUtils.isNotBlank(username)&&clients.containsKey(username)){
            clients.get(username).sendMessage(message);
        }else{
            log.error("用户"+username+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }
}
