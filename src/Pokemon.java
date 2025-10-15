import java.util.HashMap;

public class Pokemon {
    int Health;
    String Type;
    HashMap<String, Float> effectiveness = new HashMap<>();
    String[] Moves = new String[4];

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
        Moves = new String[] {"Scratch", "Ember", "Flamethrower", "Growl"};

        effectiveness.put("fire", 1.0f);
        effectiveness.put("water", 0.75f);
        effectiveness.put("electric", 0.75f);
        effectiveness.put("grass", 2.0f);
    }
}

class Pikachu extends Pokemon {
    public Pikachu() {
        Type = "electric";
        Moves = new String[] {"Quick Attack", "Thunder Shock", "Thunderbolt", "Tail Whip"};

        effectiveness.put("fire", 1.0f);
        effectiveness.put("water", 2.0f);
        effectiveness.put("electric", 1.0f);
        effectiveness.put("grass", 0.75f);
    }
}

class Squirtle extends Pokemon {
    public Squirtle() {
        Type = "water";
        Moves = new String[] {"Tackle", "Water Gun", "Hydro Pump", "Rain Dance"};

        effectiveness.put("fire", 2.0f);
        effectiveness.put("water", 1.0f);
        effectiveness.put("electric", 1.0f);
        effectiveness.put("grass", 0.75f);
    }
}

class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        Type = "grass";
        Moves = new String[] {"Tackle", "Vine Whip", "Solar Beam", "Leech Seed"};

        effectiveness.put("fire", 0.75f);
        effectiveness.put("water", 2.0f);
        effectiveness.put("electric", 0.75f);
        effectiveness.put("grass", 1.0f);
    }
}