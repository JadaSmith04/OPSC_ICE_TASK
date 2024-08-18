ICE TASK 2

Goals Management App

This application allows users to manage their goals by adding, viewing, and marking goals as completed. The goals are stored in an SQLite database.
 Features

-Add Goals: Users can add new goals with a title and description.
-View Goals: Goals are displayed in a RecyclerView.
- Mark Goals as Completed: Users can mark a goal as completed, which removes it from the list and the database.
- Settings Page: Navigate to the settings page for additional configurations 

How to Use

1. Adding a Goal:
   - Open the app.
   - Enter a title and description for your goal.
   - Click the "Add Goal" button to save the goal.
   - The goal will appear in the list.

2. Viewing a Goal:
   - Goals are displayed in the main screen's list.
   - Tap on a goal to view its details.

3. Marking a Goal as Completed:
   - Tap on a goal in the list.
   - In the goal detail screen, click the "Mark as Completed" button.
   - The goal will be removed from the list and deleted from the database.


4. Settings:
   - Click the "Settings" button to navigate to the settings page.
   - Customize your app settings where you are able to switch from light mode to dark mode. 
   - To switch modes simply click the switch that says dark mode.

Database Schema

The app uses an SQLite database with the following schema:

- Table Name: Goals
-Columns:
  - _id (INTEGER PRIMARY KEY AUTOINCREMENT) - The unique ID of the goal.
  - title (TEXT) - The title of the goal.
  - description (TEXT) - The description of the goal.


