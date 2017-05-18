package br.unb.cic.poo.gol.regras

import br.unb.cic.poo.gol.{EstrategiaDeDerivacao, GameController}
import br.unb.cic.poo.gol.GameController.gameEngine

class Conway extends EstrategiaDeDerivacao {
  
  def getNome() = "Conway"
  def getDescricao() = "Regras padrão do Game of Life - implementadas por John Conway"  
  
  /* verifica se uma celula deve ser mantida viva */
  def shouldKeepAlive(i: Int, j: Int): Boolean = {
    if (GameController.gameEngine.cells(i)(j) == null) {
      println("A")
      false
    }
    else
    GameController.gameEngine.cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3)
  }
  
  /* verifica se uma celula deve (re)nascer */
  def shouldRevive(i: Int, j: Int): Boolean = {
    if (GameController.gameEngine.cells(i)(j) != null)
      (!GameController.gameEngine.cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3)
    else {
      println("Ab")
      false
    }
  }
}