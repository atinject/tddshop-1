package kuncai

import scala.math.BigDecimal.RoundingMode

/**
  * Created by kcai on 31/05/2016.
  */
class OfferService {
  def calculate(quantity: Int, price: BigDecimal, offerable: Option[Offerable]) = {
    val offer = offerable match {
      case Some(bf: BuyForPriceOfOffer) =>
        new BuyForPriceOfOffer(bf.offerQuantity, bf.forPriceQuantity)

      case _ => new BuyForPriceOfOffer(1, 1)
    }

    (offer.getQuantity(quantity) * price).setScale(2, RoundingMode.HALF_EVEN).toDouble
  }
}

sealed trait Offerable {
  val stackable = false // assume not stackable
  val offerQuantity: Int
  val forPriceQuantity: Int
}

class BuyForPriceOfOffer(val offerQuantity: Int, val forPriceQuantity: Int) extends Offerable {
  def getQuantity(quantity: Int) = (quantity % offerQuantity) + (quantity / offerQuantity * forPriceQuantity)
}