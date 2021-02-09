package com.optimissa;
import com.optimissa.dto.ResponseDTO;
import com.optimissa.implementation.PriceReceiver;
import com.optimissa.interfaces.IPriceReceiver;
import com.optimissa.singleton.PricesCollector;
import com.optimissa.util.Constants;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@ApplicationPath("/pricer")
public class Pricer extends Application {

    //Future REST method to get the price
    @Path("/getPrice")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public static ResponseDTO getPrice(String pair) {
      return PricesCollector.getInstance().getLastPrice(pair);
  }


  //Main class used to feed the collector with data simuling an incoming message with the data from the prices.csv file
  public static void main(String[] argv) {

    IPriceReceiver iPriceReceiver=new PriceReceiver();
    byte[] encoded = new byte[0];
    try {
        encoded=Files.readAllBytes(Paths.get(Constants.CSV_PATH));
    } catch (IOException e) {
        e.printStackTrace();
    }
    iPriceReceiver.onMessageReceived(new String(encoded, StandardCharsets.UTF_8));

    //The client tries to get the latest price of the GBP/USD pair
    System.out.println(getPrice("GBP/USD"));

    //Test if a client asks for a currency pair that does not exist
    System.out.println(getPrice("EUR/GBP"));

  }
}
