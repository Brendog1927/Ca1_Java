/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import java.util.Objects;

/**
 *
 * @author Sean
 */
public class Item implements Cloneable
{

    static int max_item_id = 0;

    private int itemID;
    private String title;
    private String manufacturer;
    private String size;
    private String colour;
    private int stock;
    private double price;

    public Item(int id, String title, String manufacturer, String size, String colour, int stock, String price)
    {

        this.title = title;
        this.manufacturer = manufacturer;
        this.size = size;
        this.colour = colour;
        this.stock = stock;
        this.price = Double.parseDouble(price);

        switch (id) {
            case 0:// id = 0 - Testing if already exists
                this.itemID = id;
                break;
            case -1:// id = -1 - Generate new Item ID
                max_item_id++;
                this.itemID = max_item_id;
                break;
            default:// id > 0 - Use passed value as ID
                this.itemID = id;
                if (id > max_item_id) {
                    max_item_id = id;
                }
                break;
        }

    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setItemID(int itemID)
    {
        this.itemID = itemID;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public int getItemID()
    {
        return itemID;
    }

    public String getTitle()
    {
        return title;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getSize()
    {
        return size;
    }

    public String getColour()
    {
        return colour;
    }

    public int getStock()
    {
        return stock;
    }

    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Item{" + "id= " + itemID + ", title= " + title + ", manufacturer= " + manufacturer + ", size= " + size + ", colour= " + colour + ", quantity= " + stock + ", price= " + price + '}';
    }

    //@Override
    public String toCSVString()
    {
        return itemID + "," + title + "," + manufacturer + "," + size + "," + colour + "," + stock + "," + price;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.manufacturer);
        hash = 79 * hash + Objects.hashCode(this.size);
        hash = 79 * hash + Objects.hashCode(this.colour);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.colour, other.colour)) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone()
    {
        Item itm;
        try {
            itm = (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;   // will never happen } 
        }
        return itm;
    }
}
