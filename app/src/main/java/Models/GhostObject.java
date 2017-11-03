package Models;

/**
 * Created by android on 11/3/17.
 */

public class GhostObject {

    private String Description;
    private String Name;

    public GhostObject(String description, String name) {
        Description = description;
        Name = name;
    }

    public GhostObject() {

    }

    public String getDescription() {
        return Description;
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

