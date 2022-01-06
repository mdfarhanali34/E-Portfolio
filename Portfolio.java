package eportfolio;

/**
  *
  * @author mali34
  */

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import javax.swing.JTextArea;


public class Portfolio{

  private ArrayList<Investment> newInvest;
  private HashMap<String, ArrayList<Integer>> newMap;

  public Portfolio(){
   newInvest = new ArrayList<Investment>();
   newMap = new HashMap<String, ArrayList<Integer>>();
  }


    /**
    * This function write data from arraylist into file
    * @param str its the name of file which was hardcoded as output.txt and passed to the function.
    */
  public void write(String str){

    PrintWriter outputStream = null;
    try{
      outputStream = new PrintWriter(new FileOutputStream(str));
       for(Investment temp : newInvest){

      if(temp instanceof MutualFund){

        outputStream.print("Mutual Fund");
      } else if(temp instanceof Stock){


       outputStream.print("Stock");

      }
      outputStream.print("\n");
      outputStream.print(temp.getSymbol());
      outputStream.print("\n");

      outputStream.print(temp.getName());
      outputStream.print("\n");

      outputStream.print(temp.getQuantity());
      outputStream.print("\n");

      outputStream.print(temp.getPrice());
      outputStream.print("\n");

      outputStream.print(temp.getBookValue());
      outputStream.print("\n");
      outputStream.print("\n");


    }

      outputStream.close();

    }
    catch(IOException e){
      System.out.println("File not found");

    }

  }


    /**
    * This function load data from file into our arraylist and also create a hash map for it
    * @param str its the name of file which was read from comand line and passed to the function.
    */
  public void load(String str){

    BufferedReader br;
    try{


      br = new BufferedReader(new FileReader(str));
      String string = br.readLine();
      while(string != null){


        String symbol = br.readLine();
        String name = br.readLine();
        int quantity = Integer.parseInt(br.readLine());
        double price = Double.parseDouble(br.readLine());
        double getBookValue = Double.parseDouble(br.readLine());

        if(string.equalsIgnoreCase("stock")){
          Stock newStock = new Stock(symbol, name, quantity, price,getBookValue);


          newInvest.add(newStock);

        int loc = newInvest.size()-1;

        String[] newWord = name.toLowerCase().split(" ");
        for(String hold: newWord){

          ArrayList<Integer> now = new ArrayList<>();

          if(newMap.get(hold) != null){
            now = newMap.get(hold);
          }
          now.add(loc);
          newMap.put(hold,now);

        }


        }else{
          MutualFund newMutual = new MutualFund(symbol, name, quantity, price,getBookValue);

          newInvest.add(newMutual);

          int loc = newInvest.size()-1;

        String[] newWord = name.toLowerCase().split(" ");

        for(String hold: newWord){

          ArrayList<Integer> now = new ArrayList<>();

          if(newMap.get(hold) != null){
            now = newMap.get(hold);
          }
          now.add(loc);
          newMap.put(hold,now);

        }

          }
            string = br.readLine();
            string = br.readLine();
        }
          br.close();

      }


    catch(IOException e){
      System.out.println("File not found");
      }

    if(!(newInvest.isEmpty())){
      System.out.println("Investment loaded from file: \n");
        for (int i=0;i<newInvest.size() ;i++ ) {
        System.out.println(newInvest.get(i).toString());

        }


      }

    }



  /**
   * Check weather a stock exist by comparing symbol to entire Investment ArrayList, if it does update the price and quantity and print the curent element inside ArrayList.
   * @param symbol it is the symbol of stock entered by the user.
   * @param  quantity it is the quantity of the stock.
   * @param  price it is the price of the stock.
   * @param buyMesageArea it is the variable used to print to the mesage area
   */
  public void existingStock(String symbol, int quantity, double price, JTextArea buyMesageArea){

    String temp = new String();

    double book = 0.0, newHola, sum=0.0;
    int hola =0, hold=0;
    for(int i =0; i<newInvest.size(); i++){

      temp = newInvest.get(i).getSymbol();

      if(temp.equalsIgnoreCase(symbol)){


        newInvest.get(i).setPrice(price);
        hola = newInvest.get(i).getQuantity();

        hold = hola + quantity;

        newInvest.get(i).setQuantity(hold);

        book = price*quantity + 9.99;

        newHola = newInvest.get(i).getBookValue();

        sum = book + newHola;

        newInvest.get(i).updateBookvalue(sum);

        buyMesageArea.setText(newInvest.get(i).toString() + "\n");
      }

    }




  }


