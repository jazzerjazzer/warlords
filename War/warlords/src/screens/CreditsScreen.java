package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pikseloyun.warlords.Warlords;

public class CreditsScreen implements Screen {

	Warlords game;
	
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private Vector3 tap = new Vector3(0,0,0);
	
	private Texture bg;
	
	public CreditsScreen(Warlords game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			batch.draw(bg,0,0);
		batch.end();
		
		if(Gdx.input.justTouched()){
			
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);
			
			int x = (int) tap.x;
			int y = (int) tap.y;
			
			if(x < 780 && x > 500 && y < 550 && y > 470){
				game.setScreen(new MainMenuScreen(game));
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
		bg = game.textures.creditsScreen;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
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
