package Progetto_Ing_Sw.com.server.Model;

import Progetto_Ing_Sw.com.server.Model.RoundTrack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.SplittableRandom;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //incrementRoundTest dev'essere eseguito prima di noMoreThan10RoundsTest per via del singleton
public class RoundTrackTest {
    private RoundTrack roundTrack;

    @Before
    public void testInit(){
        roundTrack=RoundTrack.getInstance();
    }

    @Test
    public void singletonTest(){
       RoundTrack roundTrack1=RoundTrack.getInstance();
       RoundTrack roundTrack2=RoundTrack.getInstance();
        Assert.assertTrue(roundTrack1==roundTrack2);
    }

    @Test
    public void incrementRoundTest(){
        roundTrack.incrementRound();
        Assert.assertEquals(1,roundTrack.getRoundNumber());
    }

    @Test
    public void noMoreThan10RoundsTest(){
        int moreThan10=new SplittableRandom().nextInt(11,50);
        System.out.println("noMoreThan10RoundsTest: provo con "+moreThan10+" round");
        for(int i=0;i<=moreThan10;i++) roundTrack.incrementRound();
        Assert.assertEquals(11,roundTrack.getRoundNumber());
    }
}
