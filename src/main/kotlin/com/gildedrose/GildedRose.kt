package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val item = items[i]

            passADay(item)

            modifyQualityInCaseOfExpiration(item)
        }
    }

    private fun passADay(item:Item){
        if (itemIsBrie(item) || itemIsBackstagePass(item)) {
            increaseQuality(item)
        } else {
            decreaseQuality(item)
        }

        decreaseSellIn(item)
    }

    private fun modifyQualityInCaseOfExpiration(item:Item){
        if (itemIsExpired(item)) {
            if (itemIsBrie(item)) {
                increaseQuality(item)
            } else {
                decreaseQuality(item)
            }
        }
    }

    /** Conditional expressions **/

    private fun itemIsExpired(item: Item) = item.sellIn < 0

    private fun itemIsBrie(item: Item) = item.name == "Aged Brie"

    private fun itemIsBackstagePass(item: Item) = item.name == "Backstage passes to a TAFKAL80ETC concert"

    private fun itemIsSulfuras(item: Item) = item.name == "Sulfuras, Hand of Ragnaros"

    /** Value modification **/

    private fun decreaseSellIn(item: Item){
        if (itemIsExpired(item) && itemIsBackstagePass(item)) {
            item.quality -= item.quality
        } else if (!itemIsSulfuras(item)) {
            item.sellIn--
        }
    }

    private fun increaseQuality(item: Item){
        if (item.quality < 50 && !itemIsSulfuras(item)) {
            item.quality++
        }
        if (itemIsBackstagePass(item)) {
            when {
                item.sellIn < 6 -> item.quality += 2
                item.sellIn < 11 -> item.quality
            }
        }
    }

    private fun decreaseQuality(item: Item){
        if (item.quality > 0 && !itemIsSulfuras(item)) {
            item.quality--
        }
    }

    // Pre-refactoring code

    fun updateQualityLegacy() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}



