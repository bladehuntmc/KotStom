package net.bladehunt.kotstom.extras.dsl

import io.github.togar2.pvp.feature.CombatFeature
import io.github.togar2.pvp.feature.CombatFeatureSet
import io.github.togar2.pvp.feature.FeatureType
import io.github.togar2.pvp.feature.config.DefinedFeature
import io.github.togar2.pvp.feature.config.FeatureConfiguration
import io.github.togar2.pvp.feature.provider.DifficultyProvider
import io.github.togar2.pvp.utils.CombatVersion

@JvmInline
value class KFeatureConfigurationBuilder(val configuration: FeatureConfiguration) {
    var version: CombatVersion
        get() = configuration.get(FeatureType.VERSION)
        set(value) {
            configuration.add(FeatureType.VERSION, value)
        }

    var difficulty: DifficultyProvider
        get() = configuration.get(FeatureType.DIFFICULTY)
        set(value) {
            configuration.add(FeatureType.DIFFICULTY, value)
        }

    fun difficulty(provider: DifficultyProvider) {
        difficulty = provider
    }

    operator fun <T : DefinedFeature<*>> T.unaryPlus() {
        configuration.add(this.featureType(), this as CombatFeature)
    }

    operator fun CombatFeatureSet.unaryPlus() {
        this.forEach(configuration::add)
    }

    operator fun FeatureConfiguration.unaryPlus() {
        this.forEach(configuration::add)
    }
}

operator fun FeatureConfiguration.plus(other: FeatureConfiguration): FeatureConfiguration =
    buildFeatureConfiguration {
        +this@plus
        +other
    }

inline fun buildFeatureConfiguration(
    block: KFeatureConfigurationBuilder.() -> Unit
): FeatureConfiguration =
    KFeatureConfigurationBuilder(FeatureConfiguration()).apply(block).configuration