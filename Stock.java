package eportfolio;

import java.util.Objects;


/**
  *
  * @author mali34
  */

import java.util.Objects;

public class Stock extends Investment {

  /**
    * Constructor for stock class
    * @param symbol - stock symbol
    * @param name - stock name
    * @param quantity - quantity of stocks
    * @param price - price of stock
     * @param bookValue - stock bookValue
    */

  public Stock(String symbol, String name, int quantity, double price, double bookValue){

    super(symbol,name,quantity,price,bookValue);
  }

  /**
  * Copy Constructor for Investment object
  * @param e - Investment object
  */
  public Stock(Investment e){
    super(e);
  }

  @Override
  public double getGain(){

    return ((this.getQuantity() * this.getPrice()) - 9.99) - this.getBookValue();


  }



    @Override
    public String toString(){
    return "Stock{" + super.toString() + "}";

  }

    public String toStringFile(){
    return super.toStringFile() ;

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
      Investment demo = (Stock)other;
      return this.getSymbol().equals(demo.getSymbol());
    }

  }


}
