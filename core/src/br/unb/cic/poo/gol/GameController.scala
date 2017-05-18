package br.unb.cic.poo.gol

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.MutableList
import br.unb.cic.poo.gol.regras.Conway
import com.badlogic.gdx.{Game, Gdx}

object GameController extends Game{

  val height = 30
  val width = 30

  //val cells = Array.ofDim[Cell](height, width)
  var gameEngine = new GameEngine
  var regra : EstrategiaDeDerivacao = new Conway // regra padrão
 
  def loadRules(regras: Iterator[String]) : MutableList[EstrategiaDeDerivacao] = {
    val listaRegras = new MutableList[EstrategiaDeDerivacao]
    for(r <- regras)
      listaRegras += Class.forName(r).newInstance().asInstanceOf[EstrategiaDeDerivacao]
    listaRegras
  }

  def defineRegra(nome : String, listaRegras : MutableList[EstrategiaDeDerivacao]) = {
    for (r <- listaRegras) {
      if (r.getNome.equals(nome)) {
        regra = Class.forName(r.getClass.getName).newInstance().asInstanceOf[EstrategiaDeDerivacao]
        //println(regra)
      }
    }
  }
  
  override def create {
    GameView.update
    this.setScreen(GameView.screen)
    Gdx.input.setInputProcessor(new GameInputProcessor)
    //GameView.printOptions
    //GameView.escolheRegra
  }
  
  def halt() {
    //oops, nao muito legal fazer sysout na classe Controller
    println("\n \n")
    Statistics.display
    System.exit(0)
  }

  def makeCellAlive(i: Int, j: Int) {
    try {
			gameEngine.makeCellAlive(i, j)
			GameView.update
//	    GameView.printOptions
		}
		catch {
		  case ex: IllegalArgumentException => {
		    println(ex.getMessage)
		  }
		}
  }
  
  def nextGeneration() : Unit = {
    gameEngine.nextGeneration()
    GameView.update
  }
  
  def nextGenerations() : Unit = {
    val i = 1
    while (i==1) {
      gameEngine.nextGeneration()
      GameView.update
  }
  }
  
  def undo() : Unit = {
    gameEngine.originator.restore(gameEngine.caretaker.getMemento)
    var aliveCells = gameEngine.originator.state
    gameEngine.undo(aliveCells)
    GameView.update
  }
  
}