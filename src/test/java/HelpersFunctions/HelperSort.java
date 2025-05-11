package HelpersFunctions;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class HelperSort {
    /**
     * Verifies that the list of product names or prices is sorted correctly based on the given sort type.
     * Supported sort types are:
     * "A to Z" – verifies names sorted in ascending alphabetical order
     * "Z to A" – verifies names sorted in descending alphabetical order
     *"Low to High" – verifies prices sorted in ascending numerical order
     *"High to Low" – verifies prices sorted in descending numerical order
     * Fails the test if the list is not sorted correctly or if an unsupported sort type is provided.
     * @param sortType   the type of sorting to verify
     * @param productNames  the list of product names (used for alphabetical sorting)
     * @param productPrices the list of product prices (used for price sorting)
     */
    public void sortedFullyOfItems (String sortType , List<String > productNames, List<Double> productPrices){
        switch (sortType)
        {
            case "A to Z":
                List<String> sortedNamesAsc = new ArrayList<>(productNames);
                Collections.sort(sortedNamesAsc);
                assertEquals("Products are not sorted A to Z", sortedNamesAsc, productNames);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedAscAlpha(sortedNamesAsc));
                break;
            case "Z to A":
                List<String> sortedNamesDesc = new ArrayList<>(productNames);
                sortedNamesDesc.sort(Collections.reverseOrder());
                assertEquals("Products are not sorted Z to A", sortedNamesDesc, productNames);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedDescAlpha(sortedNamesDesc));
                break;
            case "Low to High":
                List<Double> sortedPricesAsc = new ArrayList<>(productPrices);
                Collections.sort(sortedPricesAsc);
                assertEquals("Products are not sorted Low to High", sortedPricesAsc, productPrices);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedAsc(sortedPricesAsc));
                break;
            case "High to Low":
                List<Double> sortedPricesDesc = new ArrayList<>(productPrices);
                sortedPricesDesc.sort(Collections.reverseOrder());
                assertEquals("Products are not sorted High to Low", sortedPricesDesc, productPrices);
                Assert.assertTrue("actual result are not sorted by Asc sorting",isSortedDesc(sortedPricesDesc));
                break;
            default:
                Assert.fail("Unsupported sort type: " + sortType);
        }
    }
    /**
     * Checks whether the list of prices is sorted in ascending order.
     *
     * @param prices the list of prices to check
     * @return true if sorted in ascending order, false otherwise
     */
    public boolean isSortedAsc(List<Double>prices){
        for (int i=0 ; i<prices.size()-1;i++){
            if (prices.get(i) >prices.get(i+1))
                return false;
        }
        return true;
    }
    /**
     * Checks whether the list of prices is sorted in descending order.
     *
     * @param prices the list of prices to check
     * @return true if sorted in descending order, false otherwise
     */
    public boolean isSortedDesc(List<Double>prices){
        for(int i=0 ;i<prices.size()-1;i++){
            if(prices.get(i)<prices.get(i+1))
                return false;
        }
        return true;
    }
    /**
     * Checks whether the list of product names is sorted in ascending (A to Z) alphabetical order.
     *
     * @param productNames the list of product names to check
     * @return true if sorted alphabetically in ascending order, false otherwise
     */
    public boolean isSortedAscAlpha(List<String>productNames){
        for(int i=0 ;i<productNames.size()-1;i++){
            if(productNames.get(i).compareTo(productNames.get(i+1))>0)
                return false;
        }
        return true;
    }
    /**
     * Checks whether the list of product names is sorted in descending (Z to A) alphabetical order.
     *
     * @param productNames the list of product names to check
     * @return true if sorted alphabetically in descending order, false otherwise
     */
    public boolean isSortedDescAlpha(List<String>productNames){
        for(int i=0 ;i<productNames.size()-1;i++){
            if(productNames.get(i).compareTo(productNames.get(i+1))<0)
                return false;
        }
        return true;
    }
}
