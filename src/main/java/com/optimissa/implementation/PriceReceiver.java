package com.optimissa.implementation;

import com.optimissa.dto.PriceDTO;
import com.optimissa.interfaces.IPriceReceiver;
import com.optimissa.singleton.PricesCollector;
import com.optimissa.util.Constants;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PriceReceiver implements IPriceReceiver{

    @Override
    public void onMessageReceived(String pricesReceived) {

        SimpleDateFormat sdf=new SimpleDateFormat(Constants.DATE_FORMAT);

        for (String priceReceived: pricesReceived.split("\n")) {
            String[] priceArr=priceReceived.split(Constants.CSV_SEPARATOR);

            PriceDTO price= null;
            try {
                price = new PriceDTO(new BigInteger(priceArr[0]), priceArr[1], new BigDecimal(priceArr[2]), new BigDecimal(priceArr[3]), sdf.parse(priceArr[4]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            addMargins(price);
            //To emulate the insert in a database i have implemented a Singleton collector in order to have all the data loaded for the price petitions
            PricesCollector.getInstance().addPrice(price);
        }
    }

    //Function that adds the margin and commission defined in the Constants file
    private void addMargins(PriceDTO price){
        price.setBid(price.getBid().subtract(Constants.MARGIN));
        price.setAsk(price.getAsk().add(Constants.COMMISSION));
    }
}
