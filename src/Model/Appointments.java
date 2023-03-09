package Model;

import DBAccess.DBAppointments;
import Main.Scheduling_Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class defines the appointments class and its methods.
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    /*
    private LocalDateTime createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
     */
    private int customerId;
    private int userId;
    private int contactId;

    public static ObservableList<ZonedDateTime> unconvertedTime = FXCollections.observableArrayList(
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 8, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 9, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 10, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 11, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 12, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 13, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 14, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 15, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 16, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 17, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 18, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 19, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 20, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 21, 0), Scheduling_Application.easternZone),
            ZonedDateTime.of(LocalDateTime.of(2001,1,1, 22, 0), Scheduling_Application.easternZone)
    );

    public static ObservableList<LocalTime> zonedTime = FXCollections.observableArrayList();

    /**
     * Default class constructor
     * @param newAppointmentId Appointment ID
     * @param newTitle Title
     * @param newDescription Description
     * @param newLocation Location
     * @param newType Type
     * @param newStart Start
     * @param newEnd End
     * @param newCustomerId Customer ID
     * @param newUserId User ID
     * @param newContactId Contact ID
     */
    public Appointments(int newAppointmentId, String newTitle, String newDescription, String newLocation, String newType, LocalDateTime newStart, LocalDateTime newEnd, /*LocalDateTime newCreateDate, String newCreatedBy, Timestamp newLastUpdate, String newLastUpdatedBy,*/ int newCustomerId, int newUserId, int newContactId) {
        this.appointmentId = newAppointmentId;
        this.title = newTitle;
        this.description = newDescription;
        this.location = newLocation;
        this.type = newType;
        this.start = newStart;
        this.end = newEnd;
        /*
        this.createDate = newCreateDate;
        this.createdBy = newCreatedBy;
        this.lastUpdate = newLastUpdate;
        this.lastUpdatedBy = newLastUpdatedBy;
         */
        this.customerId = newCustomerId;
        this.userId = newUserId;
        this.contactId = newContactId;
    }

    /**
     * Returns the current objects appointmentId.
     * @return AppointmentID Integer
     */
    public int getAppointmentId() { return this.appointmentId; }

    /**
     * Sets a new appointment ID for the current object.
     * @param newAppointmentId new Appointment ID Integer.
     */
    public void setAppointmentId(int newAppointmentId) { this.appointmentId = newAppointmentId; }

    /**
     * Returns the current object's title.
     * @return Title String
     */
    public String getTitle() { return this.title; }

    /**
     * Sets a new title for the current object.
     * @param newTitle new Title String
     */
    public void setTitle(String newTitle) { this.title = newTitle; }

    /**
     * Returns the current object's description
     * @return Description String
     */
    public String getDescription() { return this.description; }

    /**
     * Sets a new description for the current object.
     * @param newDescription new Description String.
     */
    public void setDescription(String newDescription) { this.description = newDescription; }

    /**
     * Returns the location for the current object.
     * @return Location String.
     */
    public String getLocation() { return this.location; }

    /**
     * Sets a new location for the current object.
     * @param newLocation new Location String.
     */
    public void setLocation(String newLocation) { this.location = newLocation; }

    /**
     * Returns the current objects type.
     * @return Type String.
     */
    public String getType() { return this.type; }

    /**
     * Sets a new type for the current object.
     * @param newType new Tpye String.
     */
    public void setType(String newType) { this.type = newType; }

    /**
     * Returns the start value of the current object.
     * @return Start LocalDateTime
     */
    public LocalDateTime getStart() { return this.start; }

    /**
     * Sets a new start value for the current object.
     * @param newStart new LocalDateTime start
     */
    public void setStart(LocalDateTime newStart) { this.start = newStart; }

    /**
     * Returns the end value of the current object.
     * @return End LocalDateTime
     */
    public LocalDateTime getEnd() { return this.end; }

    /**
     * Sets a ned end value for the current object.
     * @param newEnd new LocalDateTime end.
     */
    public void setEnd(LocalDateTime newEnd) { this.end = newEnd; }

    /*
    public LocalDateTime getCreateDate() { return this.createDate; }

    public void setCreateDate(LocalDateTime newCreateDate) { this.createDate = newCreateDate; }

    public String getCreatedBy() { return this.createdBy; }

    public void setCreatedBy(String newCreatedBy) { this.createdBy = newCreatedBy; }

    public Timestamp getLastUpdate() { return this.lastUpdate; }

    public void setLastUpdate(Timestamp newLastUpdate) { this.lastUpdate = newLastUpdate; }

    public String getLastUpdatedBy() { return this.lastUpdatedBy; }

    public void setLastUpdatedBy(String newLastUpdatedBy) { this.lastUpdatedBy = newLastUpdatedBy; }
     */

    /**
     * Returns the customer id for the current object.
     * @return Customer ID integer.
     */
    public int getCustomerId() { return this.customerId; }

    /**
     * Sets a new customer id for the current object.
     * @param newCustomerId new Customer ID integer.
     */
    public void setCustomerId(int newCustomerId) { this.customerId = newCustomerId; }

    /**
     * Returns the user ID for the current object.
     * @return User ID integer
     */
    public int getUserId() { return this.userId; }

    /**
     * Sets a new user ID for the given object
     * @param newUserId new User ID integer
     */
    public void setUserId(int newUserId) { this.userId = newUserId; }

    /**
     * Returns the contact ID for the current object
     * @return Contact ID integer
     */
    public int getContactId() { return this.contactId; }

    /**
     * Sets a new contact ID for the current object
     * @param newContactId new Contact ID integer
     */
    public void setContactId(int newContactId) { this.contactId = newContactId; }

    /**
     * Converts the given time zone in eastern time to the local time zone.
     * Lambda expression will convert the eastern time value to the value of the time in the local time zone.
     * @param easternTime List of eastern time zone values used for appointment setting.
     */
    public static void convertedToZoneTime(ObservableList<ZonedDateTime> easternTime) {
        easternTime.forEach(time -> {
            zonedTime.add(time.withZoneSameInstant(Scheduling_Application.localZone).toLocalDateTime().toLocalTime());
        });
    }

    /**
     * Error checks the given date and time values to prevent illogical or overlapped time values.
     * @param startDate inputted appointment date.
     * @param startTime inputted appointment start time.
     * @param endTime inputter appointment end time.
     * @return true if an error is found, false if not.
     */
    public static boolean errorCheckDates(LocalDate startDate, LocalTime startTime, LocalTime endTime, Customers customer) {

        if (LocalDateTime.of(startDate, startTime).isBefore(LocalDateTime.now())) {
            Scheduling_Application.displayError("Must choose a future appointment time.");
            return true;
        }
        if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            Scheduling_Application.displayError("Can not schedule appointments outside of business hours (8 am- 10 pm EST M-F");
            return true;
        }
        else if (startTime.isAfter(endTime)) {
            Scheduling_Application.displayError("Start Time must not be greater than End Time");
            return true;
        }


        for (Appointments a : DBAppointments.getAllAppointments()) {
            if (a.getCustomerId() == customer.getCustomerId()) {
                if (startDate.equals(a.getStart().toLocalDate())) {
                    if (startTime.isAfter(a.getStart().toLocalTime()) && startTime.isBefore(a.getEnd().toLocalTime())) {
                        Scheduling_Application.displayError("Overlapping appointment for current customer.");
                        return true;
                    }
                    else if (endTime.isAfter(a.getStart().toLocalTime()) && endTime.isBefore(a.getEnd().toLocalTime())) {
                        Scheduling_Application.displayError("Overlapping appointment for current customer.");
                        return true;
                    }
                    else if (a.getStart().toLocalTime().isAfter(startTime) && a.getStart().toLocalTime().isBefore(endTime)) {
                        Scheduling_Application.displayError("Overlapping appointment for current customer.");
                        return true;
                    }
                    else if (a.getEnd().toLocalTime().isAfter(startTime) && a.getEnd().toLocalTime().isBefore(endTime)) {
                        Scheduling_Application.displayError("Overlapping appointment for current customer.");
                        return true;
                    }
                }
            }
        }

        return false;

    }


}
