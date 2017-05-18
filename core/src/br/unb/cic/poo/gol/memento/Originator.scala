package br.unb.cic.poo.gol.memento

import br.unb.cic.poo.gol.{Cell, GameController}

class Originator {
  
  var state: Array[Array[Cell]] = Array.ofDim[Cell](GameController.height, GameController.width)
  
  def setState(state : Array[Array[Cell]]): Unit = this.state = state

  def save : Memento =  {
    val m = new Memento()
    for (i <- 0 until GameController.height)
      for (j <- 0 until GameController.width)
        if (state(i)(j).isAlive)
          m.state(i)(j).revive
    m
  }

  def restore(m : Memento): Unit = {
      state = m.getState
      //println("Originator: State after restoring from Memento: " + state);
  }
  
}