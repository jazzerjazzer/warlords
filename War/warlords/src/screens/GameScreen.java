package screens;

import java.util.ArrayList;

import units.Army;
import units.Hero;
import units.Soldier;
import units.human.General;
import units.human.Swordsman;
import units.undead.Necromancer;
import units.undead.Skeleton;

import game.Faction;
import game.GameMap;
import game.Player;
import game.Tile;

import buildings.Castle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.pikseloyun.warlords.Warlords;

public class GameScreen implements Screen, GestureListener {

	Warlords game;
	public GameMap map;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Vector3 tap = new Vector3(0,0,0);

	private GestureDetector gd = new GestureDetector(this);
	private BitmapFont font = new BitmapFont();

	public ArrayList<Player> players;
	private ArrayList<Faction> factions;
	private ArrayList<Castle> castles;
	private ArrayList<Army> armies;

	public int turn;
	private int drawX, drawY;
	private int tapX, tapY;

	private int startingGold = 300;
	private ArrayList<Boolean> ai;

	private boolean showTownMenu = false;
	private boolean showArmy = false;
	private boolean showBattleScreen = false;
	
	private boolean showFarm = false;
	private boolean showBarracks = false;
	private boolean showCastle = false;
	
	private Castle currentTown;
	private Faction currentFaction;
	private Army currentArmy;
	private int currentPage;
	
	private Army attacker, defender;

	public GameScreen(Warlords game, boolean ai1, boolean ai2){
		this.game = game;
		ai = new ArrayList<Boolean>();
		ai.add(ai1); ai.add(ai2);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		updatePlayers();
		updateCastles();
		updateFactions();
		updateArmies();

		batch.begin();
		
		try {
			controlTouch();
		} catch (InterruptedException e) {

		}
		
		drawMap();
		drawCastles();
		drawFactions();
		drawMiniMap();
		drawGui();
		drawArmies();
		
		drawBattleScreen();
		
		batch.draw(game.textures.gui, 0, 0);
		
		batch.end();

	}

	@Override
	public void show() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		Gdx.input.setInputProcessor(gd);
		batch = new SpriteBatch();

		turn = 1;

		players = new ArrayList<Player>();
		factions = new ArrayList<Faction>();
		castles = new ArrayList<Castle>();
		armies = new ArrayList<Army>();

		createMap();
		createFactions();
		createPlayers();
		createCastles();
		createArmies();
		
		currentFaction = factions.get(0);
		currentPage = 0;
		
		//drawX = players.get(0).faction.castles.get(0).x;
		//drawY = players.get(0).faction.castles.get(0).y;

