package units;

import com.badlogic.gdx.graphics.Texture;

public class Hero extends Unit {

	public String name;
	public String type;
	
	public int level;
	public int totalExp;
	public int exp;
	
	public Hero(int id, int steps, int hp, int ap, int cost, int upkeep, String name, String type, Texture t) {
		super(id, steps, hp, ap, cost, upkeep, t);
		
		this.level = 1;
		this.exp = 0;
		this.totalExp = 0;
		
		this.name = name;
		this.type = type;
		
	}
	
	public void update(){
		
		if(exp == level*100){
			level++;
			exp = 0;
		}
		
		
	}

}
