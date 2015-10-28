package units;

import com.badlogic.gdx.graphics.Texture;

public class Soldier extends Unit {
	
	public int remainingTurn;
	
	public Soldier(int id, int steps, int hp, int ap, int cost, int upkeep, int remainingTurn, Texture t) {
		super(id, steps, hp, ap, cost, upkeep, t);
		this.remainingTurn = remainingTurn;
	}
	
	public void update(){
		
	}

}
