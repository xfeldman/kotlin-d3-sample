package svg

import kotlinx.html.*
import react.RBuilder
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RBuilder.circle(classes: String? = null, block: RDOMBuilder<CIRCLE>.() -> Unit): ReactElement = tag(block) { CIRCLE(attributesMapOf("class", classes), it) }

open class CIRCLE(initialAttributes : Map<String, String>, override val consumer : TagConsumer<*>) : HTMLTag("circle", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var cx : String
        get()  = attributeStringString.get(this, "cx")
        set(newValue) {attributeStringString.set(this, "cx", newValue)}
    var cy : String
        get()  = attributeStringString.get(this, "cy")
        set(newValue) {attributeStringString.set(this, "cy", newValue)}
    var r : String
        get()  = attributeStringString.get(this, "r")
        set(newValue) {attributeStringString.set(this, "r", newValue)}
}
