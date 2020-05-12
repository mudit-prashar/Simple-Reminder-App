# Design Document

*This is the template for your design document. The parts in italics are concise explanations of what should go in the corresponding sections and should not appear in the final document.*

**Author**: Eldrid and Anthony

## 1 Design Considerations

*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

- We can keep the UI very minimalistic as well and just have 1 drop down bar, showing ALL reminder lists, user will tap on the reminder list it wants and gets sent to another page where it gives the user options to add/edit the reminders in the selected list

  **Under the dropdown, it will have the following buttons** 

  - NEW LIST: Creates new reminder list
  - NEW REMINDER: Creates a new reminder, with the ability to assign it to a list
  - SEARCH REMINDER: Ability to search a specific reminder from all existing lists
  - SHOW LIST: Shows all the reminder lists that has been created thus far
  - SHOW REMINDERS: From the top dropdown, select target list, then hit show reminders to view the list's reminders 
  - DELETE LIST : From the top dropdown, select target list,then hit delete list to scrap the list

- There will be alerts for reminders as well, alerts can be based on days as well **Not implemented** 

- Choosing a reminder list the user wants to work on will lead to a UI for the chosen list
  - when choosing a reminder list to work on, it will have a check_list of all the reminders associated with the chosen list
  - user may check off a reminder/task if they have completed it **Partially implemented** 
  - user may select multiple reminders to move to a different list
  - user may select multiple reminders to delete from the list 
  
**The following considerations are now void**
- Just showing Reminder lists 1st when opening the app
- Reminder lists should be able to drop down (extend) and show the reminders associated with it
- Lists would display it's reminder type 


### 1.1 Assumptions

*Describe any assumption, background, or dependencies of the software, its use, the operational environment, or significant project issues.*

- Software is dependent on pulling data from the reminder's DB
- Access to phone's location if doing reminders by location.
- Access to the phone's notification panel

### 1.2 Constraints

*Describe any constraints on the system that have a significant impact on the design of the system.*

- If doing reminders based on location, software will need access to the phone's location all the time, this may not be battery friendly

- We are only able to display a certain number of reminders/ reminder lists at once
due to the limitation of screen size , if it's in landscape or horizontal mode etc

  **Team has decided to go with user defined reminder types**
- Depending on how the database is designed, the team is trying to decide weather to have the user define the list type or should they be able to choose from a set of pre defined list types. 
  


### 1.3 System Environment

*Describe the hardware and software that the system must operate in and interact with.*

- Must be an android OS
- Use as much little memory and battery as possible so it can be run on most android phones
- Run on phones that have at least API level 21

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram

<img src="https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/Component Diagram.png">


### 2.2 Deployment Diagram

*This section should describe how the different components will be deployed on actual hardware devices. Similar to the previous subsection, this diagram may be unnecessary for simple systems; in these cases, simply state so and concisely state why.*

- For this project the system is fairly simple. There is no need to access another PC or a main web server. Everything that is required is stored in the main application and database. Therefore a Deployment diagram is unnecessary. 

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

*In the case of an OO design, the internal structure of a software component would typically be expressed as a UML class diagram that represents the static class structure for the component and their relationships.*

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/Group UML.png">

### 3.2 Other Diagrams

*<u>Optionally</u>, you can decide to describe some dynamic aspects of your system using one or more behavioral diagrams, such as sequence and state diagrams.*

## 4 User Interface Design
*For GUI-based systems, this section should provide the specific format/layout of the user interface of the system (e.g., in the form of graphical mockups).*

  **For more pictures of the UI with directions please see userManual**

Most up to date main menu UI. 

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/updatedMainMenu.PNG">

Creating a new reminder, now with the ability to give you alerts, you assign it to a list from the dropdown 

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/updatedReminder.PNG">

Now you can search for reminders

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/updatedsearchReminders.PNG">

Updated show lists UI 

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/updatedShowLists.PNG">

## The Mockup's below are old and part of the Alpha app-----------------------------------------------------------------

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/updatedMainMenu.PNG"> 

UI of creating a new reminder, will have the ability to name it's type as well, and select the list to assign the reminder to 
<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/newReminder.PNG"> 

UI of new list.
<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/newList.PNG">

- Another look of the User Interface, instead of having multiple "Recent" lists being displayed, gives you the option to select any of the existing lists from the drop down

<img src = "https://github.com/mudit-prashar/Simple-Reminder-App/blob/master/Docs/Images/MockUp2_AnotherLook.PNG">
