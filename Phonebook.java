/************* Project ***************
  CLASS: LinkedList.java
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

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
/*------------------------------------------------------------------------------------
  Criterias enumerations: specifically listed Criterias
  ------------------------------------------------------------------------------------*/
enum Criterias // Search Criterias
{
  NAME,
  PHONENUMBER,
  EMAILADDRESS,
  ADDRESS,
  BIRTHDAY,
  DATETIME,
  EVENT
}



/*------------------------------------------------------------------------------------
  Phonebook Class
  ------------------------------------------------------------------------------------*/
public class Phonebook 
{
  public static LinkedList<Contact> contacts = new LinkedList<Contact>();
  public static Events events = new Events();

  public static boolean validateJavaDate(String strDate)
  {
	/* Check if date is 'null' */
    if (strDate.trim().equals(""))
    {
      return true;
    }
    /* Date is not 'null' */
    else
    {
      /*
      * Set preferred date format,
      * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
      SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
      sdfrmt.setLenient(false);
      /* Create Date object
      * parse the string into date*/
      try
      {
        Date javaDate = sdfrmt.parse(strDate); 
      }
      /* Date format is invalid */
      catch (ParseException e)
      {
        return false;
      }
      /* Return true if date format is valid */
      return true;
    }
  }

  // @ Criterias Method
  // # is: check whether the data is belong to the SC category
  public static boolean is(String data, Criterias type)
  {
    switch(type)
    {
      // Case 1
      case NAME: // Assume that a name and event title can includes numbers
      case EVENT: 
      case ADDRESS: // Can Include anything
        return true; 


      // Case 2:
      case PHONENUMBER:
      {
        try
        {
          Integer.parseInt(data);
          return true;
        }
        
        catch (Exception e) 
        {
          return false;
        }
      }


      // Case 3
      case EMAILADDRESS:
      {
        try // Check if the data is pure numbers
        {
          Double.parseDouble(data);
          return false;
        }
        
        catch (Exception e) 
        {
          return true;
        }
      }
    

      // Case 4
      case BIRTHDAY:
      {
        try // check its format, whether it's: 'MM/DD/YYYY' or 'DD/MM/YYYY'
        {
          if (data.length() != 10) // Check Length
            return false;

          if (data.charAt(2) != '/' || data.charAt(5) != '/') // Check forward slashes
            return false;
          
          // Check numbers
          Integer.parseInt("" + data.charAt(0));
          Integer.parseInt("" + data.charAt(1));
          Integer.parseInt("" + data.charAt(3));
          Integer.parseInt("" + data.charAt(4));
          Integer.parseInt("" + data.charAt(6));
          Integer.parseInt("" + data.charAt(7));
          Integer.parseInt("" + data.charAt(8));
          Integer.parseInt("" + data.charAt(9));

          return validateJavaDate(data);
        } 
      
        catch (Exception e) 
        {
          return false;
        }
      }


      // Case 5
      case DATETIME:
      {
        try 
        {
          if (data.length() != 16) // Check Length
            return false;

          // check date format, whether it's: 'MM/DD/YYYY' or 'DD/MM/YYYY'
          if(is(data.substring(0, 10), Criterias.BIRTHDAY) == false)
            return false;
          
          // check time format: HH:MM
          if (data.charAt(13) != ':') // Check colon
            return false;
          
          Integer.parseInt("" + data.charAt(11));
          Integer.parseInt("" + data.charAt(12));
          Integer.parseInt("" + data.charAt(14));
          Integer.parseInt("" + data.charAt(15));
          
          return true;
        } 
      
        catch (Exception e) 
        {
          return false;
        }
      }


      // Default
      default:
      return false;
    }
  } 


  // @ Methods for events
  // #1 clearEvents: free every event within the events-list from Contact c
  public static void clearEvents(Contact c)
  {
    (events.list).findFirst();
    for(int i = 0; i < (events.list).getLength(); i++)
    {
      ((events.list).retrieve()).freeContact(c);

      if ((events.list).last() == false)
        (events.list).findNext();
    }
  }

