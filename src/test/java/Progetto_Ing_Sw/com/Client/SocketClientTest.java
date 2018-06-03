package Progetto_Ing_Sw.com.Client;

import Progetto_Ing_Sw.com.client.Model;
import Progetto_Ing_Sw.com.client.SocketClient;
import org.junit.Assert;
import org.junit.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

public class SocketClientTest {
    @Test
    public void socketClientTester(){
        Model model=Model.getInstance();
        try{
            model.setHostname("localhost");
            model.setSocketPort(1025);
            Thread client=new Thread(new SocketClient());
            client.start();
            model.setUsername("Giacomo");
            System.out.println("User: "+model.getPlayerArrayList().get(0).getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (UnknownHostException | ConnectException e){Assert.fail(e.getMessage());}

    }
}
