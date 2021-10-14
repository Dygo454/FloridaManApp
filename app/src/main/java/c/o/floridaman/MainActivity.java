package c.o.floridaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    public static HashMap<Integer,Player> players = new HashMap<>();
    public static HashMap<String,String> logins = new HashMap<>();
    public static HashMap<String,Integer> ids = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!(new File(getFilesDir()+Player.saveFileName)).exists()) {
            Player p1 = new Player("Diego", "1234", "Diego Aguilar");
            Player p2 = new Player("Kate", "4321", "Tairan Lu");
            Player p3 = new Player("Aitor", "1111", "Aitor Arrese-Igor");
            players.put(p1.getId(), p1);
            players.put(p2.getId(), p2);
            players.put(p3.getId(), p3);
            Player.savePlayers(players, getFilesDir());
            Player.setNumPlayers(0);
            players.clear();
        }
    }

    public void login(View view) {
        if (logins.isEmpty() || players.isEmpty() || ids.isEmpty()) {
            HashMap<Integer, Player> playersLoaded = Player.loadPlayers(getFilesDir());
            Iterator<Player> iter = playersLoaded.values().iterator();
            int count = 0;
            while (iter.hasNext()) {
                Player p = iter.next();
                players.put(p.getId(), p);
                logins.put(p.getUsername(), p.getPassword());
                ids.put(p.getUsername(),p.getId());
                count++;
            }
            Player.setNumPlayers(Player.getNumPlayers() + count);
        }
        EditText user = findViewById(R.id.UsernameLogin);
        String username = user.getText().toString();
        EditText pass = findViewById(R.id.PasswordLogin);
        String password = pass.getText().toString();
        Toast login = new Toast(getApplicationContext());
        if (logins.get(username) != null && logins.get(username).equals(password)) {
            login.setText("Success!");
            login.show();
            Player.setCurrPlayer(players.get(ids.get(username)));
            Intent intent = new Intent(view.getContext(),GameTime.class);
            startActivity(intent);
            return;
        }
        login.setText("Incorrect Login!");
        login.show();
    }

    public void deleteDat(View view) {
        (new File(getFilesDir()+Player.saveFileName)).delete();
    }
}