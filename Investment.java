package eportfolio;

import java.util.Objects;
import java.io.IOException;
import java.util.InputMismatchException;

/**
  *
  * @author mali34
  */

public abstract class Investment {

    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;


    /**
    * Constructor for Investment object
    * @param symbol - Investment symbol
    * @param name - Investment name
    * @param quantity - Investment quantity
    * @param price - Investment price
    * @param bookValue - Investment bookValue
    */

  public Investment(String symbol, String name, int quantity, double price, double bookValue) throws IllegalArgumentException {

    if(symbol.isEmpty()) throw new IllegalArgumentException("Symbol is required to add the investment \n");


    if(name.isEmpty()){
      throw new IllegalArgumentException("Name is required to add the investment \n");
    }

    if(quantity <= 0){

      throw new IllegalArgumentException("Quantity cannot be less then 0 \n");

    }

    if(price <= 0){

      throw new IllegalArgumentException("Price cannot be less then 0 \n");

    }

    this.symbol = symbol;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.bookValue = bookValue;

  }


  /**
  * Copy Constructor for Investment object
  * @param copy - Investment object
  */

  public Investment(Investment copy){

    String newSymbol = copy.symbol;
    String newName = copy.name;

    int newQuantity = copy.quantity;
    double newPrice = copy.price;
    double newBookvalue = copy.bookValue;

    this.symbol = newSymbol;
    this.name = newName;
    this.quantity = newQuantity;
    this.price = newPrice;
    this.bookValue = newBookvalue;

  }


  /**
     * This is an abstract function used to calculate gain on investments
     * @return gain for an investment of either stock or mutual fund
     */
  public abstract double getGain();


 /**
    * setter method to set price for a index in ArrayList.
    * @param price - value to set in the index in ArrayList.
    */
  public void setPrice(double price)
  {


    this.price = price;

  }

  /**
    * setter method to set quanitiy for a index in ArrayList.
    * @param quantity - value to set in the index in ArrayList.
    */
      public void setQuantity(int quantity)
      {

        this.quantity = quantity ;

      }

  /**
    * getter method to get symbol value stored at an index in ArrayList.
    * @return It returns symbol stored at an index in ArrayList.
    */
  public String getSymbol(){

    return symbol;

  }

  /**
    * getter method to get name value stored at an index in ArrayList.
    * @return It returns name stored at an index in ArrayList.
    */
  public String getName(){

    return name;

  }

    /**
    * getter method to get quantity value stored at an index in ArrayList.
    * @return It returns quanitiy stored at an index in ArrayList.
    */
  public int getQuantity(){

    return quantity;

  }

    /**
    * getter method to get price value stored at an index in ArrayList.
    * @return It returns price stored at an index in ArrayList.
    */
  public double getPrice(){

    return price;

  }


    /**
    * getter method to get bookValue value stored at an index in ArrayList.
    * @return It returns bookValue stored at an index in ArrayList.
    */
  public double getBookValue(){

    return bookValue;

  }



  public void sellMutual(int sellQuantity){

    this.quantity= this.quantity - sellQuantity;

  }

  public void sellStock(int sellQuantity){

    this.quantity= this.quantity - sellQuantity;


  }

    /**
    * Method to update price of Investment.
    * @param newPrice updates the price of Investment at an index in ArrayList.
    */
   public void updatePrice(double newPrice)
    {



    price = newPrice;
    }


    /**
    * Method to update bookvalue of Investment.
    * @param bookValue updates the bookvalue of Investment at an index in ArrayList.
    */
  public void updateBookvalue(double bookValue){

    this.bookValue = bookValue;
  }


    public String toString(){

        return "symbol = " + symbol + "\n" + "name = " + name + "\n" + "quantity = " + quantity + "\n" + "price = " + price + "\n" + "bookvalue = " + bookValue + "\n";



    }


  public String toStringFile(){

    return symbol + "\n" + name + "\n" +  quantity + "\n" + price + "\n" + bookValue + "\n";
  }

  /**
  * override method equals
  * @param other its and parameter of type object class
  */
  public boolean equals(Object other){


    if(other == null){
      return false;
    }
    else if(getClass() != other.getClass()){

      return false;
    }
    else{
      Investment demo = (Investment)other;
      return this.symbol.equals(demo.symbol);
    }

  }



}
