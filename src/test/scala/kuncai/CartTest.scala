package kuncai

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by kcai on 31/05/2016.
  */
class CartTest extends FlatSpec with Matchers {

  "STEP 1 - A checkout system" should "have items in basket" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "ORANGE"))
    cartService.basket.values.flatten.size should be(3)
  }

  // assume ZERO item in basket is allowed
  "STEP 1 - A checkout system" can "have ZERO item in basket" in {
    val cartService = new CartService(Nil)
    cartService.basket.values.flatten.size should be(0)
  }

  "STEP 1 - A checkout system" must "have only Apple or Orange, non-existent item is ignored" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "ORANGE", "NON-EXISTENCE"))
    cartService.basket.values.flatten.size should be(3)
  }

  "STEP 1 - A checkout system" should "have Apple priced at 0.60" in {
    val cartService = new CartService(List("APPLE"))
    cartService.basket.find(_._1.name == "APPLE") match {
      case Some(x) => x._1.price should be(0.60)
      case None => fail("No correct item found")
    }
  }

  "STEP 1 - A checkout system" should "have Orange priced at 0.25" in {
    val cartService = new CartService(List("ORANGE"))
    cartService.basket.find(_._1.name == "ORANGE") match {
      case Some(x) => x._1.price should be(0.25)
      case None => fail("No correct item found")
    }
  }

  "A checkout system" should "calculate the basket total" in {
    val cartService = new CartService(List("APPLE", "APPLE", "APPLE","ORANGE"))
    cartService.checkOut should be(1.45)
  }

  "A checkout system" must "calculate the total price of saved items in basket Apple only" in {
    val cartService = new CartService(List("APPLE"))
    cartService.checkOut should be(0.60)
  }

  "A checkout system" must "calculate the total price of saved items in basket Orange only" in {
    val cartService = new CartService(List("ORANGE"))
    cartService.checkOut should be(0.25)
  }

  "A checkout system" must "calculate the total price of saved items in basket with mixed selections" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "ORANGE"))
    cartService.checkOut should be(1.10)
  }

  "A checkout system" should "calculate BuyOneGetOneFree offer for 2 Apples" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "APPLE"))
    cartService.checkOut should be(0.85)
  }

  "A checkout system" should "calculate BuyOneGetOneFree offer for 3 Apples" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "APPLE", "APPLE"))
    cartService.checkOut should be(1.45)
  }

  "A checkout system" should "calculate BuyOneGetOneFree offer for 4 Apples" in {
    val cartService = new CartService(List("ORANGE", "APPLE", "APPLE", "APPLE", "APPLE"))
    cartService.checkOut should be(1.45)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 1 Oranges" in {
    val cartService = new CartService(List("ORANGE"))
    cartService.checkOut should be(0.25)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 2 Oranges" in {
    val cartService = new CartService(List("ORANGE", "ORANGE"))
    cartService.checkOut should be(0.50)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 3 Oranges" in {
    val cartService = new CartService(List("ORANGE", "ORANGE", "ORANGE"))
    cartService.checkOut should be(0.50)
  }

  "A checkout system" should "calculate BuyThreeForTwo offer for 4 Oranges" in {
    val cartService = new CartService(List("ORANGE", "ORANGE", "ORANGE", "ORANGE"))
    cartService.checkOut should be(0.75)
  }

  "A checkout system" should "calculate BuyThreeForTwo and BuyOneGetOneFree offers for mixed selections" in {
    val cartService = new CartService(List("ORANGE", "ORANGE", "ORANGE", "ORANGE", "APPLE", "APPLE", "APPLE"))
    cartService.checkOut should be(1.95)
  }

}
