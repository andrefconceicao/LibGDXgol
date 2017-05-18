package br.unb.cic.poo.gol.regras

import br.unb.cic.poo.gol.EstrategiaDeDerivacao
import br.unb.cic.poo.gol.GameController

class DayAndNight extends EstrategiaDeDerivacao {
  def getNome = "DayAndNight"
  def getDescricao() = "Regras Brians Brian do jogo da vida"
  
   /* verifica se uma celula deve ser mantida viva */
  def shouldKeepAlive(i: Int, j: Int): Boolean = {
    (GameController.gameEngine.cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 4 ||
          numberOfNeighborhoodAliveCells(i, j) == 6 || numberOfNeighborhoodAliveCells(i, j) == 7 ||
          numberOfNeighborhoodAliveCells(i, j) == 8)
  }
  
  /* verifica se uma celula deve (re)nascer */
  def shouldRevive(i: Int, j: Int): Boolean = {
    (!GameController.gameEngine.cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 6 ||
          numberOfNeighborhoodAliveCells(i, j) == 7 || numberOfNeighborhoodAliveCells(i, j) == 8 )
  }
}