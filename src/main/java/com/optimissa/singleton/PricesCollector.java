package com.optimissa.singleton;

import com.optimissa.dto.PriceDTO;
import com.optimissa.dto.ResponseDTO;

import java.math.BigInteger;
import java.util.*;

public class PricesCollector {

    private static PricesCollector pricesCollector;
    private Map<String,List<PriceDTO>> prices;

    private PricesCollector(){
        prices=new HashMap<>();
    }

    public void addPrice(PriceDTO price){
        if(prices.keySet().contains(price.getCurrPair())){
            prices.get(price.getCurrPair()).add(price);
            prices.get(price.getCurrPair()).sort(Comparator.comparing(PriceDTO::getTimestamp).reversed());
        }else{
            List priceArr= new ArrayList<>();
            priceArr.add(price);
            prices.put(price.getCurrPair(),priceArr);
        }
    }

    public static PricesCollector getInstance(){
        if(pricesCollector==null){
            pricesCollector=new PricesCollector();
        }
        return pricesCollector;
    }

    public ResponseDTO getLastPrice(String pair) {
        ResponseDTO responseDTO=new ResponseDTO();
        if(prices.containsKey(pair)){
            responseDTO.setPrice(prices.get(pair).stream().findFirst().get());
        }else{
            responseDTO.setErrorCode(new BigInteger("404"));
            responseDTO.setErrorMessage("Currency pair not found");
        }
        return responseDTO;
    }
}
