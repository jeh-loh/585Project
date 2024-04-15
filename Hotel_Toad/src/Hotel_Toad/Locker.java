package Hotel_Toad;

/**
 * Represents a Storage.locker with a locker number, locker type, and availability status.
 * @author Toad's Taskforce
 * @version 1.0
 * <p>
 * Date of Creation: 28 July 2023
 * </p>
 *
 * <p>
 * This class provides a structure for managing and querying information related
 * to individual lockers in a Storage or similar establishment.
 * </p>
 */
public class Locker {

    /** The locker number. */
    public int roomNumber;
    /** The type of the locker, e.g., "single", "double", "suite". */
    private String roomType;
    /** Indicates whether the locker is currently available. */
    public boolean availability;

    /**
     * Constructs a new Stroage.locker with the specified locker number, locker type, and availability.
     *
     * @param roomNumber  the locker's number.
     * @param roomType    the locker's type.
     * @param availability whether the locker is available.
     */
    public Locker(int roomNumber, String roomType, boolean availability){

        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.availability = availability;

    }

    /**
     * Returns the locker number.
     *
     * @return the locker number.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the type of the locker.
     *
     * @return the locker type.
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Checks if the locker is available.
     *
     * @return true if the locker is available, false otherwise.
     */
    public boolean isAvailable(){
        return availability;
    }

}

