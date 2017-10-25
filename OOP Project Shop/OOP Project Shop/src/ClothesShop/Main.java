/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import static ClothesShop.Main.stockControl;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sean
 */
public class Main
{

    static StockControl stockControl = new StockControl();
    static SaleStore saleStore = new SaleStore();
    static CashCreditControl cashCreditControl = new CashCreditControl();

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {

        String file = "Items.txt";
        String file1 = "Sale.txt";
        String file2 = "SaleItem.txt";

        System.out.println(file);
        System.out.println(file1);
        System.out.println(file2);

        stockControl.readFile(file);
        saleStore.readFileSale(file1);
        saleStore.readFileSaleItem(file2);

        boolean doNotExit = true;

        while (doNotExit) {
            int choice;

            System.out.println("\n--MAIN MENU-- ");

            System.out.println("\nStock Menu...............................................................<1> ");
            System.out.println("Sales Menu...............................................................<2> ");
            System.out.println("Exit Application.........................................................<3> ");

            System.out.println();
            System.out.print("Enter selection <1-3>: ");

            choice = getValidInt();

            switch (choice) {
                case 1:
                    itemsMenu();
                    break;
                case 2:
                    salesMenu();
                    break;

                case 3:
                    doNotExit = false;
                    break;

                default:
                    System.out.println("Invalid entry - try again!");
            }
        }
        System.out.println("\nLeaving program - Thank you!");
    }

    public static void itemsMenu()
    {

        String file = "Items.txt";

        boolean doNotExit = true;

        while (doNotExit) {

            int choice;

            System.out.println("\n--Stock MAIN MENU-- ");

            System.out.println("\n--Save to File--");
            System.out.println("Write Item list to File.........................................................<1> ");

            System.out.println("\n--Create / Update Item or display Item List--");
            System.out.println("Return list off all Items.......................................................<2> ");
            System.out.println("Create a new Item...............................................................<3> ");
            System.out.println("Update an Item's Quantity.......................................................<4> ");
            System.out.println("Update an Item's Price..........................................................<5> ");

            System.out.println("\n--Returning certain searches off the Array--");
            System.out.println("Return an item selected by an ID................................................<6> ");
            System.out.println("Return a list off Items containing a certain Manufacturer.......................<7> ");
            System.out.println("Return a list off Items containing a substring off the Title and Manufacturer...<8> ");
            System.out.println("Return a list off Items containing a certain Title..............................<9> ");
            System.out.println("Return a list off Items below a user defined Price..............................<10> ");
            System.out.println("Return a list off Items that has a Quantity off 0...............................<11> ");
            System.out.println("Return a list off Items between certain clothes Sizes...........................<12> ");
            System.out.println("Return a list off Items off a certain Colour....................................<13> ");

            System.out.println("\n--Sorting by Title and Id--");
            System.out.println("Sort all Items by the Title.....................................................<14> ");
            System.out.println("Sort all Items by the ID........................................................<15> ");

            System.out.println("\n--Exit Menu / Testing Methods--");
            System.out.println("Add test items..................................................................<16> ");
            System.out.println("Cloneable Item object Demo......................................................<17> ");
            System.out.println("Exit Items menu.................................................................<18> ");

            System.out.println();
            System.out.print("Enter selection <1-18>: ");

            choice = getValidInt();

            switch (choice) {

                case 1:
                    stockControl.saveToFile(file);
                    break;

                case 2:
                    List<Item> retList = stockControl.displayArrayList();
                    stockControl.displayArrayList(retList);
                    break;

                case 3:
                    stockControl.createNewItem();
                    break;

                case 4:
                    stockControl.updateQuantity();
                    break;

                case 5:
                    stockControl.updatePrice();
                    break;

                case 6:
                    List<Item> retListID = stockControl.getItemSelectedByID();
                    stockControl.displayArrayList(retListID);
                    break;

                case 7:
                    List<Item> retListManufacturer = stockControl.getItemsSelectedByManufacturer();
                    if (retListManufacturer.size() == 0) {
                        System.out.println("No Items found with selected Manufacturer" + "\n");
                    } else {
                        stockControl.displayArrayList(retListManufacturer);
                    }
                    break;

                case 8:
                    List<Item> retListSubString = stockControl.getItemsUsingSubString();
                    if (retListSubString.size() == 0) {
                        System.out.println("No Items found with keyword in Title or Manufacturer" + "\n");
                    } else {
                        stockControl.displayArrayList(retListSubString);
                    }
                    break;

                case 9:
                    List<Item> retListTitle = stockControl.getItemsSelectedByTitle();
                    if (retListTitle.size() == 0) {
                        System.out.println("No Items found with selected Title" + "\n");
                    } else {
                        stockControl.displayArrayList(retListTitle);
                    }
                    break;

                case 10:
                    List<Item> retListBelowUserDefinedPrice = stockControl.getItemsSelectedBelowUserDefinedPrice();
                    if (retListBelowUserDefinedPrice.size() == 0) {
                        System.out.println("No Items found below user defined Price" + "\n");
                    } else {
                        stockControl.displayArrayList(retListBelowUserDefinedPrice);
                    }
                    break;

                case 11:
                    List<Item> retListQuantityZero = stockControl.getItemsThatHaveQuantityOfZero();
                    if (retListQuantityZero.size() == 0) {
                        System.out.println("No Items found with Stock 0" + "\n");
                    } else {
                        stockControl.displayArrayList(retListQuantityZero);
                    }
                    break;

                case 12:
                    List<Item> retListClothesSize = stockControl.getItemsUsingClothesSizes();
                    stockControl.displayArrayList(retListClothesSize);
                    break;

                case 13:
                    List<Item> retListUsingColour = stockControl.getItemsUsingColour();
                    if (retListUsingColour.size() == 0) {
                        System.out.println("No Items found with the selected colour" + "\n");
                    } else {
                        stockControl.displayArrayList(retListUsingColour);
                    }
                    break;

                case 14:
                    stockControl.getItemsSortedByTitle();
                    break;

                case 15:
                    stockControl.getItemsSortedByID();
                    break;

                case 16:
                    stockControl.addTestItems();
                    break;

                case 17:
                    stockControl.cloneCertainItemID();
                    break;

                case 18:
                    doNotExit = false;
                    break;

                default:
                    System.out.println("Invalid entry - try again!");
            }
        }
        stockControl.endApplication(file);
    }