  /**
   * Check weather a Mutual Fund exist by comparing symbol to entire Investment ArrayList, if it does update the price and quantity and print the curent element inside ArrayList.
   * @param symbol it is the symbol of Mutual Fund entered by the user.
   * @param  quantity it is the quantity of the Mutual Fund .
   * @param  price it is the price of the Mutual Fund .
   * @param buyMesageArea it is the variable used to print to the mesage area
   */
  public void existingFund(String symbol, int quantity, double price, JTextArea buyMesageArea){

      String temp = new String();

      int hola =0, hold=0;
      double book = 0.0, newHola, sum=0.0;
      for(int i =0; i<newInvest.size(); i++){

        temp = newInvest.get(i).getSymbol();

        if(temp.equalsIgnoreCase(symbol)){

          newInvest.get(i).setPrice(price);
          hola = newInvest.get(i).getQuantity();
          hold = hola + quantity;
          newInvest.get(i).setQuantity(hold);
          book = price*quantity;

          newHola = newInvest.get(i).getBookValue();

          sum = book + newHola;

          newInvest.get(i).updateBookvalue(sum);

          buyMesageArea.setText(newInvest.get(i).toString() + "\n");

        }

      }



  }


  /**
   * Adds the stock to the Investment ArrayList if the Stock does not exist earlier, create a hashmap for name and print the curent element inside ArrayList.
   * @param symbol it is the symbol of Stock entered by the user.
   * @param  name it is the name of the Stock .
   * @param  price it is the price at which user buys the stock.
   * @param quantity it is the quanitiy of stock user wants to buy.
   * @param bookValue it is the bookValue of Stock which was calculated in main and passes to function to set it in our arraylist
   * @param buyMesageArea it is the variable used to print to the mesage area
   */

  public void newStock(String symbol, String name, int quantity, double price, double bookValue, JTextArea buyMesageArea){

    Stock objStock;
    try{
        objStock = new Stock(symbol, name, quantity, price, bookValue);
        newInvest.add(objStock);
      }
      catch(IllegalArgumentException e){

        buyMesageArea.setText(e.getMessage());

      }
        int loc = newInvest.size()-1;
        String[] newWord = name.toLowerCase().split(" ");
        for(String hold: newWord){
          ArrayList<Integer> now = new ArrayList<>();

          if(newMap.get(hold) != null){
            now = newMap.get(hold);
          }
          now.add(loc);
          newMap.put(hold,now);

        }


      for (int i=0;i<newInvest.size() ;i++ ) {

        String  temp = newInvest.get(i).getSymbol();
        if(temp.equalsIgnoreCase(symbol)){
        buyMesageArea.setText(newInvest.get(i).toString() + "\n");
        }
      }

    }

    /**
      * Adds the Mutual Fund to the Investment ArrayList if the Mutual Fund does not exist earlier, create a hashmap for name and print the curent element inside ArrayList.
      * @param symbol it is the symbol of Mutual Fund entered by the user.
      * @param  name it is the name of the Mutual Fund .
      * @param  price it is the price at which user buys the Mutual Fund.
      * @param quantity it is the quanitiy of Mutual Fund user wants to buy.
      * @param bookValue it is the bookValue of Mutual Fund which was calculated in main and passes to function to set it in our arraylist
      * @param buyMesageArea it is the variable used to print to the mesage area
      */


  public void newMutual(String symbol, String name, int quantity, double price, double bookValue, JTextArea buyMesageArea){

        MutualFund objMutual;
        try{
        objMutual = new MutualFund(symbol, name, quantity, price, bookValue);
        newInvest.add(objMutual);
        }
        catch(IllegalArgumentException e){

          buyMesageArea.setText(e.getMessage());

        }

        int loc = newInvest.size()-1;
        String[] newWord = name.toLowerCase().split(" ");
        for(String hold: newWord){

          ArrayList<Integer> now = new ArrayList<>();

          if(newMap.get(hold) != null){
            now = newMap.get(hold);
          }
          now.add(loc);
          newMap.put(hold,now);

        }




        for (int i=0;i<newInvest.size() ;i++ ) {

          String  temp = newInvest.get(i).getSymbol();
          if(temp.equalsIgnoreCase(symbol)){
          buyMesageArea.setText(newInvest.get(i).toString() + "\n");
          }
        }


  }

