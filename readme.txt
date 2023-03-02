Scheduling Application:
This application is to connect to the global Consulting Organization database for appointments and provided an interface to view and edit customers, view and edit appointments, and generate reports.


@author Jedidiah Yoxtheimer, jyoxthe@wgu.edu
@version 1.5 03-02-23


IntelliJ Community 2021.1.3x64
Java SE 17.0.1
JavaFX-SDK-11.0.2


•  directions for how to run the program
Upon starting the application, login with valid username and password credentials. Once provided, the application opens on the customers page. Custoemrs can be added, edited, or deleted. Customers can be searched for by name or ID, and can be filtered by their country or division. All filters can be reset with the reset button. 
Editing or Adding Customers will take the user to new edit and adding pages where existing customer values can be changed or new customer values can be added.
Deleting a customer will delete all of their scheduled appointments.

User can log out from the customer page. Button to 'View Appointments' will take the user to the appointments page. Appointments can be added, edited, or deleted. Appointments can be viewed by all appointments, by all appointments occurring in the coming month, or by all appointments occurring in the coming week.
Editing or Adding Appointments will take the user to new edit and adding pages where existing appointments can be changed or new appointment values can be addedd.
When adding and editing appointments, appointment times can not be out of logical time order, can not be scheduled in the past, and the appointments for a given customer can not overlap in time with existing appointments.

The appointment page has a button 'Reporting', taking the user to the reporting page. Three reports are available.
1. Appointment count and displayed filtered by the given type and month. Select a type and month and the count and available appointments will be displayed.
2. Schedule of Appointments for a selected contact. Select a contact in the dropdown, and their appointments will be displayed in order of their occurrence.
3. Schedule of Appointments for a selected user. Select a user in the dropdown, and their appointments will be displayed in order of their occurrence.

•  a description of the additional report of your choice you ran in part A3f
Report 3 of Part A3F was an appointment generating a schedule of appointments for each user in the database. Dropbox to select a user and all their assigned populate the table in order of occurence.

•  the MySQL Connector driver version number, including the update number (e.g., mysql-connector-java-8.1.23)
mysql-connector-java-8.0.25
