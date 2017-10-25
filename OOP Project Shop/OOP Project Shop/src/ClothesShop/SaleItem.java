/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 * @author Sean
 */
public class SaleItem
{

    private final int itemID;
    private final String title;
    private final int quantity;
    private final double price;
    private final double subTotal;

    public SaleItem(int itemID, String title, int quantity, double price)
    {
        this.itemID = itemID;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        double itemTotal = price * quantity; // Save this item sale subtotal
        this.subTotal = itemTotal;
    }

    public int getItemID()
    {
        return itemID;
    }

    public String getTitle()
    {
        return title;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return price;
    }

    public double getSubTotal()
    {
        return subTotal;
    }

    @Override
    public String toString()
    {
        return "SaleItem{" + "itemID=" + itemID + ", title=" + title + ", quantity=" + quantity + ", price=" + price + ", subTotal=" + subTotal + '}';
    }

    public String toCSVString(int saleID)
    {
        DecimalFormat df = new DecimalFormat("###,###.##");
        return saleID + "," + itemID + "," + title + "," + quantity + "," + price + "," + df.format(subTotal);
    }

    public String toReportString()
    {
        DecimalFormat df = new DecimalFormat("###,###.##");
        return quantity + "\t\t" + title + "\t\t" + price + "\t\t" + df.format(subTotal);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + this.itemID;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + this.quantity;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final SaleItem other = (SaleItem) obj;
        if (this.itemID != other.itemID) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        return true;
    }
}
