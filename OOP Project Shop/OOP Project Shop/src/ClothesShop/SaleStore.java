/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import static ClothesShop.Main.keyboard;
import static ClothesShop.Main.stockControl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Sean
 */
public class SaleStore
{

    ArrayList<Sale> saleList;

    public SaleStore()
    {
        saleList = new ArrayList<>();
    }

    public void readFileSale(String fName)
    {
        try {
            String line;
            Scanner fin = new Scanner(new File(fName));
            while (fin.hasNextLine()) {
                line = fin.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                int saleID = Integer.parseInt(st.nextToken());
                String customerName = st.nextToken();
                String phoneNumber = st.nextToken();
                String saleDateIn = st.nextToken();
                String totalIn = st.nextToken();
                if (!saleList.contains(new Sale(0, customerName, phoneNumber, saleDateIn, totalIn))) {
                    saleList.add(new Sale(saleID, customerName, phoneNumber, saleDateIn, totalIn));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void readFileSaleItem(String fName)
    {
        try {
            String line;
            Scanner fin = new Scanner(new File(fName));
            while (fin.hasNextLine()) {
                line = fin.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                int saleID = Integer.parseInt(st.nextToken());
                int itemID = Integer.parseInt(st.nextToken());
                String title = st.nextToken();
                int quantity = Integer.parseInt(st.nextToken());
                Double price = Double.parseDouble(st.nextToken());

                addFileItemToSale(saleID, itemID, title, quantity, price);

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void displayArrayList(List<Sale> sales)
    {
        if (sales.size() == 0) {
            System.out.println("Nothing to display");
        } else {
            for (Sale s : sales) {
                System.out.println(s.toCSVString());
                for (SaleItem si : s.getSaleItems()) {

                    System.out.println("\t" + si.toCSVString(s.getSaleID()));
                }
            }
        }
    }

    //1 on List
    public void saveToFile(String saleFN, String saleItemFN)
    {
        System.out.println("Writing Sale list to file");

        PrintWriter outs = null;
        PrintWriter outsi = null;

        try {
            outs = new PrintWriter(new FileWriter(saleFN, false));
            outsi = new PrintWriter(new FileWriter(saleItemFN, false));

            for (Sale s : saleList) {
                outs.println(s.toCSVString());
                for (SaleItem si : s.getSaleItems()) {

                    outsi.println(si.toCSVString(s.getSaleID()));
                }

            }

            System.out.println("Sale List written to file.");
            System.out.println("Leaving program - Thank you!");
            System.out.println();

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            if (outs != null) {
                outs.close();
            }
            if (outsi != null) {
                outsi.close();
            }

        }
    }

    //2 on list
    public void createNewSale()
    {
        System.out.print("Please enter the Customer's name: ");
        String customer = keyboard.nextLine();
        System.out.print("Please enter the Customer's phone numnber: ");
        String phoneNumber = keyboard.nextLine();
        String saleDateIn = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        String totalIn = "0.0";

        saleList.add(new Sale(-1, customer, phoneNumber, saleDateIn, totalIn));

        System.out.println("*******************************");
        System.out.println("***  Sale added succesfully ***");
        System.out.println("*******************************");
    }

    //4 on List
    public void addSaleItem()
    {
        System.out.print("Please enter your saleID: ");
        int saleID = Main.getValidInt();
        System.out.print("Please enter your itemID: ");
        int itemID = Main.getValidInt();
        System.out.print("Please enter your quantity: ");
        int quantity = Main.getValidInt();
        addItemToSale(saleID, itemID, quantity);

        System.out.println("*******************************");
        System.out.println("***  Item added succesfully ***");
        System.out.println("*******************************");
    }

    //5 0n list
    public List<Sale> getDetailsOfAllSales()
    {
        return saleList;
    }

    //6 on List
    public void updateSaleName()
    {
        System.out.print("Please enter a SaleID to update the Sale Name for: ");
        int saleID = Main.getValidInt();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for saleID selected " + saleID);
        } else {
            System.out.println(sale1.toCSVString());

            System.out.print("\nPlease enter your new Name: ");
            String name = keyboard.nextLine();
            System.out.println();
            sale1.setCustomerName(name);

            if (name == sale1.getCustomerName()) {
                System.out.println("SaleID " + saleID + "; name was updated successfully");
            } else {
                System.out.println("SaleID " + saleID + "; name was not updaetd successfully");
            }
        }
    }

    //7 on List
    public void updateSalePhoneNumber()
    {
        System.out.print("Please enter a SaleID to update the Sale Phone Number for: ");
        int saleID = Main.getValidInt();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for saleID selected " + saleID);
        } else {
            System.out.println(sale1.toCSVString());

            System.out.print("\nPlease enter your new Phone Number: ");
            String phoneNumber = keyboard.nextLine();
            System.out.println();
            sale1.setPhoneNumber(phoneNumber);

            if (phoneNumber == sale1.getPhoneNumber()) {
                System.out.println("SaleID " + saleID + ": phone number was updated successfully");
            } else {
                System.out.println("SaleID " + saleID + ": phone number was not updaetd successfully");
            }
        }
    }

    //8 on List
    public void deleteSale()
    {
        System.out.print("Please enter a SaleID to delete the Sale for: ");
        int saleID = Main.getValidInt();
        System.out.println();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for saleID selected " + saleID);
        } else {
            System.out.println(sale1.toCSVString());

            System.out.print("Is this the Sale you wish to delete? YES.........<1> / NO.........<2>: ");
            int choice = Main.getValidInt();

            if (choice == 1) {
                saleList.remove(sale1);
                System.out.println("\nSaleID " + saleID + "; was removed successfully");
            } else {
                System.out.println("\nSaleID " + saleID + "; was not removed");
            }
        }
    }

    public void deleteItemFromSale()
    {
        ArrayList<SaleItem> lclItems = new ArrayList();

        System.out.print("Please enter a certain SaleID to look for: ");
        int saleID = Main.getValidInt();
        System.out.println();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for saleID selected " + saleID);
        } else {

            System.out.println(sale1.toCSVString());
            for (SaleItem si : sale1.getSaleItems()) {

                System.out.println(si.toCSVString(sale1.getSaleID()));
            }

            System.out.print("\nPlease enter the ItemID off the item you wish to remove: ");
            int itemID = Main.getValidInt();

            for (SaleItem si : sale1.getSaleItems()) {
                if (itemID == si.getItemID()) {
                    lclItems = sale1.getSaleItems();
                    lclItems.remove(si);
                    System.out.println("Item removed from Sale");
                }
            }
        }
    }

    //10 on List
    public List<Sale> getDetailsOfCertainSale()
    {
        List<Sale> retList = new ArrayList();

        System.out.print("Please enter a certain SaleID to look for: ");
        int saleID = Main.getValidInt();
        System.out.println();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for saleID selected: " + saleID);
        } else {
            retList.add(sale1);
        }
        return retList;
    }

    //11 on List
    public List<Sale> getSalesSelectedByCustomer()
    {
        List<Sale> sales = new ArrayList();

        System.out.print("Please enter a Customer to look for: ");
        String customer = keyboard.nextLine();
        System.out.println();

        for (Sale s : saleList) {
            if (s.getCustomerName().equalsIgnoreCase(customer)) {
                sales.add(s);
            }
        }
        return sales;
    }

    //12 on list
    public void getSalesTwoUserDefinedDates()
    {
        System.out.print("Please enter the Date you wish to search for in the format off 'dd-mmm-yyy': ");
        String startDateIn = keyboard.nextLine();
        System.out.print("Please enter the Date you wish to end search for in the format off 'dd-mmm-yyy': ");
        String endDateIn = keyboard.nextLine();
        System.out.println();

        List<Sale> retList3 = fetchSalesBetweenDates2(startDateIn, endDateIn);
        displayArrayList(retList3);

    }

    //13 on List
    public void printReceipt()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

        System.out.print("Please enter a saleID to print a receipt: ");
        int saleID = Main.getValidInt();
        System.out.println();

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for SaleID: " + saleID);
        } else {

            System.out.println("SaleID: " + sale1.getSaleID() + "\t\t\t" + "Date: " + formatter.format(sale1.getSaleDate()) + "\n\n" + "Name: " + sale1.getCustomerName() + "\n" + "Phone: " + sale1.getPhoneNumber());

            System.out.println("\n" + "Quantity" + "\t" + "Description" + "\t" + "Unit Price" + "\t" + "Amount");

            for (SaleItem si : sale1.getSaleItems()) {
                System.out.println(si.toReportString());
            }

            System.out.println("\n" + "\t\t\t\t" + "Subtotal: " + sale1.getTotalSale() + "\n" + "\t\t\t\t" + "Taxes: " + "\n" + "\t\t\t\t" + "Handling: " + "\n" + "\t\t\t\t" + "Total Sale: " + sale1.getTotalSale());
        }
    }

