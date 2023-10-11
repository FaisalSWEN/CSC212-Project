/*------------------------------------------------------------------------------------
  Events Class: List of Event implmented using LinkedList ADT
  ------------------------------------------------------------------------------------*/
public class Events
{
  private int numOfEvnents;
  public LinkedList<Event> list;


  // Constructors
  public Events()
  {
    numOfEvnents = 0;
    list = new LinkedList<Event>();
  }


  // Methods
  public static Event createEvent(String evnetTitle, String dateAndTime, String location)
  {
    return new Event(evnetTitle, dateAndTime, location);
  }


  // Getters
  public int getNumOfEvnents() {
    return numOfEvnents;
  }
}



/*------------------------------------------------------------------------------------
  Event Class
  ------------------------------------------------------------------------------------*/
class Event 
{
  protected String evnetTitle,
                   dateAndTime,
                   location;
  
  protected int numOfContacts;
  public LinkedList<Contact> involvedContacts;


  // Constructors
  public Event() 
  {
    evnetTitle = null;
    dateAndTime = null;
    location = null;

    numOfContacts = 0;
    involvedContacts = new LinkedList<Contact>();
  }

   public Event(String evnetTitle, String dateAndTime, String location) 
   {
    this.evnetTitle = evnetTitle;
    this.dateAndTime = dateAndTime;
    this.location = location;

    numOfContacts = 0;
    involvedContacts = new LinkedList<Contact>();
  }

  
  // Setters & Getters
  public void setEvnetTitle(String evnetTitle) {
    this.evnetTitle = evnetTitle;
  }

  public void setDateAndTime(String dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getEvnetTitle() {
    return evnetTitle;
  }

  public String getDateAndTime() {
    return dateAndTime;
  }

  public String getLocation() {
    return location;
  }

  public int getNumOfContacts() {
    return numOfContacts;
  }
}
