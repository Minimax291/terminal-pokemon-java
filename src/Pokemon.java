import java.util.HashMap;

public class Pokemon {
    int Health;
    String Type;
    HashMap<String, Float> effectiveness = new HashMap<>();

    public Pokemon() {
        Health = 100;
        Type = "";

    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int newHealth) {
        Health = newHealth;
    }
}

class Charmander extends Pokemon {
    public Charmander() {
        Type = "fire";

        effectiveness.put("fire", 1.0f);
        effectiveness.put("water", 0.75f);
        effectiveness.put("lightning", 0.75f);
        effectiveness.put("grass", 2.0f);
    }
}
