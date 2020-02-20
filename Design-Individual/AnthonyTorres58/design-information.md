# Anthony Torres 
# CS 370
Assignment 5

1.	A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in the list. 
 - For this, I created addReminder(),delReminder(), and editReminder() in the List Class.
2.	The application must contain a database (DB) of reminders and corresponding data.
 - Created a database class ReminderDB composed of reminder types and reminders.
3.	Users must be able to add reminders to a list by picking them from a hierarchical list, where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment). 
 - Created a class of preset reminders and reminder types. The presets are subclasses of the general reminder and reminder type classes.
 - The addReminderFromList(typeName: String) method will search for "typeName" in the preset DefaultType class.
4.	Users must also be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the reminder, or add a new one, and then save the new reminder, together with its type, in the DB. 
 - addReminderFromName(name: String) will search ReminderDB for a Reminder with the same name.
 - If one isn't found, it will add a new Reminder.
5.	The reminders must be saved automatically and immediately after they are modified. 
 - Not considered, I assume everything in the application is saved automatically after they are modified .
6.	Users must be able to check off reminders in the list (without deleting them). 
 - checkReminder(r: Reminder) will check off the given reminder.
7.	Users must also be able to clear all the check-off marks in the reminder list at once. 
 - checkAll() will check off all reminders in the list.
8.	Check-off marks for the reminder list are persistent and must also be saved immediately. 
 - Not considered, I assume everything in the application is saved automatically after they are modified .
9.	The application must present the reminders grouped by type. 
 - To show reminders are grouped by type, I made the Type classes filled with reminders.
10.	The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”, “Kid’s Reminders”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists. 
 - The main application class has 5 methods dedicated for these abilities.
11.	The application should have the option to set up reminders with day and time alert. If this option is selected allow option to repeat the behavior. 
  - The setAlert(dateTime: Date) method uses the Date utility.
  - AlertUser() will alert the user when the given date occurs.
12.	Extra Credit: Option to set up reminder based on location. 
13.	The User Interface (UI) must be intuitive and responsive. 
 - Not considered
