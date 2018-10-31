package svg

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import react.RBuilder
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.tag

inline fun RBuilder.rect(classes: String? = null, block: RDOMBuilder<RECT>.() -> Unit): ReactElement = tag(block) { RECT(attributesMapOf("class", classes), it) }

open class RECT(initialAttributes : Map<String, String>, override val consumer : TagConsumer<*>) : HTMLTag("rect", consumer, initialAttributes, null, false, false), HtmlBlockTag {
    var x : String
        get()  = attributeStringString.get(this, "x")
        set(newValue) {attributeStringString.set(this, "x", newValue)}
    var y : String
        get()  = attributeStringString.get(this, "y")
        set(newValue) {attributeStringString.set(this, "y", newValue)}
    var width : String
        get()  = attributeStringString.get(this, "width")
        set(newValue) {attributeStringString.set(this, "width", newValue)}
    var height : String
        get()  = attributeStringString.get(this, "height")
        set(newValue) {attributeStringString.set(this, "height", newValue)}
    var rx : String
        get()  = attributeStringString.get(this, "rx")
        set(newValue) {attributeStringString.set(this, "rx", newValue)}
    var ry : String
        get()  = attributeStringString.get(this, "ry")
        set(newValue) {attributeStringString.set(this, "ry", newValue)}
}
