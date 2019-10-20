package org.eclipse.jetty.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

//import org.eclipse.jetty.util.component.LifeCycle;

public class EventClient
{
    public static void main(String[] args)
    {
    	try
    	{
    		Helper(args);	
    	}
    	catch(DeploymentException e)
    	{
    		System.out.print(e.getMessage());
    	}
    	catch (URISyntaxException e)
    	{
    		System.out.print(e.getMessage());
    	}
    	catch(Exception e)
    	{
    		
    	}
       
    }
    
    static void Helper(String[] args) throws Exception,DeploymentException,URISyntaxException
    {
    	URI uri = URI.create("wss://ws-feed.pro.coinbase.com");
        CountDownLatch latch = new CountDownLatch(1);
        
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            try
            {
                // Attempt Connect
                Session session = container.connectToServer(EventSocket.class,uri);
                // Send a message
                session.getBasicRemote().sendText("{\"channel\":\"ok_sub_futureusd_btc_depth_quarter\",\"event\":\"addChannel\"}");
                latch.await();
                // Close session
                //session.close();
            }
            catch(Exception  e) 
            {
            	System.out.println(e.getMessage());
            	throw e;
            }
//            finally
//            {
//                // Force lifecycle stop when done with container.
//                // This is to free up threads and resources that the
//                // JSR-356 container allocates. But unfortunately
//                // the JSR-356 spec does not handle lifecycles (yet)
//                if (container instanceof LifeCycle)
//                {
//                    ((LifeCycle)container).stop();
//                }
//            }
    }
}