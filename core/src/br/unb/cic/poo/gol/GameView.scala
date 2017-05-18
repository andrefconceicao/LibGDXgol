package br.unb.cic.poo.gol

import com.badlogic.gdx.graphics.Texture

import scala.io.StdIn.{readInt, readLine}

/**
 * Representa o componente View do GoL
 * 
 * @author Breno Xavier (baseado na implementacao Java de rbonifacio@unb.br
 */
object GameView {

	val screen = new GameScreen

	private final val LINE = "+-----+"
	private final val DEAD_CELL = "|     |"
	private final val ALIVE_CELL = "X"//new Texture("badlogic.jpg")

	private final val INVALID_OPTION = 0
	private final val MAKE_CELL_ALIVE = 1
	private final val NEXT_GENERATION = 2
	private final val HALT = 3
	private final val NEXT_GENERATIONS = 4
	private final val CHOOSE_RULES = 5
  
  /**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualiza��o do jogo.
	 */
	def update {

	}
  
  def printOptions {
	  
	  var option = 0
	  println("\n\n")
	  
	  do{
	    println("Select one of the options: \n \n"); 
			println("[1] Make a cell alive");
			println("[2] Next generation");
			println("[3] Halt");
			println("[4] Next generations");
			println("[5] Choose derivation rules");			
		
			print("\n \n Option: ");
			
			option = parseOption(readLine)
	  }while(option == 0)
	  
	  option match {
      case MAKE_CELL_ALIVE => makeCellAlive
      case NEXT_GENERATION => nextGeneration
      case HALT => halt
      case NEXT_GENERATIONS => nextGenerations
      case CHOOSE_RULES => escolheRegra
    }
	}
  
  def escolheRegra {
    val regras = scala.io.Source.fromFile("regras").getLines()
    val listaRegras = GameController.loadRules(regras)
    
    println("Escolha uma regra: ")
    for (r <- listaRegras) {
      println(r.getNome + " - " + r.getDescricao)
    }
    print("Opção: ")
    var nome_r = scala.io.StdIn.readLine()
    
    try {
        GameController.defineRegra(nome_r, listaRegras)  
    } catch {
      case c : NullPointerException => println("Regra não encontrada.")
    }
  }
  
  private def makeCellAlive {
	  
	  var i = 0
	  var j = 0
	  
	  do {
      print("\n Inform the row number (0 - " + (GameController.gameEngine.height - 1) + "): ")
      i = readInt
      
      print("\n Inform the column number (0 - " + (GameController.gameEngine.width - 1) + "): ")
      j = readInt
      
    } while(!validPosition(i,j))
      
    GameController.makeCellAlive(i, j)
	}

  private def nextGeneration = GameController.nextGeneration
  private def nextGenerations = GameController.nextGenerations
  private def halt = GameController.halt
	
  private def validPosition(i: Int, j: Int): Boolean = {
		println(i)
		println(j)
		i >= 0 && i < GameController.gameEngine.height && j >= 0 && j < GameController.gameEngine.width
	}
  
	def parseOption(option: String): Int = option match {
    case "1" => MAKE_CELL_ALIVE
    case "2" => NEXT_GENERATION
    case "3" => HALT
    case "4" => NEXT_GENERATIONS
    case "5" => CHOOSE_RULES
    case _ => INVALID_OPTION
  }
	
  
  /* Imprime uma linha usada como separador das linhas do tabuleiro */
	private def printLine() {
	  for(j <- (0 until GameController.gameEngine.width)) {
	    print(LINE)
	  }
	  println()
	}
  
  /*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private def printFirstRow {
		println("\n \n")
		
		for(j <- (0 until GameController.gameEngine.width)) {
		  print("   " + j + "   ")
		}
		println()
	}
  
}