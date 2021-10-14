package c.o.floridaman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {
    public final static String saveFileName = "players.dat";
    private static int numPlayers = 0;
    private int id;
    private String username;
    private String password;
    private String fullName;
    private int highScore;
    private static Player currPlayer;

    public Player(String u, String p, String n) {
        id = numPlayers++;
        username = u;
        password = p;
        fullName = n;
        highScore = 0;
    }

    @Override
    public boolean equals(Object other) {
        return ((Player)other).getId() == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public static HashMap<Integer,Player> loadPlayers(File dir) {
        try {
            HashMap<Integer,Player> players;
            FileInputStream file = new FileInputStream(dir.toString()+saveFileName);
            ObjectInputStream in = new ObjectInputStream(file);
            players = (HashMap<Integer,Player>) in.readObject();
            in.close();
            file.close();
            System.out.println("Players loaded successfully!");
            return players;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading players! Constructing new Player List");
            return new HashMap<>();
        }
    }

    public static boolean savePlayers(HashMap<Integer,Player> players, File dir) {
        try {
            File saveFile = new File(dir.toString()+saveFileName);
            saveFile.createNewFile();
            FileOutputStream file = new FileOutputStream(dir.toString()+saveFileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(players);
            out.close();
            file.close();
            System.out.println("Player Data Saved!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return fullName+" (id "+id+"): High Score: "+highScore;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;
    }
    public int getHighScore() {
        return highScore;
    }
    public static int getNumPlayers() {
        return numPlayers;
    }
    public void setHighScore(int s) {
        highScore = s;
    }
    public static void setNumPlayers(int n) {
        numPlayers = n;
    }
    public static Player getCurrPlayer() {return currPlayer;}
    public static void setCurrPlayer(Player p) {currPlayer = p;}
}
