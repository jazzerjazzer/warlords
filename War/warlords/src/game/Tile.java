package game;

import units.Army;
import buildings.Castle;

import com.badlogic.gdx.graphics.Texture;

public class Tile {

	public int id;
	public int x, y;
	public String type;
	public boolean onCastle, onArmy;
	public Castle castle;
	public Army army ;
	
	public Texture texture;
	public boolean walkable;
	public int stepCost;
	
	public Tile(int id, int x, int y, String type){
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		
		onCastle = false;
		onArmy = false;
		
		castle = null;
		army = null;
		
	}
	
	public void assignCastle(Castle castle){
		
		onCastle = true;
		this.castle = castle;
		
	}
	
	public void assignArmy(Army army){
		
		onArmy = true;
		this.army = army;
		
	}
	
	public void removeArmy(){
		
		onArmy = false;
		this.army = null;
		
	}
	
}
