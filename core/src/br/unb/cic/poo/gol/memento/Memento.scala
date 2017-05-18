package br.unb.cic.poo.gol.memento

import br.unb.cic.poo.gol.{Cell, GameController}

class Memento () {
    var state: Array[Array[Cell]] = Array.ofDim[Cell](GameController.height,GameController.width)

    for (i <- 0 until GameController.height)
        for (j <- 0 until GameController.width)
            state(i)(j) = new Cell

    def getState : Array[Array[Cell]] = state
}