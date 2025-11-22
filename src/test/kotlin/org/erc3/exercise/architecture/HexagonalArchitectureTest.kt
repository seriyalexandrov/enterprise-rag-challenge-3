package org.erc3.exercise.architecture

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class HexagonalArchitectureTest {

    private val domainLayer = Layer("Domain", "org.erc3.exercise.domain..")
    private val applicationLayer = Layer("Application", "org.erc3.exercise.application..")
    private val incomingAdapterLayer = Layer("IncomingAdapter", "org.erc3.exercise.adapter.incoming..")
    private val outgoingAdapterLayer = Layer("OutgoingAdapter", "org.erc3.exercise.adapter.outgoing..")

    @Test
    fun `domain layer stays independent`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                domainLayer.dependsOnNothing()
            }
    }

    @Test
    @Disabled("Layer Application doesn't contain any files now")
    fun `application layer depends only on domain`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                applicationLayer.dependsOn(domainLayer)
            }
    }

    @Test
    @Disabled("Layer IncomingAdapter doesn't contain any files")
    fun `incoming adapters depend only on application and domain`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                incomingAdapterLayer.dependsOn(applicationLayer, domainLayer)
            }
    }

    @Test
    fun `outgoing adapters depend only on domain`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                outgoingAdapterLayer.dependsOn(domainLayer)
            }
    }
}
