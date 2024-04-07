### Bladehunt Rewrite
- CoroutineUtil
  -  `CompletableFuture.await()` can be used instead
    - Old: `entity.suspendTeleport(pos)`
    - New: `entity.teleport(pos).await()`
  - Makes more sense
  - Schedulables now have an `await` function that suspends until the delay is finished
    - Example: `player.await(TaskSchedule.tick(12))`
- DSLs separated
  - DSLs were separated to remove any builder logic from classes
- Tag delegation changed to just use `var tagValue by tag` instead of requiring `TagDelegate(tag)`