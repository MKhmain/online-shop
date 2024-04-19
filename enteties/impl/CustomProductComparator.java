package enteties.impl;

import enteties.Product;

import java.util.Comparator;

public class CustomProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o1==null)
            return -1;
        if(o2==null)
            return 1;

        if(o1.getCategoryName()!=o2.getCategoryName())
            return o1.getCategoryName().compareTo(o2.getCategoryName());
        if(o1.getPrice()!=o2.getPrice())
            return (int)(o1.getPrice()-o2.getPrice());
        return o1.getProductName().compareTo(o2.getProductName());
    }
}
