import java.nio.ByteBuffer

fun intToBytesNative(num: Int, size: Int): ByteArray {
    val buffer = ByteBuffer.allocate(size)
    buffer.putInt(num)
    return buffer.array()
}

fun intToBytes(num: Int, size: Int): ByteArray {
    val data = ByteArray(size)

    var shifted = num
    for (i in size - 1 downTo  0) {
        data[i] = (shifted and 0xFF).toByte()
        shifted = shifted shr 8
    }

    return data
}

fun bytesToIntNative(bytes: ByteArray): Int {
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.int
}

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

fun longToBytesNative(num: Long): ByteArray {
    val buffer = ByteBuffer.allocate(java.lang.Long.BYTES)
    buffer.putLong(num)
    return buffer.array()
}

fun longToBytes(num: Long, size: Int): ByteArray {
    val data = ByteArray(size)

    var shifted = num
    for (i in size - 1 downTo  0) {
        data[i] = (shifted and 0xFF).toByte()
        shifted = shifted shr 8
    }

    return data
}

fun bytesToLongNative(bytes: ByteArray): Long {
    val buffer = ByteBuffer.wrap(bytes)
    return buffer.long
}

fun bytesToLong(bytes: ByteArray): Long {
    var result = 0L
    val size = bytes.size
    var shift = 0

    for (i in size - 1 downTo  0) {
        result = result or (bytes[i].toLong() and 0XFF shl shift)
        shift += 8
    }

    return result
}


fun byteArrayToString(byte: ByteArray): String {
    return byte.joinToString(" ") { byteToString(it) }
}

fun byteToString(byte: Byte): String {
    return String.format("%8s", Integer.toBinaryString(byte.toInt() and 0xFF))
        .replace(' ', '0')
}