		drawX = 0; drawY = 0;
		tapX = 0; tapY = 0;

	}

	public void createMap(){

		String fileName = "map/demo.txt";
		int mapX = 0, mapY = 0;

		FileHandle fStream = Gdx.files.internal(fileName);
		fileName = fStream.readString();

		String[] str;
		str = fileName.split("\\r?\\n");

		mapX = Integer.parseInt(str[0].substring(0, str[0].indexOf('x')));
		mapY = Integer.parseInt(str[0].substring(str[0].indexOf('x') + 1));

		map = new GameMap(mapX, mapY, game);

	}

	public void createFactions(){

		String file = "map/demoFactions.txt";

		FileHandle fStream = Gdx.files.internal(file);
		file = fStream.readString();

		String[] str;
		str = file.split("\\r?\\n");

		for(int i=0; i<str.length; i++){
			factions.add(new Faction(i ,str[i], startingGold));
		}
		
		factions.get(0).flagMini = game.textures.humanFlagMini;
		factions.get(1).flagMini = game.textures.undeadFlagMini;
		
		factions.get(0).shield = game.textures.humanShield;
		factions.get(1).shield = game.textures.undeadShield;
		
		factions.get(0).battleFlag = game.textures.humanBattleFlag;
		factions.get(1).battleFlag = game.textures.undeadBattleFlag;
		
	}

	public void createPlayers(){

		String file = "map/demoPlayers.txt";

		FileHandle fStream = Gdx.files.internal(file);
		file = fStream.readString();

		String[] str;
		str = file.split("\\r?\\n");

		for(int i=0; i<str.length; i++){
			players.add(new Player(i, ai.get(i), factions.get(i), false, game.textures.portrait));
		}

	}

	public void createCastles(){

		String file = "map/demoCastles.txt";

		FileHandle fStream = Gdx.files.internal(file);
		file = fStream.readString();

		String[] str;
		str = file.split("\\r?\\n");
		
		int castleId = 1;

		for(int i=0; i<str.length; i++){

			int faction = Integer.parseInt(str[i].substring(0, str[i].indexOf('.')));
			str[i] = str[i].substring(str[i].indexOf('.')+1);

			int x = Integer.parseInt(str[i].substring(0, str[i].indexOf('*')));
			int y = Integer.parseInt(str[i].substring(str[i].indexOf('*')+1, str[i].indexOf('.')));
			str[i] = str[i].substring(str[i].indexOf('.')+1);

			int farms = Integer.parseInt(str[i].substring(0, 1));
			int barracks = Integer.parseInt(str[i].substring(1, 2));
			int walls = Integer.parseInt(str[i].substring(2, 3));

			Castle castle = new Castle(castleId++, x, y, factions.get(faction-1), farms, barracks, walls, Integer.toString(castleId-1), game.textures.westernCastle);
			castles.add(castle);
			factions.get(faction-1).castles.add(castle);

			for(int m = 0; m < 4; m++){
				for(int n = -1; n > -5; n--){
					map.tiles[x+m][y+n].onCastle = true;
					map.tiles[x+m][y+n].castle = castle;
				}
			}
			
		}

	}

	public void createArmies(){

		boolean firstHuman = true;
		boolean firstUndead = true;

		boolean createHuman = false;
		boolean createUndead = false;

		for(int i=0; i<castles.size(); i++){

			Soldier sold = null;
			Hero hero = null;

			if(castles.get(i).faction.name.equals("Humans")){
				if(firstHuman){
					hero = new General(i, 20, 200, 25, 1000, 0, "Name", game.textures.general);
					firstHuman = false;
					createHuman = true;
				}
				sold = new Swordsman(i, 20, 100, 20, 100, 10, 0, game.textures.swordsman);
			} 

			if(castles.get(i).faction.name.equals("Undeads")) {
				if(firstUndead){
					hero = new Necromancer(i, 20, 200, 25, 1000, 0, "Name", "Necromancer", game.textures.necromancer);
					firstUndead = false;
					createUndead= true;
				}
				sold = new Skeleton(i, 20, 100, 20, 100, 10, 0, game.textures.skeleton);
			}

			int x = castles.get(i).x;
			int y = castles.get(i).y;
			Faction faction = castles.get(i).faction;

			int tileId = 0;
			Tile tile = null;

			for(int j=0; j<map.tiles.length; j++){
				for(int k=0; k<map.tiles[j].length; k++){
					if(map.tiles[j][k].x == x && map.tiles[j][k].y == y){
						tileId = map.tiles[j][k].id;
						tile = map.tiles[j][k];
					}
				}
			}

			if(createHuman){

				Army army = new Army(i, hero, tileId);
				army.heroes.add(hero);
				army.soldiers.add(sold);
				army.inCastle = true;

				army.faction = faction;
				army.tile = tile;
				
				armies.add(army);

				faction.armies.add(army);
				firstHuman = false;
				createHuman = false;
				
				castles.get(i).garrison.add(army);

				continue;
			}

			if(createUndead){

				Army army = new Army(i, hero, tileId);
				army.heroes.add(hero);
				army.soldiers.add(sold);
				army.inCastle = true;
				
				army.faction = faction;
				army.tile = tile;

				armies.add(army);

				faction.armies.add(army);
				firstUndead = false;
				createUndead = false;
				
				castles.get(i).garrison.add(army);

				continue;
			}

			Army army = new Army(i, sold, tileId);
			army.soldiers.add(sold);
			army.inCastle = true;

			army.faction = faction;
			army.tile = tile;
			
			armies.add(army);
			faction.armies.add(army);
			
			castles.get(i).garrison.add(army);

		}
	}

	public void drawMap(){

		int x = 0;
		int y = 680;

		for(int i=drawY; i<map.tiles.length; i++){
			for(int j=drawX; j<map.tiles[0].length; j++){
				if(map.tiles[i][j].type.equals("Tree"))
					batch.draw(game.textures.grass2, x, y);
				if(map.tiles[i][j].type.equals("Road"))
					batch.draw(game.textures.grassCenter3, x, y);
				if(map.tiles[i][j].type.equals("Mountain"))
					batch.draw(game.textures.grassCenter3, x, y);
				batch.draw(map.tiles[i][j].texture, x, y);
				if(x <= 960){
					x += 40;
				} else {
					break; 
				}
			}
			x = 0;
			y -= 40;
		}
	}

	public void drawMiniMap(){

		int x = 1050;
		int y = 680;

		for(int i=0; i<map.tiles.length; i++){
			for(int j=0; j<map.tiles[0].length; j++){

				if(map.tiles[i][j].type.equals("Grass")){
					batch.draw(game.textures.grassMini, x, y);
				}
				else if(map.tiles[i][j].type.equals("Road")){
					batch.draw(game.textures.roadMini, x, y);
				}
				else if(map.tiles[i][j].type.equals("Sea") || map.tiles[i][j].type.equals("River")){
					batch.draw(game.textures.waterMini, x, y);
				}
				else if(map.tiles[i][j].type.equals("Tree")){
					batch.draw(game.textures.treeMini, x, y);
				}
				else if (map.tiles[i][j].type.equals("Mountain")){
					batch.draw(game.textures.mountainMini, x, y);
				}

				x += 2;
			}
			x = 1050;
			y -= 2;
		}
		
		for(int i=0; i<castles.size(); i++){
			
			x = 1050 + castles.get(i).x*2;
			y = 680 - castles.get(i).y*2;
			
			if(castles.get(i).faction.id == 0){
				batch.draw(game.textures.castleHumanMini, x, y);
			}
			
			if(castles.get(i).faction.id == 1){
				batch.draw(game.textures.castleUndeadMini, x, y);
			}
		}
		
		batch.draw(game.textures.border, 1050 + drawX*2, 630 - drawY*2);

	}

	public void drawCastles(){

		for(int i = 0; i < castles.size(); i++){

			int x = 0;
			int y = 720;

			int castleX = castles.get(i).x - drawX;
			int castleY = castles.get(i).y - drawY;

			x += castleX*40;
			y -= castleY*40;

			if(x < 960){
				batch.draw(castles.get(i).texture, x, y);
				batch.draw(castles.get(i).faction.flagMini, x + 150, y + 150);
			}
		}

	}

	public void drawArmies(){

		for(int i=0; i<armies.size(); i++){
			
			if(!armies.get(i).inCastle && !showTownMenu && !showFarm && !showBarracks && !showCastle){
				
				int y = armies.get(i).tile.y + 1;
				int x = armies.get(i).tile.x;
				
				int dX = (x - drawX)*40 - 8;
				int dY = 720 - (y - drawY)*40 - 8;
				
				batch.draw(armies.get(i).primary.texture, dX, dY );
				batch.draw(armies.get(i).faction.flagMini, dX + 51, dY + 51);
			
				
				
			} 
			
			if(armies.get(i).inCastle) {
				if(showTownMenu && currentTown.garrison.size() > 0){
					if(currentTown.garrison.get(currentPage).id == armies.get(i).id){
						int u = 0;
						int bonusY = 0;
						for(int k=0; k<currentTown.garrison.get(currentPage).heroes.size(); k++){
							int bonusX = u*85;
							if(u == 3){
								u = 0;
								bonusY -= 50;
							}
							batch.draw(currentTown.garrison.get(currentPage).heroes.get(k).texture, 560 + bonusX, 420 + bonusY);
							u++;
						}
						for(int k=0; k<currentTown.garrison.get(currentPage).soldiers.size(); k++){
							int bonusX = u*85;
							if(u == 3){
								u = 0;
								bonusY -= 50;
							}
							batch.draw(currentTown.garrison.get(currentPage).soldiers.get(k).texture, 560 + bonusX, 420 + bonusY);
							
						}
					}
				}
				
			}
		}
		
		

	}

	public void drawFactions(){



	}
	
	public void drawBattleScreen(){
		
		if(showBattleScreen){
			
			batch.draw(game.textures.battleScreen, 250, 150);
			batch.draw(attacker.faction.battleFlag, 390, 505);
			batch.draw(defender.faction.battleFlag, 650, 505);

			int u = 0;
			
			for(int i=0; i<attacker.soldiers.size(); i++){
				batch.draw(attacker.soldiers.get(i).texture, 380, 420 - u*75);
				u++;
			}

			for(int i=0; i<attacker.heroes.size(); i++){
				batch.draw(attacker.heroes.get(i).texture, 380, 420 - u*75);
				u++;
			}
			
			u = 0;
			for(int i=0; i<defender.soldiers.size(); i++){
				batch.draw(defender.soldiers.get(i).texture, 640, 420 - u*75);
				u++;
			}
			
			for(int i=0; i<defender.heroes.size(); i++){
				batch.draw(defender.heroes.get(i).texture, 640, 420 - u*75);
				u++;
			}
			
			
		}
		
	}

	public void drawGui(){

		if(showTownMenu){
			batch.draw(game.textures.shad, 0, 0);
			batch.draw(game.textures.townMenu, 250, 150);
			
			if(currentTown.id != 0){
				font.draw(batch, castles.get(currentTown.id-1).name, 500, 550);
				
				if(castles.get(currentTown.id-1).barracks == 1)
					batch.draw(game.textures.barracks1, 300, 410);
				if(castles.get(currentTown.id-1).barracks == 2)
					batch.draw(game.textures.barracks2, 300, 410);
				if(castles.get(currentTown.id-1).barracks == 3)
					batch.draw(game.textures.barracks3, 300, 410);
				
				if(castles.get(currentTown.id-1).walls == 1)
					batch.draw(game.textures.castle1, 300, 315);
				if(castles.get(currentTown.id-1).walls == 2)
					batch.draw(game.textures.castle2, 300, 315);
				if(castles.get(currentTown.id-1).walls == 3)
					batch.draw(game.textures.castle3, 300, 315);
				
				if(castles.get(currentTown.id-1).farms == 1)
					batch.draw(game.textures.farm1, 300, 220);
				if(castles.get(currentTown.id-1).farms == 2)
					batch.draw(game.textures.farm2, 300, 220);
				if(castles.get(currentTown.id-1).farms == 3)
					batch.draw(game.textures.farm3, 300, 220);
				
				batch.draw(game.textures.tmp, 670, 175);
			}	
			
		}
		
		if(showFarm){
			batch.draw(game.textures.shad, 0, 0);
			batch.draw(game.textures.townFarm, 250, 150);
			
			font.draw(batch, castles.get(currentTown.id-1).name + "'s Farm", 500, 550);
			font.draw(batch, "Current Level: ", 300, 480);
			font.draw(batch, "Next Level: ", 500, 480);
			
			font.draw(batch, "Effects: ", 300, 350);
			font.draw(batch, "Effects: ", 500, 350);

			font.draw(batch, "Tap to the picture to upgrade.", 500, 290);
			
			if(castles.get(currentTown.id-1).farms == 1){
				batch.draw(game.textures.farm1, 300, 370);
				font.draw(batch, "10 Gold per turn.", 300, 320);
			}
			if(castles.get(currentTown.id-1).farms == 2){
				batch.draw(game.textures.farm2, 300, 370);
				font.draw(batch, "20 Gold per turn.", 300, 320);
			}
			if(castles.get(currentTown.id-1).farms == 3){
				batch.draw(game.textures.farm3, 300, 370);
				font.draw(batch, "30 Gold per turn.", 300, 320);
			}
			
			if(castles.get(currentTown.id-1).farms == 1){
				batch.draw(game.textures.farm2, 500, 370);
				font.draw(batch, "20 Gold per turn.", 500, 320);
			}
			if(castles.get(currentTown.id-1).farms == 2){
				batch.draw(game.textures.farm3, 500, 370);
				font.draw(batch, "30 Gold per turn.", 500, 320);
			}
			
		}
		
		if(showCastle){
			batch.draw(game.textures.shad, 0, 0);
			batch.draw(game.textures.townCastle, 250, 150);	
			
			font.draw(batch, castles.get(currentTown.id-1).name + "'s Castle", 500, 550);
			font.draw(batch, "Current Level: ", 300, 480);
			font.draw(batch, "Next Level: ", 500, 480);
			
			font.draw(batch, "Effects: ", 300, 350);
			font.draw(batch, "Effects: ", 500, 350);

			font.draw(batch, "Tap to the picture to upgrade.", 500, 290);
			
			if(castles.get(currentTown.id-1).walls == 1){
				batch.draw(game.textures.castle1, 300, 370);
				font.draw(batch, "Defense Bonus: 1", 300, 320);
			}
			if(castles.get(currentTown.id-1).walls == 2){
				batch.draw(game.textures.castle2, 300, 370);
				font.draw(batch, "Defense Bonus: 2", 300, 320);
			}
			if(castles.get(currentTown.id-1).walls == 3){
				batch.draw(game.textures.castle3, 300, 370);
				font.draw(batch, "Defense Bonus: 3", 300, 320);
			}
			
			if(castles.get(currentTown.id-1).walls == 1){
				batch.draw(game.textures.castle2, 500, 370);
				font.draw(batch, "Defense Bonus: 2", 500, 320);
			}
			if(castles.get(currentTown.id-1).walls == 2){
				batch.draw(game.textures.castle3, 500, 370);
				font.draw(batch, "Defense Bonus: 3", 500, 320);
			}
			
			
		}
		
		if(showBarracks){
			batch.draw(game.textures.shad, 0, 0);
			batch.draw(game.textures.townBarracks, 250, 150);
			
			font.draw(batch, castles.get(currentTown.id-1).name + "'s Barracks", 500, 550);
			font.draw(batch, "Current Level: ", 300, 480);
			font.draw(batch, "Next Level: ", 500, 480);
			
			font.draw(batch, "Effects: ", 300, 350);
			font.draw(batch, "Effects: ", 500, 350);

			font.draw(batch, "Tap to the picture to upgrade.", 500, 290);
			
			if(castles.get(currentTown.id-1).barracks == 1){
				batch.draw(game.textures.barracks1, 300, 370);
				font.draw(batch, "Can train swordsmen.", 300, 320);
				
				batch.draw(game.textures.swordsman, 750, 400);
			}
			if(castles.get(currentTown.id-1).barracks == 2){
				batch.draw(game.textures.barracks2, 300, 370);
				font.draw(batch, "Can train archers.", 300, 320);
				
				batch.draw(game.textures.swordsman, 750, 400);
				batch.draw(game.textures.archer, 750, 300);
			}
			if(castles.get(currentTown.id-1).barracks == 3){
				batch.draw(game.textures.barracks3, 300, 370);
				font.draw(batch, "Can train heroes.", 300, 320);
				
				batch.draw(game.textures.swordsman, 750, 400);
				batch.draw(game.textures.archer, 750, 300);
				batch.draw(game.textures.general, 750, 200);
			}
			
			if(castles.get(currentTown.id-1).barracks == 1){
				batch.draw(game.textures.barracks2, 500, 370);
				font.draw(batch, "Can train archers.", 500, 320);
			}
			if(castles.get(currentTown.id-1).barracks == 2){
				batch.draw(game.textures.barracks3, 500, 370);
				font.draw(batch, "Can train heroes.", 500, 320);
			}
			
		}
		
		if(showArmy){
			if(currentArmy != null){
				int u = 0;
				int bonusY = 0;
				for(int k=0; k<currentArmy.heroes.size(); k++){
					int bonusX = u*85;
					if(u == 2){
						u = 0;
						bonusY -= 50;
					}
					batch.draw(currentArmy.heroes.get(k).texture, 1080 + bonusX, 250 + bonusY);
					u++;
				}
				for(int k=0; k<currentArmy.soldiers.size(); k++){
					int bonusX = u*85;
					if(u == 2){
						u = 0;
						bonusY -= 50;
					}
					batch.draw(currentArmy.soldiers.get(k).texture, 1080 + bonusX, 250 + bonusY);
					
				}
				
				if(!currentArmy.route.isEmpty() && !showTownMenu){
					for(int i=0; i<currentArmy.route.size(); i++){
						int x = ((currentArmy.route.get(i).x - drawX)*40) + 10;
						int y = (720 - (currentArmy.route.get(i).y+1 - drawY)*40) + 10;
						if(x < 1000 && y > 50 && y < 670)
							batch.draw(game.textures.step, x, y);
					}
				}
				
			}
		}
		
		font.draw(batch, "Current Gold: " + Integer.toString(currentFaction.gold), 1100, 460);
		batch.draw(currentFaction.shield, 1110, 320);

	}

	public void controlTouch() throws InterruptedException{

		if(Gdx.input.justTouched())
		{
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			tapX = (int) tap.x;
			tapY = (int) tap.y;

			if(tapX < 1200 && tapX > 1100 && tapY < 460 && tapY > 350){
				
				showTownMenu = false;
				showArmy = false;
				showFarm = false;
				showBarracks = false;
				showCastle = false;
				currentFaction = factions.get((currentFaction.id + 1) % factions.size());
				
			}
			
			if(!showTownMenu && !showFarm && !showBarracks && !showCastle && tapX > 50 &&  tapX < 1000 && tapY > 50 && tapY < 670){
				
				int curX = tapX / 40 ;
				int curY = (720 - tapY) / 40 ;

				curX += drawX;
				curY += drawY;

				if(map.tiles[curX][curY].onCastle && !showArmy){
					showTownMenu = true;
					currentTown = map.tiles[curX][curY].castle;
				}
				
				if(map.tiles[curX][curY].onArmy){
					
					if(currentArmy == null){
						currentArmy = map.tiles[curX][curY].army;
						showArmy = true;
					} else {
						if(currentArmy.id == map.tiles[curX][curY].army.id){
							showArmy = false;
							currentArmy = null;
						}
					}
				}
				
				if(showArmy){
					if(!map.tiles[curX][curY].onCastle){
						if(currentArmy.route.isEmpty()){
							
							int myX = currentArmy.tile.x;
							int myY = currentArmy.tile.y;
						
							if(map.tiles[curX][curY].onArmy && currentArmy.id != map.tiles[curX][curY].army.id){
								
								int fX = Math.abs(curX-myX);
								int fY = Math.abs(curX-myX);
								
								if(fX > 2 && fY > 2){
									if((myX - curX) < (myY - curY)){
										if(myY - curY > 0)
											curY++;
										else
											curY--;
									} else {
										if(myX - curX > 0)
											curX++;
										else
											curX--;
									}
								} else {

									if(currentArmy.faction.id != map.tiles[curX][curY].army.faction.id){
										showBattleScreen = true;
										attacker = currentArmy;
										defender = map.tiles[curX][curY].army;
									}
									
								}
							}
							
							while(curX != myX || curY != myY){
								
								if(curX > myX){
									myX++;
								} else if(curX < myX){
									myX--;
								}
								
								if(curY > myY){
									myY++;
								} else if(curY < myY){
									myY--;
								}
								
								currentArmy.route.add(map.tiles[myX][myY]);
								
							}
						} else {
							
							boolean walk = false;
							
							for(int i=0; i<currentArmy.route.size(); i++){
								if(currentArmy.route.get(i).id == map.tiles[curX][curY].id){
									walk = true;
									currentArmy.walking = true;
								}
							}
							
							if(!walk)
								currentArmy.route = new ArrayList<Tile>();
							else {
								if(currentArmy.walking)
									currentArmy.walk(this);
							}
								
							
						}
					}
					
					
				}
				
			}
			
			if(showTownMenu){
				
				if(tapX < 820 && tapX > 770 && tapY < 580 && tapY > 530){
					showTownMenu = false;
				}
				
				if(tapX < 695 && tapX > 670 && tapY < 195 && tapY > 170){
					if(currentTown.garrison.size() >= 1){
						currentTown.garrison.get(0).atGate = true;
						currentTown.garrison.get(0).inCastle = false;
						showTownMenu = false;
						currentTown.garrison.get(0).tileId = map.tiles[currentTown.x+1][currentTown.y].id;
						currentTown.garrison.get(0).tile = map.tiles[currentTown.x+1][currentTown.y];
						map.tiles[currentTown.x+1][currentTown.y].onArmy = true;
						map.tiles[currentTown.x+1][currentTown.y].army = currentTown.garrison.get(0);
						currentTown.garrison.remove(0);
					}
				}
				
				if(tapX < 375 && tapX > 300 && tapY < 485 && tapY > 410){
					showBarracks = true;
					showTownMenu = false;
					showCastle = false;
					showFarm = false;
				}
				
				if(tapX < 375 && tapX > 300 && tapY < 390 && tapY > 315){
					showCastle = true;
					showTownMenu = false;
					showFarm = false;
					showBarracks = false;
				}
				
				if(tapX < 375 && tapX > 300 && tapY < 295 && tapY > 220){
					showFarm = true;
					showTownMenu = false;
					showCastle = false;
					showBarracks = false;
				}
				
			}
			
			if(showFarm){
				if(tapX < 820 && tapX > 770 && tapY < 580 && tapY > 530){
					showTownMenu = true;
					showFarm = false;
				}
				if(tapX < 575 && tapX > 500 && tapY < 445 && tapY > 370 && currentTown.farms < 3){
					currentTown.farms++;
					currentTown.faction.gold -= currentTown.farms*100;
				}
			}
			
			if(showCastle){
				if(tapX < 820 && tapX > 770 && tapY < 580 && tapY > 530){
					showTownMenu = true;
					showCastle = false;
				}
				if(tapX < 575 && tapX > 500 && tapY < 445 && tapY > 370 && currentTown.walls < 3){
					currentTown.walls++;
					currentTown.faction.gold -= currentTown.walls*100;
				}
			}
			
			if(showBarracks){
				if(tapX < 820 && tapX > 770 && tapY < 580 && tapY > 530){
					showTownMenu = true;
					showBarracks = false;
				}
				if(tapX < 575 && tapX > 500 && tapY < 445 && tapY > 370 && currentTown.barracks < 3){
					currentTown.barracks++;
					currentTown.faction.gold -= currentTown.barracks*100;
				}
			}
			
			if(!showTownMenu && !showBarracks && !showFarm && !showCastle && !showBattleScreen){
				
				if(tapX < 1240 && tapX > 1050 && tapY < 680 && tapY > 500){
					
					tapX = tapX - 1050;
					tapY = 680 - tapY;
					drawX = tapX/2 - 13;
					drawY = tapY/2 - 13;
					
					if(drawX < 0)
						drawX = 0;
					if(drawY < 0)
						drawY = 0;
					
					if(drawX > 76)
						drawX = 76;
					if(drawY > 80)
						drawY = 80;
					
				}
				
			}

		}

	}

	public void updatePlayers(){

		for(int i=0; i<players.size(); i++){
			players.get(i).update(this);
		}

	}

	public void updateCastles(){

		for(int i=0; i<castles.size(); i++){
			castles.get(i).update();
		}

	}

	public void updateFactions(){

		for(int i=0; i<factions.size(); i++){
			factions.get(i).update();
		}

	}

	public void updateArmies(){

		for(int i=0; i<armies.size(); i++){
			armies.get(i).update();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method for dragging map.
	 * It takes parameters as difference X and Y between finger down and up.
	 */
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		if(!((int) x < 970))
			return false;

		if(!((int) y < 700))
			return false;
		
		if(showTownMenu)
			return false;

		int dragX = (int) (deltaX / 16);
		int dragY = (int) (deltaY / 16);

		dragX *= -1;
		dragY *= -1;

		if(dragX < 0 && drawX + dragX < 0){
			drawX = 0;
		} else {
			drawX += (dragX);
		}

		if(drawX >= map.mapX - 31){
			drawX = map.mapX - 31;
		}
		
		if(dragY < 0 && drawY + dragY < 0){
			drawY = 0;
		} else {
			drawY += (dragY);
		}

		if(drawY >= map.mapY - 21){
			drawY = map.mapY - 21;
		}

		return false;

	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
