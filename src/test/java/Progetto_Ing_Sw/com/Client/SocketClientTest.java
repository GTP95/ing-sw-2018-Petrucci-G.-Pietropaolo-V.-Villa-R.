package Progetto_Ing_Sw.com.Client;

import Progetto_Ing_Sw.com.client.Model;
import Progetto_Ing_Sw.com.client.SocketClient;
import org.junit.Assert;
import org.junit.Test;

import java.net.UnknownHostException;

public class SocketClientTest {
    @Test
    public void socketClientTester(){
        Model model=Model.getInstance();
        try{
            Thread client=new Thread(new SocketClient("localhost",1025));
            client.start();
            model.setUsername("Giacomo");
        }
        catch (UnknownHostException e){Assert.fail(e.getMessage());}

    }
}
