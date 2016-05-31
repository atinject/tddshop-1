package kuncai

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by kcai on 31/05/2016.
  */
class CartTest extends FlatSpec with Matchers {

  "STEP 1 - A checkout system" should "have items in basket" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "ORANGE"))
    cartService.basket.size should be(3)
  }

  // assume ZERO item in basket is allowed
  "STEP 1 - A checkout system" can "have ZERO item in basket" in {
    val cartService = new CartService(Nil)
    cartService.basket.size should be(0)
  }

  "STEP 1 - A checkout system" must "have only Apple or Orange, non-existent item is ignored" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "ORANGE", "NON-EXISTENCE"))
    cartService.basket.size should be(3)
  }


}