  // #2 checkEvents: -this fuction should be called after clearEvents- delete event if empty
  public static void checkEvents()
  {
    (events.list).findFirst();
    for(int i = 0; i < (events.list).getLength(); i++)
    {
      if(((events.list).retrieve()).involvedContacts.empty())
      {
        (events.list).remove();
      }

      else 
      {
        if ((events.list).last() == false)
          (events.list).findNext();
      }
    }
  }

  // #3 conflictEvent: check if it conflicts with another event within the contact
  public static boolean conflictEvent(Event e, Contact c)
  {
    ((c.events).list).findFirst();
    for(int i = 0; i < ((c.events).list).getLength(); i++)
    {
      if(((c.events.list).retrieve()).getDateAndTime().equalsIgnoreCase(e.getDateAndTime()))
      {
        return true;
      }

      else 
      {
        if ((c.events.list).last() == false)
          (c.events.list).findNext();
      }
    }

    return false;
  }

  // #4 addEvent
  public static boolean addEvent(Event e, String name) throws Exception
  {
    Contact c = uniqueSearch(name, Criterias.NAME); // Check if the contact is exist
    if (c == null)
      throw new Exception("Error: No Contact with name '" + name + "' has been found");
    
    if(is(e.getDateAndTime(), Criterias.DATETIME) == false)
      throw new Exception("Error: Invaild Input, check the date and time and its format");

    if ((events.list).find(e) == false) // if the event is new, add it to the list
    {
      if (conflictEvent(e, c)) // Check condition for date conflict
         throw new Exception("\nError: Date conflict");

      (events.list).insert(e);
      ((c.events).list).insert(e);
      (e.involvedContacts).insert(c);
      return true;
    }

    else // the event is already exist case
    {
      Event tmp = (events.list).retrieve();
      if (((c.events).list).find(tmp)) // the event is already exist within the contact
        return false;
      
      else
      {
        if (conflictEvent(tmp, c)) // Check condition for date conflict
          throw new Exception("Error: Date conflict");

         ((c.events).list).insert(tmp);
         (tmp.involvedContacts).insert(c);
         return true;
      }
    }
  }


  // @ Methods for contacts
  // #1
  static boolean add(Contact c)
  {
    if (contacts.find(c)) 
      return false;

    contacts.insert(c);
    return true;
  }

  // #2
  static boolean delete(Contact c)
  {
    if(contacts.find(c))
    {
      clearEvents(contacts.retrieve());
      checkEvents();
      contacts.remove();
      return true;
    }

    else return false;
  }

  // #3
  static LinkedList<Contact> search(String key, Criterias type) throws Exception
  {
    if (!is(key, type)) // Check Condition
      throw new Exception("Error: Invaild input, check the input and its Category");


    LinkedList<Contact> tmp = new LinkedList<Contact>();
    switch(type)
    {
      // Case 1
      case EMAILADDRESS:
        contacts.findFirst();
        for (int i = 0; i < contacts.getLength(); i++)
        {
          if ((contacts.retrieve()).getEmailAddress().equalsIgnoreCase(key))
            tmp.insert(contacts.retrieve());
          
          if ((contacts).last() == false)
            (contacts).findNext();
        }

        return tmp;

      // Case 2
      case ADDRESS:
        contacts.findFirst();
        for (int i = 0; i < contacts.getLength(); i++)
        {
          if ((contacts.retrieve()).getAddress().equalsIgnoreCase(key))
            tmp.insert(contacts.retrieve());
          
          if ((contacts).last() == false)
            (contacts).findNext();
        }

        return tmp;

      // Case 3
      case BIRTHDAY:
        contacts.findFirst();
        for (int i = 0; i < contacts.getLength(); i++)
        {
          if ((contacts.retrieve()).getBirthday().equalsIgnoreCase(key))
            tmp.insert(contacts.retrieve());
          
          if ((contacts).last() == false)
            (contacts).findNext();
        }

        return tmp;
      
      // Default
      default:
        return null;
    }
  }

