package com.ehome.websocket.endpoint;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * WebSocket
 *
 * @author: 郝晓雷
 * @date: 2015-07-12 15:25
 * @desc: 广播
 */
@ServerEndpoint("/echo")
public class ChatSocket {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    static Map<String,Session> sessionMap = new Hashtable<>();


    @OnOpen
    public void open(Session session) {
        sessionMap.put(session.getId(), session);
    }

    @OnMessage
    public void message(Session session, String msg) {
        msg = "sessionId" + session.getId() + "说：" + msg;
        broadcast("message", msg);
    }

    @OnClose
    public void close(Session session, CloseReason closeReason) {
        sessionMap.remove(session.getId());
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));

    }

    @OnError
    public void error(Session session, java.lang.Throwable throwable) {
        sessionMap.remove(session.getId());
        System.err.println("session " + session.getId() + " error:" + throwable);
    }


    /**
     * 广播
     * @param type
     * @param message
     */
    private static void broadcast(String type, String message) {
        Set<Map.Entry<String, Session>> set = sessionMap.entrySet();
        for (Map.Entry<String, Session> session : set) {
            try {
                session.getValue().getBasicRemote().sendText("{type:'" + type + "',text:'" + message + "'}");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}