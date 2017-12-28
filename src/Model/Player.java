package Model;

public class Player {
    public Player(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    public void playerInit(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    public String getName(){
        return name;
    }
    public char getMark(){
        return mark;
    }
    private String name;
    private char mark;
}
