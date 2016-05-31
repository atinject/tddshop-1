package kuncai

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by kcai on 31/05/2016.
  */
class CartTest extends FlatSpec with Matchers {

  "STEP 1 - A checkout system" should "have items in basket" in {
  }

  // assume ZERO item in basket is allowed
  "STEP 1 - A checkout system" can "have ZERO item in basket" in {
  }

  "STEP 1 - A checkout system" must "have only Apple or Orange, non-existent item is ignored" in {
  }

  "STEP 1 - A checkout system" should "have Apple priced at 0.60" in {
  }

  "STEP 1 - A checkout system" should "have Orange priced at 0.25" in {
  }

}
