package kuncai

/**
  * Created by kcai on 31/05/2016.
  */
class CartService(items: List[String]) {

  def basket: List[Item] = {
    items.map(n => Item(n, 0.00))
  }

  def checkout = {

  }

}


case class Item(name: String, price: BigDecimal) {
  override val toString = name
}