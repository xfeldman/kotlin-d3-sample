package svg

import kotlinx.html.*
import react.*
import react.dom.RDOMBuilder
import react.dom.tag



abstract class RComponentWithSVG<P : RProps, S : RState> : RComponent<P, S>() {
    inline fun RBuilder.svg(classes: String? = null, block: RDOMBuilder<SVG>.() -> Unit): ReactElement = tag(block) { SVG(attributesMapOf("class", classes), it) }
}

open class SVG(initialAttributes: Map<String, String>, override val consumer: TagConsumer<*>) : kotlinx.html.SVG(initialAttributes, consumer) {
    var width: String
        get() = attributeStringString.get(this, "width")
        set(newValue) {
            attributeStringString.set(this, "width", newValue)
        }
    var height: String
        get() = attributeStringString.get(this, "height")
        set(newValue) {
            attributeStringString.set(this, "height", newValue)
        }
    var viewBox: String
        get() = attributeStringString.get(this, "viewBox")
        set(newValue) {
            attributeStringString.set(this, "viewBox", newValue)
        }
}