  /**
    * This function calculate the total gain.
    * @param gainMesageArea used to print to mesage area
    * @return total gain of current value in bot the ArrayList.
    */
  public double gain(JTextArea gainMesageArea){


    double total1 =0;

    for(int i=0; i< newInvest.size(); i++){

      total1 = total1 + newInvest.get(i).getGain();
      

      gainMesageArea.append(newInvest.get(i).getName() +" " + newInvest.get(i).getGain() + "\n");

    }

    return total1;


  }

  /**
    * It sells the Investment by reducing the quantity and if the quantity reduces to zero we removes the Investment from our ArrayList.
    * @param symbol it is the symbol of Investment entered by the user.
    * @return payment it returns payment value calculated in the function.
    * @param  price it is the price at which user want to sell the Investment at.
    * @param sellQuantity it is the quantity user wants to sell.
    * @param sellMesageArea it is used to print on the mesage area
    */
  public double sellInvestment(String symbol, int sellQuantity, double price, JTextArea sellMesageArea){

      String temp = new String();

      double payment = 0.0;
      double book = 0.0, newHola, sum=0.0;
      int reduced =0;
      int holdQua =0;

      for(int i =0; i<newInvest.size(); i++){

        temp = newInvest.get(i).getSymbol();

        if(temp.equalsIgnoreCase(symbol)){

          if(newInvest.get(i).getQuantity() >= sellQuantity)
          {
            payment = sellQuantity*price - 9.99;
            holdQua = newInvest.get(i).getQuantity();
            reduced = holdQua - sellQuantity;

            newInvest.get(i).sellStock(sellQuantity);

            if(reduced >0){

              newHola = newInvest.get(i).getBookValue();
              Double tomo = Double.valueOf(reduced);
              Double totok = Double.valueOf(holdQua);
              book = newHola*(tomo/totok);
              newInvest.get(i).updateBookvalue(book);
            }else{
              newInvest.remove(i);
              int loc =0;
              newMap.clear();
              for(Investment hold : newInvest){

                   for(String wo : hold.getName().toLowerCase().split(" ")){
                    ArrayList<Integer> l = new ArrayList<>();
                      if(newMap.get(wo) != null){
                        l = newMap.get(wo);
                      }
                      l.add(loc);
                      newMap.put(wo,l);
                    }
                    loc++;
                  }
                }
             }
          }
      }


              for (int i=0;i<newInvest.size() ;i++ ) {

                String  holdThis = newInvest.get(i).getSymbol();
                if(holdThis.equalsIgnoreCase(symbol)){
                sellMesageArea.setText(newInvest.get(i).toString() + "\n");
                }
              }


      return payment;
    }


   /**
    * This function updates all the price of Investment ArrayList.
    * @param num position
    * @param price price of the investment
    * @param updateMesageArea to print on the mesage area
    */
  public void updateS(int num, double price, JTextArea updateMesageArea){

      newInvest.get(num).updatePrice(price);
      for(int i =0; i<newInvest.size(); i++){
        updateMesageArea.append(newInvest.get(i).toString() + "\n");
      }


  }


  /**
   * This is a arraylist of string symbols.
   */
  public ArrayList<String> getSymbolNew(){

    ArrayList<String> symbols = new ArrayList<>();
    for(Investment upInvest : newInvest){
      symbols.add(upInvest.getSymbol());
    }
      return symbols;

  }

  /**
   * This is a arraylist of string for names.
   */
  public ArrayList<String> getNameNew(){

    ArrayList<String> nName = new ArrayList<>();
    for(Investment upInvest : newInvest){
      nName.add(upInvest.getName());
    }
      return nName;

  }

  /**
   * This is a arraylist of Double for prices.
   */
  public ArrayList<Double> getPricesNew(){

    ArrayList<Double> nPrice = new ArrayList<>();
    for(Investment upInvest : newInvest){
      nPrice.add(upInvest.getPrice());
    }
      return nPrice;

  }




  /**
    * @param symbol it checks the Investment ArrayList to check for symbol using instansof.
    * @return true when stock symbol is found else return false.
    */

