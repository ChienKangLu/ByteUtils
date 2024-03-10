import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ByteUtilTest {
    @Test
    fun testIntToBytesNative() {
        // GIVEN
        val num = 3
        val size = 4 // lower than 4 will through BufferOverflowException

        // WHEN
        val bytes = intToBytesNative(num, size)
        val result = byteArrayToString(bytes)

        // THEN
        assertEquals("00000000 00000000 00000000 00000011", result)
    }

    @Test
    fun testIntToBytes() {
        // GIVEN
        val num = 10
        val size = 4

        // WHEN
        val bytes = intToBytes(num, size)
        val result = byteArrayToString(bytes)

        // THEN
        assertEquals("00000000 00000000 00000000 00001010", result)
    }

    @Test
    fun testBytesToIntNative() {
        // GIVEN
        val num = 1856
        val size = 4
        val bytes = intToBytes(num, size)

        // WHEN
        val result = bytesToIntNative(bytes)

        // THEN
        assertEquals(num, result)
    }

    @Test
    fun testBytesToInt() {
        // GIVEN
        val raw = 1856
        val byte = 4
        val bytes = intToBytes(raw, byte)

        // WHEN
        val result = bytesToInt(bytes) // 0 0 7 64

        // THEN
        assertEquals(raw, result)
    }

    @Test
    fun testLongToBytesNative() {
        // GIVEN
        val num: Long = 1234567890000

        // WHEN
        val bytes = longToBytesNative(num)
        val result = byteArrayToString(bytes)

        // THEN
        assertEquals("00000000 00000000 00000001 00011111 01110001 11111011 00000100 01010000", result)
    }

    @Test
    fun testLongToBytes() {
        // GIVEN
        val num: Long = 1234567890000

        // WHEN
        val bytes = longToBytes(num, 8)
        val result = byteArrayToString(bytes)

        // THEN
        assertEquals("00000000 00000000 00000001 00011111 01110001 11111011 00000100 01010000", result)
    }

    @Test
    fun testBytesToLongNative() {
        // GIVEN
        val num: Long = 1234567890000
        val size = 8
        val bytes = longToBytes(num, size)

        // WHEN
        val result = bytesToLongNative(bytes)

        // THEN
        assertEquals(num, result)
    }

    @Test
    fun testBytesToLong() {
        // GIVEN
        val num: Long = 1234567890000
        val size = 8
        val bytes = longToBytes(num, size)

        // WHEN
        val result = bytesToLong(bytes)

        // THEN
        assertEquals(num, result)
    }
}