/**
 * Bu sýnýfta, oyunda kullanýlacak olan tüm görselleri yükleyip, ana menüye gidiliyor.
 * Görseller yüklenirken, bekleme sýrasýnda, loading screen resmi gösteriliyor.
 */

package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pikseloyun.warlords.Warlords;

public class LoadingScreen implements Screen {

	Warlords game;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private Texture bg;
	private boolean everythingLoaded = false;
	private boolean splash = true, bgLoaded = false, rendered = false;
	
	public LoadingScreen(Warlords warlords) {
		game = warlords;
	}

	@Override
	public void render(float delta) {

		if (!rendered){
		      rendered = true;
		   } else {
			  if(splash && bgLoaded){
				loadTextures();
			  }
				
			  if (everythingLoaded) { 
		        game.setScreen(new MainMenuScreen(game));
		      }
		   }
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(bg,0,0);
		batch.end();
		
	}
	
	public void loadTextures(){
		
		game.textures.loadMenus();
		game.textures.loadGui();
		game.textures.loadTilesets();
		game.textures.loadTowns();
		game.textures.loadChars();
		
		splash = false;
		everythingLoaded = true;
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		batch.setProjectionMatrix(camera.combined);
		
		bg = new Texture(Gdx.files.internal("screens/loadingScreen.jpg"));
		
		bgLoaded = true;
		
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

}
