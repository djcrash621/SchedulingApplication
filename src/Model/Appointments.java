package Model;

import Main.Scheduling_Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDate;

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

    public int getAppointmentId() { return this.appointmentId; }

    public void setAppointmentId(int newAppointmentId) { this.appointmentId = newAppointmentId; }

    public String getTitle() { return this.title; }

    public void setTitle(String newTitle) { this.title = newTitle; }

    public String getDescription() { return this.description; }

    public void setDescription(String newDescription) { this.description = newDescription; }

    public String getLocation() { return this.location; }

    public void setLocation(String newLocation) { this.location = newLocation; }

    public String getType() { return this.type; }

    public void setType(String newType) { this.type = newType; }

    public LocalDateTime getStart() { return this.start; }

    public void setStart(LocalDateTime newStart) { this.start = newStart; }

    public LocalDateTime getEnd() { return this.end; }

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

    public int getCustomerId() { return this.customerId; }

    public void setCustomerId(int newCustomerId) { this.customerId = newCustomerId; }

    public int getUserId() { return this.userId; }

    public void setUserId(int newUserId) { this.userId = newUserId; }

    public int getContactId() { return this.contactId; }

    public void setContactId(int newContactId) { this.contactId = newContactId; }

    public static void convertedToZoneTime(ObservableList<ZonedDateTime> easternTime) {
        zonedTime = FXCollections.observableArrayList();
        for (ZonedDateTime n : easternTime) {
            zonedTime.add(n.withZoneSameInstant(Scheduling_Application.localZone).toLocalDateTime().toLocalTime());
        }
    }

    public static boolean checkFutureDate(LocalDate startDate, LocalTime startTime) {
        return startDate.isAfter(LocalDate.now()) || (startDate.isEqual(LocalDate.now()) && startTime.isAfter(LocalTime.now()));
    }

    public static boolean checkDate(LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(endDate);
    }

    public static boolean checkTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            return startDate.isAfter(endDate) || startDate.isEqual(endDate);
        }
        return false;
    }

    public static boolean errorCheckDates(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        if (LocalDateTime.of(startDate, startTime).isBefore(LocalDateTime.now())) {
            Scheduling_Application.displayError("Must choose a future appointment time.");
        }
        if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            Scheduling_Application.displayError("Can not schedule appointments outside of business hours (8 am- 10 pm EST M-F");
            return true;
        }
        else if (startTime.isAfter(endTime)) {
            Scheduling_Application.displayError("Start Time must not be greater than End Time");
            return true;
        }

        return false;

    }


}
