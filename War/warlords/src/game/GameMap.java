package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.pikseloyun.warlords.Warlords;

public class GameMap {

	public Tile[][] tiles;
	public int mapX, mapY;
	
	Warlords game;
	
	public GameMap(int mapX, int mapY, Warlords game){
		
		this.game = game;
		
		this.mapX = mapX;
		this.mapY = mapY;
		
		tiles = new Tile[mapX][mapY];
		
		initializeMap();
		
	}
	
	public void initializeMap(){
		
		initializeAll();
		initializeGrass();
		initializeSea();
		initializeForest();
		initializeRiver();
		initializeRoad();
		initializeMountain();
		
	}
	
	private void initializeAll(){
		
		String fileName = "map/demoMap.txt";
		
		FileHandle fStream = Gdx.files.internal(fileName);
		fileName = fStream.readString();
		
		String[] str;
		str = fileName.split("\\r?\\n");
		
		int id = 1;
		
		for(int i=0; i<str.length; i++){
			for(int j=0; j<str[i].length(); j++){
				if(str[i].charAt(j) == 'G'){
					tiles[i][j] = new Tile(id++, i, j, "Grass");
					tiles[i][j].texture = game.textures.grassCenter3;
				} else if(str[i].charAt(j) == 'T'){
					tiles[i][j] = new Tile(id++, i, j, "Tree");
					tiles[i][j].texture = game.textures.treeCenter;
				} else if(str[i].charAt(j) == 'S'){
					tiles[i][j] = new Tile(id++, i, j, "Sea");
					tiles[i][j].texture = game.textures.seaCenter;
				} else if(str[i].charAt(j) == 'M'){
					tiles[i][j] = new Tile(id++, i, j, "Mountain");
					tiles[i][j].texture = game.textures.mountain1TopRight;
				} else if(str[i].charAt(j) == 'R'){
					tiles[i][j] = new Tile(id++, i, j, "Road");
					tiles[i][j].texture = game.textures.roadBottomLeft;
				} else if(str[i].charAt(j) == 'A'){
					tiles[i][j] = new Tile(id++, i, j, "River");
					tiles[i][j].texture = game.textures.riverBottomLeft;
				}
			}
		}
		
	}

	private void initializeGrass() {
		
	}
	
