### Requirements

1. Created a class called "List" that will have reminders saved into.
Also the "List" class contains the methods to add, delete, and edit reminders in a list.

2. Created a class called "Reminder" and these reminders, along with its attributes, are saved into the database when created.

3. The "Reminder" class contains two attributes called reminderType and reminderName, which represent the type of the reminder and the actual name of the reminder, respectively.

4. The "User" class will contain a method called searchReminder that will look in the database for any reminders that have the similar attribute "reminderName".

5. Not considered because it does not affect the design directly.

6. The "List" class will contain a method called checkOffReminder, allowing a reminder to be checked off.
Also in the "Reminder" class, there will be an attribute called checkedOff which will be changed to true when the previous method is called.

7. The "List" class will contain a method called clearCheckMarks, allowing all check-off marks to be cleared at once.
The attribute "checkedOff" in the reminders contained in the list will then be changed to false when this method is called.

8. Not considered because it does not affect the design directly.

9. The "Reminder" class contains an attribute called reminderType, allowing the reminders to be grouped by type.

10. The "List" class contains an attribute called listType that will describe what type of list it is.
The "User" class contains the methods to create a list, rename a list, select a list, and delete a list.

11. The "Reminder" class will contain a method called setDayTimeAlert that will allow a reminder that is created to have an alert attached to it.
Also created is an "Alert" class that will contain the attributes alertTime and alertDate, representing the time and date of the alert.
These two attributes will be retrieved from the utility methods getTime and getDate.
Also the "Reminder" class contains a boolean attribute called repeatAlert that will allow the alert to be repeated if true.

12. Reminders based on location not completed.

13. Not considered because it does not affect the design directly.