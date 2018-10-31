@file:Suppress("UNUSED", "UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
@file:JsModule("d3")

package d3.selection

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

external interface EnterElement {
    val ownerDocument: Document?
    var namespaceURI: String
    fun appendChild(node: Node): Node
    fun insertBefore(node: Node, refChild: Node): Node
    fun querySelector(selectors: String): Element
    fun querySelectorAll(selectors: String): NodeList
}