	private void initializeSea() {

		for(int i=0; i<tiles.length; i++){
			for(int j=0; j<tiles[i].length; j++){
				
				if(tiles[i][j].type.equals("Sea")){
				
					//en �stteki sat�rsa
					if (i == 0){
						//k��eler hari� en �st sat�r kontrol�
						if(j > 0 && j < tiles[i].length-1){
						
							//i'ler dikey, j'ler yatay
							
							// Sol �st.
							if(!tiles[i][j-1].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaEast;
							}
							
							// Orta �st. 
							else if(tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							} 
							
							// Sa� �st.
							else if(tiles[i+1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && !tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaWest;
							} 
						}
						
						//sol �st k��e kontrol�, 
						else if (j == 0){
							if(tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							}
						}
						
						//sa� �st k��e kontrol�,
						else if (j == tiles[i].length-1){
							if(tiles[i+1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							}
						}
					}
					
					// en alt sat�rsa
					else if (i == tiles.length-1){
						
						//k��eler hari� en alt sat�r kontrol�
						if(j > 0 && j < tiles[i].length-1){
							
							// Sol alt.
							if(tiles[i-1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && !tiles[i][j-1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaEast;
							} 
							
							// Orta alt.
							else if(tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							} 
							
							// Sa� alt.
							else if(tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && !tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaWest;
							}
						}	
						
						//sol k��e en alt sat�r kontrol�
						else if(j == 0){
							
							// Sol alt.
							if(tiles[i-1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							}
						}
						
						//sa� en alt sat�r kontrol�
						else if(j == tiles[i].length-1){
							
							// Sa� alt.
							if(tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
								tiles[i][j].texture = game.textures.seaCenter;
							} 
						}
					}
					
					//en sol sat�r kontrol�
					else if (j == 0){
						
						// Sol �st.
						if(!tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaSouth;
						}
						
						// Sol orta. 
						else if(tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaCenter;
						} 
						
						// Sol alt.
						else if(!tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaNorth;
						} 
					}
					
					//en sa� sat�r kontrol�
					else if (j == tiles[i].length-1){
						
						// Sa� �st.
						if(tiles[i+1][j].type.equals("Sea") && !tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaSouth;
						} 
						
						// Sa� orta.
						else if(tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaCenter;
						} 
						
						// Sa� alt.
						else if(!tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaNorth;
						} 
					}
					
					else {
						// Sol �st.
						if(!tiles[i-1][j].type.equals("Sea") && !tiles[i][j-1].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaNorthEast;
						}
						
						// Sol orta. 
						else if(!tiles[i][j-1].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaEast;
						} 
						
						// Sol alt.
						else if(!tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && !tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaSouthWest;
						} 
						
						// Orta �st
						else if(!tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaSouth;
						} 
						
						// Orta orta.
						else if(tiles[i+1][j].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaCenter;
						}
						
						// Orta alt.
						else if(tiles[i-1][j].type.equals("Sea") && !tiles[i+1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaNorth;
						} 
						
						// Sa� �st.
						else if(tiles[i+1][j].type.equals("Sea") && !tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && !tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaNorthWest;
						} 
						
						// Sa� orta.
						else if(tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && !tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaWest;
						} 
						
						// Sa� alt.
						else if(!tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && !tiles[i][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.seaSouthEast;
						} 
						
						// �� b�key sa� �st.
						if(tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && !tiles[i+1][j-1].type.equals("Sea") && tiles[i-1][j+1].type.equals("Sea") && tiles[i-1][j-1].type.equals("Sea") && tiles[i+1][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.shoreNorthEast;
						} 
						
						// �� b�key sol �st.
						if(tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i+1][j-1].type.equals("Sea") && tiles[i-1][j+1].type.equals("Sea") && tiles[i-1][j-1].type.equals("Sea") && !tiles[i+1][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.shoreNorthWest;
						} 
						
						// �� b�key sa� alt.
						if(tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i+1][j-1].type.equals("Sea") && tiles[i-1][j+1].type.equals("Sea") && !tiles[i-1][j-1].type.equals("Sea") && tiles[i+1][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.shoreSouthEast;
						} 
						
						// �� b�key sol alt.
						if(tiles[i+1][j].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i+1][j-1].type.equals("Sea") && !tiles[i-1][j+1].type.equals("Sea") && tiles[i-1][j-1].type.equals("Sea") && tiles[i+1][j+1].type.equals("Sea")){
							tiles[i][j].texture = game.textures.shoreSouthWest;
						} 
						
						// ---- Do�ancan 07.09.13 - 13:10
						
						// river connect left
						if(tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea")){
							tiles[i][j].texture = game.textures.riverConnectLeft;
						}
						// river connect right
						if(tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("Sea")){
							tiles[i][j].texture = game.textures.riverConnectRight;
						}
						//river connect up
						if(tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("Sea")){
							tiles[i][j].texture = game.textures.riverConnectUp;
						}
						//river connect down 
						if(tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("Sea") && tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverConnectDown;
						}
					}
				}
			}
		}
	}
	
	private void initializeForest() {

		for(int i=0; i<tiles.length; i++){
			for(int j=0; j<tiles[i].length; j++){
				
				if(tiles[i][j].type.equals("Tree")){
					
					//i'ler dikey, j'ler yatay
					
					if (i == 0){
						
						//k��eler hari� en �st sat�r kontrol�
						if(j > 0 && j < tiles[i].length-1){
						
							//i'ler dikey, j'ler yatay
							
							// Sol �st.
							if(!tiles[i][j-1].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeWest;
							}
							
							// Orta �st. 
							else if(tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							} 
							
							// Sa� �st.
							else if(tiles[i+1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeEast;
							} 
						}
						
						//sol �st k��e kontrol�, 
						else if (j == 0){
							if(tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							}
						}
						
						//sa� �st k��e kontrol�,
						else if (j == tiles[i].length-1){
							if(tiles[i+1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							}
						}
					}
					
					// en alt sat�rsa
					else if (i == tiles.length-1){
						
						//k��eler hari� en alt sat�r kontrol�
						if(j > 0 && j < tiles[i].length-1){
							
							// Sol alt.
							if(tiles[i-1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && !tiles[i][j-1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeWest;
							} 
							
							// Orta alt.
							else if(tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							} 
							
							// Sa� alt.
							else if(tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeWest;
							}
						}
						
						//sol k��e en alt sat�r kontrol�
						else if(j == 0){
							
							// Sol alt.
							if(tiles[i-1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							}
						}
						
						//sa� en alt sat�r kontrol�
						else if(j == tiles[i].length-1){
							
							// Sa� alt.
							if(tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
								tiles[i][j].texture = game.textures.treeCenter;
							} 
						}
					}
					
					//en sol sat�r kontrol�
					else if (j == 0){
						
						// Sol �st.
						if(!tiles[i-1][j].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeNorth;
						}
						
						// Sol orta. 
						else if(tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && tiles[i-1][j].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeCenter;
						} 
						
						// Sol alt.
						else if(!tiles[i+1][j].type.equals("Tree") && tiles[i-1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeSouth;
						} 
					}
					
					//en sa� sat�r kontrol�
					else if (j == tiles[i].length-1){
						
						// Sa� �st.
						if(tiles[i+1][j].type.equals("Tree") && !tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeNorth;
						} 
						
						// Sa� orta.
						else if(tiles[i-1][j].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeCenter;
						} 
						
						// Sa� alt.
						else if(!tiles[i+1][j].type.equals("Tree") && tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeSouth;
						} 
					}
					
					else {
						
						// Sol �st.
						if(!tiles[i-1][j].type.equals("Tree") && !tiles[i][j-1].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeNorthWest;
						}
						
						// Sol orta. 
						else if(!tiles[i][j-1].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && tiles[i-1][j].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeWest;
						} 
						
						// Sol alt.
						else if(!tiles[i+1][j].type.equals("Tree") && tiles[i-1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && !tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeSouthWest;
						} 
						
						// Orta �st
						else if(!tiles[i-1][j].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeNorth;
						} 
						
						// Orta orta.
						else if(tiles[i+1][j].type.equals("Tree") && tiles[i][j+1].type.equals("Tree") && tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeCenter;
						}
						
						// Orta alt.
						else if(tiles[i-1][j].type.equals("Tree") && !tiles[i+1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeSouth;
						} 
						
						// Sa� �st.
						else if(tiles[i+1][j].type.equals("Tree") && !tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeNorthEast;
						} 
						
						// Sa� orta.
						else if(tiles[i-1][j].type.equals("Tree") && tiles[i+1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeEast;
						} 
						
						// Sa� alt.
						else if(!tiles[i+1][j].type.equals("Tree") && tiles[i-1][j].type.equals("Tree") && tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.treeSouthEast;
						} 
						
						// Tek ba��na
						if(!tiles[i+1][j].type.equals("Tree") && !tiles[i-1][j].type.equals("Tree") && !tiles[i][j-1].type.equals("Tree") && !tiles[i][j+1].type.equals("Tree") && !tiles[i+1][j-1].type.equals("Tree") && !tiles[i-1][j+1].type.equals("Tree") && !tiles[i-1][j-1].type.equals("Tree") && !tiles[i+1][j+1].type.equals("Tree")){
							tiles[i][j].texture = game.textures.singleTree2;
						} 
					}
				}
			}
		}
	}
	
	private void initializeRiver() {
	//Do�ancan 06.09.13 - 16.11
		for (int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				
				if(tiles[i][j].type.equals("River")){
					//i'ler dikey, j'ler yatay
					if (i == 0){
						if(j > 0 && j < tiles[i].length-1){
							//dikey river
							if(tiles[i+1][j].type.equals("River") && !tiles[i][j+1].type.equals("River") && !tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverVertical;
							}
							//yatay river
							else if (tiles[i][j+1].type.equals("River") && tiles[i][j-1].type.equals("River") && !tiles[i+1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
							//sol �st
							else if(tiles[i+1][j].type.equals("River") && tiles[i][j+1].type.equals("River") && !tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverTopLeft;
							}
							
							//sa� �st
							else if(tiles[i+1][j].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverTopRight;
							}
						}
						
						if (j == 0){
							if(tiles[i][j+1].type.equals("River") && tiles[i+1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverTopLeft;
							}
							else if(tiles[i][j+1].type.equals("River") && !tiles[i+1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
							else if(!tiles[i][j+1].type.equals("River") && tiles[i+1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverVertical;
							}
						}
						
						if(j == tiles[i].length-1){
							if(tiles[i+1][j].type.equals("River") && tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverTopRight;
							}
							
							else if(tiles[i+1][j].type.equals("River") && !tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverVertical;
							}
							
							else if(!tiles[i+1][j].type.equals("River") && tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
						}
					}
					
					//en alt sat�r
					else if (i == tiles.length -1){
						if(j > 0 && j < tiles[i].length){
							
							if (tiles[i][j-1].type.equals("River") &&tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
							else if(tiles[i-1][j].type.equals("River") && !tiles[i][j+1].type.equals("River")&& !tiles[i][j-1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverVertical;
							}
							
							else if (tiles[i][j-1].type.equals("River") &&tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverDoubleTurnUp;
							}
							
							else if(!tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverBottomLeft;
							}
							
							else if(tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverBottomRight;
							}
						}
						
						if (j == 0){
							if(tiles[i-1][j].type.equals("River") && tiles[i][j+1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverBottomLeft;
							}
							
							else if(tiles[i-1][j].type.equals("River") && !tiles[i][j+1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverVertical;
							}
							
							else if(!tiles[i-1][j].type.equals("River") && tiles[i][j+1].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
						}
						
						else if(j == tiles[i].length-1){
							if(tiles[i][j-1].type.equals("River") && tiles[i+1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverBottomRight;
							}
							
							else if(tiles[i][j-1].type.equals("River") && !tiles[i-1][j].type.equals("River")){
								tiles[i][j].texture = game.textures.riverHorizontal;
							}
							
							else if(tiles[i-1][j].type.equals("River") && !tiles[i][j-1].type.equals("River") ){
								tiles[i][j].texture = game.textures.riverVertical;
							}
						}
						
					}
					
					if (j == 0){
						if(tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverHorizontal;
						}
						
						else if(tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverVertical;
						}
						
						else if(tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverBottomLeft;
						}
						
						else if(tiles[i][j+1].type.equals("River") && tiles[i+1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverTopLeft;
						}
						
						else if (tiles[i][j+1].type.equals("River") && tiles[i+1][j].type.equals("River") && tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverDoubleTurnRight;
						}
					}
					
					else if (j == tiles[i].length-1){
						if(tiles[i][j-1].type.equals("River") && !tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverHorizontal;
						}
						
						else if(tiles[i][j-1].type.equals("River") && 	tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverBottomRight;
						}
						
						else if(tiles[i+1][j].type.equals("River") && tiles[i][j-1].type.equals("River") && !tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverTopRight;
						}
						else if(tiles[i][j-1].type.equals("River") && tiles[i+1][j].type.equals("River") && tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverDoubleTurnLeft;
						}
						else if(!tiles[i][j-1].type.equals("River") && tiles[i+1][j].type.equals("River") && tiles[i-1][j].type.equals("River")){
							tiles[i][j].texture = game.textures.riverVertical;
						}
					}
					
					else {
						//bottom left
						if (!tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverBottomLeft;
						
						//bottom right
						else if (tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverBottomRight;
						
						//cross
						else if (tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverCross;
						
						//double turn down
						else if (tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && tiles[i+1][j].type.equals("River") && !tiles[i-1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverDoubleTurnDown;
						
						//double turn left
						else if (tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverDoubleTurnLeft;
						
						//double turn right
						else if(!tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverDoubleTurnRight;
						
						//double turn up
						else if(tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverDoubleTurnUp;
						
						//horizontal
						else if (tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverHorizontal;
						
						//top left
						if(!tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverTopLeft;
						
						//top right
						else if(tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverTopRight;
						
						// vertical
						else if (!tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverVertical;
						
						// end left
						else if(!tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")  && !tiles[i+1][j].type.equals("River")  )
							tiles[i][j].texture = game.textures.riverEndLeft;
						
						//end right
						else if(tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")  && !tiles[i+1][j].type.equals("River")  )
							tiles[i][j].texture = game.textures.riverEndRight;
						
						//end down
						else if(!tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River")  && !tiles[i+1][j].type.equals("River")  )
							tiles[i][j].texture = game.textures.riverEndDown;
						
						//end up
						else if(!tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River")  && tiles[i+1][j].type.equals("River")  )
							tiles[i][j].texture = game.textures.riverEndUp;
						
						//connect left
						if (tiles[i][j-1].type.equals("River") && tiles[i][j+1].type.equals("Sea") && !tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverHorizontal;
						
						//connect right
						if (tiles[i][j-1].type.equals("Sea") && tiles[i][j+1].type.equals("River") && !tiles[i-1][j].type.equals("River") && !tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverHorizontal;
						
						//connect up
						if(!tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("River") && tiles[i+1][j].type.equals("Sea"))
							tiles[i][j].texture = game.textures.riverVertical;
						
						//connect down
						if (!tiles[i][j-1].type.equals("River") && !tiles[i][j+1].type.equals("River") && tiles[i-1][j].type.equals("Sea") && tiles[i+1][j].type.equals("River"))
							tiles[i][j].texture = game.textures.riverVertical;
					}
				}
			}
		}
	}
	
	private void initializeRoad() {
	//Do�ancan, 07.09.13, 00.28
		for (int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if (tiles[i][j].type.equals("Road")){
					
					//bottom left
					if (!tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road"))
						tiles[i][j].texture = game.textures.roadBottomLeft;
					
					//bottom right
					else if (tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road"))
						tiles[i][j].texture = game.textures.roadBottomRight;
					//cross
					else if (tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road"))
						tiles[i][j].texture = game.textures.roadCross;
					
					//double turn down
					else if(tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road"))
						tiles[i][j].texture = game.textures.roadDoubleTurnDown;
					
					//double turn left
					else if (tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadDoubleTurnLeft;
					
					//double turn right
					else if (!tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadDoubleTurnRight;
					
					//double turn up
					else if (tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadDoubleTurnUp;
					
					//horizontal
					if (tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadHorizontal;
					
					//top left
					else if (!tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadTopLeft;
					
					//top right
					else if (tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadTopRight;
					
					//vertical
					else if (!tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadVertical;
					
					//end down
					else if (!tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadVertical;
					
					//end left
					else if (tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadHorizontal;
					
					//end right
					else if (!tiles[i][j-1].type.equals("Road") && tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && !tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadHorizontal;
					
					//end up
					else if (!tiles[i][j-1].type.equals("Road") && !tiles[i][j+1].type.equals("Road") && !tiles[i-1][j].type.equals("Road") && tiles[i+1][j].type.equals("Road") )
						tiles[i][j].texture = game.textures.roadVertical;
				}	
			}
		}
		
	}
	
	private void initializeMountain (){
		//Do�ancan 06.09.13 - 18.30
		for (int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if(tiles[i][j].type.equals("Mountain")){
					//i'ler dikey, j'ler yatay
					
					//bottom center
					if(tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && !tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1BottomCenter;
					
					//bottom left
					else if(!tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && !tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1BottomLeft;
					
					//bottom right
					else if(tiles[i][j-1].type.equals("Mountain") && !tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && !tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1BottomRight;
					
					//center center
					else if (tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1CenterCenter;
					
					//center left
					else if (!tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1CenterLeft;
					
					//center right
					else if (tiles[i][j-1].type.equals("Mountain") && !tiles[i][j+1].type.equals("Mountain") && tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1CenterRight;
					
					//top center 
					else if (tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && !tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1TopCenter;
					
					//top left 
					else if(!tiles[i][j-1].type.equals("Mountain") && tiles[i][j+1].type.equals("Mountain") && !tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1TopLeft;
					
					//top right
					else if(tiles[i][j-1].type.equals("Mountain") && !tiles[i][j+1].type.equals("Mountain") && !tiles[i-1][j].type.equals("Mountain") && tiles[i+1][j].type.equals("Mountain"))
						tiles[i][j].texture = game.textures.mountain1TopRight;
				}
			}
		}
	}
	
	public void drawMiniMap(){
		
	}
	
}