  public boolean checkStock(String symbol){


      String temp = new String();
      int flag =0;
      for(int i =0; i<newInvest.size(); i++){

        temp = newInvest.get(i).getSymbol();

        if(temp.equalsIgnoreCase(symbol)){
          if(newInvest.get(i) instanceof Stock){
          flag =1;
          }
        }
      }

      if(flag == 1){
        return true;
      }else{
        return false;
      }

    }

  /**
    * @param symbol it checks the Investment ArrayList to check for symbol using instanceof.
    * @return true when Mutual Fund symbol is found else return false.
    */

  public boolean checkMutual(String symbol){

        String temp = new String();
        int flag =0;
        for(int i =0; i<newInvest.size(); i++){

          temp = newInvest.get(i).getSymbol();

            if(temp.equalsIgnoreCase(symbol)){
              if(newInvest.get(i) instanceof MutualFund){
              flag =1;
              }
            }

          }

          if(flag == 1){
            return true;
          }else{
            return false;
          }


      }


  /**
    * @param symbol it checks the Investment ArrayList to check for symbol.
    * @return true when Investment symbol is found else return false.
    */
  public boolean checkInvestment(String symbol){

     String temp = new String();
        int flag =0;
        for(int i =0; i<newInvest.size(); i++){

          temp = newInvest.get(i).getSymbol();

            if(temp.equalsIgnoreCase(symbol)){

              flag =1;

            }

          }

          if(flag == 1){
            return true;
          }else{
            return false;
          }

  }


/**
  * @param s it is the symbol entered by the user to search in the ArrayList.
  * @param w it is the word entered by the user to search in the ArrayList.
  * @param lrange it is the range of price which user is intrested in knowing.
  * @param hrange it is the range of price which user is intrested in knowing.
  * @param searchText a variable which print to mesage area
  */

    public void searchInvestment(String s, String w, String lRange, String hRange, JTextArea searchText){


      boolean founds = false;
      double lowP=0;
      double highP=0;
      boolean matches;

      if(lRange.isEmpty()){
        lowP =0;
      }
      else{

        try{
          lowP = Double.parseDouble(lRange);
        }catch (NumberFormatException e) {
          searchText.setText("Invalid input for low price\n");
        }


      }

      if(hRange.isEmpty()){

        highP = Double.POSITIVE_INFINITY;

      }
      else{
        try{
          highP = Double.parseDouble(hRange);
        }
        catch(NumberFormatException e){
          searchText.setText("Invalid input for high price\n");
        }
      }

      if(highP < 0 || lowP < 0){

        searchText.setText("Price should be more than zero\n");


      }

      if(lowP > highP){


        searchText.setText("Low Price must be lower then High Price \n");

      }

      String[] newWord = w.toLowerCase().split(" ");
      if(!newWord[0].isEmpty()){

        ArrayList<Integer> rList = new ArrayList<Integer>();
        ArrayList<Integer> sList = new ArrayList<Integer>();

        if(newMap.get(newWord[0]) == null){
          searchText.setText(" No Investment was Found");
          return;
        }
        sList.addAll(newMap.get(newWord[0]));
        for(String wo: newWord){
          if(newMap.get(wo) == null){
            searchText.setText(" No Investment was Found");
            return;

          }
          for(int i : sList){
            if(!newMap.get(wo).contains(i)){
              rList.add(i);
            }
          }
        }

        for(int i : rList){
          if(sList.contains(i)){
            sList.remove((Object)i);
          }
        }

        for(int i : sList){
          Investment nowInvest = newInvest.get(i);
          if(s.equalsIgnoreCase(nowInvest.getSymbol())  ||  s.isEmpty()){
            matches = nowInvest.getPrice() >= lowP && nowInvest.getPrice() <= highP;


          }
          else{
            matches = false;
          }
          if(matches){
            searchText.append(nowInvest.toString()+ "\n");
          //  matches = false;
            founds = true;
          }
        }

      }
      else{

        for(Investment nowInvest : newInvest){

          if(s.equalsIgnoreCase(nowInvest.getSymbol()) || s.isEmpty()){
            matches = nowInvest.getPrice() >= lowP && nowInvest.getPrice() <= highP;
          }
          else{
            matches = false;
          }
          if(matches){
             searchText.append(nowInvest.toString() + "\n");
            // matches = false;
             founds = true;
          }
        }



      }

      if(!founds){
        searchText.setText(" No Investment was Found");
      }

  }

}
