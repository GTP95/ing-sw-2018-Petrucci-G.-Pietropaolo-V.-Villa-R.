package Progetto_Ing_Sw.com.Client;

import Progetto_Ing_Sw.com.client.ClientSettings;
import Progetto_Ing_Sw.com.client.LocalModel;
import Progetto_Ing_Sw.com.client.SocketClient;
import org.junit.Assert;
import org.junit.Test;

import java.net.ConnectException;
import java.net.UnknownHostException;

public class SocketClientTest { //Only for integration testing, requires a running server!
  /*  @Test
    public void socketClientTester(){
        LocalModel localModel =LocalModel.getInstance();
        try{
            ClientSettings.getInstance().setHostname("localhost");
            ClientSettings.getInstance().setSocketPort(1025);
            Thread client=new Thread(new SocketClient(), "SocketClient");
            client.start();
            ClientSettings.getInstance().setUsername("Giacomo");
            System.out.println("User: "+ localModel.getClientPlayerArrayList().get(0).getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (UnknownHostException | ConnectException e){Assert.fail(e.getMessage());}

    }*/
}
