package br.unb.cic.poo.gol

import scala.collection.mutable.ListBuffer
import scala.util.control.TailCalls.TailRec
import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.MutableList
import br.unb.cic.poo.gol.memento.Caretaker
import br.unb.cic.poo.gol.memento.Originator

class GameEngine {
  
  val height: Int = GameController.height
  val width: Int = GameController.width
  
  var caretaker = new Caretaker
  var originator = new Originator
  var cells: Array[Array[Cell]] = originator.state

//  var aliveCells: Array[Array[Cell]] = Array.ofDim[Cell](height, width) //lista de células vivas da geração atual
  var celulasAdicionadas : Array[Array[Cell]] = _ //dar nome melhor, são as células adicionadas à mão pelo usuário
  
  for(i <- 0 until height) {
    for(j <- 0 until width) {
      cells(i)(j) = new Cell
    }
  }
  
  def nextGeneration() : Unit = {
    originator.setState(cells)
    caretaker.addMemento(originator.save)

    val mustRevive = new ListBuffer[Cell]
    val mustKill = new ListBuffer[Cell]
    
    for(i <- 0 until height) {
      for (j <- 0 until width) {
        if (GameController.regra.shouldRevive(i, j)) {
          mustRevive += cells(i)(j)
        }
        else if ((!GameController.regra.shouldKeepAlive(i, j)) && cells(i)(j).isAlive) {
          mustKill += cells(i)(j)
        }
      }
    }
    
    for(cell <- mustRevive) {
      cell.revive
      Statistics.recordRevive
    }
    
    for(cell <- mustKill) {
      cell.kill
      Statistics.recordKill
    }

    // verifica o estado das células vivas do tabuleiro após a geração atual
    //for (i <- 0 until height) {
    //  for (j <- 0 until width) {
    //    if (cells(i)(j).isAlive)
    //      aliveCells(i)(j) = cells(i)(j)
    //  }
    //}
  }

  def undo(aliveCells : Array[Array[Cell]]) : Unit = {
    for (i <- 0 until height) {
      for (j <- 0 until width) {
        if (aliveCells(i)(j).isAlive)
          cells(i)(j).revive
        else
          cells(i)(j).kill
      }
    }
  }
  
  def validPosition(i: Int, j: Int): Boolean =
    i >= 0 && i < height && j >= 0 && j < width
  
  @throws(classOf[IllegalArgumentException])
  def makeCellAlive(i: Int, j: Int): Unit = {
    if(validPosition(i, j)){
      cells(i)(j).revive
      Statistics.recordRevive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  @throws(classOf[IllegalArgumentException])
  def isCellAlive(i: Int, j: Int): Boolean = {
    if(validPosition(i, j)) {
      cells(i)(j).isAlive
    } else {
      throw new IllegalArgumentException
    }
  }
  
  def numberOfAliveCells() : Unit = {
    var aliveCells = 0
    for(i <- 0 until height) {
      for(j <- 0 until width) {
        if(isCellAlive(i, j)) aliveCells += 1
      }
    }
  }
  
}