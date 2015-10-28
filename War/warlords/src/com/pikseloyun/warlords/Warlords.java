/**
 *	Ana oyun sýnýfý. Uygulama çalýþtýrýldýðýnda, ilk olarak bu sýnýf çalýþtýrýlýyor.
 *	Bu sýnýfta, oyundaki sesleri, müzikleri ve görselleri tutan 3 adet nesne bulunuyor.
 *	Sýnýf, create metodunda, oyundaki yüklemeleri yapmak için loading screen sýnýfýný çaðýrýyor.
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
