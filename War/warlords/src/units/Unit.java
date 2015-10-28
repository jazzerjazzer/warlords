package units;

import com.badlogic.gdx.graphics.Texture;

public class Unit {

	public int id;
	
	public int steps;
	public int hp, ap;
	
	public int cost, upkeep;
	public boolean isAvaible;
	
	public Texture texture;
	
	public Unit(int id, int steps, int hp, int ap, int cost, int upkeep, Texture t){
		
		this.id = id;
		this.steps = steps;
		this.hp = hp;
		this.ap = ap;
		
		this.cost = cost;
		this.upkeep = upkeep;
		
		this.isAvaible = false;
		this.texture = t;
		
	}
	
}
