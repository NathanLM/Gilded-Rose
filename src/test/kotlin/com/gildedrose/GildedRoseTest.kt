package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    /** Pass a Day : SellIn decrease **/

    private fun assertSellInDeceaseForItem(item:Item){
        val app = GildedRose(arrayOf())
        val originalSellIn = item.sellIn
        app.passADay(item)
        assert(item.sellIn == 0  || item.sellIn == originalSellIn - 1)
    }

    @Test
    fun passADay_should_decrease_SellIn_For_All_Except_Sulfuras() {

        assertSellInDeceaseForItem(Item("Aged Brie", 2, 0))
        assertSellInDeceaseForItem(Item("Elixir of the Mongoose", 5, 7))
        assertSellInDeceaseForItem(Item("Backstage passes to a TAFKAL80ETC concert", 10, 49))
        assertSellInDeceaseForItem(Item("New Item", 20, 33))
    }

    /** Pass a Day : quality increase/decrease **/
    private fun assertQualityDeceaseForItem(item:Item){
        val app = GildedRose(arrayOf())
        val originalQuality = item.quality
        app.passADay(item)
        assert(item.quality == 0  || item.quality == originalQuality - 1)
    }

    private fun assertQualityIncreaseForItem(item:Item){
        val app = GildedRose(arrayOf())
        val originalQuality = item.quality
        app.passADay(item)
        assert(item.quality == 50  || item.quality > originalQuality)
    }

    @Test
    fun passADay_should_decrease_quality() {
        assertQualityDeceaseForItem(Item("Elixir of the Mongoose", 5, 7))
        assertQualityDeceaseForItem(Item("New Item", 50, 33))
    }

    @Test
    fun passADay_should_decrease_quality_of_conjured_items_twice_as_fast() {

        val app = GildedRose(arrayOf())
        val originalQuality = 10
        val item = Item("Conjured Mana Cake", 3, originalQuality)
        app.passADay(item)
        assert(item.quality == originalQuality - 2)
        app.passADay(item)
        assert(item.quality == originalQuality - 4)
    }

    @Test
    fun passADay_should_increase_quality_of_Brie() {
        assertQualityIncreaseForItem(Item("Aged Brie", 2, 0))
    }

    @Test
    fun passADay_should_increase_quality_of_BackstagePass() {
        assertQualityIncreaseForItem(Item("Backstage passes to a TAFKAL80ETC concert", 10, 33))
    }

    /** Pass a Day : Sulfuras **/

    @Test
    fun passADay_should_not_decrease_SellIn_or_Quality_for_Sulfuras(){
        val app = GildedRose(arrayOf())
        val sulfurasSellIn = 0
        val sulfurasQuality = 80
        val sulfuras = Item("Sulfuras, Hand of Ragnaros", sulfurasSellIn, sulfurasQuality)
        app.passADay(sulfuras)
        assertEquals(sulfurasSellIn, sulfuras.sellIn, "The SellIn value of Sulfuras was modified")
        assertEquals(sulfurasQuality, sulfuras.quality, "The Quality value of Sulfuras was modified")
    }

    /** Backstage Pass quality **/

    @Test
    fun passADay_should_increase_BackstagePass_quality_once_if_SellIn_Is_More_Than_11() {
        val app = GildedRose(arrayOf())
        val backstagePassQuality = 40
        val backstagePass = Item("Backstage passes to a TAFKAL80ETC concert", 20, backstagePassQuality)
        app.passADay(backstagePass)
        assertEquals(backstagePassQuality + 1, backstagePass.quality, "The backstage pass quality was not increased by the correct amount")
    }

    @Test
    fun passADay_should_increase_BackstagePass_quality_twice_if_SellIn_Is_Less_Than_11() {
        val app = GildedRose(arrayOf())
        val backstagePassQuality = 40
        val backstagePass = Item("Backstage passes to a TAFKAL80ETC concert", 9, backstagePassQuality)
        app.passADay(backstagePass)
        assertEquals(backstagePassQuality + 2, backstagePass.quality, "The backstage pass quality was not increased by the correct amount")
    }

    @Test
    fun passADay_should_increase_BackstagePass_quality_thrice_if_SellIn_Is_Less_Than_6() {
        val app = GildedRose(arrayOf())
        val backstagePassQuality = 40
        val backstagePass = Item("Backstage passes to a TAFKAL80ETC concert", 5, backstagePassQuality)

        app.passADay(backstagePass)
        assertEquals(backstagePassQuality + 3, backstagePass.quality, "The backstage pass quality was not increased by the correct amount")
    }

    @Test
    fun passADay_should_not_increase_BackstagePass_quality_above_50() {
        val app = GildedRose(arrayOf())
        val backstagePass = Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)

        app.passADay(backstagePass)
        assert(backstagePass.quality <= 50)
    }

}


