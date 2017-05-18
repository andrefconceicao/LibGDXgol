package br.unb.cic.poo.gol.regras

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class GameButton(
                _name: String,
                _action: (GameButton) => (Unit),
                _color: Color = Color.PINK,
                _colorHighlight: Color = Color.MAGENTA,
                _colorFont: Color = Color.BLACK
                ) {

  var highlight: Boolean = false

  def setHighlight(on: Boolean): Unit = highlight = on

  var pos1: Vector2 = _
  var pos2: Vector2 = _

  def setPos1(x: Int, y: Int): Unit = pos1 = new Vector2(x, y)
  def setPos2(x: Int, y: Int): Unit = pos2 = new Vector2(x, y)

  def width = pos2.x - pos1.x
  def height = pos2.y - pos1.y

  def padW(font: BitmapFont) = width

  def drawButton(shapeRenderer: ShapeRenderer): Unit = {
    if(highlight) shapeRenderer.setColor(_colorHighlight)
    else shapeRenderer.setColor(_color)
    shapeRenderer.rect(pos1.x, pos1.y, width, height)
  }

  def drawLetters(font: BitmapFont, fontBatch: SpriteBatch): Unit ={

  }

}
