import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void Damage(Pokemon Pokemon1, Pokemon Pokemon2, int Player1Move, int Player2Move) {
        // If both players do a defence move, 0 vs 0 = 0, nothing changes
        if (Player1Move == 4 && Player2Move == 4) {
            System.out.println("Both players chose defence, round is void");
            return;
        }

        // If either player chooses a defense
        Random dice = new Random();

        // Duplication due to being unable to figure out which player did the defence move in a attack v defence scenario
        if (Player1Move == 4) {
            float result = dice.nextFloat();
            if (result < 0.8f) { // There's an 80% chance the defense works and deflects the enemy pokemon's move
                System.out.println(Pokemon1.getName() + "'s defence has worked! Moving to next round.");
                return;
            }
        }
        if (Player2Move == 4) {
            float result = dice.nextFloat();
            if (result < 0.8f) {
                System.out.println(Pokemon2.getName() + "'s defence has worked! Moving to next round.");
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
        System.out.println("Do you understand this? [y/n]");
        String understanding = kin1.nextLine().toLowerCase();
        if (understanding.equals("n") || understanding.equals("no")) {
            return;
        }


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

            //exception handling
            while (!value)
            {
                if (!kin1.hasNextInt()) // If the player inputs anything that isn't an integer
                {
                    System.out.println("Please enter a number between 1-4");
                    kin1.next();
                    continue;
                }

                playerMove = kin1.nextInt() - 1;

                // exception handling. user input is out of bounds(i.e 1-4)
                if (playerMove >= 0 && playerMove < 4)
                {
                    value = true;
                }
                else
                    System.out.println("Invalid input. Try again");
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