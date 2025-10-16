import java.util.HashMap;

// Base pokemon class
public class Pokemon {

    // Initialise variables for each pokemon
    int Health;
    String Type;

    /*
        Effectiveness is a type v type multiplier for every pokemon.
        For example in Fire vs Grass, Fire's multiplier would be 2x against grass
    */
    HashMap<String, Float> effectiveness = new HashMap<>();

    // Each pokemon has 3 attack and 1 defense move
    String[] Moves = new String[4];

    // Attack points
    int[] Damage = {5, 12, 25, 0};

    public Pokemon() {
        Health = 100;
        Type = "";
    }

    // getter/setter(s)
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