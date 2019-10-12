package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    // First, going to write 4-5 more tests according the guidelines to see if it works
    // It actually does work...
    public void updateQuality() {
        // Changing the for loop for readability
        for (Item item: items) {
            // A lot of duplicate if statements - could probably be refactored to just a couple of methods
            // Never mind, going to get rid of the logic and replace it with something much more readable


            // Going to create a new class for item types, since there are 4 edge cases and each case will have it's own item type
            // Creating protected methods so that each type could override them with ease
            // Still works!
            // Correcting a couple
            ItemType type = getItemType(item);
            type.updateItem(item);
        }
    }

    private class ItemType {
        protected void increaseQuality(Item item) {
            // Cannot be over 50
            if(item.quality < 50) {
                item.quality++;
            }
        }

        protected void decreaseQuality(Item item) {
            // Cannot be lower than 0
            if(item.quality > 0) {
                item.quality--;
            }
        }

        protected void decreaseSellIn(Item item) {
            item.sellIn--;
        }

        // Once the sellIn value is below 0 the item quality should decrease twice as quick
        protected void updateItem(Item item) {
            decreaseQuality(item);
            decreaseSellIn(item);

            if(item.sellIn < 0) {
                decreaseQuality(item);
            }
        }
    }

    private class Sulfuras extends ItemType {
        protected void increaseQuality(Item item) {}
        protected void decreaseQuality(Item item) {}
        protected void decreaseSellIn(Item item) {}
    }

    private class AgedBrie extends ItemType {
        protected void updateItem(Item item) {
            decreaseSellIn(item);
            increaseQuality(item);
        }
    }

    private class BackstagePass extends ItemType {
        protected void updateItem(Item item) {
            decreaseSellIn(item);
            increaseQuality(item);

            if(item.sellIn <= 10) {
                increaseQuality(item);
            }

            if(item.sellIn <= 5) {
                increaseQuality(item);
            }

            if(item.sellIn < 0) {
                item.quality = 0;
            }
        }
    }

    private class Conjured extends ItemType {
        protected void updateItem(Item item) {
            decreaseSellIn(item);
            decreaseQuality(item);
            decreaseQuality(item);

            if(item.sellIn <= 0) {
                decreaseQuality(item);
                decreaseQuality(item);
            }
        }
    }

    // Move item name strings to constants
    private ItemType getItemType(Item item) {
        if (item.name.equals(SULFURAS)) {
            return new Sulfuras();
        }

        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrie();
        }

        if (item.name.equals(BACKSTAGE_PASS)) {
            return new BackstagePass();
        }

        if (item.name.startsWith(CONJURED)) {
            return new Conjured();
        }

        return new ItemType();
    }
}