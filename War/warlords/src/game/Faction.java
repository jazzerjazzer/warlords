package game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

import buildings.Castle;

import units.Army;

public class Faction {
	
	public int id;

	public String name;
	public int gold;
	
	public Texture flagMini;
	public Texture shield;
	public Texture battleFlag;
	
	public ArrayList<Army> armies;
	public ArrayList<Castle> castles;
	
	public Faction(int id, String name, int gold){
		
		this.id = id;
		this.name = name;
		this.gold = gold;
		
		armies = new ArrayList<Army>();
		castles = new ArrayList<Castle>();
		
	}
	
	public void draw(){
		
	}
	
	public void update(){
		
	}
	
}
