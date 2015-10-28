package units.human;

import com.badlogic.gdx.graphics.Texture;

import units.Hero;

public class General extends Hero {
	
	public int moraleBonus;
	public int stepBonus;

	public General(int id, int steps, int hp, int ap, int cost, int upkeep,
			String name, Texture t) {
		super(id, steps, hp, ap, cost, upkeep, name, "General", t);
		
		stepBonus = 1;
		moraleBonus = 1;
		
	}

}
