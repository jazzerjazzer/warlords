package buildings;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

import units.Army;

import game.Faction;

public class Castle {

	public int id;
	
	public int x, y;
	public Faction faction;
	
	public int level;
	public int farms, barracks, walls;
	
	public String name;
	public ArrayList<Army> garrison;
	
	public Texture texture;
	public boolean selected;

	public Castle(int id, int x, int y, Faction faction, int farms, int barracks, int walls, String name, Texture texture){
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.faction = faction;
		this.farms = farms;
		this.barracks = barracks;
		this.walls = walls;
		this.name = name;
		this.texture = texture;
		this.selected = false;
		
		this.level = farms + barracks + walls;
		this.garrison = new ArrayList<Army>();
		
	}
	
	public void upgradeFarms(){
		
		faction.gold -= farms*100;
		farms++;
		level++;
		updateLevel();
		
	}
	
	public void upgradeBarracks(){
		
		faction.gold -= barracks*100;
		barracks++;
		level++;
		updateLevel();
		
	}

	public void upgradeWalls(){
	
		faction.gold -= walls*100;
		walls++;
		level++;
		updateLevel();
	
	}
	
	/**
	 * 
	 */
	public void updateLevel(){
		
		if(level > 3 && level < 7){
			
		}
		
		if(level > 6 && level < 10){
			
		}
		
		if(level == 10){
			
		}
		
	}
	
	/**
	 * When the castle selected, we'll draw castle menu on screen in this method.
	 */
	public void showMenu(){
		
	}
	
	/**
	 * Drawing castles on screen.
	 */
	public void draw(int drawX, int drawY){

	}
	
	/**
	 * All castle updates will be done in this method.
	 * For example, we'll control if the user touch this castle and if it is his turn. If it is, than we'll call showMenu() method.
	 */
	public void update(){
		
	}
	
}
