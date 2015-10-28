package units;

import game.Faction;
import game.Tile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;

import screens.GameScreen;

public class Army {

	public int id;
	
	public ArrayList<Hero> heroes;
	public ArrayList<Soldier> soldiers;
	public Unit primary;
	
	public int steps;
	public int tileId;
	
	public Tile tile;
	public Faction faction;
	
	public boolean isSelected;
	public boolean inCastle;
	public boolean atGate;
	
	public boolean walking;
	public ArrayList<Tile> route;
	
	public Army(int id, Unit primary, int tileId){
		
		this.id = id;
		this.tileId = tileId;
		
		this.primary = primary;
		this.steps = primary.steps;
		
		heroes = new ArrayList<Hero>();
		soldiers = new ArrayList<Soldier>();
		route = new ArrayList<Tile>();
		
		isSelected = false;
		inCastle = false;
		atGate = false;
		
		walking = false;
		
	}
	
	public void draw(int drawX, int drawY){
		
		if(!inCastle){
			
		}
		
	}
	
	public void update(){
		
		for(int i=0; i<soldiers.size(); i++){
			soldiers.get(i).update();
			
			if(soldiers.get(i).hp <= 0){
				soldiers.remove(i);
			}
			
		}
		
		for(int i=0; i<heroes.size(); i++){
			heroes.get(i).update();
			
			if(heroes.get(i).hp <= 0){
				heroes.remove(i);
			}
		}
		
	}
	
	public void walk(GameScreen gs) throws InterruptedException{
		
		float time = 0;
		
		for(int i=0; i<this.route.size(); i++){
			
			this.tile.onArmy = false;
			this.tile = route.get(i);
			this.tileId = this.tile.id;
			this.tile.onArmy = true;
			this.tile.army = this;

			time = 0;
			while(time < 1){
				time += Gdx.app.getGraphics().getDeltaTime();
			}
			
		}
		
		 route = new ArrayList<Tile>();
		
	}
	
	public void findRoute(){
		
	}
	
}
