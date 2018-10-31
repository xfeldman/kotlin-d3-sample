import kotlinx.html.HTMLTag
import react.RBuilder
import react.ReactElement
import react.dom.RDOMBuilder
import react.dom.svg
import react.dom.tag

// a custom tag builder, reuses the tag(...) function from kotlin-react and HTMLTag from kotlinx.html
inline fun RBuilder.custom(tagName: String, block: RDOMBuilder<HTMLTag>.() -> Unit): ReactElement = tag(block) {
    HTMLTag(tagName, it, mapOf(), null, true, false) // I dont know yet what the last 3 params mean... to lazy to look it up
}

inline fun RBuilder.customShape() {
    svg {
        attrs["width"] = "800"
        attrs["height"] = "600"
        attrs["viewBox"] = "0 0 800 600"

        custom("circle") {
            attrs["cx"] = 150
            attrs["cy"] = 150
            attrs["r"] = 50.0
            attrs["style"] = object {
                val stroke = "black"
                val fill = "red"
            }
        }
    }
}
