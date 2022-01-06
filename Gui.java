package eportfolio;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;



public class Gui extends JFrame
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    int num;
    ArrayList<String> upSymb = new ArrayList<String>();
    ArrayList<String> upname = new ArrayList<String>();
    ArrayList<Double> upprice = new ArrayList<Double>();



    public Gui(String filename){
     super("Investment Portfolio");
     Portfolio newPortfolio = new Portfolio();
     setSize(WIDTH,HEIGHT);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

     this.num =0;
     newPortfolio.load(filename);
    JMenu newMenu = new JMenu("Commands");

    JMenuItem buy = new JMenuItem("Buy Investment");
    newMenu.add(buy);

    JMenuItem sell = new JMenuItem("Sell Investment");
    newMenu.add(sell);

    JMenuItem update = new JMenuItem("Update Investment");
    newMenu.add(update);

    JMenuItem gain = new JMenuItem("Get Gain");
    newMenu.add(gain);

    JMenuItem search = new JMenuItem("search Investment");
    newMenu.add(search);

    JMenuItem quit = new JMenuItem("quit Investment");
    newMenu.add(quit);

    JMenuBar newBar = new JMenuBar();

    newBar.add(newMenu);

    setJMenuBar(newBar);


    //User landing page

    JPanel comand = new JPanel();

    comand.setLayout(new BorderLayout());

    //comand.setBackground(Color.WHITE);


    JTextArea newIntro = new JTextArea();
    newIntro.setEnabled(false);
    newIntro.setFont(new Font("Serif",Font.BOLD,20));
    newIntro.setForeground(Color.BLACK);
    newIntro.setEnabled(false);
    newIntro.setText("Welcome to ePortfolio. \n\n\n\n\n\n\n Choose a command from the Commands menu to buy or sell an Investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program. \n\n\n");
    newIntro.setLineWrap(true);
    comand.add(newIntro);


    //Buy page


    JPanel bigB = new JPanel();
    bigB.setLayout(new BorderLayout());

    JPanel topB = new JPanel();
    topB.setLayout(new GridLayout(1,2));

    JPanel leftB = new JPanel();
    leftB.setLayout(new GridLayout(6,1));


    JPanel rightB = new JPanel();
    rightB.setLayout(new GridLayout(2,1));

    topB.add(leftB);
    topB.add(rightB);

    //adding welcome label
    JLabel welcomeLabel = new JLabel("Buying an investment:");
    leftB.add(welcomeLabel);

    //adding comboBox for type
    JPanel typeCombo = new JPanel();
    typeCombo.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel comboLabel = new JLabel("Type");

    String[] comboBuy = {"Stock", "Mutual Fund"};
    JComboBox<String> buyList = new JComboBox<>(comboBuy);
    typeCombo.add(comboLabel);
    typeCombo.add(buyList);
    leftB.add(typeCombo);

    //adding symbol input

    JPanel bSym = new JPanel();
    bSym.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel buyLabel = new JLabel("Symbol");

    JTextField buyText = new JTextField(15);

    bSym.add(buyLabel);
    bSym.add(buyText);

    leftB.add(bSym);

    //adding Name

    JPanel addName = new JPanel();

    addName.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel nameLabel = new JLabel("Name");

    JTextField nameText = new JTextField(25);

    addName.add(nameLabel);
    addName.add(nameText);

    leftB.add(addName);

    //adding quantity

    JPanel addQuantity = new JPanel();

    addQuantity.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel quantityLabel = new JLabel("Quantity");

    JTextField quantityText = new JTextField(10);

    addQuantity.add(quantityLabel);
    addQuantity.add(quantityText);

    leftB.add(addQuantity);

    //adding price

    JPanel addPrice = new JPanel();

    addPrice.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel priceLabel = new JLabel("Price");

    JTextField priceText = new JTextField(10);

    addPrice.add(priceLabel);
    addPrice.add(priceText);

    leftB.add(addPrice);

    //Right side buttons

    JButton buyButton = new JButton("Buy");
    //buyButton.setPreferredSize(new Dimension(1, 1));
    JButton resetButton = new JButton("Reset");

    rightB.add(resetButton);
    rightB.add(buyButton);



    //Creating bottom mesage pannel

    JPanel bottomBuy = new JPanel();
    bottomBuy.setLayout(new BorderLayout());
    JLabel bottomMesageBuy = new JLabel("Messages");

    bottomBuy.add(bottomMesageBuy, BorderLayout.NORTH);

    JTextArea buyMesageAreaNew = new JTextArea();

    buyMesageAreaNew.setEditable(false);

    JScrollPane scrollMesageArea = new JScrollPane(buyMesageAreaNew);

    scrollMesageArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    scrollMesageArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    bottomBuy.add(scrollMesageArea, BorderLayout.CENTER);

    //adding to master pane

    bigB.add(topB,  BorderLayout.NORTH);
    bigB.add(bottomBuy, BorderLayout.CENTER);



    //adding action listner



    buyButton.addActionListener((ActionEvent e) -> {

        String temp1 = (String)buyList.getSelectedItem();

        if(temp1.equals("Stock")){

            String symbol = buyText.getText();

            if(symbol.equals("")){
              buyMesageAreaNew.setText("Invalid input");
              return;
            }

            if(!(newPortfolio.checkMutual(symbol))){

            if(newPortfolio.checkStock(symbol)){


              int newQuan;

              try{

                newQuan = Integer.parseInt(quantityText.getText());

              }catch(NumberFormatException ea){
                buyMesageAreaNew.setText("Invalid input");
                return;
              }


             double newPrice;

             try{
             newPrice = Double.parseDouble(priceText.getText());

           }catch(NumberFormatException ea){

              buyMesageAreaNew.setText("Invalid input");
                return;
            }

            newPortfolio.existingStock(symbol, newQuan, newPrice, buyMesageAreaNew);


            }else{
                String nam = nameText.getText();

                if(nam.equals("")){
                  buyMesageAreaNew.setText("Invalid input");
                  return;
                }

                 int quan;

                 try{

                  quan = Integer.parseInt(quantityText.getText());
                }catch(NumberFormatException ea){

                  buyMesageAreaNew.setText("Invalid input");
                  return;
                }

                  double pri;

                  try{

                  pri  = Double.parseDouble(priceText.getText());
                }catch(NumberFormatException ea){

                  buyMesageAreaNew.setText("Invalid input");
                  return;
                }
                  double bookStock = pri*quan - 9.99;

                newPortfolio.newStock(symbol, nam, quan, pri,bookStock, buyMesageAreaNew);

            }



            }else{
            buyMesageAreaNew.setText("This symbol already exist in other investment");
            }



        }else if(temp1.equals("Mutual Fund")){


             String symbol = buyText.getText();

             if(symbol.equals("")){
               buyMesageAreaNew.setText("Invalid input");
               return;
             }

              if(!(newPortfolio.checkStock(symbol))){
                  if(newPortfolio.checkMutual(symbol)){

                    int newQuan;

                    try{

                      newQuan = Integer.parseInt(quantityText.getText());

                    }catch(NumberFormatException ea){
                      buyMesageAreaNew.setText("Invalid input");
                      return;
                    }


                    double newPrice;

                    try{
                    newPrice = Double.parseDouble(priceText.getText());

                  }catch(NumberFormatException ea){

                     buyMesageAreaNew.setText("Invalid input");
                       return;
                   }

                    newPortfolio.existingFund(symbol, newQuan, newPrice, buyMesageAreaNew);

                  }else{

                    String nam = nameText.getText();

                    if(nam.equals("")){
                      buyMesageAreaNew.setText("Invalid input");
                      return;
                    }

                    int quan;

                    try{

                     quan = Integer.parseInt(quantityText.getText());
                   }catch(NumberFormatException ea){

                     buyMesageAreaNew.setText("Invalid input");
                     return;
                   }

                   double pri;

                   try{

                   pri  = Double.parseDouble(priceText.getText());
                 }catch(NumberFormatException ea){

                   buyMesageAreaNew.setText("Invalid input");
                   return;
                 }

                    double bookStock = pri*quan ;
                    newPortfolio.newMutual(symbol, nam, quan, pri, bookStock, buyMesageAreaNew);
                  }
                }else{
                  buyMesageAreaNew.setText("This symbol already exist in other investment");
                }

              }


    });

    resetButton.addActionListener((ActionEvent e) -> {

        buyText.setText("");
        quantityText.setText("");
        priceText.setText("");
        nameText.setText("");
        buyMesageAreaNew.setText("");


    });


    // Sell page

    JPanel bigS = new JPanel();
    bigS.setLayout(new BorderLayout());


    JPanel topS = new JPanel();
    topS.setLayout(new GridLayout(1,2));

    JPanel leftS = new JPanel();
    leftS.setLayout(new GridLayout(4,1));


    JPanel rightS = new JPanel();
    rightS.setLayout(new GridLayout(2,1));

    topS.add(leftS);
    topS.add(rightS);


    //adding welcome label
    JLabel welcomeLabelSell = new JLabel("Selling an investment:");
    leftS.add(welcomeLabelSell);




    //adding symbol input

    JPanel sSym = new JPanel();
    sSym.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel sellLabel = new JLabel("Symbol");

    JTextField sellText = new JTextField(25);

    sSym.add(sellLabel);
    sSym.add(sellText);

    leftS.add(sSym);

    //adding quantity

    JPanel sellQuantity = new JPanel();

    sellQuantity.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel quantityLabelSell = new JLabel("Quantity");

    JTextField quantityTextSell = new JTextField(10);

    sellQuantity.add(quantityLabelSell);
    sellQuantity.add(quantityTextSell);

    leftS.add(sellQuantity);


    //adding price

    JPanel addPriceSell = new JPanel();

    addPriceSell.setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel priceLabelSell = new JLabel("Price");

    JTextField priceTextSell = new JTextField(10);

    addPriceSell.add(priceLabelSell);
    addPriceSell.add(priceTextSell);

    leftS.add(addPriceSell);

    //Right side buttons

    JButton sellButton = new JButton("Sell");
    JButton resetButtonSell = new JButton("Reset");

    rightS.add(resetButtonSell);
    rightS.add(sellButton);


    //Creating bottom mesage pannel

    JPanel bottomSell = new JPanel();
    bottomSell.setLayout(new BorderLayout());
    JLabel bottomMesageSell = new JLabel("Messages");

    bottomSell.add(bottomMesageSell, BorderLayout.NORTH);

    JTextArea sellMesageArea = new JTextArea();

    sellMesageArea.setEditable(false);

    JScrollPane scrollMesageSell = new JScrollPane(sellMesageArea);

    scrollMesageSell.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    scrollMesageSell.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    bottomSell.add(scrollMesageSell, BorderLayout.CENTER);

    //adding to master pane

    bigS.add(topS,  BorderLayout.NORTH);
    bigS.add(bottomSell, BorderLayout.CENTER);



    resetButtonSell.addActionListener((ActionEvent e) -> {

        sellText.setText("");
        quantityTextSell.setText("");
        priceTextSell.setText("");
        sellMesageArea.setText("");



    });

    sellButton.addActionListener((ActionEvent e) -> {

        int flag1 =0, flag2=0;

        double hoody = 0.0;

        String symbol = sellText.getText();


             int sellit = Integer.parseInt(quantityTextSell.getText());
             double price = Double.parseDouble(priceTextSell.getText());



            if(newPortfolio.checkInvestment(symbol)){

              hoody = newPortfolio.sellInvestment(symbol, sellit, price,sellMesageArea);
              flag1 =1;
            }else{
              sellMesageArea.setText("Symbol not found in Investment list");
            }

 });

  //update

  JPanel bigUpdate = new JPanel();
  bigUpdate.setLayout(new BorderLayout());


  JPanel topUpdate = new JPanel();
  topUpdate.setLayout(new GridLayout(1,2));

  JPanel leftUpdate = new JPanel();
  leftUpdate.setLayout(new GridLayout(4,1));

  JPanel rightUpdate = new JPanel();
  rightUpdate.setLayout(new GridLayout(3,1));

  JPanel bottomUpdate = new JPanel();
  bottomUpdate.setLayout(new BorderLayout());

  topUpdate.add(leftUpdate);
  topUpdate.add(rightUpdate);


  //adding welcome label
  JLabel welcomeLabelUpdate = new JLabel("Updating an investment:");
  leftUpdate.add(welcomeLabelUpdate);


  //adding symbol input

  JPanel updateSymbol = new JPanel();
  updateSymbol.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel updateLabel = new JLabel("Symbol");

  JTextField updateText = new JTextField(15);

  updateSymbol.add(updateLabel);
  updateSymbol.add(updateText);

  leftUpdate.add(updateSymbol);


  //adding Name

  JPanel updateName = new JPanel();

  updateName.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel updateNatext= new JLabel("Name");

  JTextField updateTextLabel = new JTextField(25);

  updateName.add(updateNatext);
  updateName.add(updateTextLabel);

  leftUpdate.add(updateName);



  //adding price

  JPanel addPriceUpdate = new JPanel();

  addPriceUpdate.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel priceLabelUpdate = new JLabel("Price");

  JTextField priceTextUpdate = new JTextField(10);

  addPriceUpdate.add(priceLabelUpdate);
  addPriceUpdate.add(priceTextUpdate);

  leftUpdate.add(addPriceUpdate);

  //Right side buttons

  JButton updatePrev = new JButton("Prev");
  JButton updateNext = new JButton("Next");
  JButton updateSave = new JButton("Save");



  rightUpdate.add(updatePrev);
  rightUpdate.add(updateNext);
  rightUpdate.add(updateSave);

  //Creating bottom mesage pannel


  JLabel bottomMesageUpdate = new JLabel("Messages");

  bottomUpdate.add(bottomMesageUpdate, BorderLayout.NORTH);

  JTextArea updateMesageArea = new JTextArea();

  updateMesageArea.setEditable(false);

  JScrollPane scrollMesageUpdate = new JScrollPane(updateMesageArea);

  scrollMesageUpdate.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

  scrollMesageUpdate.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

  bottomUpdate.add(scrollMesageUpdate, BorderLayout.CENTER);


  //adding to master pane

  bigUpdate.add(topUpdate,  BorderLayout.NORTH);
  bigUpdate.add(bottomUpdate, BorderLayout.CENTER);

  updateSave.addActionListener((ActionEvent e) -> {
    double temp = Double.parseDouble(priceTextUpdate.getText());
    newPortfolio.updateS(num,temp,updateMesageArea);

    upprice = newPortfolio.getPricesNew();



  });

  updateNext.addActionListener((ActionEvent e) -> {

    num++;
    updateText.setText(upSymb.get(num));
    updateTextLabel.setText(upname.get(num));
    priceTextUpdate.setText(upprice.get(num).toString());
    updateMesageArea.setText("");

    if(num == upSymb.size() - 1){
      updateNext.setEnabled(false);
    }

    updatePrev.setEnabled(true);


  });

  updatePrev.addActionListener((ActionEvent e) -> {
    num--;
    updateText.setText(upSymb.get(num));
    updateTextLabel.setText(upname.get(num));
    priceTextUpdate.setText(upprice.get(num).toString());
    updateMesageArea.setText("");
    if(num ==0){
      updatePrev.setEnabled(false);
    }

    updateNext.setEnabled(true);


  });

  //working on GetGain

  JPanel bigGain = new JPanel();
  bigGain.setLayout(new BorderLayout());


  JPanel topGain = new JPanel();
  topGain.setLayout(new GridLayout(2,1));

  JPanel bottomGain = new JPanel();
  bottomGain.setLayout(new BorderLayout());

  JLabel gainlabel = new JLabel("Getting total gain");
  topGain.add(gainlabel);

  JPanel getGain = new JPanel();
  getGain.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel gainName = new JLabel("Total Gain");

  JTextField gainTextFeild = new JTextField(30);
  gainTextFeild.setEditable(false);
  getGain.add(gainName);
  getGain.add(gainTextFeild);

  topGain.add(getGain);


  JLabel bottomMesageGain = new JLabel("Indivisual Gain");

  bottomGain.add(bottomMesageGain, BorderLayout.NORTH);

  JTextArea gainMesageArea = new JTextArea();

  gainMesageArea.setEditable(false);

  JScrollPane scrollMesageGain = new JScrollPane(gainMesageArea);

  scrollMesageGain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

  scrollMesageGain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

  bottomGain.add(scrollMesageGain, BorderLayout.CENTER);

  bigGain.add(topGain,  BorderLayout.NORTH);
  bigGain.add(bottomGain, BorderLayout.CENTER);



  //working on search

  JPanel bigSearch = new JPanel();
  bigSearch.setLayout(new BorderLayout());

  JPanel topSearch = new JPanel();
  topSearch.setLayout(new GridLayout(1,2));

  JPanel leftSearch = new JPanel();
  leftSearch.setLayout(new GridLayout(5,1));


  JPanel rightSearch = new JPanel();
  rightSearch.setLayout(new GridLayout(2,1));

  topSearch.add(leftSearch);
  topSearch.add(rightSearch);

  //adding welcome label
  JLabel welcomeLabelSearch = new JLabel("Searching investment:");
  leftSearch.add(welcomeLabelSearch);

  //adding symbol input

  JPanel searchSym = new JPanel();
  searchSym.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel searchLabel = new JLabel("Symbol");

  JTextField searchText = new JTextField(15);

  searchSym.add(searchLabel);
  searchSym.add(searchText);

  leftSearch.add(searchSym);

  //adding Name

  JPanel searchName = new JPanel();

  searchName.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel nameLabelsearch = new JLabel("Name (Keywords)");

  JTextField nameTextsearch = new JTextField(25);

  searchName.add(nameLabelsearch);
  searchName.add(nameTextsearch);

  leftSearch.add(searchName);


  //adding low price

  JPanel searchLowPrice = new JPanel();

  searchLowPrice.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel searchLabelLow = new JLabel("Low Price");

  JTextField searchFeildLow = new JTextField(10);

  searchLowPrice.add(searchLabelLow);
  searchLowPrice.add(searchFeildLow);

  leftSearch.add(searchLowPrice);

  //adding high price

  JPanel searchHighPrice = new JPanel();

  searchHighPrice.setLayout(new FlowLayout(FlowLayout.LEFT));

  JLabel searchLabelHigh = new JLabel("High Price");

  JTextField searchFeildHigh = new JTextField(10);

  searchHighPrice.add(searchLabelHigh);
  searchHighPrice.add(searchFeildHigh);

  leftSearch.add(searchHighPrice);

  //Right side buttons

  JButton searchButton = new JButton("Search");
  JButton resetButtonSearch = new JButton("Reset");

  rightSearch.add(resetButtonSearch);
  rightSearch.add(searchButton);



  //Creating bottom mesage pannel

  JPanel bottomSearch = new JPanel();
  bottomSearch.setLayout(new BorderLayout());
  JLabel bottomMesagesearch = new JLabel("Search results");

  bottomSearch.add(bottomMesagesearch, BorderLayout.NORTH);

  JTextArea searchMessageArea = new JTextArea();
  searchMessageArea.setBackground(Color.BLACK);
  searchMessageArea.setEnabled(false);

  JScrollPane scrollMesagesearch = new JScrollPane(searchMessageArea);

  scrollMesagesearch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

  scrollMesagesearch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

  bottomSearch.add(scrollMesagesearch, BorderLayout.CENTER);

  //adding to master pane

  bigSearch.add(topSearch,  BorderLayout.NORTH);
  bigSearch.add(bottomSearch, BorderLayout.CENTER);


  searchButton.addActionListener((ActionEvent e) -> {

      searchMessageArea.setText("");
   newPortfolio.searchInvestment(searchText.getText(), nameTextsearch.getText(), searchFeildLow.getText(), searchFeildHigh.getText(), searchMessageArea);


  });

  resetButtonSearch.addActionListener((ActionEvent e) -> {

    searchText.setText("");
    nameTextsearch.setText("");
    searchFeildLow.setText("");
    searchFeildHigh.setText("");
    searchMessageArea.setText("");


  });




    //adding action listner

    sell.addActionListener((ActionEvent e) -> {

        bigS.setVisible(true);
        bigB.setVisible(false);
        bigUpdate.setVisible(false);
        bigGain.setVisible(false);
        comand.setVisible(false);
        bigSearch.setVisible(false);
        sellMesageArea.setText("");

    });
    buy.addActionListener((ActionEvent e) -> {
        bigB.setVisible(true);
        bigS.setVisible(false);
        bigUpdate.setVisible(false);
        bigGain.setVisible(false);
        comand.setVisible(false);
        bigSearch.setVisible(false);
        buyMesageAreaNew.setText("");

    });

    update.addActionListener((ActionEvent e) -> {
      bigB.setVisible(false);
      bigS.setVisible(false);
      bigUpdate.setVisible(true);
      bigGain.setVisible(false);
      bigSearch.setVisible(false);
      comand.setVisible(false);

      updateTextLabel.setEnabled(false);
      updateText.setEnabled(false);

      upSymb = newPortfolio.getSymbolNew();
      upname = newPortfolio.getNameNew();
      upprice = newPortfolio.getPricesNew();
      num =0;

      if(upSymb.size() >0){

        updateText.setText(upSymb.get(0));
        updateTextLabel.setText(upname.get(0));
        priceTextUpdate.setText(upprice.get(0).toString());
        updateMesageArea.setText("");
        updatePrev.setEnabled(false);
        updateNext.setEnabled(true);
        if(upSymb.size() == 1){
          updateNext.setEnabled(false);
        }
      }else{
        updateMesageArea.setText("No investment found");
        updateTextLabel.setText("");
        updateText.setText("");
        priceTextUpdate.setText("");
        updatePrev.setEnabled(false);
        updateNext.setEnabled(false);


      }
    });

    gain.addActionListener((ActionEvent e) -> {

      bigB.setVisible(false);
      bigS.setVisible(false);
      bigUpdate.setVisible(false);
      bigGain.setVisible(true);
      bigSearch.setVisible(false);
      comand.setVisible(false);
      gainMesageArea.setText("");
      double tempGain = newPortfolio.gain(gainMesageArea);
      gainTextFeild.setText("" + tempGain);


      gainTextFeild.setEnabled(false);

    });

    search.addActionListener((ActionEvent e) ->{

      bigB.setVisible(false);
      bigS.setVisible(false);
      bigUpdate.setVisible(false);
      bigGain.setVisible(false);
      bigSearch.setVisible(true);
      comand.setVisible(false);

      searchText.setText("");
      nameTextsearch.setText("");
      searchFeildLow.setText("");
      searchFeildHigh.setText("");
      searchMessageArea.setText("");


    });

    quit.addActionListener((ActionEvent e) ->{

      newPortfolio.write("output.txt");
      System.exit(0);

    });





    this.add(bigB);
    this.add(bigS);
    this.add(bigUpdate);
    this.add(bigGain);
    this.add(bigSearch);
    this.add(comand);
    comand.setVisible(true);
    bigS.setVisible(false);
    bigB.setVisible(false);
    bigUpdate.setVisible(false);
    bigGain.setVisible(false);
    bigSearch.setVisible(false);






    }

    /**
      * @param args the command line arguments
      */
  public static void main(String[] args){

    String hold[] = {};
    Gui test;
    if(Arrays.equals(args, hold)){
      test = new Gui("");
    }
    else{
      test = new Gui(args[0]);
    }

    test.setVisible(true);
  }


}
