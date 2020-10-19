package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                decreaseQuality(items, i)
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            increaseQuality(items, i)
                        }

                        if (items[i].sellIn < 6) {
                            decreaseQuality(items, i)
                        }
                    }
                }
            }

            decreaseSellIn(items, i)

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        decreaseQuality(items, i)
                    } else {
                        items[i].quality -= items[i].quality
                    }
                } else {
                    decreaseQuality(items, i)
                }
            }
        }
    }

    private fun decreaseSellIn(items: Array<Item>, i: Int){
        if (items[i].name != "Sulfuras, Hand of Ragnaros") {
            items[i].sellIn--
        }
    }

    private fun increaseQuality(items: Array<Item>, i: Int){
        if (items[i].quality < 50 && items[i].name != "Sulfuras, Hand of Ragnaros") {
            items[i].quality++
        }
    }

    private fun decreaseQuality(items: Array<Item>, i: Int){
        if (items[i].quality > 0 && items[i].name != "Sulfuras, Hand of Ragnaros") {
            items[i].quality--
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



