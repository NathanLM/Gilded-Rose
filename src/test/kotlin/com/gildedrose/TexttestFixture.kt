package com.gildedrose

import org.junit.jupiter.api.Assertions


private fun getNewItemList() = arrayOf(Item("+5 Dexterity Vest", 10, 20), //
    Item("Aged Brie", 2, 0), //
    Item("Elixir of the Mongoose", 5, 7), //
    Item("Sulfuras, Hand of Ragnaros", 0, 80), //
    Item("Sulfuras, Hand of Ragnaros", -1, 80),
    Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
    Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
    Item("Backstage passes to a TAFKAL80ETC concert", 5, 40),
    // this conjured item does not work properly yet
    Item("Conjured Mana Cake", 3, 6))

fun main(args: Array<String>) {
    val items = getNewItemList()
    val itemsLegacy = getNewItemList()

    var days = 2
    if (args.isNotEmpty()) {
        days = Integer.parseInt(args[0]) + 1
    }

    println("------------ Legacy ------------")
    testLegacyVersion(itemsLegacy, days)

    println("------------ Refactored ------------")
    testRefactoredVersion(items, days)

    compareRefactoredToLegacy(itemsLegacy, items)
}

private fun testRefactoredVersion(items: Array<Item>, days: Int){
    val app = GildedRose(items)
    for (i in 0 until days) {
        printListContent(i, items)
        app.updateQuality()
    }
}

private fun testLegacyVersion(items: Array<Item>, days: Int){
    val app = GildedRose(items)
    for (i in 0 until days) {
        printListContent(i, items)
        app.updateQualityLegacy()
    }
}

private fun printListContent(i: Int, items: Array<Item>){
    println("-------- day $i --------")
    println("name, sellIn, quality")
    for (item in items) {
        println(item)
    }
    println()
}

private fun compareRefactoredToLegacy(items: Array<Item>, itemsLegacy: Array<Item>){
    val comparisonArray = items zip itemsLegacy
    for (itemPair in comparisonArray){
        Assertions.assertEquals(itemPair.first.name, itemPair.second.name, "The two compared items are different")
        Assertions.assertEquals(itemPair.first.sellIn, itemPair.second.sellIn, "Inconsistency in the sellIn value for ${itemPair.first.name}")
        Assertions.assertEquals(itemPair.first.quality, itemPair.second.quality, "Inconsistency in the quality value for ${itemPair.first.name}")
    }
}