    //14 on List
    public void addSaleTest()
    {

        saleList.add(new Sale(-1, "Ben Jones", "042 933 8321", "15-Jan-2017", "00.00"));
        saleList.add(new Sale(-1, "Stephen Gearard", "042 922 8231", "15-Jan-2017", "00.00"));
        saleList.add(new Sale(-1, "Ben Taylor", "042 876 8431", "15-Jan-2017", "00.00"));
        saleList.add(new Sale(-1, "Sean Brennan", "042 937 1782", "15-Jan-2017", "00.00"));
    }

    //16 on List
    public void cloneCertainSaleID()
    {
        System.out.print("Please enter a saleID to clone: ");
        int saleID = Main.getValidInt();
        System.out.println();

        Sale s = fetchSaleById(saleID);
        Sale cloneItem = (Sale) s.clone();

        System.out.println("--Cloned toString--");
        cloneItem.setCustomerName("Clone - Clone - Name " + saleID);
        System.out.println(cloneItem);

        System.out.println("\n--Original toString--");
        System.out.println(s);
    }

    public void addItemToSale(int saleID, int itemID, int quantity)  //Passing in ItemID, saleId and quantity
    {

        boolean err_not_found = true;
        int newStock = 0;

        Item item1 = stockControl.fetchItemById(itemID);  //Fetching a Item object from the Item list based on the ItemID 
        Sale sale1 = fetchSaleById(saleID);  //Fetching a sale object from the sale list based on the saleID 

        if (item1 == null) {
            System.out.println("Item not found for Item id: " + itemID);
            err_not_found = false;
        } else {
            newStock = (item1.getStock() - quantity);
        }

        if (sale1 == null) {
            System.out.println("Sale not found for Sale id: " + saleID);
            err_not_found = false;
        }

        if (err_not_found) {

            if (newStock < 0) {
                System.out.println("There is not enough quantity for sale ");

            } else {
                sale1.addSaleItem(new SaleItem(itemID, item1.getTitle(), quantity, item1.getPrice()));  //adding a new SaleItem object into the Sale
                item1.setStock(newStock);  //seting the newStock in item1

                System.out.println(sale1.toString());  //Printing out the sale with the item object inside it
            }
        }
    }

