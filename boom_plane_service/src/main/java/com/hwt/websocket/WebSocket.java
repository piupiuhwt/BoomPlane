package com.hwt.websocket;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocket {
    static Logger log = LoggerFactory.getLogger(WebSocket.class);
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    private static int onlineCount;
//    private Session session;
//    private String username;


    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session){
        clients.put(username, session);
        try {
            sendMessage(session,"欢迎" + username + "加入连接！");
            log.info(username + "加入链接");
            clients.values().forEach(value -> {
                try {
                    sendMessage(session,username + "加入");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        clients.forEach((key,value) -> {
            if (value == session) {
                log.error("用户错误:"+key+",原因:"+error.getMessage());
            }
        });
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Session session,String message) throws  IOException {
        session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     * */
    public void sendInfo(String message,@PathParam("username") String username) throws IOException {
        log.info("发送消息到:"+username+"，报文:"+message);
        if(StringUtils.isNotBlank(username)&&clients.containsKey(username)){
            sendMessage(clients.get(username),message);
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
