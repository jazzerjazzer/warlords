package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pikseloyun.warlords.Warlords;

public class GameSetupScreen implements Screen {

	Warlords game;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private Vector3 tap = new Vector3(0,0,0);
	
	private Texture bg;
	private boolean ai1, ai2;
	
	public GameSetupScreen(Warlords game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			batch.draw(bg,0,0);
			batch.draw(game.textures.portrait,350,350);
			batch.draw(game.textures.portrait,725,350);
		batch.end();
		
		if(Gdx.input.justTouched()){
			
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);
			
			int x = (int) tap.x;
			int y = (int) tap.y;
			
			if(x < 780 && x > 500 && y < 230 && y > 160){
				game.setScreen(new GameScreen(game, ai1, ai2));
			}
			
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {

		batch = new SpriteBatch();
		bg = game.textures.gameSetupScreen;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		ai1 = false;
		ai2 = true;
		
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
