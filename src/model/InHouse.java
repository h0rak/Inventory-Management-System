package model;
/**
 * The InHouse class inherits from the superclass Part.
 */
public class InHouse extends Part{
    private int machineId;
    /**
     * The InHouse method is a constructor for InHouse parts.
     * @param id the id for the InHouse part
     * @param name the name for the InHouse part
     * @param price the price for the InHouse part
     * @param stock the stock for the InHouse part
     * @param min the min for the InHouse part
     * @param max the max for the InHouse part
     * @param machineId the machineId for the InHouse part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /** @param machineId the machineId to set */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    /** @return the machineId */
    public int getMachineId() {
        return machineId;
    }
}
