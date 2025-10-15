import java.util.Random;

public class Main {
    public static void Damage(Pokemon Pokemon1, Pokemon Pokemon2, int Player1Move, int Player2Move) {
        if (Player1Move == 4 || Player2Move == 4) {
            Random dice = new Random();
            float result = dice.nextFloat();
            if (result < 0.8f) {
                return;
            }
        }

        // TODO: Convert Moves into a hashmap, eg: "Growl": 10, 10 being damage value
        // Applying Damage
        Pokemon1.setHealth(Pokemon1.getHealth() - (
                Pokemon2.Moves.values().get(Player2Move)
                        * Pokemon2.effectiveness.get(Pokemon1.Type)));

        Pokemon2.setHealth(Pokemon2.getHealth() - (
                Pokemon1.Moves.values().get(Player1Move)
                        * Pokemon1.effectiveness.get(Pokemon2.Type)));
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Pokemon!");
        Charmander playerPokemon = new Charmander();
        Bulbasaur enemyPokemon = new Bulbasaur();
    }
}