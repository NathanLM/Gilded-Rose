package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val item = items[i]
            if (!itemIsBrie(item) && !itemIsBackstagePass(item)) {
                decreaseQuality(item)
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (itemIsBackstagePass(item)) {
                        if (item.sellIn < 11) {
                            increaseQuality(item)
                        }

                        if (item.sellIn < 6) {
                            increaseQuality(item)
                        }
                    }
                }
            }

            decreaseSellIn(item)

            if (itemIsExpired(item)) {
                if (!itemIsBrie(item)) {
                    if (!itemIsBackstagePass(item)) {
                        decreaseQuality(item)
                    } else {
                        item.quality = item.quality - item.quality
                    }
                } else {
                    increaseQuality(item)
                }
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
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn--
        }
    }

    private fun increaseQuality(item: Item){
        if (item.quality < 50 && !itemIsSulfuras(item)) {
            item.quality++
        }
    }

    private fun decreaseQuality(item: Item){
        if (item.quality > 0 && item.name != "Sulfuras, Hand of Ragnaros") {
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