  static Contact uniqueSearch(String key, Criterias type) throws Exception
  {
    if (!is(key, type)) // Check Condition
      throw new Exception("Error: Invaild input, check the input and its Category");
    
    switch(type)
    {
      case NAME:
        contacts.findFirst();
        for (int i = 0; i < contacts.getLength(); i++)
        {
          if ((contacts.retrieve()).getName().equalsIgnoreCase(key))
            return contacts.retrieve();

          if ((contacts).last() == false)
            (contacts).findNext();
        }

        return null;


      case PHONENUMBER:
        contacts.findFirst();
        for (int i = 0; i < contacts.getLength(); i++)
        {
          if ((contacts.retrieve()).getPhoneNumber().equalsIgnoreCase(key))
            return contacts.retrieve();

          if ((contacts).last() == false)
            (contacts).findNext();
        }

      default:
        return null;
    }
  }

  
  // @ Print Methods
  // #1 printContactsByFirstName
  public static String printContactsByFirstName(String firstName)
  {
    (contacts).findFirst();
    String name;
    int endIndex;
    String print = "";

    for(int i = 0; i < contacts.getLength(); i++)
    {
      name = (contacts.retrieve()).getName();
      endIndex = name.indexOf(" ");
      if (endIndex != -1)
        name = name.substring(0, endIndex);
     
      if ((name).equalsIgnoreCase(firstName))
      {
        print +=
        "Name: " + (contacts.retrieve()).getName() + "\n"
      + "Phone Number: " + (contacts.retrieve()).getPhoneNumber() + "\n"
      + "Email Address: " + (contacts.retrieve()).getEmailAddress() + "\n"
      + "Address: " + (contacts.retrieve()).getAddress() + "\n"
      + "Birthday: " + (contacts.retrieve()).getBirthday() + "\n"
      + "Note: " + (contacts.retrieve()).getNote() + "\n\n";

        if (contacts.last() == false)
          contacts.findNext();
      }
    }

    return print;
  }

  // #2 printContactsByEventShare
  public static void printContactsByEventShare(Event e)
  {
    if((events.list).find(e) == false)
    {
      System.out.println("No Event found");
      return;
    }

     System.out.println("\nEvent found!");
     System.out.println("Event title: " + ((events.list).retrieve()).geteventTitle());

     LinkedList<Contact> tmp = ((events.list).retrieve()).involvedContacts; // shorthand for contact list
     tmp.findFirst();
     for(int i = 0; i < tmp.getLength(); i++)
    {
      System.out.println("Contact Name: " + (tmp.retrieve()).getName());

      if (tmp.last() == false)
        tmp.findNext();
    }

    System.out.println(
      "Event date and time (MM/DD/YYYY HH:MM): " + ((events.list).retrieve()).getDateAndTime() + "\n"
    + "Event location: " + ((events.list).retrieve()).getLocation() + "\n"
      );
  }

  // #3 printEventsByContact
  public static void printEventsByContact(Contact c)
  {
    if(contacts.find(c) == false)
    {
      System.out.println("\nContact not found");
      return;
    }
    
      System.out.println("\nContact found!");

      LinkedList<Event> tmp = ((contacts.retrieve()).events).list; // shorthand for event list
      tmp.findFirst();
      for (int i = 0; i <tmp.getLength(); i++)
      {
        System.out.println
        (
        "Event title: " + (tmp.retrieve()).geteventTitle() + "\n"
      + "Event date and time (MM/DD/YYYY HH:MM): " + (tmp.retrieve()).getDateAndTime() + "\n"
      + "Event location: " + (tmp.retrieve()).getLocation() + "\n"
        );

        if (tmp.last() == false)
          tmp.findNext();
      }
  }

  // #4 print Event alphabetically
  public static void printEventsAlphabetically()
  {
    (events.list).findFirst();
    for(int i = 0; i < (events.list).getLength(); i++)
    {
      System.out.println
      (
    "\nEvent title: " + ((events.list).retrieve()).geteventTitle() + "\n"
    + "Event date and time (MM/DD/YYYY HH:MM): " + ((events.list).retrieve()).getDateAndTime() + "\n"
    + "Event location: " + ((events.list).retrieve()).getLocation() + "\n"
      );

      if ((events.list).last() == false)
        (events.list).findNext();
    }
  }


