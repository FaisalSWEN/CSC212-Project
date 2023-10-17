/************* Project ***************
  CLASS: Contact.java
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

public class Contact implements Comparable
{
  private String name,
                 phoneNumber,
                 emailAddress,
                 address,
                 birthday,
                 note;

  public Events events;

  
  // @ Constructors
  public Contact() 
  {
    name = null;
    phoneNumber = null;
    emailAddress = null;
    address = null;
    birthday = null;
    note = null;

    events = new Events();
  }

  public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String note) 
  {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.address = address;
    this.birthday = birthday;
    this.note = note;

    events = new Events();
  }

  public Contact(String name) 
  {
    this.name = name;
    this.phoneNumber = null;
    this.emailAddress = null;
    this.address = null;
    this.birthday = null;
    this.note = null;
  }

  // @ Methods
  // #1 Implmeneted Methods
  @Override
  public boolean equals(Object c)
  {
    if (c instanceof Contact)
    {
      return ((this.name).equalsIgnoreCase(((Contact) c).getName())
            || (this.phoneNumber).equalsIgnoreCase(((Contact) c).getPhoneNumber()));
    }

    else return false;
  }

  @Override
  public boolean precedes(Object c)
  {
    if (c instanceof Contact)
    {
      if ((this.name).compareToIgnoreCase(((Contact) c).getName()) < 0)
        return true;

      else return false;
    }

    else return false;
  }


  // #2 Setters & Getters
  public void setName (String name) {
    this.name = name;
  }

  public void setPhoneNumber (String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setEmailAddress (String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setAddress (String address) {
    this.address = address;
  }

  public void setBirthday (String birthday) {
    this.birthday = birthday;
  }

  public void setNote (String note) {
    this.note = note;
  }
  

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getAddress() {
    return address;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getNote() {
    return note;
  }
}
