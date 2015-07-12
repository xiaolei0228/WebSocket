package com.ehome.websocket.endpoint;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * WebSocket
 *
 * @author: 郝晓雷
 * @date: 2015-07-12 15:25
 * @desc:
 */
@ServerEndpoint("/echo")
public class ChatEndPoint {

    @OnOpen
    public void open(Session session) {
        System.out.println("session已打开...sessionId：" + session.getId());
    }

    @OnMessage
    public void message(Session session, String msg) {
        System.out.println("客户端说：" + msg + "\tsessionId：" + session.getId());
        try {
            session.getBasicRemote().sendText("服务端说：" + msg + " too\tsessionId：" + session.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("session已关闭...sessionId：" + session.getId());
    }
}