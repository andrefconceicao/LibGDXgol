package br.unb.cic.poo.gol.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.unb.cic.poo.libgdx.GameOfLifeS_old;

public class DesktopLauncher_old {
	public static void main (String[] arg) {
		System.setProperty("user.name","CorrectUserName");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameOfLifeS_old(), config);
	}
}
