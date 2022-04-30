import java.util.*;
import java.io.IOException;

/**
 * The controller of the reminders application. The responsibilities of this 
 * class are light -- it's a thin layer between the View and the Model.
 */
public class Controller implements ListObserver
{
    private static final String REMINDER_FILE = "reminders.txt";

    private ReminderList list;

    /**
     * Takes in an existing ReminderList -- the Model -- and populates it with
     * data read from the reminders file.
     */
    public Controller(ReminderList inReminderList)
    {
        list = inReminderList;
        addNewObserver(this);
        try
        {
            list.addReminders(FileManager.read(REMINDER_FILE));
        }
        catch(IOException e)
        {
            System.err.println("Warning: unable to open the reminders file! Continuing without it.");
        }
    }
    
    /** Used by the UI when a reminder needs adding. */
    public void addReminder(String task, Date dateObj)
    {
        list.addReminder(new Reminder(task, dateObj));
    }
    
    /** Used by the UI when a reminder needs removing. */
    public void removeReminder(int index)
    {
        list.removeReminder(index);
    }
    
    /** Used by the UI to obtain reminders to display. */
    public List<Reminder> getReminders()
    {
        return list.getReminders();
    }

    /** Allows the View to add itself as an observer to the model */
    public void addNewObserver(ListObserver observer)
    {
        list.addObserver(observer);
    }

    /** Implementing interface for Observer Pattern. */
    @Override
    public void listUpdated()
    {
        try
        {
            FileManager.write(REMINDER_FILE, getReminders());
        }
        catch(IOException e)
        {
            System.err.println("Warning: unable to write the reminders file! Continuing without updating.");
        }
    }
}