import java.util.*;

/**
 * Represents a collection of Reminders.
 * Acts as the subject in the Observer Pattern.
 */
public class ReminderList
{
    private List<Reminder> reminders;
    private Set<ListObserver> observers = new HashSet<>();

    public ReminderList()
    {
        reminders = new ArrayList<Reminder>();
    }

    /** Adds a ListObserver to the current objects hash set. */
    public void addObserver(ListObserver observer)
    {
        observers.add(observer);
    }

    /** Notifies all current observers when a change to the list has happened. */
    public void notifyObservers()
    {
        for(ListObserver observer : observers)
        {
            observer.listUpdated();
        }
    }
    
    /** Add a single reminder to the list. */
    public void addReminder(Reminder rem)
    {
        reminders.add(rem);
        notifyObservers();
    }
    
    /** Add a complete list of reminders to the existing list. */
    public void addReminders(List<Reminder> newReminders)
    {
        reminders.addAll(newReminders);
    }
    
    /** Remove a reminder by index (i.e. 0 to #reminders-1) */
    public void removeReminder(int index)
    {
        reminders.remove(index);
        notifyObservers();
    }
    
    /** Retrieve a copy of the reminder list. */
    public List<Reminder> getReminders()
    {
        return Collections.unmodifiableList(reminders);
    }
}
