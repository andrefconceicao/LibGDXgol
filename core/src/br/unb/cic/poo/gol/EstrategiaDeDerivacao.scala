package br.unb.cic.poo.gol

import java.util.ArrayList

trait EstrategiaDeDerivacao {
  
  def getNome : String
  def getDescricao: String
  
  def shouldKeepAlive(i: Int, j: Int): Boolean
  def shouldRevive(i: Int, j: Int): Boolean
  
  def numberOfNeighborhoodAliveCells(i: Int, j: Int): Int = {
    var alive = 0
    for(a <- i - 1 to i + 1) {
      for(b <- j - 1 to j + 1) {
        if (validPosition(a, b)  && (!(a==i && b == j)) && GameController.gameEngine.cells(a)(b).isAlive)
					alive += 1
        
        else if (!validPosition(a, b) && !(a==i && b == j)) {
          if ( ((b == -1) || (b == GameController.width)) && aValidPosition(a) ) {
            if (b == -1 && GameController.gameEngine.cells(a)(GameController.width-1).isAlive)
              alive += 1
            else if (b == GameController.width && GameController.gameEngine.cells(a)(0).isAlive)
              alive += 1
          }
          
          else if ( ( (a == -1) || (a == GameController.height) ) && bValidPosition(b) ) {
            if (a == -1 && GameController.gameEngine.cells(GameController.height-1)(b).isAlive)
              alive += 1
            else if (a == GameController.height && GameController.gameEngine.cells(0)(b).isAlive)
              alive += 1
          }
          
          else if ( (a == -1 || a == GameController.height) && (b == -1 || b == GameController.width ) ) {
            if (a == -1 && b == -1 && GameController.gameEngine.cells(GameController.height-1)(GameController.width-1).isAlive )
              alive += 1
            else if (a == -1 && b == GameController.width && GameController.gameEngine.cells(GameController.height-1)(0).isAlive )
              alive += 1
            else if (a == GameController.height && b == -1 && GameController.gameEngine.cells(0)(GameController.width-1).isAlive )
              alive += 1
            else if (a == GameController.height && b == GameController.width && GameController.gameEngine.cells(0)(0).isAlive )
              alive += 1
          }
        }
      }
    }
    alive
  }
  
  def aValidPosition(i: Int): Boolean =
    i >= 0 && i < GameController.height
    
  def bValidPosition(j: Int): Boolean =
    j >= 0 && j < GameController.width
  
  def validPosition(i: Int, j: Int): Boolean =
    i >= 0 && i < GameController.height && j >= 0 && j < GameController.width;
  
}