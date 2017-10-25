/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Sean
 */
public class Sale implements Cloneable
{

    static int max_sale_id = 0;

    private int saleID;
    private ArrayList<SaleItem> saleItems;
    private String customerName;
    private String phoneNumber;
    private Date saleDate;
    private double totalSale;

    public Sale(int id, String customerName, String phoneNumber, String saleDateIn, String TotalIn)
    {

        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.saleItems = new ArrayList<>();             // Array list to store list of items in sale
        this.totalSale = Double.parseDouble(TotalIn);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"); //Formatting date and time
        try {
            this.saleDate = formatter.parse(saleDateIn); //Parsing the date in to format it
        } catch (ParseException e) { //Catching the exception
            e.printStackTrace(); //Printing the error
        }

        switch (id) {
            case 0:                     // Use to check if same sale exists 
                this.saleID = id;       // but OK to have multiple sales for same customer on same day.
                break;
            case -1:                        // Generate a new sale id.
                max_sale_id++;
                this.saleID = max_sale_id;
                break;
            default:                    // Use passed id as sale id. 
                this.saleID = id;
                if (id > max_sale_id) {
                    max_sale_id = id;
                }
                break;
        }
    }

    public void addSaleItem(SaleItem s)
    { //Passing in the SaleItem into this method 
        this.totalSale = (totalSale + s.getSubTotal()); // Totaling the subtotal for the products bought
        this.saleItems.add(s);
    }

    public static void setMax_sale_id(int max_sale_id)
    {
        Sale.max_sale_id = max_sale_id;
    }

    public void setSaleItems(ArrayList<SaleItem> saleItems)
    {
        this.saleItems = saleItems;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setSaleDate(Date saleDate)
    {
        this.saleDate = saleDate;
    }

    public void setTotalSale(double totalSale)
    {
        this.totalSale = totalSale;
    }

    public static int getMax_sale_id()
    {
        return max_sale_id;
    }

    public int getSaleID()
    {
        return saleID;
    }

    public ArrayList<SaleItem> getSaleItems()
    {
        return saleItems;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public Date getSaleDate()
    {
        return saleDate;
    }

    public double getTotalSale()
    {
        return totalSale;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        DecimalFormat df = new DecimalFormat("###,###.##");
        return "Sale{" + "saleID=" + saleID + ", saleItems=" + saleItems + ", "
                + "customerName=" + customerName + ", phoneNumber=" + phoneNumber + ", "
                + "saleDate=" + formatter.format(saleDate) + ", totalSale=" + df.format(totalSale);
    }

    public String toCSVString()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        DecimalFormat df = new DecimalFormat("###,###.##");

        return saleID + "," + customerName + "," + phoneNumber + "," + formatter.format(saleDate) + "," + df.format(totalSale);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + this.saleID;
        hash = 29 * hash + Objects.hashCode(this.saleItems);
        hash = 29 * hash + Objects.hashCode(this.customerName);
        hash = 29 * hash + Objects.hashCode(this.phoneNumber);
        hash = 29 * hash + Objects.hashCode(this.saleDate);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.totalSale) ^ (Double.doubleToLongBits(this.totalSale) >>> 32));
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
        final Sale other = (Sale) obj;
        if (this.saleID != other.saleID) {
            return false;
        }
        if (!Objects.equals(this.saleItems, other.saleItems)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.saleDate, other.saleDate)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalSale) != Double.doubleToLongBits(other.totalSale)) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone()
    {
        Sale sle;
        try {
            sle = (Sale) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;   // will never happen } 
        }
        return sle;
    }

}
