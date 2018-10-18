package d3.selection

import org.w3c.dom.Element

external interface Local<T> {
    /**
     * Retrieves a local variable stored on the node (or one of its parents).
     *
     * @param node A node element.
     */
    fun get(node: Element): T?
    /**
     * Deletes the value associated with the given node. Values stored on ancestors are not affected, meaning that child nodes will still see inherited values.
     *
     * This function returns true if there was a value stored directly on the node, and false otherwise.
     *
     * @param node A node element.
     */
    fun remove(node: Element): Boolean
    /**
     * Store a value for this local variable. Calling `.get()` on children of this node will also retrieve the variable's value.
     *
     * @param node A node element.
     * @param value Value to store locally
     */
    fun set(node: Element, value: T): Element
    /**
     * Obtain a string with the internally assigned property name for the local
     * which is used to store the value on a node
     */
    override fun toString(): String
}
