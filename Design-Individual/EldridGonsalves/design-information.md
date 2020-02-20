1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

To fufill this function, I have added the remindersList class, the constructor for this class takes a 
string inorder to name the list. When the remindersList is created , the constructor will also create a 
remindersListArray which holds the object individualReminder. From the reminderMain class, the method createReminder()
is used to create the reminder, and then a list can be made using the createReminderList method. Once a reminderList is created
from the previous method, you can access the reminderList's class's methods to add reminders using addtoList method, and delete
with deletefromList(), along with other methods inside to edit the reminders in the list 

2. The application must contain a database (DB) of reminders and corresponding data.

As you can see, there is the phone's database which holds the data needed in order for this 
 Reminder's App to function. There is also a class called dataPuller, which is used to pull and convert the
data so it may be manipulated in code. 

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the
name of the actual reminder (e.g., Dentist Appointment).

To fufill this function, in the remindersList class, you can specify the type in the constructor . And to name the
individual reminder, the nameReminder() method is there in the individualReminder class for it, it is also in the constructor
as well. 

4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB

users can search for the reminder by using the method getReminder() from the reminderMain class. 

5. The reminders must be saved automatically and immediately after they are modified.

the saveSession() method is in the individualReminder class, it will be used to save the reminder after it is
modified.

6. Users must be able to check off reminders in the list (without deleting them).

To fulfill this function, there is an array in the reminderMain class, that will hold all archived reminders that 
has been checked off, the array is called archivedReminders[]. use addtoArchive() method to add it to the archivedReminders[] array

7. Users must also be able to clear all the check-off marks in the reminder list at once.

To fulfill this function, I have the uncheckAll() method in the remindersList class

8. Check-off marks for the reminder list are persistent and must also be saved immediately.

To fullfill this, I have the saveSession() method in the remindersList class.

9. The application must present the reminders grouped by type.

Each individualReminder object has a ReminderType parameter to show what type it is, also when a reminderList is 
created , the constructor specifies a type of reminder list as well.

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”,
“Kid’s Reminders”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.

To fulfill this function, the reminderMain class gives the method createReminderList() and deleteReminderList()
methods to create and delete reminder lists.  While the remindersList class has methods like setReminderName() 
to rename the list and setReminderType to change its type. 

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.

when an individualReminder object is created, you can use it's setDate() and setTime() methods to set the 
time and date of when the user should be reminded. Once this is done, the notifyUser() method will be using those
information to alert the user once the date and time of a reminder has come. If you would like to repeat it, the notifyUserRepeat()
method is there so you can repeat it for the given number of days 

12. Extra Credit: Option to set up reminder based on location.

13. The User Interface (UI) must be intuitive and responsive

Not considered because it does not affect the design directly
