class Pokemon extends Charmander{
	int Health;
	String[] moves = {"small", "medium", "large", "defense"};
	String type = "fire";
	effectiveness = {
		"grass": 1.5,
		"lightning": 1.0,
		"water": 0.75,
		"fire": 1.0
	}

	static void getHealth() {
		return Health;
	}
	
	static void setHealth(int newHealth) {
		Health = newHealth
	}
}

static void Damage(Pokemon1, Pokemon2, int Pokemon1Move, int Pokemon2Move) {
	Pokemon1Damage = Pokemon2[Pokemon2Move].value * Pokemon2.effectiveness[Pokemon1.type];
	Pokemon2Damage = Pokemon1[Pokemon1Move].value * Pokemon1.effectiveness[Pokemon2.type];

	Pokemon1.setHealth(Pokemon1.getHealth() - Pokemon1Damage);
	Pokemon2.setHealth(Pokemon1.getHealth() - Pokemon2Damage);
}


if (defenseWorked) {
	nextRound();
}
else {
	Damage();
}
