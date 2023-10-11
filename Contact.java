public class Contact 
{
  private String name,
                 phoneNumber,
                 emailAddress,
                 address,
                 birthday,
                 note;

  private int numOfEvnents;
  public Events events;

  // Constructors
  public Contact() 
  {
    name = null;
    phoneNumber = null;
    emailAddress = null;
    address = null;
    birthday = null;
    note = null;

    numOfEvnents = 0;
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

    numOfEvnents = 0;
    events = new Events();
  }


  // Setters & Getters
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

  public void setNumOfEvnents(int numOfEvnents) {
    this.numOfEvnents = numOfEvnents;
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

  public int getNumOfEvnents() {
    return numOfEvnents;
  }
}
