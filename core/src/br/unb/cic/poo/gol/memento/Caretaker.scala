package br.unb.cic.poo.gol.memento

import scala.collection.mutable.MutableList

class Caretaker {
  var mementos = new MutableList[Memento]
  var num_mementos = 0

//  def limites() : Unit = {
//    if (num_mementos < 0)
//      num_mementos = 0
//    else if (num_mementos > 10)
//      num_mementos = 10
//  }

  def addMemento(m : Memento): Unit = {
    //limites()
    mementos += m
    num_mementos += 1
  }
  def getMemento : Memento = {
    //limites()
    num_mementos -= 1
    if (num_mementos < 0)
      num_mementos = 0
    else if (num_mementos > 10) {
      mementos = mementos.drop(1)
      num_mementos = mementos.length-1
    }
    mementos(num_mementos)
  }
}