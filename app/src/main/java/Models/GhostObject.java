package Models;

/**
 * Created by android on 11/3/17.
 */

public class GhostObject {

    private String Description;
    private String Name;
    private int Id;

    public GhostObject(String description, String name) {
        Description = description;
        Name = name;
    }

    public GhostObject(String description, String name,int id) {
        Description = description;
        Name = name;
        Id = id;
    }

    public GhostObject() {

    }

    public String getDescription() {
        return Description;
    }

    public int getid() {
        return Id;
    }

    public void setid(int id) {
        Id = id;
    }



    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

