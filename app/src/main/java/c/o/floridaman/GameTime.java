package c.o.floridaman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;

public class GameTime extends AppCompatActivity {
    private TextView stats;
    private TextView body;
    private RadioButton op1;
    private RadioButton op2;
    private RadioButton op3;
    private RadioButton op4;
    private FloridaManStory currStory = null;
    private static int numRight = 0;

    public void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_game_time);
        init();
    }

    private String getStats() {
        return Player.getCurrPlayer().toString()+". Correct: "+numRight+", Incorrect: "+Math.max(0,FloridaManStory.headlinesUsed.size()-numRight-1)+".";
    }

    private void init() {
        stats = findViewById(R.id.stats);
        stats.setText(getStats());
        body = findViewById(R.id.body);
        String bodyText = "Hello! Welcome to the Florida Man Game! This is your chance to prove your skills in identifying what florida man does. Good luck soldier!\no7\n(press go when you are ready)";
        body.setText(bodyText);
        String none = "I'm Ready!";
        op1 = findViewById(R.id.op1);
        op1.setText(none);
        op2 = findViewById(R.id.op2);
        op2.setText(none);
        op3 = findViewById(R.id.op3);
        op3.setText(none);
        op4 = findViewById(R.id.op4);
        op4.setText(none);
    }

    public void submitAnswer(View view) {
        if (currStory != null) {
            boolean correct = false;
            if (op1.isChecked()) {
                op1.setChecked(false);
                if (currStory.isAnswer(op1.getText().toString())) {
                    correct = true;
                }
            }
            else if (op2.isChecked()) {
                op2.setChecked(false);
                if (currStory.isAnswer(op2.getText().toString())) {
                    correct = true;
                }
            }
            else if (op3.isChecked()) {
                op3.setChecked(false);
                if (currStory.isAnswer(op3.getText().toString())) {
                    correct = true;
                }
            }
            else if (op4.isChecked()) {
                op4.setChecked(false);
                if (currStory.isAnswer(op4.getText().toString())) {
                    correct = true;
                }
            }
            else {
                Toast toast = new Toast(view.getContext());
                toast.setText("Choose an option!");
                toast.show();
                return;
            }
            if (correct) {
                numRight++;
                Toast toast = new Toast(view.getContext());
                toast.setText("Correct!");
                toast.show();
            }
            else {
                Toast toast = new Toast(view.getContext());
                toast.setText("Nope! It was "+currStory.getAnswer());
                toast.show();
            }
        }
        if (FloridaManStory.headlinesNotUsed.isEmpty()) {
            Intent intent = new Intent(view.getContext(), Done.class);
            startActivity(intent);
            Toast toast = new Toast(view.getContext());
            toast.setText("Done!");
            toast.show();
            return;
        }
        FloridaManStory toRemove = (FloridaManStory) ((Object[])FloridaManStory.headlinesNotUsed.values().toArray())[(int) (Math.random()*FloridaManStory.headlinesNotUsed.size())];
        currStory = FloridaManStory.headlinesNotUsed.remove(toRemove.getId());
        FloridaManStory.headlinesUsed.put(toRemove.getId(),currStory);
        stats.setText(getStats());
        body.setText(currStory.getQuestion());
        String[] choices = currStory.getChoices();
        op1.setText(choices[0]);
        op2.setText(choices[1]);
        op3.setText(choices[2]);
        op4.setText(choices[3]);
    }

    public static int getNumRight() {
        return numRight;
    }

    public static void setNumRight(int n) {
        numRight = n;
    }
}