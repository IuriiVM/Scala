package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      def initState(r: Int): Array[Array[Int]] =
      {
         val result = new Array[Array[Int]](r+1)
         for (row <- 0 to r) {
           result(row) = new Array[Int](r+1)
         }
        result
      }
      def pascalInner(c: Int, r: Int, state: Array[Array[Int]]): Int = {
        for (row <- 0 to r) {
          for (col <- 0 to row)
            print(state(row)(col) + " ")
          println()
        }
        0
       }
      val state = initState(r)
      pascalInner(c, r, state)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = ???
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = ???
  }
