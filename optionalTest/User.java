package optionalTest;

import java.util.Optional;

public class User {
    private String mail;
    private String ID;

    public User()
    {
        System.out.println("creating a new user");
    }

    public User(String mail, String ID)
    {
        this.mail = mail;
        this.ID = ID;
    }

    public String getMail()
    {
        return mail;
    }

    public String getID()
    {
        return ID;
    }

    //return an Optional<String> object
    public Optional<String> getMailOptional()
    {
        return Optional.ofNullable(mail);
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public static User creatNewUser()
    {
        return new User();
    }
}
