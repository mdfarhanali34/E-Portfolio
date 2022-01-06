package eportfolio;

import java.util.Objects;

/**
  *
  * @author mali34
  */

public class MutualFund extends Investment {

  /**
    * Constructor for mutual fund object
    * @param symbol - mutual fund symbol
    * @param name - mutual fund name
    * @param quantity - mutual fund quantity
    * @param price - mutual fund price
    * @param bookValue - mutual fund bookValue
    */
  public MutualFund(String symbol, String name, int quantity, double price, double bookValue){

    super(symbol,name,quantity,price,bookValue);

  }

  /**
  * Copy Constructor for Investment object
  * @param e - Investment object
  */
  public MutualFund(Investment e){
    super(e);
  }

  @Override
  public double getGain(){

    return ((this.getQuantity() * this.getPrice()) - 45) - this.getBookValue();

  }


  @Override
  public String toString(){
    return "Mutual Fund{" + super.toString() + "}";

  }

  public String toStringFile(){

    return super.toStringFile();

  }

  @Override
  public boolean equals(Object other){


    if(other == null){
      return false;
    }
    else if(getClass() != other.getClass()){

      return false;

    }
    else{
      Investment demo = (MutualFund)other;
      return this.getSymbol().equals(demo.getSymbol());
    }

  }

}