    public static void salesMenu()
    {

        String file1 = "Sale.txt";
        String file2 = "SaleItem.txt";
        boolean doNotExit = true;

        while (doNotExit) {

            int choice;

            System.out.println("\n--Sale MAIN MENU-- ");

            System.out.println("\n--Save to File--");
            System.out.println("Write Sale list to File..................................... ..................<1> ");

            System.out.println("\n--Create / Edit / Delete Sale and print details off Sale--");
            System.out.println("Create a new Sale...............................................................<2> ");
            System.out.println("Return a list off the Items that can be added to a Sale.........................<3> ");
            System.out.println("Add an Item to a certain Sale......................................... .........<4> ");
            System.out.println("Return a list off all the Sales.................................................<5> ");
            System.out.println("Update a Sale's Name............................................................<6> ");
            System.out.println("Update a Sale's Phone Number....................................................<7> ");
            System.out.println("Delete a Sale...................................................................<8> ");
            System.out.println("Delete an item from a Sale......................................................<9> ");
            System.out.println("Return the details off a certain Sale...........................................<10> ");
            System.out.println("Return a list off Sales containg a certain Customer.............................<11> ");
            System.out.println("Return the Sale's between two user-defined Dates................................<12> ");
            System.out.println("Print a Receipt for a certain Sale..............................................<13> ");

            System.out.println("\n--Exit Menu / Testing Methods");
            System.out.println("Add test Sale List..............................................................<14> ");
            System.out.println("Add saleItems test List.........................................................<15> ");
            System.out.println("Cloneable Sale object Demo......................................................<16> ");

            System.out.println("\n-- Cash / Credit Sales / Exit Menu--");
            System.out.println("Add Cash Credit Sales to the ArrayList.............Must Always Add..............<17> ");
            System.out.println("Return a list off all Sales Cash and Credit.....................................<18> ");
            System.out.println("Return a list off Credit Sales..................................................<19> ");
            System.out.println("Return a list off off total amount off all Sales................................<20> ");
            System.out.println("Return a list off total Balance Due.............................................<21> ");
            System.out.println("Exit Sale menu..................................................................<22> ");

            System.out.println();
            System.out.print("Enter selection <1-22>: ");

            choice = getValidInt();

            switch (choice) {

                case 1:
                    saleStore.saveToFile(file1, file2);
                    break;

                case 2:
                    saleStore.createNewSale();
                    break;

                case 3://3 on List
                    List<Item> retList = stockControl.displayArrayList();
                    stockControl.displayArrayList(retList);
                    break;

                case 4:
                    saleStore.addSaleItem();
                    break;

                case 5:
                    List<Sale> retListSale = saleStore.getDetailsOfAllSales();
                    saleStore.displayArrayList(retListSale);
                    break;

                case 6:
                    saleStore.updateSaleName();
                    break;

                case 7:
                    saleStore.updateSalePhoneNumber();
                    break;

                case 8:
                    saleStore.deleteSale();
                    break;

                case 9:
                    saleStore.deleteItemFromSale();
                    break;

                case 10:
                    List<Sale> retListCertainSale = saleStore.getDetailsOfCertainSale();
                    saleStore.displayArrayList(retListCertainSale);
                    break;

                case 11:
                    List<Sale> retListSaleByCustomer = saleStore.getSalesSelectedByCustomer();
                    if (retListSaleByCustomer.size() == 0) {
                        System.out.println("No Sales found with selected Customer" + "\n");
                    } else {
                        saleStore.displayArrayList(retListSaleByCustomer);
                    }
                    break;

                case 12:
                    saleStore.getSalesTwoUserDefinedDates();
                    break;

                case 13:
                    saleStore.printReceipt();
                    break;

                case 14:
                    saleStore.addSaleTest();
                    break;

                case 15://15 on List
                    saleStore.addItemToSale(1, 1, 2);
                    saleStore.addItemToSale(2, 3, 2);
                    saleStore.addItemToSale(2, 4, 4);
                    saleStore.addItemToSale(4, 1, 2);
                    saleStore.addItemToSale(4, 3, 2);
                    saleStore.addItemToSale(4, 4, 4);
                    break;

                case 16:
                    saleStore.cloneCertainSaleID();

                case 17:
                    cashCreditControl.addTestCashCreditSales();
                    break;

                case 18:
                    List<CashCreditSale> retListCCS = cashCreditControl.getCashCreditSale();
                    cashCreditControl.displayArrayList(retListCCS);
                    break;

                case 19:
                    List<CashCreditSale> retListCS = cashCreditControl.getCreditSale();
                    cashCreditControl.displayArrayList(retListCS);
                    break;

                case 20:
                    cashCreditControl.getTotalSales();
                    break;

                case 21:
                    cashCreditControl.getTotalBalance();
                    break;

                case 22:
                    doNotExit = false;
                    break;

                default:
                    System.out.println("Invalid entry - try again!");
            }
        }
        saleStore.endApplication(file1, file2);
    }

    //Validaing Int
    public static int getValidInt()
    {
        while (!keyboard.hasNextInt()) {
            keyboard.nextLine();
            System.out.print("Invalid entry; Please try again: ");
        }
        int i = keyboard.nextInt();
        keyboard.nextLine();
        return i;
    }

    //Validating the Price
    public static double getValidDouble()
    {
        while (!keyboard.hasNextDouble()) {
            keyboard.nextLine();
            System.out.print("Invalid entry; Please try again: ");
        }
        double i = keyboard.nextDouble();
        keyboard.nextLine();
        return i;
    }
}
