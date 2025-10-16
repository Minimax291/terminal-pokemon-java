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

        // Applying Damage
        Pokemon1.setHealth( Pokemon1.getHealth() -
                Math.round(Pokemon2.Damage[Player2Move] *
                        Pokemon2.effectiveness.get(Pokemon1.Type)));

        // Before v After Debug print statements

        /*
        System.out.println("Pokemon1: Damage: " + Pokemon2.Damage[Player2Move] + " Effectiveness multiplier: " + Pokemon2.effectiveness.get(Pokemon1.Type));
        System.out.println("Total: " + Math.round(Pokemon2.Damage[Player2Move] * Pokemon2.effectiveness.get(Pokemon1.Type)));
        */

        Pokemon2.setHealth( Pokemon2.getHealth() -
                Math.round(Pokemon1.Damage[Player1Move] *
                        Pokemon1.effectiveness.get(Pokemon2.Type)));

        /*
        System.out.println("Pokemon2: Damage: " + Pokemon1.Damage[Player1Move] + " Effectiveness multiplier: " + Pokemon1.effectiveness.get(Pokemon2.Type));
        System.out.println("Total: " + Math.round(Pokemon1.Damage[Player1Move] * Pokemon1.effectiveness.get(Pokemon2.Type)));
         */
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Pokemon!");
        Charmander playerPokemon = new Charmander();
        Bulbasaur enemyPokemon = new Bulbasaur();

        System.out.println("Player Pokemon health: " + playerPokemon.getHealth());
        System.out.println("Enemy Pokemon health: " + enemyPokemon.getHealth());

        // Damage(playerPokemon, enemyPokemon, 1, Math.toIntExact(Math.round(Math.random() * 4)));
        Damage(playerPokemon, enemyPokemon, 1, 1);

        System.out.println("Player Pokemon health: " + playerPokemon.getHealth());
        System.out.println("Enemy Pokemon health: " + enemyPokemon.getHealth());
    }
}