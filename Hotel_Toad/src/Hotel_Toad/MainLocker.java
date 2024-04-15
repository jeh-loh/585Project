package Hotel_Toad;

import java.sql.SQLException;

/**
 * Represents a locker entity with functionalities encompassing storeing and emtpying.
 * @author Toad's Taskforce
 * @version 1.0
 * <p>
 * Date of Creation: 28 July 2023
 * </p>
 *
 * <p>
 * This class not only manages the in-memory representation of the system  but also interfaces with a database
 * for persistent storage of reservation details.
 * </p>
 */
public class MainLocker {

    /** An array representing all the lockers in the Storage. */
    public static Locker[] lockers;

    /**
     * Constructs a new Locker with the given lockers.
     *
     * @param lockers the array of lockers that belong to this Storage locker.
     */
    public MainLocker(Locker[] lockers){
        this.lockers = lockers;
    }

    /**
     * Processes a locker request for a guest. If successful, details of the reservation are returned.
     * Otherwise, a null value indicates the absence of available lockers of the desired type.
     *
     * @param guest    the guest making the reservation.
     * @param lockerType the type of locker desired by the guest.
     * @param checkIn  the check-in date.
     * @param checkOut the check-out date.
     * @param email    the email of the guest.
     * @param bill     total billing details.
     *
     * @return details of the reserved locker or null if the desired locker type is unavailable.
     *
     * @throws ClassNotFoundException if there's an issue loading a database class.
     * @throws SQLException if there's a database access error.
     */
    public static String reserveRoom(Guest guest, String roomType, String checkIn, String checkOut, String email, String bill) throws ClassNotFoundException, SQLException{
        for (int i = 0; i < lockers.length; i++){
            if (lockers[i].getRoomType().equalsIgnoreCase(roomType) && lockers[i].isAvailable()){
               
                return "Locker Successfully Stored!" +
                        "\nGuest Name: " + guest.getFirstName() + " " + guest.getLastName() + " "
                        + "\nList of Items:  " + guest.getEmail()  +"\nLocker Type: " + roomType + "\nLocker number: "
                        + lockers[i].getRoomNumber() + "\nCheck-in: " + checkIn + "\nCheck-out: " + checkOut + bill
                        + "\nDon't forget your Locker number if you want to cancel your reservation!";
            }
        }
        return null;
    }

    /**
     * Handles locker reservation and simultaneously updates the database to reflect these changes.
     *
     * @param guest    details of the guest requesting the reservation.
     * @param roomType the kind of locker the guest desires.
     * @param checkIn  intended check-in date.
     * @param checkOut intended check-out date.
     * @param email    contact email of the guest.
     *
     * @throws ClassNotFoundException if there's an issue connecting with the database driver.
     * @throws SQLException if there's an issue accessing the database.
     */
    public static void reserveRoom1(Guest guest, String roomType, String checkIn, String checkOut, String email) throws ClassNotFoundException, SQLException{
        for (int i = 0; i < lockers.length; i++){
            if (lockers[i].getRoomType().equalsIgnoreCase(roomType) && lockers[i].isAvailable()){
                lockers[i].availability = false;
                
                int roomNumber = lockers[i].getRoomNumber();
                //Calls addtoCustomerDetails from DataBase class to store the information
                Database.addtoCustomerDetails(guest.getFirstName(), guest.getLastName(), email, roomNumber);
                //Calls addBooking from DataBase class to store the information
                Database.addBooking(roomNumber,checkIn,checkOut);
                //Calls updateRoomAvailability from DataBase class to store the information
                Database.updateRoomAvailability(roomNumber);
                return;
            }
        }
        
    }

    /**
     * Cancels an existing locker reservation and updates the database to reflect the locker's availability.
     *
     * @param lockerNumber the specific number of the locker whose reservation needs cancellation.
     *
     * @return a message indicating whether the cancellation was successful or not.
     *
     * @throws ClassNotFoundException if there's an issue connecting with the database driver.
     * @throws SQLException if there's an issue accessing the database.
     */
    public static String cancelRoom(int roomNumber) throws ClassNotFoundException, SQLException{
        String message;
        for (int i = 0; i < lockers.length; i++){
            if (lockers[i].getRoomNumber() == roomNumber && !lockers[i].isAvailable()) {
                // Mark locker as available;
                lockers[i].availability = true;

                // Update database to reflect cancellation
                //Calls deleteCustomerDetails from DataBase class to delete in the Database
                Database.deleteCustomerDetails(roomNumber);
                //Calls nowAvailabe from DataBase class to Updates in the Database
                Database.nowAvailable(roomNumber);
                //Calls deleteBooking from DataBase class to delete in the Database
                Database.deleteBooking(roomNumber);
                message = "Reservation Canceled!";
                return message;
            }
        }
        message = "Error: No Matching Reservation Found.";
        return message;
    }

}
