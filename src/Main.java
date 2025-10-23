import java.util.Random;
import java.util.Scanner;

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
    public static void main(String[] args)
    {
        Scanner kin1 = new Scanner(System.in); //input for user
        Random rnd = new Random(); //for ai, randomized pokimon and move selection

        System.out.println("Welcome to Pokemon!");
        Pokemon player = null; // empty box to put pokemon object later
        boolean valid = false; //exception handling for pokemon choosing


        //pokemon object creation according to user input
        while (!valid)
        {
            System.out.println("Choose your Pokemon:\nA) Bulbasaur!\nB) Charmander!\nC) Pikachu!\nD) Squirtle! ");
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
        Pokemon[] poki = {new Bulbasaur(),new Charmander(), new Pikachu(), new Squirtle()};
        Pokemon aipoki = poki[rnd.nextInt(poki.length)];
        System.out.println("Your opponent chose: " + aipoki.getClass().getSimpleName() + "!");


        //battle battle loop(input & output)
        while(player.getHealth()>0 && aipoki.getHealth()>0)
        {
            //showing current health of both pokemons
            System.out.println("------------------------------");
            System.out.println("Your Pokimon health: " + player.getHealth());
            System.out.println("Opponent Pokimon health: " + aipoki.getHealth());
            System.out.println("------------------------------");
            System.out.println("\n\nChoose a move for your Pokimon: \n");

            //printing available moves for player pokemon
            for (int i=0;i<player.Moves.length;i++)
            {
                System.out.println((i+1) + ") " +player.Moves[i]);
            }

            int playerMove = kin1.nextInt()-1; //player move storage variable

            //exception handling
            if(playerMove<0 || playerMove>=4)
            {
                System.out.println("Invalid input. Try again");
                continue;
            }


            int enemyMove = rnd.nextInt(4); //ai pokemon randomized move selection

            //showing the pokemon moves of both player and ai
            System.out.println("Your " + player.getClass().getSimpleName() + " used " + player.Moves[playerMove]);
            System.out.println("Opponent " + aipoki.getClass().getSimpleName() + " used " + aipoki.Moves[enemyMove]);

            Damage(player,aipoki,playerMove,enemyMove); //calling main battle combat function
        }


        //Battle end dialouges
        if (player.getHealth()<=0 && aipoki.getHealth()<=0)
        {
            System.out.println("\n\n It's a draw!!!!!");
        }
        else if (player.getHealth()>0 && aipoki.getHealth()<=0)
        {
            System.out.println("\n\nOpponent Pokimon fainted!!!!\nYou win");
        }
        else
            System.out.println("\n\n Your Pokimon fainted!!!!\nOpponent wins ");

        kin1.close();
    }
}