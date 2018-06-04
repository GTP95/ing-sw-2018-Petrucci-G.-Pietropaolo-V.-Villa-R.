package Progetto_Ing_Sw.com.server.Model;

public class ToolCard extends Card {

    private String title;
    private String description;
    private String info;
    private int ID;
    private boolean firstUsage;
    private String color; //Single ClientPlayer Only
    //TODO EFFECT

    public ToolCard(String title, String description, String info, int ID, boolean firstUsage, String color) {
        this.title = title;
        this.description = description;
        this.info = info;
        this.ID = ID;
        this.firstUsage = firstUsage;
        this.color = color;
    }

    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getInfo() {return info;}
    public int getID() {return ID;}
    public boolean isFirstUsage() {return firstUsage;}
    public String getColor() {return color;}
}
