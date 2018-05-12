package toplist;

public class Player {
    private String username;
    private int points;

    public Player(String username, int points) {
        if (username.equals(""))
            this.username = System.getProperty("user.name");
        else
            this.username = username;
        this.points = points;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return points + "\t\t\t " + username;
    }
}