  // @ Main Method
  public static void main(String args[])
  {
    // General Variables
    Scanner input = new Scanner(System.in);
    int choice = -1;

    // Hold Variables for contact
    String name;
    String phone;
    String email;
    String address;
    String birthday;
    String note;
  
    // Hold Variables for Event
    String eventName;
    String date;
    String location;

    // Tmp variables
    Contact cTmp;

    // Tmp lists
    LinkedList<Contact> cTmpList;

    System.out.println("Welcome to the Linked Phonebook!");
    do
    {
      System.out.print
      (
     "\nPlease choose an option:" + '\n'
      +"1. Add a contact" + '\n'
      +"2. Search for a contact" + '\n'
      +"3. Delete a contact" + '\n'
      +"4. Schedule an event" + '\n'
      +"5. Print event details" + '\n'
      +"6. Print contacts by first name" + '\n'
      +"7. Print all events alphabetically" + '\n'
      +"8. Exit" + '\n'
      
   +"\nEnter your choice:"
      );

      try
      {
        choice = input.nextInt();
        switch(choice)
        {
          case 1: // ADD CASE
          {
            input.nextLine(); // Solution
            System.out.print("\nEnter the contact's name: ");
            name = input.nextLine();
  
            System.out.print("Enter the contact's phone number: ");
            phone = input.nextLine();
            if(is(phone, Criterias.PHONENUMBER) == false)
            {
              System.out.println("\nError: Invaild Number input\n\n");
              break;
            }
  
            System.out.print("Enter the contact's email address: ");
            email = input.nextLine();
             if(is(email, Criterias.EMAILADDRESS) == false)
            {
              System.out.println("\nError: Invaild Email input\n\n");
              break;
            }
  
            System.out.print("Enter the contact's address: ");
            address = input.nextLine();
  
            System.out.print("Enter the contact's birthday: ");
            birthday = input.nextLine();
            if(is(birthday, Criterias.BIRTHDAY) == false)
            {
              System.out.println("\nError: Invaild birthday input, Check the format.\n\n");
              break;
            }
  
            System.out.print("Enter the contact's note:" );
            note = input.nextLine();
  
            if (add(new Contact(name, phone, email, address, birthday, note)))
            {
              System.out.println("\n\nContact added successfully!");
            }
            
            else
            {
              System.out.println("\nThe contact is already exist.");
            }

            break;
          }
  
  
          case 2: // SEARCH CASE
          {
            System.out.print
            (
            "\n Enter search criteria:" + '\n'
              +"1. Name" + '\n'
              +"2. Phone Number" + '\n'
              +"3. Email Address" + '\n'
              +"4. Address" + '\n'
              +"5. Birthday" + '\n'
  
            + "\nEnter your choice:"
            );
  
            choice = input.nextInt();
  
            switch(choice)
            {
              case 1: // NAME CASE
              case 2: // PHONE NUMBER
                input.nextLine(); // Solution
                if (choice == 1) 
                {
                  System.out.print("\nEnter the contact's name: ");
                  cTmp = uniqueSearch(input.nextLine(), Criterias.NAME);
                }

                else 
                {
                  System.out.print("\nEnter the contact's Phone Number: ");
                  cTmp = uniqueSearch(input.nextLine(), Criterias.PHONENUMBER);
                }

                
                if (cTmp != null)
                {
                  System.out.println
                  (
                  "\nContact found!" + "\n\n"
                  + "Name: " + cTmp.getName() + "\n"
                  + "Phone Number: " + cTmp.getPhoneNumber() + "\n"
                  + "Email Address: " + cTmp.getEmailAddress() + "\n"
                  + "Address: " + cTmp.getAddress() + "\n"
                  + "Birthday: " + cTmp.getBirthday() + "\n"
                  + "Note: " + cTmp.getNote() + "\n"
                  );
                }

                else 
                {
                  System.out.println("\nContact hasn't found");
                }

                break;

              case 3:
              case 4:
              case 5:
              input.nextLine(); // Solution
                if (choice == 3) 
                  {
                    System.out.print("\nEnter the contact's Email Address: ");
                    cTmpList = search(input.nextLine(), Criterias.EMAILADDRESS);
                  }

                else if (choice == 4)
                {
                  System.out.print("\nEnter the contact's Address: ");
                  cTmpList = search(input.nextLine(), Criterias.ADDRESS);
                }

                else
                {
                  System.out.print("\nEnter the contact's Birthday: ");
                  cTmpList = search(input.nextLine(), Criterias.BIRTHDAY);
                }

                if (cTmpList != null)
                {
                  System.out.println("\nContact found!");

                  for(int i = 0; i < cTmpList.getLength(); i++)
                  {
                    cTmp = cTmpList.retrieve();
                    System.out.println
                    (
                      "Name: " + cTmp.getName() + "\n"
                    + "Phone Number: " + cTmp.getPhoneNumber() + "\n"
                    + "Email Address: " + cTmp.getEmailAddress() + "\n"
                    + "Address: " + cTmp.getAddress() + "\n"
                    + "Birthday: " + cTmp.getBirthday() + "\n"
                    + "Note: " + cTmp.getNote() + "\n"
                    );

                    if (cTmpList.last() == false)
                      cTmpList.findNext();
                  }
                }
                
                else 
                {
                  System.out.println("\nContact hasn't found");
                }

                break;

              default: // INVAILD INPUT CASE
                System.out.println("\nInvaild Choice: choose a number from 1 up to 5");
                break;
            }

            break;
          }
  
  
          case 3: // DELETE CASE
          {
            input.nextLine(); // Solution
            System.out.print("\nEnter the contact's name: ");
            name = input.nextLine();
  
            if(delete(new Contact(name)))
            {
              System.out.println("\n\nContact deleted successfully!");
            }
  
            else
            {
              System.out.println("\nNo Contact with name '" + name + "' has been found");
            }

            break;
          }
  
          case 4: // Schedule an event Case
          {
            input.nextLine(); // Solution
            System.out.print("Enter event title: ");
            eventName = input.nextLine();

            System.out.print("Enter contact name: ");
            name = input.nextLine();

            System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
            date = input.nextLine();

            System.out.print("Enter event location: ");
            location = input.nextLine();

            try 
            {
              if (addEvent(new Event(eventName, date, location), name) == true)
              {
                System.out.println("\nEvent scheduled successfully!");
              }

              else
              {
                System.out.println("\nEvent already exist!");
              }
            } 
            
            catch (Exception e) 
            {
              System.out.println(e.getMessage());
            }

            break;
          }

          case 5: // PRINT EVENT DETAILS
          {
            input.nextLine(); // Solution
            System.out.print
              (
                "Enter search criteria:" + '\n'
                +"1. contact name" + '\n'
                +"2. Event tittle" + '\n'
    
              + "\nEnter your choice:"
              );
  
            choice = input.nextInt();
            input.nextLine(); // Solution
  
            switch(choice)
            {
              case 1: // Search by contact name Case
              {
                System.out.print("Enter the Contact's name:");
                name = input.nextLine();

                printEventsByContact(new Contact(name));
                break;
              }

              case 2:// Search by Event Tittle
              {
                System.out.print("Enter the Event's Title:");
                eventName = input.nextLine();

                printContactsByEventShare(new Event(eventName));
                break;
              }

              default: // INVAILD INPUT CASE
                System.out.println("Invaild Choice: choose a number from 1 up to 2");
                break;
            }
            break;
          }

          case 6: // PRINT CONTACTS BY FIRST NAME
          {
            System.out.print("Enter the first name: ");
            name = input.next();
            name = printContactsByFirstName(name);

            if (name != "")
            {
              System.out.println("\nContacts found!");
              System.out.println(name);
            }

            else
            {
              System.out.println("No Contacts have been found");
            }

            break;
          }

          case 7: // PRINT ALPHABETICALLY CASE
            printEventsAlphabetically();
            break;

          case 8: // EXIT CASE
            System.out.println("Goodbye!");
            input.close();
            break;
  
  
          default: // INVAILD INPUT CASE
            System.out.println("Invaild Choice: choose a number from 1 up to 8");
            break;
        }
      }

      catch (InputMismatchException e)
      {
        System.out.println("\nInvaild input: Non-numeric input");
        choice = -1;
        input.nextLine(); // solution
      }

      catch (Exception e)
      {
        System.out.println("\n" + e.getMessage());
        choice = -1;
        input.nextLine(); // solution
      }
    } while (choice != 8);
  }
}

