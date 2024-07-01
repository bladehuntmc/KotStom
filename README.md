# <img alt="logo" width="45" align="center" src=".github/assets/logo.png" /> KotStom

---

### Adding as a dependency

```kotlin
repositories {
    maven("https://mvn.bladehunt.net/releases")
}

dependencies {
    implementation("net.bladehunt:kotstom:0.3.0-alpha.1")

    // If you need Adventure NBT kotlinx.serialization support
    implementation("net.bladehunt:kotstom-adventure-serialization:0.3.0-alpha.1")
}
```

## Module Overview

### KotStom

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

This module contains an Adventure NBT encoder and decoder.

- Supports polymorphism
- Supports collections
- Usage: `AdventureNbt.encodeToCompound`, `AdventureNbt.decodeFromCompound`
    - Initializing your own instance of `AdventureNbt` can be done using `AdventureNbt`'s constructor

### For more information, visit [the wiki](https://www.bladehunt.net/developers/kotstom) or view [the example](example/src/main/kotlin)
