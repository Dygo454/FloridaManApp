package c.o.floridaman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        String summary = "Wow! You got "+GameTime.getNumRight()+" questions right... not quite you're high score of "+Player.getCurrPlayer().getHighScore()+".";
        if (Player.getCurrPlayer().getHighScore() < GameTime.getNumRight()) {
            int oldHS = Player.getCurrPlayer().getHighScore();
            Player.getCurrPlayer().setHighScore(GameTime.getNumRight());
            Player.savePlayers(MainActivity.players, getFilesDir());
            summary = "WOOOO! You beat you're high score of "+oldHS+" questions and got "+GameTime.getNumRight()+"!";
        }
        Player curr = Player.getCurrPlayer();
        String textToShow = curr.getUsername()+":\n"+curr+"\n"+summary;
        TextView body = findViewById(R.id.doneBody);
        body.setText(textToShow);
    }

    public void backToTitle(View view) {
        MainActivity.players.clear();
        MainActivity.logins.clear();
        MainActivity.ids.clear();
        Player.setCurrPlayer(null);
        Player.setNumPlayers(0);
        GameTime.setNumRight(0);
        FloridaManStory.headlinesUsed.clear();
        FloridaManStory.headlinesNotUsed.clear();
        FloridaManStory.headlinesNotUsed.putAll(FloridaManStory.headlines);
        Intent intent = new Intent(view.getContext(),MainActivity.class);
        startActivity(intent);
    }
}