/************* Project ***************
  CLASS: Events.java
  CSC212 Data structures - Project phase I
  Fall 2023

  EDIT DATE:
  10-17-2023

  TEAM:

  Faisal AlBader - 443102460
  Bader Alshehri - 443100744
  Turki Alhussan - 443101793

  AUTHORS:
  Faisal AlBader , (ID: 443102460)
***********************************/

/*------------------------------------------------------------------------------------
  Events Class: List of Event implmented using LinkedList ADT
  ------------------------------------------------------------------------------------*/
public class Events
{
  public LinkedList<Event> list;


  // @ Constructors
  public Events()
  {
    list = new LinkedList<Event>();
  }
}



/*------------------------------------------------------------------------------------
  Event Class
  ------------------------------------------------------------------------------------*/
class Event implements Comparable
{
  private String eventTitle,
                 dateAndTime,
                 location;
  
  public LinkedList<Contact> involvedContacts;


  // Constructors
  public Event() 
  {
    eventTitle = null;
    dateAndTime = null;
    location = null;

    involvedContacts = new LinkedList<Contact>();
  }

  public Event(String title) 
  {
    eventTitle = title;
    dateAndTime = null;
    location = null;

    involvedContacts = new LinkedList<Contact>();
  }

   public Event(String eventTitle, String dateAndTime, String location) 
   {
    this.eventTitle = eventTitle;
    this.dateAndTime = dateAndTime;
    this.location = location;

    involvedContacts = new LinkedList<Contact>();
  }
  
  // @ Methods
  // #1 Implmented Methods
  @Override
  public boolean equals(Object e)
  {
    if (e instanceof Event)
      return ((this.eventTitle).equalsIgnoreCase(((Event) e).geteventTitle()));
    
    else return false;
  }

  @Override
  public boolean precedes(Object e)
  {
    if (e instanceof Event)
    {
      if ((this.eventTitle).compareToIgnoreCase(((Event) e).geteventTitle()) < 0) 
        return true;
      
      else return false;
    }

    else return false;
  }

  // #2 freeContact: free this event from contact c (remove the contact from involved list)
  public void freeContact(Contact c) 
  {
    if(involvedContacts.exist(c))
      involvedContacts.remove();
  }


  // #3 Setters & Getters
  public void seteventTitle(String eventTitle) {
    this.eventTitle = eventTitle;
  }

  public void setDateAndTime(String dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String geteventTitle() {
    return eventTitle;
  }

  public String getDateAndTime() {
    return dateAndTime;
  }

  public String getLocation() {
    return location;
  }
}
