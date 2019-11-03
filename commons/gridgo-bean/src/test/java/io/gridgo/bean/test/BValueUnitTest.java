package io.gridgo.bean.test;

import org.junit.Assert;
import org.junit.Test;

import io.gridgo.bean.BElement;
import io.gridgo.bean.BValue;
import io.gridgo.bean.serialization.text.JsonSerializer;

public class BValueUnitTest {

    @Test
    public void testNull() {
        Assert.assertNull(JsonSerializer.toJsonElement(BValue.of(null)));
    }

    @Test
    public void testInteger() {
        Assert.assertEquals(1, (int) JsonSerializer.toJsonElement(BValue.of(1)));
    }

    @Test
    public void testEncodeDecode() {
        var val = BValue.of(new byte[] { 1, 2, 4, 8, 16, 32, 64 });
        val.encodeHex();
        Assert.assertEquals("0x01020408102040", val.getData());
        val.decodeHex();
        Assert.assertArrayEquals(new byte[] { 1, 2, 4, 8, 16, 32, 64 }, (byte[]) val.getData());
        val.encodeBase64();
        Assert.assertEquals("AQIECBAgQA==", val.getData());
        val.decodeBase64();
        Assert.assertArrayEquals(new byte[] { 1, 2, 4, 8, 16, 32, 64 }, (byte[]) val.getData());

        val = BElement.ofJson(val.toJson()).asValue();
        val.decodeHex();
        Assert.assertArrayEquals(new byte[] { 1, 2, 4, 8, 16, 32, 64 }, (byte[]) val.getData());
    }

    @Test
    public void testSerialization() {
        var val = BValue.of(0);
        var after = BElement.ofBytes(new String(val.toBytes()).getBytes());
        Assert.assertEquals(0, (int) after.getInnerValue());
    }
}
