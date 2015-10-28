package units.undead;

import com.badlogic.gdx.graphics.Texture;

import units.Hero;

public class Necromancer extends Hero {
	
	public int necromancy;
	public int fearBonus;

	public Necromancer(int id, int steps, int hp, int ap, int cost, int upkeep,
			String name, String type, Texture t) {
		super(id, steps, hp, ap, cost, upkeep, name, type, t);

		this.necromancy = 1;
		this.fearBonus = 1;
		
	}

}
