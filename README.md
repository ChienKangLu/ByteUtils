# Bytes conversion

## Convert integer to bytes

### Built-in solution
To convert a integer into 4 bytes, we can utilize `ByteBuffer` as following:

```kotlin
    val size = 4
    val buffer = ByteBuffer.allocate(size)
    buffer.putInt(num)
    return buffer.array()
```

Notice: if allocating the `size` lower than `4`, it will through `BufferOverflowException`.

### Manual solution

To convert integer manually:

```kotlin
    val data = ByteArray(size)
    data[0] = (num ushr 24 and 0xFF).toByte()
    data[1] = (num ushr 16 and 0xFF).toByte()
    data[2] = (num ushr 8 and 0xFF).toByte()
    data[3] = (num ushr 0 and 0xFF).toByte()
```

It's useful when we need to convert integer into arbitrary size of bytes (< 4).
It can be generalized for different bytes:

```kotlin
fun intToBytes(num: Int, size: Int): ByteArray {
    val data = ByteArray(size)

    var shifted = num
    for (i in size - 1 downTo  0) {
        data[i] = (shifted and 0xFF).toByte()
        shifted = shifted shr 8
    }

    return data
}
```

Reference: https://stackoverflow.com/questions/1735840/how-do-i-split-an-integer-into-2-byte-binary

## Convert bytes to integer

### Built-in solution

```kotlin
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.int
```

Reference: https://mkyong.com/java/java-convert-byte-to-int-and-vice-versa/

### Manual solution

To convert bytes array back to integer, we can shift all bytes left and concatenate them by `or`:

```kotlin
    var result = 0

    result = result or (bytes[3].toInt() and 0XFF shl 0)
    result = result or (bytes[2].toInt() and 0XFF shl 8)
    result = result or (bytes[1].toInt() and 0XFF shl 16)
    result = result or (bytes[0].toInt() and 0XFF shl 24)
    
    return result
```

It can be generalized for different bytes:

```kotlin
fun bytesToInt(bytes: ByteArray): Int {
    var result = 0
    val size = bytes.size
    var shift = 0

    for (i in size - 1 downTo  0) {
        result = result or (bytes[i].toInt() and 0XFF shl shift)
        shift += 8
    }

    return result
}
```

## Convert long to bytes

### Built-in solution

```kotlin
    val buffer = ByteBuffer.allocate(java.lang.Long.BYTES)
    buffer.putLong(num)
    return buffer.array()
```

Reference: https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java

### Manual solution

Same as converting integer to bytes but the size of bytes should be `8` to avoid overflow.

## Convert bytes to long

### Built-in solution

```kotlin
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.long
```

### Manual solution

Same as converting bytes to integer but each byte should convert to `Long` before shifting and concatenating together.

## What does AND 0xFF do?
`0xFF` is a number represented in the hexadecimal numeral system (base 16). It's composed of two `F` numbers in hex. As we know, `F` in hex is equivalent to `1111` in the binary numeral system. So, `0xFF` in binary is `11111111`.

If `byte` is `0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1` which is more than 8 bits, then `& 0xFF` will essentially give you the last 8 bits of the value.

```
    0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1
 &  0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1
    -------------------------------
    0 0 0 0 0 0 0 0 0 1 0 1 0 1 0 1
```

Reference: https://stackoverflow.com/questions/14713102/what-does-and-0xff-do

## Represent bytes array as String of bits

To represent single `Byte` as `String` of bits:
```kotlin
fun byteToString(byte: Byte): String {
    return String.format("%8s", Integer.toBinaryString(byte.toInt() and 0xFF))
        .replace(' ', '0')
}
```

For `ByteArray`, simply apply above function through all bytes in array:

```kotlin
fun byteArrayToString(byte: ByteArray): String {
    return byte.joinToString(" ") { byteToString(it) }
}
```