package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

	// Declare Textures.
	
	// Menus 
	public Texture gameSetupScreen, mainMenuScreen, optionsScreen, creditsScreen, defeatScreen, victoryScreen;
	
	// Gui
	public Texture gui, human, portrait, button, computer;
	
	public Texture humanFlagMini, undeadFlagMini;
	public Texture humanShield, undeadShield;
	public Texture humanBattleFlag, undeadBattleFlag;
	
	public Texture step, stepUn;
	
	public Texture battleScreen;
	
	// Charachters
	public Texture general;
	public Texture archer, swordsman;
	
	public Texture necromancer;
	public Texture banshee, skeleton;
	
	public Texture tmp;
	
	// ----------------------------------Tiles-----------------------------------------------
	
	// Grasses
	public Texture grass1, grass2, grass3, grass4, grassCenter, grassCenter2, grassCenter3;
	
	// Mountains
	public Texture mountain1BottomCenter, mountain1BottomLeft, mountain1BottomRight, mountain1CenterCenter, mountain1CenterLeft;
	public Texture mountain1CenterRight, mountain1TopCenter, mountain1TopLeft, mountain1TopRight;
	
	public Texture mountain2BottomCenter, mountain2BottomLeft, mountain2BottomRight, mountain2CenterCenter, mountain2CenterLeft;
	public Texture mountain2CenterRight, mountain2TopCenter, mountain2TopLeft, mountain2TopRight;
	
	 // Rivers
	 public Texture riverBottomLeft, riverBottomRight, riverConnectDown, riverConnectLeft,riverConnectRight, riverConnectUp;
	 public Texture riverCross, riverDoubleTurnDown, riverDoubleTurnLeft, riverDoubleTurnRight, riverDoubleTurnUp;
	 public Texture riverHorizontal, riverTopLeft, riverTopRight, riverVertical;
	 public Texture riverEndDown, riverEndLeft, riverEndRight, riverEndUp;
	 
	 // Roads
	 public Texture roadBottomLeft, roadBottomRight, roadCross, roadDoubleTurnDown, roadDoubleTurnLeft; 
	 public Texture roadDoubleTurnUp, roadHorizontal, roadDoubleTurnRight;
	 public Texture roadTopLeft, roadTopRight, roadVertical;
	 public Texture roadEndDown, roadEndLeft, roadEndRight, roadEndUp;
	 
	// Seas
	public Texture seaEast, seaNorth, seaNorthEast, seaNorthWest, seaSouth, seaSouthEast, seaSouthWest, seaWest, seaCenter;
	public Texture  shoreNorthEast, shoreNorthWest, shoreSouthEast,shoreSouthWest;
	
	// Trees
	public Texture deadTree, deadTree2, deadTree3, deadTree4, palm, palm2, palm3, singleTree, singleTree2;
	public Texture treeCenter, treeEast, treeNorth, treeNorthEast, treeNorthWest, treeSouth, treeSouthEast, treeSouthWest, treeWest;
	
	// Towns
	
	public Texture barracks1, barracks2, barracks3, castle1, castle2, castle3, easternTown, easternTown2, farm1, farm2, farm3;
	public Texture townMenu, westernCastle, westernCastle2, westernTown, westernTown2, westernVillage, westernVillage2, shad;
	
	public Texture townCastle, townBarracks, townFarm;
	
	// Mini Map
	
	public Texture grassMini, waterMini, roadMini, treeMini, mountainMini;
	public Texture castleUndeadMini, castleHumanMini;
	public Texture border;
	
	// Loads the tiles that is required to draw the game map. 
	public void loadTilesets(){
		
		grass1 			= new Texture (Gdx.files.internal("tilesets/grass/grass1.png"));
		grass2 			= new Texture (Gdx.files.internal("tilesets/grass/grass2.png"));
		grass3 			= new Texture (Gdx.files.internal("tilesets/grass/grass3.png"));
		grass4 			= new Texture (Gdx.files.internal("tilesets/grass/grass4.png"));
		grassCenter 	= new Texture (Gdx.files.internal("tilesets/grass/grassCenter.png"));
		grassCenter2 	= new Texture (Gdx.files.internal("tilesets/grass/grassCenter2.png"));
		grassCenter3 	= new Texture (Gdx.files.internal("tilesets/grass/grassCenter3.png"));
		
		mountain1BottomCenter 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainBottomCenter.png"));
		mountain1BottomLeft 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainBottomLeft.png"));
		mountain1BottomRight 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainBottomRight.png"));
		mountain1CenterCenter 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainCenterCenter.png"));
		mountain1CenterLeft 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainCenterLeft.png"));
		mountain1CenterRight 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainCenterRight.png"));
		mountain1TopCenter		= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainTopCenter.png"));
		mountain1TopLeft 		= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainTopLeft.png"));
		mountain1TopRight 		= new Texture(Gdx.files.internal("tilesets/mountain/mountain1/mountainTopRight.png"));
		
		mountain2BottomCenter 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2BottomCenter.png"));
		mountain2BottomLeft 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2BottomLeft.png"));
		mountain2BottomRight 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2BottomRight.png"));
		mountain2CenterCenter	= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2CenterCenter.png"));
		mountain2CenterLeft		= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2CenterLeft.png"));
		mountain2CenterRight 	= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2CenterRight.png"));
		mountain2TopCenter		= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2TopCenter.png"));
		mountain2TopLeft 		= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2TopLeft.png"));
		mountain2TopRight		= new Texture(Gdx.files.internal("tilesets/mountain/mountain2/mountain2TopRight.png"));
		
		riverBottomLeft 		= new Texture (Gdx.files.internal("tilesets/river/riverBottomLeft.png"));
		riverBottomRight 		= new Texture (Gdx.files.internal("tilesets/river/riverBottomRight.png"));
		riverConnectDown 		= new Texture (Gdx.files.internal("tilesets/river/riverConnectDown.png"));
		riverConnectLeft 		= new Texture (Gdx.files.internal("tilesets/river/riverConnectLeft.png"));
		riverConnectRight 		= new Texture (Gdx.files.internal("tilesets/river/riverConnectRight.png"));
		riverConnectUp 			= new Texture (Gdx.files.internal("tilesets/river/riverConnectUp.png"));
		riverCross 				= new Texture (Gdx.files.internal("tilesets/river/riverCross.png"));
		riverDoubleTurnDown 	= new Texture (Gdx.files.internal("tilesets/river/riverDoubleTurnDown.png"));
		riverDoubleTurnLeft 	= new Texture (Gdx.files.internal("tilesets/river/riverDoubleTurnLeft.png"));
		riverDoubleTurnRight 	= new Texture (Gdx.files.internal("tilesets/river/riverDoubleTurnRight.png"));
		riverDoubleTurnUp 		= new Texture (Gdx.files.internal("tilesets/river/riverDoubleTurnUp.png"));
		riverHorizontal 		= new Texture (Gdx.files.internal("tilesets/river/riverHorizontal.png"));
		riverTopLeft 			= new Texture (Gdx.files.internal("tilesets/river/riverTopLeft.png"));
		riverTopRight			= new Texture (Gdx.files.internal("tilesets/river/riverTopRight.png"));
		riverVertical 			= new Texture (Gdx.files.internal("tilesets/river/riverVertical.png"));
		riverEndDown			= new Texture (Gdx.files.internal("tilesets/river/riverEndDown.png"));
		riverEndLeft			= new Texture (Gdx.files.internal("tilesets/river/riverEndLeft.png"));
		riverEndRight			= new Texture (Gdx.files.internal("tilesets/river/riverEndRight.png"));
		riverEndUp				= new Texture (Gdx.files.internal("tilesets/river/riverEndUp.png"));
		
		roadBottomLeft				= new Texture (Gdx.files.internal("tilesets/road/roadBottomLeft.png"));
		roadBottomRight				= new Texture (Gdx.files.internal("tilesets/road/roadBottomRight.png"));
		roadCross					= new Texture (Gdx.files.internal("tilesets/road/roadCross.png"));
		roadDoubleTurnDown			= new Texture (Gdx.files.internal("tilesets/road/roadDoubleTurnDown.png"));
		roadDoubleTurnLeft			= new Texture (Gdx.files.internal("tilesets/road/roadDoubleTurnLeft.png"));
		roadDoubleTurnRight			= new Texture (Gdx.files.internal("tilesets/road/roadDoubleTurnRight.png"));
		roadDoubleTurnUp			= new Texture (Gdx.files.internal("tilesets/road/roadDoubleTurnUp.png"));
		roadTopLeft					= new Texture (Gdx.files.internal("tilesets/road/roadTopLeft.png"));
		roadTopRight				= new Texture (Gdx.files.internal("tilesets/road/roadTopRight.png"));
		roadVertical				= new Texture (Gdx.files.internal("tilesets/road/roadVertical.png"));
		roadHorizontal				= new Texture (Gdx.files.internal("tilesets/road/roadHorizontal.png"));
		roadEndDown					= new Texture (Gdx.files.internal("tilesets/road/roadEndDown.png"));
		roadEndLeft					= new Texture (Gdx.files.internal("tilesets/road/roadEndLeft.png"));
		roadEndRight				= new Texture (Gdx.files.internal("tilesets/road/roadEndRight.png"));
		roadEndUp					= new Texture (Gdx.files.internal("tilesets/road/roadEndUp.png"));
		
		seaEast 		= new Texture(Gdx.files.internal("tilesets/sea/seaEast.png"));
		seaNorth 		= new Texture(Gdx.files.internal("tilesets/sea/seaNorth.png"));
		seaNorthEast 	= new Texture(Gdx.files.internal("tilesets/sea/seaNorthEast.png"));
		seaNorthWest 	= new Texture(Gdx.files.internal("tilesets/sea/seaNorthWest.png"));
		seaSouth 		= new Texture(Gdx.files.internal("tilesets/sea/seaSouth.png"));
		seaSouthEast	= new Texture(Gdx.files.internal("tilesets/sea/seaSouthEast.png"));
		seaSouthWest 	= new Texture(Gdx.files.internal("tilesets/sea/seaSouthWest.png"));
		seaWest 		= new Texture(Gdx.files.internal("tilesets/sea/seaWest.png"));
		seaCenter 		= new Texture(Gdx.files.internal("tilesets/sea/seaCenter.png"));
		
		shoreNorthEast = new Texture(Gdx.files.internal("tilesets/sea/shoreNorthEast.png"));
		shoreNorthWest = new Texture(Gdx.files.internal("tilesets/sea/shoreNorthWest.png"));
		shoreSouthEast = new Texture(Gdx.files.internal("tilesets/sea/shoreSouthEast.png"));
		shoreSouthWest = new Texture(Gdx.files.internal("tilesets/sea/shoreSouthWest.png"));
		
		deadTree 	= new Texture(Gdx.files.internal("tilesets/tree/deadTree.png"));
		deadTree2 	= new Texture(Gdx.files.internal("tilesets/tree/deadTree2.png"));
		deadTree3 	= new Texture(Gdx.files.internal("tilesets/tree/deadTree3.png"));
		deadTree4 	= new Texture(Gdx.files.internal("tilesets/tree/deadTree4.png"));
		palm 		= new Texture(Gdx.files.internal("tilesets/tree/palm.png"));
		palm2 		= new Texture(Gdx.files.internal("tilesets/tree/palm2.png"));
		palm3 		= new Texture(Gdx.files.internal("tilesets/tree/palm3.png"));
		singleTree 	= new Texture(Gdx.files.internal("tilesets/tree/singleTree.png"));
		singleTree2 = new Texture(Gdx.files.internal("tilesets/tree/singleTree2.png"));
		
		treeCenter 		= new Texture(Gdx.files.internal("tilesets/tree/treeCenter.png"));
		treeEast 		= new Texture(Gdx.files.internal("tilesets/tree/treeEast.png"));
		treeNorth 		= new Texture(Gdx.files.internal("tilesets/tree/treeNorth.png"));
		treeNorthEast 	= new Texture(Gdx.files.internal("tilesets/tree/treeNorthEast.png"));
		treeNorthWest 	= new Texture(Gdx.files.internal("tilesets/tree/treeNorthWest.png"));
		treeSouth 		= new Texture(Gdx.files.internal("tilesets/tree/treeSouth.png"));
		treeSouthEast 	= new Texture(Gdx.files.internal("tilesets/tree/treeSouthEast.png"));
		treeSouthWest 	= new Texture(Gdx.files.internal("tilesets/tree/treeSouthWest.png"));
		treeWest 		= new Texture(Gdx.files.internal("tilesets/tree/treeWest.png"));
	
		grassMini = new Texture(Gdx.files.internal("miniMap/grassMini.png"));
		treeMini = new Texture(Gdx.files.internal("miniMap/treeMini.png"));
		waterMini = new Texture(Gdx.files.internal("miniMap/waterMini.png"));
		roadMini = new Texture(Gdx.files.internal("miniMap/roadMini.png"));
		mountainMini = new Texture (Gdx.files.internal("minimap/mountainMini.png"));
		
		castleUndeadMini = new Texture(Gdx.files.internal("miniMap/castleUndead.png"));
		castleHumanMini = new Texture(Gdx.files.internal("miniMap/castleHuman.png"));
		
		border = new Texture(Gdx.files.internal("miniMap/border.png"));
		
	}
	
	// Loads the game menus. 
	public void loadMenus(){
		gameSetupScreen 	= new Texture (Gdx.files.internal("screens/gameSetupScreen.jpg")); 
		mainMenuScreen 		= new Texture (Gdx.files.internal("screens/mainMenuScreen.jpg"));
		optionsScreen 		= new Texture (Gdx.files.internal("screens/optionsScreen.jpg"));
		creditsScreen 		= new Texture (Gdx.files.internal("screens/creditsScreen.jpg"));
		defeatScreen 		=  new Texture (Gdx.files.internal("screens/defeatScreen.jpg"));
		victoryScreen 		= new Texture (Gdx.files.internal("screens/victoryScreen.jpg"));
	}
	
	
	public void loadGui(){
		
		gui 		= new Texture (Gdx.files.internal("gui/gui.png")); 
		human 		= new Texture (Gdx.files.internal("gui/human.png")); 
		portrait 	= new Texture (Gdx.files.internal("gui/portrait.png")); 
		button 		= new Texture (Gdx.files.internal("gui/button.png")); 
		computer 	= new Texture (Gdx.files.internal("gui/computer.png")); 
		tmp = new Texture (Gdx.files.internal("button.png")); 
		
		battleScreen = new Texture (Gdx.files.internal("gui/battleScreen.png"));
		
		humanFlagMini = new Texture (Gdx.files.internal("humanFlagMini.png")); 
		undeadFlagMini = new Texture (Gdx.files.internal("undeadFlagMini.png")); 
		
		humanShield = new Texture (Gdx.files.internal("gui/humanShield.png"));
		undeadShield = new Texture (Gdx.files.internal("gui/undeadShield.png"));
		
		humanBattleFlag = new Texture (Gdx.files.internal("gui/humanBattleFlag.png"));
		undeadBattleFlag = new Texture (Gdx.files.internal("gui/undeadBattleFlag.png"));
		
		step = new Texture (Gdx.files.internal("gui/step.png"));
		stepUn = new Texture (Gdx.files.internal("gui/stepUn.png"));
		
	}
	
	public void loadTowns(){
		
 		easternTown = new Texture(Gdx.files.internal("towns/easternTown.png"));
		easternTown2 = new Texture(Gdx.files.internal("towns/easternTown2.png"));
		westernTown = new Texture(Gdx.files.internal("towns/westernTown.png"));
		westernTown2 = new Texture(Gdx.files.internal("towns/westernTown2.png"));
		westernVillage = new Texture(Gdx.files.internal("towns/westernVillage.png"));
		westernVillage2 = new Texture(Gdx.files.internal("towns/westernVillage2.png"));
		westernCastle = new Texture(Gdx.files.internal("towns/westernCastle.png"));
		westernCastle2 = new Texture(Gdx.files.internal("towns/westernCastle2.png"));
		farm1 = new Texture (Gdx.files.internal("towns/farm1.png"));
		farm2 = new Texture (Gdx.files.internal("towns/farm2.png"));
		farm3 = new Texture (Gdx.files.internal("towns/farm3.png"));
		barracks1 = new Texture (Gdx.files.internal("towns/barracks1.png"));
		barracks2 = new Texture (Gdx.files.internal("towns/barracks2.png"));
		barracks3 = new Texture (Gdx.files.internal("towns/barracks3.png"));
		castle1 = new Texture (Gdx.files.internal("towns/castle1.png"));
		castle2 = new Texture (Gdx.files.internal("towns/castle2.png"));
		castle3 = new Texture (Gdx.files.internal("towns/castle3.png"));
		
		townMenu = new Texture(Gdx.files.internal("towns/townMenu.png"));
		shad = new Texture (Gdx.files.internal("towns/shad.png"));
		
		townFarm = new Texture(Gdx.files.internal("towns/townFarm.png"));
		townBarracks = new Texture(Gdx.files.internal("towns/townBarracks.png"));
		townCastle = new Texture(Gdx.files.internal("towns/townCastle.png"));
		
	}
	
	public void loadChars(){
		
		general = new Texture(Gdx.files.internal("chars/knight.png"));
		swordsman = new Texture(Gdx.files.internal("chars/swordsman.png"));
		archer = new Texture(Gdx.files.internal("chars/archer.png"));
		
		necromancer = new Texture(Gdx.files.internal("chars/imp.png"));
		banshee = new Texture(Gdx.files.internal("chars/banshee.png"));
		skeleton = new Texture(Gdx.files.internal("chars/skeleton.png"));
		
	}
}
