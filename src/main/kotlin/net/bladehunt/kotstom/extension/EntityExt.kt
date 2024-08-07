package net.bladehunt.kotstom.extension

import net.minestom.server.entity.Entity
import net.minestom.server.entity.metadata.EntityMeta

/**
 * Edits an Entity's meta.
 *
 * @param T The type of EntityMeta
 * @author oglassdev
 */
inline fun <reified T : EntityMeta> Entity.editMeta(block: T.() -> T) {
    entityMeta.setNotifyAboutChanges(false)

    try {
        block(entityMeta as T)
    } catch (exception: ClassCastException) {
        throw RuntimeException(
            "Error editing entity " + this.entityId + " " + entityType.name() + " meta", exception)
    } finally {
        entityMeta.setNotifyAboutChanges(true)
    }
}
