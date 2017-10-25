/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Sean
 */
public class CashCreditSale extends Sale
{

    private String saleType;//Cash sale or credit sale
    private String cashMethod;
    private double deposit;
    private double balanceDue;

    public CashCreditSale(int id, String customerName, String phoneNumber, String saleDateIn, String TotalIn, String saleType, String cashMethod, String deposit, String balanceDue)
    {
        super(id, customerName, phoneNumber, saleDateIn, TotalIn);

        if (saleType.equals("Cash")) {
            this.saleType = saleType;
            this.cashMethod = cashMethod;
            this.deposit = 0.0;
            this.balanceDue = 0.0;
        } else {
            if (saleType.equals("Credit")) {
                this.saleType = saleType;
                this.cashMethod = null;

                double lclNum = Double.parseDouble(deposit);
                this.deposit = lclNum;
                super.setTotalSale(lclNum);  //Deposit also applied to sale total
                lclNum = Double.parseDouble(balanceDue);
                this.balanceDue = lclNum;
            } else {
                throw new IllegalArgumentException("Invalid SaleType: <" + saleType + ">");
            }
        }
    }

    public String getSaleType()
    {
        return saleType;
    }

    public String getCashMethod()
    {
        return cashMethod;
    }

    public double getDeposit()
    {
        return deposit;
    }

    public void setSaleType(String saleType)
    {
        this.saleType = saleType;
    }

    public void setCashMethod(String cashMethod)
    {
        this.cashMethod = cashMethod;
    }

    public void setDeposit(double deposit)
    {
        this.deposit = deposit;
    }

    public void setBalanceDue(double balanceDue)
    {
        this.balanceDue = balanceDue;
    }

    public double getBalanceDue()
    {
        return balanceDue;
    }

    public int getSaleID()
    {
        return super.getSaleID();
    }

    public String getCustomerName()
    {
        return super.getCustomerName();
    }

    public String getPhoneNumber()
    {
        return super.getPhoneNumber();
    }

    public Date getSaleDate()
    {
        return super.getSaleDate();
    }

    public double getTotalSale()
    {
        return super.getTotalSale();
    }

    @Override
    public int hashCode()
    {
        int hash = 7 + super.hashCode();
        hash = 53 * hash + Objects.hashCode(this.saleType);
        hash = 53 * hash + Objects.hashCode(this.cashMethod);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.deposit) ^ (Double.doubleToLongBits(this.deposit) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.balanceDue) ^ (Double.doubleToLongBits(this.balanceDue) >>> 32));
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
        final CashCreditSale other = (CashCreditSale) obj;
        if (!Objects.equals(this.saleType, other.saleType)) {
            return false;
        }
        if (!Objects.equals(this.cashMethod, other.cashMethod)) {
            return false;
        }
        if (Double.doubleToLongBits(this.deposit) != Double.doubleToLongBits(other.deposit)) {
            return false;
        }
        if (Double.doubleToLongBits(this.balanceDue) != Double.doubleToLongBits(other.balanceDue)) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", " + "saleType= " + saleType + ", cashMethod= " + cashMethod + ", deposit= " + deposit + ", balanceDue= " + balanceDue + '}';
    }

}
