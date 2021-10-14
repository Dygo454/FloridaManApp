package c.o.floridaman;

import java.util.ArrayList;
import java.util.HashMap;

public class FloridaManStory {
    public static HashMap<Integer, FloridaManStory> headlines = new HashMap<>();
    public static HashMap<Integer, FloridaManStory> headlinesNotUsed = new HashMap<>();
    public static HashMap<Integer, FloridaManStory> headlinesUsed = new HashMap<>();
    private static int numHeadlines = 0;
    private int id;
    private String headline;
    private String keyWord;
    private ArrayList<String> choices = new ArrayList<>();
    static {
        new FloridaManStory("Florida man arrested for calling 911 after a kitten was/were denied entry into [REDACTED] club","a kitten","an old man","they","everyone");
        new FloridaManStory("Florida man chews off another man's face","another man's face","their own nail","an ice cream truck's fake ice cream","door handle");
        new FloridaManStory("Florida man desperate for ride to Hooters calls 911", "ride to Hooters", "chicken tenders", "a pet alligator", "a jacket");
        new FloridaManStory("Florida man insists syringes pulled from [REDACTED] aren't his", "syringes", "pills", "shells", "rocks");
        new FloridaManStory("Florida man in dog costume filmed himself having [REDACTED] with Siberian husky", "[REDACTED]", "a play date", "dinner", "breakfast");
        new FloridaManStory("Florida man arrested over 'I eat a-' sticker", "'I eat a-'", "nazi", "political", "smiley face");
        new FloridaManStory("Florida man protects car from Hurricane Dorian by parking it in kitchen", "kitchen", "garage", "friend's garage", "shelter");
        new FloridaManStory("Florida man arrested for trying to get alligator drunk", "alligator", "minors", "friends", "wife");
        new FloridaManStory("Florida man tries to evade arrest by cartwheeling away from cops", "cartwheeling", "skipping", "running", "driving");
        new FloridaManStory("Florida man busted feeding iguanas to alligator in jail zoo", "iguanas", "left overs", "trash", "steak");
        new FloridaManStory("Florida man wears \"[REDACTED] the police\" shirt to court, wins case","\"[REDACTED] the police\"","hello kitty","anarchist","yellow");
        new FloridaManStory("Florida man attacked during selfie with squirrel","squirrel","minors","wife","spittoon");
        new FloridaManStory("Florida man in \"No, seriously, I have drugs\" T-Shirt Arrested for possession of drugs","\"No, seriously, I have drugs\"","money","minecraft","ahegao");
        new FloridaManStory("Florida man suspected of using private plane to draw giant radar [REDACTED]","draw giant radar [REDACTED]","escape police","transport alligators","clear the clouds");
        new FloridaManStory("Florida man charged with assault with a deadly weapon after throwing alligator through Wendy's drive-thru window","throwing alligator through","pointing gun at","blowing up propane tank at","Throwing rifle at");
        new FloridaManStory("Florida man steals car, realizes a baby is in it, drops off baby safely, and makes his get away","drops off baby safely","steals a pacifier from the house","returns to ask to adopt the baby","takes baby anyways");
        new FloridaManStory("Florida cop claims Burger King put dirt on his food- investigation reveals it was seasoning","seasoning",", in fact, dirt","sand","spit");
        new FloridaManStory("Thousands of gun owners in Florida planning to 'shoot down' Hurricane Irma","Hurricane Irma","gun regulations","defenses when storming area 51","social media");
        new FloridaManStory("Florida man tries to rob GameStop while wearing transparent bag on his head","transparent bag","sans mask","ski mask","paper bag");
        new FloridaManStory("Florida man breaks in to jail to hang with his friends", "jail","school","dorm","an attic");
    }

    public FloridaManStory(String h, String... cs) {
        id = numHeadlines;
        numHeadlines++;
        headline = h;
        for (String c : cs) {
            choices.add(c);
        }
        headlines.put(id,this);
        headlinesNotUsed.put(id,this);
    }

    public String getQuestion() {
        for (String choice : choices) {
            if (headline.contains(choice)) {
                int ind = headline.indexOf(choice);
                keyWord = choice;
                return headline.substring(0, ind)+"______"+headline.substring(ind+choice.length());
            }
        }
        System.out.println(Colors.RED+"Error: Florida Man Headline incorrectly instantiated!"+Colors.RESET);
        return null;
    }

    public boolean isAnswer(String answer) {
        return headline.contains(answer);
    }

    public String[] getChoices() {
        ArrayList<String> choicesClone = new ArrayList<>(choices);
        String[] answer = new String[4];
        for (int i = 0; i < 4; i++) {
            answer[i] = choicesClone.remove((int)(Math.random()*choicesClone.size()));
        }
        return answer;
    }

    public String getAnswer() {
        return keyWord;
    }

    public int getId() {
        return id;
    }

    public int hashCode() {
        return id;
    }
    public boolean equals(Object other) {
        return (id == ((FloridaManStory) other).getId());
    }
}
