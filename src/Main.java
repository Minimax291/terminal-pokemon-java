public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        Charmander playerPokemon = new Charmander();
        playerPokemon.setHealth(playerPokemon.getHealth() - 15);
        System.out.println("Player's Charmander health is: " + playerPokemon.getHealth());
    }
}