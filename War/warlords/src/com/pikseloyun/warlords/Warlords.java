/**
 *	Ana oyun s�n�f�. Uygulama �al��t�r�ld���nda, ilk olarak bu s�n�f �al��t�r�l�yor.
 *	Bu s�n�fta, oyundaki sesleri, m�zikleri ve g�rselleri tutan 3 adet nesne bulunuyor.
 *	S�n�f, create metodunda, oyundaki y�klemeleri yapmak i�in loading screen s�n�f�n� �a��r�yor.
 *
 */
package com.pikseloyun.warlords;
import screens.LoadingScreen;

import assets.Textures;
import com.badlogic.gdx.Game;

public class Warlords extends Game {
	
	public Textures textures;
	
	@Override
	public void create() {	
		textures = new Textures();
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
