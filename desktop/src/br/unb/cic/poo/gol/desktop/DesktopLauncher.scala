package br.unb.cic.poo.gol.desktop

import br.unb.cic.poo.gol.GameController
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object DesktopLauncher {

  val height = 10
  val width = 10

  def main(arg: Array[String]): Unit = {
    System.setProperty("user.name", "CorrectUserName")
    val config = new LwjglApplicationConfiguration
    config.height = 900
    config.width = 900
    config.resizable = false
    new LwjglApplication(GameController, config)
  }
}