    public void addFileItemToSale(int saleID, int itemID, String title, int quantity, Double price)
    {

        Sale sale1 = fetchSaleById(saleID);

        if (sale1 == null) {
            System.out.println("Sale not found for Sale id: " + saleID);

        } else {
            sale1.addSaleItem(new SaleItem(itemID, title, quantity, price));
        }
    }

    //Searches for the dates
    public List<Sale> fetchSalesBetweenDates2(String startDateIn, String endDateIn)
    {
        List<Sale> retList = new ArrayList();
        Date startDate = null;
        Date endDate = null;
        Date saleDate = null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

        try {
            startDate = formatter.parse(startDateIn);
            endDate = formatter.parse(endDateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Sale s : saleList) {
            saleDate = s.getSaleDate();
            if ((saleDate.compareTo(startDate) >= 0) && (saleDate.compareTo(endDate) < 0)) {
                retList.add(s);
            }
        }
        return retList;
    }

    public void endApplication(String saleFN, String saleItemFN)
    {
        System.out.print("Would you like to save the Sales to file? YES.......<1> / No.......<2>: ");
        int userInput = Main.getValidInt();
        System.out.println();

        if (userInput == 1) {
            saveToFile(saleFN, saleItemFN);
        } else {
            System.out.println("Sale's List not written to File");
            System.out.println("Leaving program - Thank you!");
        }
    }

    //Fetching a sale by it's ID
    public Sale fetchSaleById(int saleID) //Passing in saleID
    {
        for (Sale s : saleList) { //Looping through the arrayList()
            if (s.getSaleID() == saleID) { //If the saleID is = to a saleID inside the list enter the if statement
                return s; // returning the object at s
            }
        }
        return null; // Otherwise if it is not found you are returning null
    }
}
