### 0.2.0-beta

- **Breaking Changes**
    - Moved adventure extensions
    - Removed deprecated Manager object (also renamed to MinecraftServer)
    - (Minestom) Use registries instead of managers
    - Renamed KommandBuilder's `default` to `defaultExecutor`
- Features
    - Updated row sizes
    - Async/blocking handlers for events and kommands
    - KBar (Sidebar DSL)
- Code quality
    - Added KDoc comments
    - Formatted project with `ktfmt`
- Dependencies
    - Updated Gradle from `8.7` to `8.8`
    - Updated Kotlin to `2.0.0`
    - Updated Minestom, MiniMessage, Kotest, Kt Serialization, Kt Coroutines, Kt Reflect

### 0.1.0

- Updated to Java 21 & Minestom 1.20.5
- Schedulers use `ExecutationType.TICK_START`
- Example server rewritten
- `EventNodeContainerInventory`
    - A ContainerInventory with an EventNode
    - Registers a single global listener that calls the inventory's EventNodes upon event.
    - Usage:
        - Initialization: `EventNodeContainerInventory(InventoryType, Component)`
        - Accessing the EventNode: `eventNodeContainerInventory.eventNode()`
- Items
    - Introduced `customName` and `itemName`
    - Removed `displayName`
    - TagComponents can be used with `invoke` (e.g. `ItemComponent.DAMAGE(5)`)

### Bladehunt Rewrite

- CoroutineUtil
    - `CompletableFuture.await()` can be used instead
    - Old: `entity.suspendTeleport(pos)`
    - New: `entity.teleport(pos).await()`
    - Makes more sense
    - Schedulables now have an `await` function that suspends until the delay is finished
        - Example: `player.await(TaskSchedule.tick(12))`
- DSLs separated
    - DSLs were separated to remove any builder logic from classes
- Tag delegation changed to just use `var tagValue by tag` instead of requiring `TagDelegate(tag)`