package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    public void normalItem() {
        Item[] items = new Item[] { new Item("Normal item", 2, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Normal item", app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(9, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    public void sulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void agedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
        app.items[0].quality = 50;
        app.updateQuality();
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void backstagePass() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0),
                                    new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0),
                                    new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        assertEquals(9, app.items[1].sellIn);
        assertEquals(2, app.items[1].quality);

        assertEquals(4, app.items[2].sellIn);
        assertEquals(3, app.items[2].quality);
    }

    @Test
    public void conjured() {
        Item[] items = new Item[] { new Item("Conjured whatever", 3, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured whatever", app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);

        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

}