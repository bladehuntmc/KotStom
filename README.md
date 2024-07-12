# <img alt="logo" width="45" align="center" src=".github/assets/logo.png" /> KotStom

---

### Adding as a dependency (Gradle)

First, add the Bladehunt maven repo to the repositories

```kotlin
repositories {
    maven("https://mvn.bladehunt.net/releases")
}
```

The latest version can be found [here](https://mvn.bladehunt.net/#/releases/net/bladehunt/kotstom).

## Module Overview

### KotStom

```kotlin
implementation("net.bladehunt:kotstom:<version>")
```

- Minestom Extensions
    - Tag delegates (`by Tag`)
    - Scheduler extensions (`#await`)
    - Adventure extensions (`String#color, String#decorate, String#asMini`)
    - CommandManager extension (Register multiple commands at once)
    - Point extensions
    - Inventory extensions (`Inventory#rowSize, get, set`)
    - EventNode extensions (Register multiple listeners)
- DSLs
    - Kommand (`kommand`) - Easier commands w/ delegates
    - Item (`item`) - Item builder
    - KBar (`kbar`) - Advanced sidebar
    - Particle (`particle`) - Particle packet builder
    - Event (`listen, buildListener`) - Better kotlin support with reified type parameters and better builders
    - MinestomRunnable (`runnable`) - Builds a task that can be scheduled
- MinecraftServer
    - Each registry and manager has a corresponding `inline val`
        - e.g. `CommandManager` means `MinecraftServer.getCommandManager()`
- Utils
    - EventNodeInventory
        - An inventory with an event node.

### adventure-serialization

```kotlin
implementation("net.bladehunt:kotstom-adventure-serialization:<version>")
```

This module contains an Adventure NBT encoder and decoder.

- Supports polymorphism
- Supports collections
- Usage: `AdventureNbt.encodeToCompound`, `AdventureNbt.decodeFromCompound`
    - Initializing your own instance of `AdventureNbt` can be done using `AdventureNbt`'s constructor

### serialization

```kotlin
implementation("net.bladehunt:kotstom-serialization:<version>")
```

This module contains some kotlinx.serialization serializers for Minestom.

- Adventure
    - MiniMessage Serializer
- StaticProtocolObject
    - Material serializer
    - EntityType serializer
    - PotionEffect serializer
    - Namespace serializer
- Point
    - Supports non-content based serialization (Needs a `type` field)
    - Serial names are the simple names
    - BlockVecSerializer
    - PosSerializer
    - VecSerializer
- UUID
    - StringUuidSerializer (Serializes to string)
    - UuidSerializer (Serializes to most significant/least significant bits)
- Module
    - Note: MiniMessageSerializer is not included in these modules
    - MinestomModule
        - Prefers serializers such as UuidSerializer
    - MinestomConfigModule
        - Prefers more human readable serializers

### For more information, visit [the wiki](https://www.bladehunt.net/developers/kotstom) or view [the example](example/src/main/kotlin)
