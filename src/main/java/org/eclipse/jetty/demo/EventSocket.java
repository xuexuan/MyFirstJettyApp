package org.eclipse.jetty.demo;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ClientEndpoint
@ServerEndpoint(value="/events/")
public class EventSocket
{
	
    @OnOpen
    public void onWebSocketConnect(Session sess)
    {
//    	try {
//        	sess.getBasicRemote().sendText("send test");
//        	} catch (IOException ex) {
//        	ex.printStackTrace();
//        	}
        System.out.println("Socket Connected: " + sess);
    }
    
    @OnMessage
    public void onWebSocketText(String message)
    {
        System.out.println("Received TEXT message: " + message);
        
//        try {
//        	session.getBasicRemote().sendText(message+" over.....");
//        	} catch (IOException ex) {
//        	ex.printStackTrace();
//        	}
    }
    
    @OnClose
    public void onWebSocketClose(CloseReason reason)
    {
        System.out.println("Socket Closed: " + reason);
    }
    
    @OnError
    public void onWebSocketError(Throwable cause)
    {
        cause.printStackTrace(System.err);
    }
}