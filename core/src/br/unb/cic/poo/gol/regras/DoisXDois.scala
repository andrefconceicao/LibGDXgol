package br.unb.cic.poo.gol.regras

import br.unb.cic.poo.gol.EstrategiaDeDerivacao
import br.unb.cic.poo.gol.GameController

class DoisXDois extends EstrategiaDeDerivacao {
  def getNome = "DoisXDois"
  def getDescricao() = "Regras Brians Brian do jogo da vida"
  
   /* verifica se uma celula deve ser mantida viva */
  def shouldKeepAlive(i: Int, j: Int): Boolean = {
    GameController.gameEngine.cells(i)(j).isAlive &&
      (numberOfNeighborhoodAliveCells(i, j) == 1 || numberOfNeighborhoodAliveCells(i, j) == 2 ||
          numberOfNeighborhoodAliveCells(i, j) == 5)
  }
  
  /* verifica se uma celula deve (re)nascer */
  def shouldRevive(i: Int, j: Int): Boolean = {
    (!GameController.gameEngine.cells(i)(j).isAlive) &&
      (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 6)
  }
}