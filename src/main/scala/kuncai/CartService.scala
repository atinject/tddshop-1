package kuncai

import scala.annotation.tailrec

/**
  * Created by kcai on 31/05/2016.
  */
class CartService(items: List[String]) extends ItemRepository {

  def basket = items.foldLeft(List.empty[Item])((savedItems: List[Item], x: String) =>
    getItem(x) match {
      case Some(e) => e :: savedItems
      case None => savedItems
    }
  )

  def checkOut = {
    @tailrec
    def sum(savedItems: List[Item], total: BigDecimal): BigDecimal = {
      savedItems match {
        case Nil => total
        case x :: tail => sum(tail, total + x.price)
      }
    }
    sum(basket, 0.00)
  }
}

case class Item(name: String, price: BigDecimal) {
  override val toString = name
}

object ItemRepository {
  val items = Map(
    "APPLE" -> Item("APPLE", 0.60),
    "ORANGE" -> Item("ORANGE", 0.25)
  )
}

sealed trait ItemRepository {
  import ItemRepository._

  def getItem(name: String) = {
    items.get(name)
  }
}