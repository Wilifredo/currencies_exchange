package com.optimissa.dto;

import java.math.BigInteger;

public class ResponseDTO {
    private BigInteger errorCode=BigInteger.ZERO;
    private String errorMessage="";
    private PriceDTO price;

    public BigInteger getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BigInteger errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public PriceDTO getPrice() {
        return price;
    }

    public void setPrice(PriceDTO price) {
        this.price = price;
    }

    //The parsing to JSON for the response will be part of the REST calls, until that part gets implemented, the parsing is done manually
    @Override
    public String toString() {
        String result="{\n";
        result+="errorCode:"+getErrorCode()+",\n";
        result+="errorMessage:\""+getErrorMessage()+"\",\n";
        if(getPrice()!=null)
            result+="price:"+getPrice().toString()+"\n";
        result+="}";
        return result.toString();
    }
}
