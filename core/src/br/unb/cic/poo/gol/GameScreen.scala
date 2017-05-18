package br.unb.cic.poo.gol

import java.util.Calendar

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2

/**
  * Created by João Anselmo on 17/05/2017.
  */
class GameScreen extends Screen {

  var ButtonPressed = false
  var ButtonPressed2 = false

  val camera = new OrthographicCamera(600,600)

  val batch = new SpriteBatch()
  val shapeRenderer = new ShapeRenderer()

  batch.setProjectionMatrix(camera.combined)

  case class LiveCell(pos: Vector2, cell: Cell)

  val squareSide = 30
  private def drawSquare(cell: Cell, x: Int, y: Int): Unit ={
    shapeRenderer.setColor(Color.GRAY)
    if(cell.isAlive)
      shapeRenderer.rect(x*squareSide,y*squareSide, squareSide, squareSide)
  }

  val cells: Array[Array[Cell]] = GameController.gameEngine.cells
  private def drawSquares(): Unit ={
    for(i <- 0 until GameController.height){
      for(j <- 0 until GameController.width){
        drawSquare(cells(i)(j), i, j)
      }
    }
  }

  var lastTime: Long = Calendar.getInstance().getTimeInMillis
  val tempoFrame = 100
  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(1,1,1,1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    batch.begin()
    shapeRenderer.begin(ShapeType.Filled)
    try{
      drawSquares()
    } catch {
      case c : NullPointerException => println("puemba")
      case _ => println("ahh")
    }
    shapeRenderer.end()
    batch.end()
    //var space= Gdx.input.isKeyPressed(Keys.SPACE)
    //if(space && !ButtonPressed) {
    //  val now = Calendar.getInstance().getTimeInMillis
    //  if (now >= lastTime + tempoFrame) {
    //    GameController.nextGeneration()
    //    lastTime = now
    //  }
    //}
    var enter = Gdx.input.isKeyPressed(Keys.ENTER)
    var continua = false
    if(enter && !ButtonPressed2) {
      if (!continua)
        continua=true
      else
        continua=false
      while (continua){
        val now = Calendar.getInstance().getTimeInMillis
        if (now >= lastTime + tempoFrame) {
          GameController.nextGeneration()
          lastTime = now
        }
      }
    }
  }

  override def dispose(): Unit = {
    //Roda dispose em altos batchs e fontes
    batch.dispose()
    shapeRenderer.dispose()
  }

  override def resume(): Unit = {}
  override def show(): Unit = {}
  override def pause(): Unit = {}
  override def hide(): Unit = {}
  override def resize(width: Int, height: Int): Unit = {}
}
