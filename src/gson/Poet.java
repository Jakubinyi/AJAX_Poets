package gson;

import java.util.ArrayList;

public class Poet {
    private String name;
    private ArrayList<WorkWithPath> poems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<WorkWithPath> getPoems() {
        return poems;
    }

    public void setPoems(ArrayList<WorkWithPath> poems) {
        this.poems = poems;
    }
}
