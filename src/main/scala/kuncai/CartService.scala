package kuncai

import scala.annotation.tailrec

/**
  * Created by kcai on 31/05/2016.
  */
class CartService(items: List[String]) extends ItemRepository {

  lazy private val offerService = new OfferService

  def basket = items.foldLeft(List.empty[Item])((savedItems: List[Item], x: String) =>
    getItem(x) match {
      case Some(e) => e :: savedItems
      case None => savedItems
    }
  ).groupBy(w => w)

  def checkOut = {
    @tailrec
    def sum(savedItems: List[Item], total: BigDecimal): BigDecimal = {
      savedItems match {
        case Nil => total
        case x :: tail =>
          val col = basket.getOrElse(x, Nil)
          sum(tail, total + offerService.calculate(col.size, x.price, x.offer))
      }
    }
    sum(basket.keys.toList, 0.00)
  }
}

case class Item(name: String, price: BigDecimal, offer: Option[Offerable]) {
  override val toString = name
}

object ItemRepository {
  val items = Map(
    "APPLE" -> Item("APPLE", 0.60, Some(new BuyForPriceOfOffer(2, 1))),
    "ORANGE" -> Item("ORANGE", 0.25, Some(new BuyForPriceOfOffer(3, 2)))
  )
}

sealed trait ItemRepository {
  import ItemRepository._

  def getItem(name: String) = {
    items.get(name)
  }
}