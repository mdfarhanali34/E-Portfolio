# E-Portfolio

Assignment: eportfolio

Name: Mohammad Farhan Ali

Email: mali34@uoguelph.ca

*********************************

About the Software:
1) Gui.java contains the void main, Portfolio.java contains the backend funcnality of the software, Investment is a abstarct parent class where stock and mutual fund is their child class.
2) The Software implements counstuctor error handling.
3) The program uses the concept of inheritance where Investment is the abstract super class, MutualFund is the sub class of Investment, Stock is the sub class of Investment.
4) It allows user to buy , sell, update price, compute gain, and search using hashmap in the List for two type of investments Stocks and Mutual Fund stored in single arraylist.
5) Its a user driven program implemented using GUI deshin.
6) Gui.java contains the main program, Portfolio.java contains function for implementing both type of investments.
7) The package is called eportfolio.
8) A document about how it has been tested is provided below
9) A file called output.txt will be genrated at the end of execution of program which writes the content inside ArrayList into output.txt file.
10) It accepts a filename from comand line input but it always at the end of the program genrates a output.txt file and write the content of arraylist inside it.


******************************************


TEST PLAN:  


How is it tested using Test.java file:

1) User have to manually test the Software, I testetd it in the following way:
2) I passed a comand line argument files name. It works fine with or without passing the filename.
3) The software consist of a menue in a drop down menue format.
4) I added a stock and mutual fund using the buy investment interface.
5) I then update the stock using update interface on the software
6) I then used sell interface to sell the investment.
7) I then clicked on the gain button under the drop down menue and it auotmatically calculate the independent and total gain and print to non editable text area.
8) I then tested the searchInvestment() method and its graphical interface.
9) Case 1: Searching with symbol
10) Case 2: Searching with name
11) Case 3: Searching with lower range as (10).
12) Case 4: Searching with range as (1-10) and name of an investment.
13) As I select quit from the drop down menue it quits the GUI and write content inside output.txt.


*******************************************************************************************************************


Some known Assumption and Limitation:

1) The program is not able to accept a file which is empty (with no content inside). It either acepts a file name from comand line which contains data similar to demo.txt provided or no filename provided at all at comand line. In any of these cases it always genrate a output.txt file at the end of execution of main.

2) The getGain interface works fine but it start to show gain value after we update the value once using update interface of the software.


******************************************


******************************************

How to compile and run the program:
1) Compile with javac.
2) Run with java.

Note: If the file name is provided as comand line input the program expect it to have some data inside it. Empty files as comand line input is not acepted. A file called output.txt will be genrated at the end of execution.
***************************************************************************************************************************



Possible Improvment:

1) The menue of the program could have been desghined more neetly.
2) The load method could have been made more robust considring all posible scenarios.
3) The gain Investment backend could have been improved.
