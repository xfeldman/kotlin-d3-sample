package d3.selection

import org.w3c.dom.Node

typealias ValueFn<T, Datum, Result> = (datum: Datum, index: Number, groups: Array<T>) -> Result

typealias BaseType = Node