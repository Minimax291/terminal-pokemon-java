import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void Damage(Pokemon Pokemon1, Pokemon Pokemon2, int Player1Move, int Player2Move) {
        // If both players do a defence move, 0 vs 0 = 0, nothing changes
        if (Player1Move == 3 && Player2Move == 3) {
            System.out.println("Both players chose defence, round is void");
            return;
        }

        // If either player chooses a defense
        Random dice = new Random();

        // Duplication due to being unable to figure out which player did the defence move in a attack v defence scenario
        if (Player1Move == 3) {
            float result = dice.nextFloat();
            if (result < 0.8f) { // There's an 80% chance the defense works and deflects the enemy pokemon's move
                System.out.println("Your " + Pokemon1.getName() + "'s defence has worked! Moving to next round.\n");
                return;
            }
        }
        if (Player2Move == 3) {
            float result = dice.nextFloat();
            if (result < 0.8f) {
                System.out.println("Enemy " + Pokemon2.getName() + "'s defence has worked! Moving to next round.\n");
                return;
            }
        }

        // Applying Damage

        // Critical Damage (1/16) chance of occuring

        float crit_dmg_poke1 = 1;
        float crit_dmg_poke2 = 1;

        // Debug if bypass
        //if (true) {
        if (dice.nextFloat() < ((float) 1 / 6)) {
            // 50/50 chance of crit damage is done by either pokemon
            if (dice.nextBoolean()) {
                crit_dmg_poke1 = 1.5f;
                System.out.println("Your " + Pokemon1.getName() + " did a critical damage! Applying 1.5x damage");
            } else {
                crit_dmg_poke2 = 1.5f;
                System.out.println("Enemy " + Pokemon2.getName() + " did a critical damage! Applying 1.5x damage");
            }
        }

        Pokemon1.setHealth( Pokemon1.getHealth() -
                Math.round(Pokemon2.Damage[Player2Move] *
                        Pokemon2.effectiveness.get(Pokemon1.Type) * crit_dmg_poke2)); // Enemy's crit will be applied to player

        // Before v After Debug print statements

        /*
        System.out.println("Pokemon1: Damage: " + Pokemon2.Damage[Player2Move] + " Effectiveness multiplier: " + Pokemon2.effectiveness.get(Pokemon1.Type));
        System.out.println("Total: " + Math.round(Pokemon2.Damage[Player2Move] * Pokemon2.effectiveness.get(Pokemon1.Type)));
        */

        Pokemon2.setHealth( Pokemon2.getHealth() -
                Math.round(Pokemon1.Damage[Player1Move] *
                        Pokemon1.effectiveness.get(Pokemon2.Type) * crit_dmg_poke1)); // Player's crit will be applied to enemy

        /*
        System.out.println("Pokemon2: Damage: " + Pokemon1.Damage[Player1Move] + " Effectiveness multiplier: " + Pokemon1.effectiveness.get(Pokemon2.Type));
        System.out.println("Total: " + Math.round(Pokemon1.Damage[Player1Move] * Pokemon1.effectiveness.get(Pokemon2.Type)));
         */
    }
    public static void main(String[] args)
    {
        Scanner kin1 = new Scanner(System.in); //input for user
        Random rnd = new Random(); //for ai, randomized pokemon and move selection

        System.out.println("Welcome to Pokemon!");
        Pokemon player = null; // empty box to put pokemon object later
        boolean valid = false; //exception handling for pokemon choosing

        System.out.println("Pick a pokemon");
        System.out.println("You can pick between 4 moves, 3 attack and 1 defence");
        System.out.println("Small, Medium, Large and Defence");
        System.out.println("Defence has a 50/50 chance of deflecting the enemy");
        System.out.println("Do you understand this? [Y/n]");
        String understanding = kin1.nextLine().trim().toLowerCase();
        if (!understanding.isEmpty()) {
            if (understanding.charAt(0) == 'y') {
               ;
            } else if(understanding.charAt(0) == 'n') {
                return;
            } else {
                return;
            }
        }

        System.out.println();


        //pokemon object creation according to user input
        while (!valid)
        {
            System.out.println("Choose your Pokemon:\nA) Bulbasaur\nB) Charmander\nC) Pikachu\nD) Squirtle ");
            String choice = kin1.nextLine().trim().toUpperCase(); //make the input fully upper class and removes white space

            if (choice.equals("A") || choice.equals("BULBASAUR"))
            {
                player = new Bulbasaur();
                valid =true;
            }
            else if (choice.equals("B") || choice.equals("CHARMANDER"))
            {
                player = new Charmander();
                valid =true;
            }
            else if (choice.equals("C") || choice.equals("PIKACHU"))
            {
                player = new Pikachu();
                valid =true;
            }
            else if (choice.equals("D") || choice.equals("SQUIRTLE"))
            {
                player = new Squirtle();
                valid =true;
            }
            else
                System.out.println("Invalid choice. Try again"); //exception handling
        }

        //ai pokemon selection
        Pokemon[] poke = {new Bulbasaur(),new Charmander(), new Pikachu(), new Squirtle()};
        Pokemon aipoke = poke[rnd.nextInt(poke.length)];
        System.out.println("Your opponent chose: " + aipoke.getName() + "!");


        //battle battle loop(input & output)
        while(player.getHealth()>0 && aipoke.getHealth()>0)
        {
            //showing current health of both pokemons
            System.out.println("------------------------------");
            System.out.println("Your Pokemon health: " + player.getHealth());
            System.out.println("Opponent Pokemon health: " + aipoke.getHealth());
            System.out.println("------------------------------");
            System.out.printf("\nChoose a move for your Pokemon (%d%% effectiveness): \n", (int)Math.ceil(player.effectiveness.get(aipoke.Type) * 100));

            //printing available moves for player pokemon
            String[] movesDesc = {"Small", "Medium", "Large", "Defence"};

            for (int i=0;i<player.Moves.length;i++)
            {
                System.out.println((i+1) + ") " +player.Moves[i] + " (" + movesDesc[i] + ")");
            }

            int playerMove = 0;//player move storage variable

            boolean value = false;

            //Taking player's move input
            while (!value)
            {
                try
                {
                    System.out.println("Select your move(1-4): ");
                    playerMove=kin1.nextInt() - 1;

                    //exception handling for Int input outside 1-4
                    if (playerMove >= 0 && playerMove < 4)
                    {
                        value = true;
                    }
                    else
                        System.out.println("Invalid Input");
                }

                //exception handling with any other output
                catch (Exception e)
                {
                    System.out.println("Only numbers are accepted");
                    kin1.nextLine();
                }
            }

            // Note: by default it goes from 0-n where n is exclusive, in our case it's 0-3... ¯\_(ツ)_/¯
            // https://docs.oracle.com/javase/8/docs/api/java/util/Random.html#nextInt--:~:text=Returns%20a%20pseudorandom%2C%20uniformly%20distributed%20int-,value%20between%200%20(inclusive)%20and%20the%20specified%20value%20(exclusive),-%2C%20drawn%20from%20this%20random%20number%20generator%27s%20sequence.%20The
            int enemyMove = rnd.nextInt(4); //ai pokemon randomized move selection

           int[] beforeHealth = new int[2];
            beforeHealth[0] = player.getHealth();
            beforeHealth[1] = aipoke.getHealth();

            Damage(player,aipoke,playerMove,enemyMove); //calling main battle combat function

            int[] afterHealth = new int[2];
            afterHealth[0] = player.getHealth();
            afterHealth[1] = aipoke.getHealth();

            //showing the pokemon moves of both player and ai
            System.out.println("Your " + player.getName() + " used " + player.Moves[playerMove] + " (" + (beforeHealth[1] - afterHealth[1]) + " Damage)");
            System.out.println("Opponent " + aipoke.getName() + " used " + aipoke.Moves[enemyMove] + " (" + (beforeHealth[0] - afterHealth[0]) + " Damage)");
            System.out.println();
        }


        //Battle end dialouges
        if (player.getHealth()<=0 && aipoke.getHealth()<=0)
        {
            System.out.println("\n\nIt's a draw!!!!!");
        }
        else if (player.getHealth()>0 && aipoke.getHealth()<=0)
        {
            System.out.println("\n\nOpponent Pokemon fainted!!!!\nYou win");
        }
        else
            System.out.println("\n\nYour Pokemon fainted!!!!\nOpponent wins ");

        kin1.close();
    }
}
/* reference used - JOYDEEP BANIK
https://www.youtube.com/watch?v=mJ-qvsxPHpY - GIT explanation for beginners
https://www.youtube.com/watch?v=hwP7WQkmECE - GIT mini explanation
https://www.geeksforgeeks.org/java/generating-random-numbers-in-java/ - Random number generator
https://pokemon.fandom.com/wiki/Games - Pokemon Fandom(Game knowledge)
https://pokemon.fandom.com/wiki/Pokémon_battle - Pokemon Battles (Battle rules and UI knowledge)
https://www.w3schools.com/java/ - W3 School to help me with understanding + solving the code
ChatGPT - for helping with debugging and error finding

Akanksh Chitimalla references:
https://pokemon.fandom.com/wiki/Pok%C3%A9mon_battle - Pokemon battle system
https://pokemondb.net/type - Pokemon Type system
https://www.w3schools.com/java/java_hashmap.asp - Java Hashmaps
https://www.youtube.com/watch?v=WEILxTBDy0Y - Visualisation of HashMap
 */