/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//I NEED TO BE ABLE TO ACCESS THE CLASS FORM EVERYWHERE JUST BEING USED ON THIS CONSOLE  
package ClothesShop;

import static ClothesShop.Main.keyboard;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Sean
 */
public class StockControl
{

    ArrayList<Item> itemList;

    public StockControl()
    {
        itemList = new ArrayList<>();
    }

    public enum Size
    {

        XXS, XS, S, M, L, XL, XXL
    }

    public void readFile(String fName)
    {
        try {
            String line;
            Scanner fin = new Scanner(new File(fName));
            while (fin.hasNextLine()) {
                line = fin.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                int itemID = Integer.parseInt(st.nextToken());
                String title = st.nextToken();
                String manafacturer = st.nextToken();
                String size = st.nextToken();
                String colour = st.nextToken();
                String strQuantity = st.nextToken();
                int stock = Integer.parseInt(strQuantity);
                String price = st.nextToken();
                if (!itemList.contains(new Item(0, title, manafacturer, size, colour, stock, price))) {
                    itemList.add(new Item(itemID, title, manafacturer, size, colour, stock, price));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void displayArrayList(List<Item> items)
    {
        if (items.size() == 0) {
            System.out.println("Nothing to diplay");
        } else {
            for (Item i : items) {
                System.out.println(i);
            }
        }
    }

    //1 on List
    public void saveToFile(String fileName)
    {
        System.out.println("Writing item list to file");

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(fileName, false));
            for (Item i : itemList) {
                out.println(i.toCSVString());
            }

            System.out.println("Item List written to file.");
            System.out.println("Leaving program - Thank you!");
            System.out.println();

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    //2 on List
    public List<Item> displayArrayList()
    {
        List<Item> items = new ArrayList();

        for (Item i : itemList) {
            items.add(i);
        }
        return items;
    }

    //3 on List
    public void createNewItem()
    {
        System.out.print("Please enter the item's Title: ");
        String title = keyboard.nextLine();
        System.out.print("Please enter the item's Manufacturer: ");
        String manufacturer = keyboard.nextLine();
        System.out.print("Please enter the item's Size: ");
        String size = keyboard.nextLine();
        System.out.print("Please enter the item's Colour: ");
        String colour = keyboard.nextLine();
        System.out.print("Please enter the item's Stock: ");
        //ASK WILL I JUST VALIDATE THIS AS A STRING
        String sQuantity = keyboard.nextLine();
        int stock = Integer.parseInt(sQuantity);
        System.out.print("Please enter the item's Price: ");
        String price = keyboard.nextLine();

        if (!itemList.contains(new Item(0, title, manufacturer, size, colour, stock, price))) {
            itemList.add(new Item(-1, title, manufacturer, size, colour, stock, price));
            System.out.println("******************************");
            System.out.println("***  Item added succesfully **");
            System.out.println("******************************");
        } else {
            int currentItemId = getItemIDforObj(new Item(0, title, manufacturer, size, colour, stock, price));
            System.out.println("**********************************************************");
            System.out.println("***  Not a new Item - found in list as ItemID: " + currentItemId);
            System.out.println("**********************************************************");
        }
    }

    //4 on List
    public void updateQuantity()
    {
        System.out.print("Please enter an ID to look for to Update the Quantity: ");
        int itemID = Main.getValidInt();

        Item item1 = fetchItemById(itemID);

        if (item1 == null) {
            System.out.println("Item not found for itemID selected " + itemID);
        } else {
            System.out.println(item1);

            System.out.print("\nPlease enter your new Quantity: ");
            int quantity = Main.getValidInt();
            System.out.println();
            item1.setStock(quantity);

            if (quantity == item1.getStock()) {
                System.out.println("ItemID " + itemID + "; quantity was updated successfully");
            } else {
                System.out.println("ItemID " + itemID + "; quantity was not updated");
            }
        }
    }

    //5 on List
    public void updatePrice()
    {
        System.out.print("Please enter an ID to look for to Update the Price: ");
        int itemID = Main.getValidInt();

        Item item1 = fetchItemById(itemID);

        if (item1 == null) {
            System.out.println("Item not found for itemID selected " + itemID);
        } else {
            System.out.println(item1);

            System.out.print("\nPlease enter your new Price: ");
            double price = Main.getValidDouble();
            System.out.println();
            item1.setPrice(price);

            if (price == item1.getPrice()) {
                System.out.println("ItemID " + itemID + "; price was updated successfully");
            } else {
                System.out.println("ItemID " + itemID + "; price was not updated successfully");
            }
        }

    }

    //6 on List
    public List<Item> getItemSelectedByID()
    {
        List<Item> retList = new ArrayList();

        System.out.print("Please enter a certain SaleID to look for: ");
        int itemID = Main.getValidInt();
        System.out.println();

        Item item1 = fetchItemById(itemID);

        if (item1 == null) {
            System.out.println("Sale not found for saleID selected: " + itemID);
        } else {
            retList.add(item1);
        }
        return retList;
    }

    //7 on List
    public List<Item> getItemsSelectedByManufacturer()
    {
        List<Item> items = new ArrayList();
        System.out.print("Please enter a Manufacturer to look for: ");
        String manufacturer = keyboard.nextLine();
        System.out.println();

        for (Item i : itemList) {
            if (i.getManufacturer().equalsIgnoreCase(manufacturer)) {
                items.add(i);
            }
        }
        return items;
    }

    //8 on List
    public List<Item> getItemsUsingSubString()
    {
        List<Item> items = new ArrayList();

        System.out.print("Please enter a keyword to search for off the Manufacturer or Title: ");
        String manufacturer_Title = keyboard.nextLine();
        System.out.println();

        for (Item i : itemList) {
            if (i.getManufacturer().contains(manufacturer_Title) || i.getTitle().contains(manufacturer_Title)) {

                items.add(i);
            }
        }
        return items;
    }

    //9 on List
    public List<Item> getItemsSelectedByTitle()
    {
        List<Item> items = new ArrayList();

        System.out.print("Please enter a Title to look for: ");
        String title = keyboard.nextLine();
        System.out.println();

        for (Item i : itemList) {
            if (i.getTitle().equalsIgnoreCase(title)) {

                items.add(i);
            }
        }
        return items;
    }

    //10 on List
    public List<Item> getItemsSelectedBelowUserDefinedPrice()
    {
        List<Item> items = new ArrayList();

        System.out.print("Please enter an Item Price: ");
        double price = Main.getValidDouble();
        System.out.println();

        for (Item i : itemList) {
            if (i.getPrice() < price) {
                items.add(i);
            }
        }
        return items;
    }

    //11 on List
    public List<Item> getItemsThatHaveQuantityOfZero()
    {
        List<Item> items = new ArrayList();

        for (Item i : itemList) {
            if (i.getStock() == 0) {
                items.add(i);
            }
        }
        return items;
    }

    //12 on List
    public List<Item> getItemsUsingClothesSizes()
    {
        List<Item> items = new ArrayList();

        System.out.print("Please enter the minimum Size off clothing you wish to search for: ");
        String minSize = keyboard.nextLine();
        int iMinSize = getSizeOrdinal(minSize);

        System.out.print("Please enter the maxium Size off clothing you wish to search for: ");
        String maxSize = keyboard.nextLine();
        int iMaxSize = getSizeOrdinal(maxSize);

        for (Item i : itemList) {
            if ((getSizeOrdinal(i.getSize()) >= iMinSize) && (getSizeOrdinal(i.getSize()) <= iMaxSize)) {

                items.add(i);
            }
        }
        return items;
    }

    //13 on List
    public List<Item> getItemsUsingColour()
    {
        List<Item> items = new ArrayList();

        System.out.print("Please enter a colour to look for: ");
        String colour = keyboard.nextLine();
        System.out.println();

        for (Item i : itemList) {
            if (i.getColour().equalsIgnoreCase(colour)) {
                items.add(i);
            }
        }
        return items;
    }

    //14 on List--NOT WORKING ANYMORE--
    public void getItemsSortedByTitle()
    {
        Collections.sort(itemList, new Comparator<Item>()
        {
            @Override
            public int compare(Item t1, Item t2)
            {
                return t1.getTitle().compareTo(t2.getTitle());
            }
        });
    }

    //15 on List
    public void getItemsSortedByID()
    {
        Collections.sort(itemList, new Comparator<Item>()
        {
            @Override
            public int compare(Item t1, Item t2)
            {
                if (t1.getItemID() > t2.getItemID()) {
                    return 1;
                } else if (t1.getItemID() < t2.getItemID()) {
                    return -1;
                }
                return 0;
            }

        });
    }

    //16 on List
    public void addTestItems()
    {
        if (!itemList.contains(new Item(0, "Jeans", "Jack Jones", "L", "Red", 65, "69.09"))) {
            itemList.add(new Item(-1, "Jeans", "Jack Jones", "L", "Red", 65, "69.09"));
        }

        if (!itemList.contains(new Item(0, "Jumper", "Pennys", "S", "Pink", 12, "80"))) {
            itemList.add(new Item(-1, "Jumper", "Pennys", "S", "Pink", 12, "80"));
        }

        if (!itemList.contains(new Item(0, "Hoodie", "Dunnes", "M", "Blue", 85, "99"))) {
            itemList.add(new Item(-1, "Hoodie", "Dunnes", "M", "Blue", 85, "99"));
        }

        if (!itemList.contains(new Item(0, "Socks", "Jack Jones", "XS", "Yellow", 36, "68.65"))) {
            itemList.add(new Item(-1, "Socks", "Jack Jones", "XS", "Yellow", 36, "68.65"));
        }

        if (!itemList.contains(new Item(0, "Boxers", "Pennys", "XL", "Green", 85, "65.99"))) {
            itemList.add(new Item(-1, "Boxers", "Pennys", "XL", "Green", 85, "65.99"));
        }

        if (!itemList.contains(new Item(0, "Hat", "Jack Jones", "M", "Black", 18, "12"))) {
            itemList.add(new Item(-1, "Hat", "Jack Jones", "M", "Black", 18, "12"));
        }

        if (!itemList.contains(new Item(0, "Shoes", "Pennys", "L", "White", 85, "99.04"))) {
            itemList.add(new Item(-1, "Shoes", "Pennys", "L", "White", 85, "99.04"));
        }

        if (!itemList.contains(new Item(0, "T-shirt", "Dunnes", "S", "Black", 36, "68.88"))) {
            itemList.add(new Item(-1, "T-shirt", "Dunnes", "S", "Black", 36, "68.88"));
        }

        if (!itemList.contains(new Item(0, "Scarf", "Dunnes", "L", "yellow", 10, "12.88"))) {
            itemList.add(new Item(-1, "Scarf", "Dunnes", "L", "yellow", 10, "12.88"));
        }

        //NEW TEST ITEMS ---- ABOVE ADDED ALREADY
        if (!itemList.contains(new Item(0, "Shirt", "Farah", "XS", "Blue", 6, "62.88"))) {
            itemList.add(new Item(-1, "Shirt", "Farah", "XS", "Blue", 6, "62.88"));
        }

        if (!itemList.contains(new Item(0, "Jacket", "Penguin", "S", "Black", 2, "98.00"))) {
            itemList.add(new Item(-1, "Jacket", "Penguin", "S", "Black", 2, "98.00"));
        }

        if (!itemList.contains(new Item(0, "Leggings", "Under Armour", "S", "Navy", 12, "30.00"))) {
            itemList.add(new Item(-1, "Leggings", "Under Armour", "S", "Navy", 12, "30.00"));
        }
    }

    //17 0n List
    public void cloneCertainItemID()
    {
        System.out.print("Please enter a itemID to clone: ");
        int itemID = Main.getValidInt();
        System.out.println();

        Item i = fetchItemById(itemID);
        Item cloneItem = (Item) i.clone();

        System.out.println("--Cloned toString--");
        cloneItem.setTitle("Clone - Clone - Title " + itemID);
        System.out.println(cloneItem);

        System.out.println("\n--Original toString--");
        System.out.println(i);
    }

    //18 on List
    public void endApplication(String fileName)
    {
        System.out.print("Would you like to save the Items list to file? YES.......<1> / No.......<2>: ");
        int userInput = Main.getValidInt();
        System.out.println();

        if (userInput == 1) {
            saveToFile(fileName);
        } else {
            System.out.println("Item List not written to File");
            System.out.println("Leaving program - Thank you!");
        }
    }

    //Linked to add new Item method
    private int getItemIDforObj(Item item)
    {
        int lclItemID = 0;

        for (Item i : itemList) {
            if (i.equals(item)) {
                lclItemID = i.getItemID();
                break;
            }
        }
        return lclItemID;
    }

    public int getSizeOrdinal(String str)
    {
        for (Size s : Size.values()) {
            if (s.name().equalsIgnoreCase(str)) {
                return s.ordinal();
            }
        }
        return -1;
    }

    public Item fetchItemById(int itemID)
    {
        for (Item i : itemList) {
            if (i.getItemID() == itemID) {
                return i;
            }
        }
        return null;
    }
}
