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
           for (col <- 0 to row) {
             if (col == 0 || col == row) {
               result(row)(col) = 1
             }
             else {
               result(row)(col) = result(row-1)(col-1) + result(row-1)(col)
             }
           }
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
      state(r)(c)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def balanceInner(chars: List[Char], counter: Int): Boolean = {
        var result = false
        if (chars.isEmpty) {
          result = (counter == 0)
        }
        else {
          if (chars.head == '(') {
            result = balanceInner(chars.tail, counter + 1)
          }
          else {
            if (chars.head == ')') {
              if (counter == 0) {
                result = false
              }
              else {
                result = balanceInner(chars.tail, counter - 1)
              }
            }
            else {
              result = balanceInner(chars.tail, counter)
            }
          }
        }
        result
      }
      balanceInner(chars,0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = ???
  }
