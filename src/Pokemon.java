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
    HashMap<String, Integer> Moves = new HashMap<>();

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
        Moves.put("Scratch", 5);
        Moves.put("Ember", 12);
        Moves.put("Flamethrower", 25);
        Moves.put("Growl", 0);

        effectiveness.put("fire", 1.0f);
        effectiveness.put("water", 0.75f);
        effectiveness.put("electric", 0.75f);
        effectiveness.put("grass", 2.0f);
    }
}

class Pikachu extends Pokemon {
    public Pikachu() {
        Type = "electric";
        Moves.put("Quick Attack", 5);
        Moves.put("Thunder Shock", 12);
        Moves.put("Thunderbolt", 25);
        Moves.put("Tail Whip", 0);

        effectiveness.put("fire", 1.0f);
        effectiveness.put("water", 2.0f);
        effectiveness.put("electric", 1.0f);
        effectiveness.put("grass", 0.75f);
    }
}

class Squirtle extends Pokemon {
    public Squirtle() {
        Type = "water";
        Moves.put("Tackle", 5);
        Moves.put("Water Gun", 12);
        Moves.put("Hydro Pump", 25);
        Moves.put("Rain Dance", 0);

        effectiveness.put("fire", 2.0f);
        effectiveness.put("water", 1.0f);
        effectiveness.put("electric", 1.0f);
        effectiveness.put("grass", 0.75f);
    }
}

class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        Type = "grass";
        Moves.put("Tackle", 5);
        Moves.put("Vine Whip", 12);
        Moves.put("Solar Beam", 25);
        Moves.put("Leech Seed", 0);

        effectiveness.put("fire", 0.75f);
        effectiveness.put("water", 2.0f);
        effectiveness.put("electric", 0.75f);
        effectiveness.put("grass", 1.0f);
    }
}