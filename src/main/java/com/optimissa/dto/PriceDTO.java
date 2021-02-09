package com.optimissa.dto;

import com.optimissa.util.Constants;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PriceDTO{
    private BigInteger id;
    private String currPair;
    private BigDecimal bid;
    private BigDecimal ask;
    private Date timestamp;

    public PriceDTO(BigInteger id, String currPair, BigDecimal bid, BigDecimal ask, Date timestamp) {
        this.id=id;
        this.currPair = currPair;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCurrPair() {
        return currPair;
    }

    public void setCurrPair(String currPair) {
        this.currPair = currPair;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    //The parsing to JSON for the response will be part of the REST calls, until that part gets implemented, the parsing is done manually
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(Constants.DATE_FORMAT);
        String result="{\n";
        result+="id:"+getId()+",\n";
        result+="currPair:\""+getCurrPair()+"\",\n";
        result+="bid:"+getBid()+",\n";
        result+="ask:"+getAsk()+",\n";
        result+="timestamp:\""+simpleDateFormat.format(getTimestamp())+"\"\n";
        result+="}";
        return result.toString();
    }
}
