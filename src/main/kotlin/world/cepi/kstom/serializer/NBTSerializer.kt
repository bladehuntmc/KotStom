package world.cepi.kstom.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.parser.SNBTParser
import java.io.StringReader

object NBTSerializer : KSerializer<NBT> {
    override fun deserialize(decoder: Decoder): NBT {
        return SNBTParser(StringReader(decoder.decodeString())).parse()
    }

    override val descriptor: SerialDescriptor
        get() = String.serializer().descriptor

    override fun serialize(encoder: Encoder, value: NBT) {
        encoder.encodeString(value.toSNBT()
            .replace("1B", "true")
            .replace("0B", "false")
            .replace("\\\\", "\\")
        )
    }

    fun NBT.serializer() = this@NBTSerializer
}