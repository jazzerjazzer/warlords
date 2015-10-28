package game;

import screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;

public class Player {

	public int id;
	
	public boolean isAI;
	public boolean isTurn;

	public Texture flagShield;
	public Faction faction;
	
	public Player(int id, boolean isAI, Faction faction, boolean isTurn, Texture flagShield){
		
		this.id = id;
		this.isAI = isAI;
		this.faction = faction;
		this.isTurn = isTurn;
		this.flagShield = flagShield;
		
	}
	
	public void update(GameScreen game){
		
		controlTurn(game);
		
		if(isTurn){
			if(isAI){
				comPlay();
			} else {
				play();
			}
		}
		
	}
	
	public void controlTurn(GameScreen game){
		
		if(game.turn % game.players.size() == id){
			isTurn = true;
		} else {
			isTurn = false;
		}
		
	}
	
	public void play(){
		
	}
	
	public void comPlay(){
		
	}
	
}
