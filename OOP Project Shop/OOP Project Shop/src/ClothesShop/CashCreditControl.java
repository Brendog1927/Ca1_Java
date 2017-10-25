/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClothesShop;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sean
 */
public class CashCreditControl
{

    ArrayList<CashCreditSale> cashSaleList;

    public CashCreditControl()
    {
        cashSaleList = new ArrayList<>();
    }

    public void addTestCashCreditSales()
    {
        cashSaleList.add(new CashCreditSale(-1, "Billy Bob", "042 93 512 214", "1-Nov-2016", "10.50", "Cash", "Cash", "0.0", "0.0"));
        cashSaleList.add(new CashCreditSale(-1, "Jon James", "054 93 123 023", "10-Nov-2016", "55.70", "Cash", "Credit card", "0.0", "0.0"));
        cashSaleList.add(new CashCreditSale(-1, "Ken Daniels", "033 93 178", "30-Dec-2016", "16.87", "Credit", "", "16.78", "36.75"));
        cashSaleList.add(new CashCreditSale(-1, "Ray Jones", "042 933 878", "2-Feb-2017", "60.00", "Credit", "", "12.90", "35.00"));
    }

    public void displayArrayList(List<CashCreditSale> cashSaleList)
    {
        if (cashSaleList.size() == 0) {
            System.out.println("Nothing to diplay");
        } else {
            for (CashCreditSale ccs : cashSaleList) {
                System.out.println(ccs);
            }
        }
    }

    public List<CashCreditSale> getCashCreditSale()
    {
        return cashSaleList;
    }

    public List<CashCreditSale> getCreditSale()
    {
        List<CashCreditSale> retListCredit = new ArrayList();

        String credit = "Credit";

        for (CashCreditSale ccs : cashSaleList) {
            if (ccs.getSaleType().equals("Credit"));
            {
                retListCredit.add(ccs);
            }
        }
        return retListCredit;
    }

    public void getTotalSales()
    {
        DecimalFormat df = new DecimalFormat("###,###.##");
        System.out.println("\nTotal Sales for Cash and Credit " + df.format(calcTotal()));
    }

    public void getTotalBalance()
    {
        DecimalFormat df = new DecimalFormat("###,###.##");
        System.out.println("\nTotal Balance due " + df.format(calcBalance()));
    }

    public double calcTotal()
    {
        double lclTotal = 0.0;

        for (CashCreditSale ccs : cashSaleList) {
            lclTotal = lclTotal + ccs.getTotalSale();
        }
        return lclTotal;
    }

    public double calcBalance()
    {
        double lclBalance = 0.0;

        for (CashCreditSale ccs : cashSaleList) {
            lclBalance = lclBalance + ccs.getBalanceDue();
        }
        return lclBalance;
    }
}
