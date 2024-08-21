import io.kotest.assertions.fail
import java.lang.ref.WeakReference
import net.minestom.server.MinecraftServer

val SERVER = MinecraftServer.init()

fun waitUntilCleared(ref: WeakReference<*>) {
    val maxTries = 100

    for (i in 0 until maxTries) {
        System.gc()
        if (ref.get() == null) {
            return
        }
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
    fail("Reference was not cleared")
}
