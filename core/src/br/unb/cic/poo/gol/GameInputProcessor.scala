package br.unb.cic.poo.gol

import com.badlogic.gdx.{Gdx, InputProcessor}

/**
  * Created by João Anselmo on 17/05/2017.
  */
class GameInputProcessor extends InputProcessor {
  val height: Int = Gdx.graphics.getHeight

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {false}

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    println("Toquei: " + screenX + "," + (height - screenY))
    val x = screenX
    val y = height - screenY
    GameController.makeCellAlive(x/30,y/30)
    true
  }

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = {
    touchDown(screenX, screenY, pointer, 0)
    true
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {false}

  override def scrolled(amount: Int): Boolean = {false}

  override def keyUp(keycode: Int): Boolean = {false}

  override def keyDown(keycode: Int): Boolean = {false}

  override def keyTyped(character: Char): Boolean = {
    if (character == ' ')
      GameController.nextGeneration()
    else if (character == 'z')
      GameController.undo()
    true
  }
}
