package model;

/**
 * This Outsourced class inherits from the superclass of Part.
 */
public class Outsourced extends Part{
    private String companyName;
    /**
     * The Outsourced method is a constructor for the class which includes the super constructor and companyName.
     * @param id the id for the Outsourced part
     * @param name the name for the Outsourced part
     * @param price the price for the Outsourced part
     * @param stock the stock for the Outsourced part
     * @param min the min for the Outsourced part
     * @param max the max for the Outsourced part
     * @param companyName the companyName for the Outsourced part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** @param companyName the company name to set */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** @return the companyName */
    public String getCompanyName() {
        return companyName;
    }

}
