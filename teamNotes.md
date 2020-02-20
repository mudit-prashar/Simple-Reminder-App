## Good
- newReminder
- viewList
- showLists
- showReminders
- viewReminder

## Not Good
- viewlist class file
- alerts 

## To-Do
- Test Plan and other documents need to be updated 
- checkmarks for reminders 
- new Reminder should have autofill feature which is a requirement 
	- it should look into the RTable for all the reminders and provide the user with options 
	
- discuss option layout in MainActivity (consider using a hamburger menu vs buttons).
- Add scrollview to all activities
- Add closeKeyboard method to activities where necessary (copy the method from searchReminders class)
- Discuss whether reminder type can be custom text or picked from a set list.
- ViewReminder: 
	- Edit- db.update(String ReminderID, Values[] values)
	- Delete- needs updates (fill in the blanks using methods in DBHelper or make new one)
	- Switch listName field to list. Should pull existing lists from db.

<<<<<<< HEAD
- Multiple items can be selected and opened from list if tapped quickly.
- showreminders don't work 
- layout changes for each activity to display data within the screen
- alerts not implemented 
=======
## Ideas for V2
- Hamburger Menu for Main Activity Options

## Bugs
- Multiple items can be selected and opened from list if tapped quickly.
>>>>>>> dev
