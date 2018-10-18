@file:Suppress("UNUSED", "UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
@file:JsQualifier("d3")

package d3.selection

external interface CustomEventParameters {
    /**
     * If true, the event is dispatched to ancestors in reverse tree order
     */
    var bubbles: Boolean
    /**
     * If true, event.preventDefault is allowed
     */
    var cancelable: Boolean
    /**
     * Any custom number associated with the event
     */
    var detail: Any
}
