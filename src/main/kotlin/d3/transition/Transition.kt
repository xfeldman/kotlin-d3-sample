@file:Suppress("UNUSED", "UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
@file:JsQualifier("d3")

package d3.transition

import d3.selection.BaseType
import d3.selection.Selection
import d3.selection.ValueFn
import org.w3c.dom.HTMLElement

/**
 * Return the active transition on the specified node with the specified name, if any.
 * If no name is specified, null is used. Returns null if there is no such active transition on the specified node.
 * This method is useful for creating chained transitions.
 *
 * The first generic "GElement" refers to the type of element on which the returned active transition was defined. The second generic "Datum" refers to the type of the
 * datum, of a selected element on which the transition is defined. The third generic refers to the type of the parent elements in the returned Transition.
 * The fourth generic refers to the type of the datum defined on the parent elements in the returned Transition.
 *
 * @param node Element for which the active transition should be returned.
 * @param name Name of the transition.
 */
external fun <GElement:BaseType, Datum, PElement:BaseType, PDatum> active(node: GElement, name: String = definedExternally): Transition<GElement, Datum, PElement, PDatum>?

/**
 * Interrupts the active transition of the specified name on the specified node, and cancels any pending transitions with the specified name, if any.
 * If a name is not specified, null is used.
 *
 * @param node Element for which the transition should be interrupted.
 * @param name Name of the transition to be interrupted. If a name is not specified, null is used.
 */
external fun interrupt(node: BaseType, name: String)

/**
 * A D3 Transition.
 *
 * The first generic "GElement" refers to the type of the selected element(s) in the Transition.
 * The second generic "Datum" refers to the type of the datum of a selected element(s) in the Transition.
 * The third generic "PElement" refers to the type of the parent element(s) in the D3 selection in the Transition.
 * The fourth generic "PDatum" refers to the type of the datum of the parent element(s) in the Transition.
 */
external interface Transition<GElement:BaseType, Datum, PElement:BaseType, PDatum> {
    // Sub-selection -------------------------

    /**
     * For each selected element, select the first descendant element that matches the specified selector string, if any,
     * and returns a transition on the resulting selection. The new transition has the same id, name and timing as this transition;
     * however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * The generic represents the type of the descendant element to be selected.
     *
     * @param selector CSS selector string
     */
    fun <DescElement:BaseType> select(selector: String): Transition<DescElement, Datum, PElement, PDatum>
    /**
     * For each selected element, select the descendant element returned by the selector function, if any,
     * and returns a transition on the resulting selection. The new transition has the same id, name and timing as this transition;
     * however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * The generic represents the type of the descendant element to be selected.
     *
     * @param selector A selector function, which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]).
     * It must return an element, or null if there is no matching element.
     */
    fun <DescElement:BaseType> select(selector: ValueFn<GElement, Datum, DescElement>): Transition<DescElement, Datum, PElement, PDatum>

    /**
     * For each selected element, select all descendant elements that match the specified selector string, if any,
     * and returns a transition on the resulting selection. The new transition has the same id, name and timing as this transition;
     * however, if a transition with the same id already exists on a selected element, the existing transition is returned for that element.
     *
     * The first generic "DescElement" refers to the type of descendant element to be selected. The second generic "OldDatum" refers to the type of the
     * datum, of a selected element. This is useful when re-selecting elements with a previously set, know datum type.
     *
     * @param selector CSS selector string
     */
    fun <DescElement:BaseType, OldDatum> selectAll(selector: String): Transition<DescElement, OldDatum, GElement, Datum>
    /**
     * For each selected element, select all descendant elements returned by the selector function, if any,
     * and returns a transition on the resulting selection. The new transition has the same id, name and timing as this transition;
     * however, if a transition with the same id already exists on a selected element, the existing transition is returned for that element.
     *
     * The first generic "DescElement" refers to the type of descendant element to be selected. The second generic "OldDatum" refers to the type of the
     * datum, of a selected element. This is useful when re-selecting elements with a previously set, know datum type.
     *
     * @param selector A selector function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). It must return an array of elements
     * (or a pseudo-array, such as a BaseTypeList), or the empty array if there are no matching elements.
     */
    fun <DescElement:BaseType, OldDatum> selectAll(selector: ValueFn<GElement, Datum, Array<DescElement>>): Transition<DescElement, OldDatum, GElement, Datum>

    /**
     * Return the selection corresponding to this transition.
     */
    fun selection(): Selection<GElement, Datum, PElement, PDatum>

    /**
     * Returns a new transition on the same selected elements as this transition, scheduled to start when this transition ends.
     * The new transition inherits a reference time equal to this transition’s time plus its delay and duration.
     * The new transition also inherits this transition’s name, duration, and easing.
     * This method can be used to schedule a sequence of chained transitions.
     *
     * A delay configured for the new transition will be relative to the previous transition.
     */
    fun transition(): Transition<GElement, Datum, PElement, PDatum>

    // Modifying -------------------------------

    /**
     * For each selected element, assigns the attribute tween for the attribute with the specified name to the specified target value.
     * The starting value of the tween is the attribute’s value when the transition starts.
     * The target value is the specified constant value for all elements.
     *
     * An interpolator is chosen based on the type of the target value, using the following algorithm:
     * 1.) If value is a number, use interpolateNumber.
     * 2.) If value is a color or a string coercible to a color, use interpolateRgb.
     * 3.) Use interpolateString.
     *
     * To apply a different interpolator, use transition.attrTween.
     *
     * @param name Name of the attribute.
     * @param value Target value for the attribute.
     */
    fun attr(name: String, value: String?): Transition<GElement, Datum, PElement, PDatum>
    fun attr(name: String, value: Number?): Transition<GElement, Datum, PElement, PDatum>
    fun attr(name: String, value: Boolean?): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, assigns the attribute tween for the attribute with the specified name to the specified target value.
     * The starting value of the tween is the attribute’s value when the transition starts.
     * The target value is return value of the value function evaluated for the selected element.
     *
     * An interpolator is chosen based on the type of the target value, using the following algorithm:
     * 1.) If value is a number, use interpolateNumber.
     * 2.) If value is a color or a string coercible to a color, use interpolateRgb.
     * 3.) Use interpolateString.
     *
     * To apply a different interpolator, use transition.attrTween.
     *
     * @param name Name of the attribute.
     * @param value A value function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]).
     * A null value will clear the attribute at the start of the transition.
     */
    fun attr(name: String, value: ValueFn<GElement, Datum, Any?>): Transition<GElement, Datum, PElement, PDatum>
//    KT-11265
//    fun attr(name: String, value: ValueFn<GElement, Datum, Boolean>): Transition<GElement, Datum, PElement, PDatum>
//    fun attr(name: String, value: ValueFn<GElement, Datum, Number>): Transition<GElement, Datum, PElement, PDatum>
//    fun attr(name: String, value: ValueFn<GElement, Datum, Unit>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Return the current interpolator factory for attribute with the specified name, or undefined if no such tween exists.
     *
     * @param name Name of attribute.
     */
    fun attrTween(name: String): ValueFn<GElement, Datum, (t: Number) -> String>?
    /**
     * Assign the attribute tween for the attribute with the specified name to the specified interpolator factory.
     * An interpolator factory is a function that returns an interpolator; when the transition starts, the factory is evaluated for each selected element.
     * The returned interpolator will then be invoked for each frame of the transition, in order,
     * being passed the eased time t, typically in the range [0, 1]. Lastly, the return value of the interpolator will be used to set the attribute value.
     * The interpolator must return a string.
     *
     * @param name Name of attribute.
     * @param factory An interpolator factory which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). The interpolator factory returns a string interpolator,
     * which takes as its argument eased time t, typically in the range [0, 1] and returns the interpolated string.
     */
    fun attrTween(name: String, factory: ValueFn<GElement, Datum, (t: Number) -> String>?): Transition<GElement, Datum, PElement, PDatum>

    /**
     * For each selected element, assigns the style tween for the style with the specified name to the specified target value with the
     * specified priority.
     * The starting value of the tween is the style’s inline value if present, and otherwise its computed value.
     * The target value is the specified constant value for all elements.
     *
     * An interpolator is chosen based on the type of the target value, using the following algorithm:
     * 1.) If value is a number, use interpolateNumber.
     * 2.) If value is a color or a string coercible to a color, use interpolateRgb.
     * 3.) Use interpolateString.
     *
     * To apply a different interpolator, use transition.attrTween.
     *
     * @param name Name of the style.
     * @param value Target value for the style.
     * @param priority An optional priority flag, either null or the string important (without the exclamation point)
     */
    fun style(name: String, value: String?, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
    fun style(name: String, value: Boolean?, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
    fun style(name: String, value: Number?, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, assigns the style tween for the style with the specified name to the specified target value with the
     * specified priority.
     * The starting value of the tween is the style's inline value if present, and otherwise its computed value.
     * The target value is return value of the value function evaluated for the selected element.
     *
     * An interpolator is chosen based on the type of the target value, using the following algorithm:
     * 1.) If value is a number, use interpolateNumber.
     * 2.) If value is a color or a string coercible to a color, use interpolateRgb.
     * 3.) Use interpolateString.
     *
     * To apply a different interpolator, use transition.attrTween.
     *
     * @param name Name of the style.
     * @param value A value function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]).
     * A null value will clear the style at the start of the transition.
     * @param priority An optional priority flag, either null or the string important (without the exclamation point)
     */
    fun style(name: String, value: ValueFn<GElement, Datum, Any?>, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
//    KT-11265
//    fun style(name: String, value: ValueFn<GElement, Datum, Boolean>, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
//    fun style(name: String, value: ValueFn<GElement, Datum, Number>, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>
//    fun style(name: String, value: ValueFn<GElement, Datum, Unit>, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Return the current interpolator factory for style with the specified name, or undefined if no such tween exists.
     *
     * @param name Name of style.
     */
    fun styleTween(name: String): ValueFn<GElement, Datum, (t: Number) -> String>?
    /**
     * Assign the style tween for the style with the specified name to the specified interpolator factory.
     * An interpolator factory is a function that returns an interpolator; when the transition starts, the factory is evaluated for each selected element.
     * The returned interpolator will then be invoked for each frame of the transition, in order,
     * being passed the eased time t, typically in the range [0, 1]. Lastly, the return value of the interpolator will be used to set the style value.
     * The interpolator must return a string.
     *
     * @param name Name of style.
     * @param factory An interpolator factory which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). The interpolator factory returns a string interpolator,
     * which takes as its argument eased time t, typically in the range [0, 1] and returns the interpolated string.
     * @param priority An optional priority flag, either null or the string important (without the exclamation point)
     */
    fun styleTween(name: String, factory: ValueFn<GElement, Datum, (t: Number) -> String>?, priority: String? = definedExternally): Transition<GElement, Datum, PElement, PDatum>

    /**
     * For each selected element, sets the text content to the specified target value when the transition starts.
     * To interpolate text rather than to set it on start, use transition.tween (for example) or
     * append a replacement element and cross-fade opacity (for example). Text is not interpolated by default because it is usually undesirable.
     *
     * @param value Value used for text content
     */
    fun text(value: String?): Transition<GElement, Datum, PElement, PDatum>
    fun text(value: Number?): Transition<GElement, Datum, PElement, PDatum>
    fun text(value: Boolean?): Transition<GElement, Datum, PElement, PDatum>

    /**
     * For each selected element, sets the text content returned by the value function for each selected element when the transition starts.
     *
     * To interpolate text rather than to set it on start, use transition.tween (for example) or
     * append a replacement element and cross-fade opacity (for example). Text is not interpolated by default because it is usually undesirable.
     *
     * @param value A value function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]).
     * A null value will clear the text content at the start of the transition.
     */
    fun text(value: ValueFn<GElement, Datum, Any?>): Transition<GElement, Datum, PElement, PDatum>
//    KT-11265
//    fun text(value: ValueFn<GElement, Datum, Number>): Transition<GElement, Datum, PElement, PDatum>
//    fun text(value: ValueFn<GElement, Datum, Boolean>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Returns the tween with the specified name, or undefined, if no tween was previously assigned to
     * that name.
     *
     * @param name Name of tween.
     */
    fun tween(name: String): ValueFn<GElement, Datum, (t: Number) -> Unit>?
    /**
     * For each selected element, assigns the tween with the specified name with the specified value function.
     * The value must be specified as a function that returns a function.
     * When the transition starts, the value function is evaluated for each selected element.
     * The returned function is then invoked for each frame of the transition, in order,
     * being passed the eased time t, typically in the range [0, 1].
     *
     * @param name Name of tween.
     * @param tweenFn A tween function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). The tween function returns a function
     * which takes as its argument eased time t, typically in the range [0, 1] and performs the tweening activities for each transition frame.
     */
    fun tween(name: String, tweenFn: ValueFn<GElement, Datum, (t: Number) -> Unit>?): Transition<GElement, Datum, PElement, PDatum>;

    /**
     * For each selected element, removes the element when the transition ends, as long as the element has no other active or pending transitions.
     * If the element has other active or pending transitions, does nothing.
     */
    fun remove(): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Returns a new transition merging this transition with the specified other transition,
     * which must have the same id as this transition. The returned transition has the same number of groups,
     * the same parents, the same name and the same id as this transition.
     * Any missing (null) elements in this transition are filled with the corresponding element, if present (not null), from the other transition.
     *
     * @param other The transition to be merged.
     */
    fun merge(other: Transition<GElement, Datum, PElement, PDatum>): Transition<GElement, Datum, PElement, PDatum>;

    /**
     * For each selected element, selects only the elements that match the specified filter, and returns a transition on the resulting selection.
     *
     * The new transition has the same id, name and timing as this transition; however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * @param filter A CSS selector string.
     */
    fun filter(filter: String): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, selects only the elements that match the specified filter, and returns a transition on the resulting selection.
     *
     * The new transition has the same id, name and timing as this transition; however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * The generic refers to the type of element which will be selected after applying the filter, i.e. if the element types
     * contained in a pre-filter selection are narrowed to a subset as part of the filtering.
     *
     * @param filter A CSS selector string.
     */
    fun <FilteredElement: BaseType> filter(filter: String): Transition<FilteredElement, Datum, PElement, PDatum>;
    /**
     * For each selected element, selects only the elements that match the specified filter, and returns a transition on the resulting selection.
     *
     * The new transition has the same id, name and timing as this transition; however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * @param filter A filter function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). The filter function returns a boolean indicating,
     * whether the selected element matches.
     */
    fun filter(filter: ValueFn<GElement, Datum, Boolean>): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, selects only the elements that match the specified filter, and returns a transition on the resulting selection.
     *
     * The new transition has the same id, name and timing as this transition; however, if a transition with the same id already exists on a selected element,
     * the existing transition is returned for that element.
     *
     * The generic refers to the type of element which will be selected after applying the filter, i.e. if the element types
     * contained in a pre-filter selection are narrowed to a subset as part of the filtering.
     *
     * @param filter A filter function which is evaluated for each selected element, in order, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this as the current DOM element (nodes[i]). The filter function returns a boolean indicating,
     * whether the selected element matches.
     */
    fun <FilteredElement:BaseType> filter(filter: ValueFn<GElement, Datum, Boolean>): Transition<FilteredElement, Datum, PElement, PDatum>

    // Event Handling -------------------

    /**
     * Return the currently-assigned listener for the specified event typename on the first (non-null) selected element, if any.
     * If multiple typenames are specified, the first matching listener is returned.
     *
     * @param typenames The typenames is one of the following string event types: start (when the transition starts), end (when the transition ends),
     * interrupt (when the transition is interrupted.) Note that these are not native DOM events. The type may be optionally followed by a period (.) and a name;
     * the optional name allows multiple callbacks to be registered to receive events of the same type, such as "start.foo"" and "start.bar".
     * To specify multiple typenames, separate typenames with spaces, such as "interrupt end"" or "start.foo start.bar".
     */
    fun on(type: String): ValueFn<GElement, Datum, Unit>?
    /**
     * Add a listener to each selected element for the specified event typenames.
     *
     * When a specified transition event is dispatched on a selected node, the specified listener will be invoked for each transitioning element.
     * Listeners always see the latest datum for their element, but the index is a property of the selection and is fixed when the listener is assigned;
     * to update the index, re-assign the listener.
     *
     * @param typenames The typenames is one of the following string event types: start (when the transition starts), end (when the transition ends),
     * interrupt (when the transition is interrupted.) Note that these are not native DOM events. The type may be optionally followed by a period (.) and a name;
     * the optional name allows multiple callbacks to be registered to receive events of the same type, such as "start.foo"" and "start.bar".
     * To specify multiple typenames, separate typenames with spaces, such as "interrupt end"" or "start.foo start.bar".
     * @param listener A listener function which will be evaluated for each selected element, being passed the current datum (d), the current index (i),
     * and the current group (nodes), with this as the current DOM element (nodes[i]). Listeners always see the latest datum for their element,
     * but the index is a property of the selection and is fixed when the listener is assigned; to update the index, re-assign the listener.
     */
    fun on(type: String, listener: ValueFn<GElement, Datum, Unit>?): Transition<GElement, Datum, PElement, PDatum>

    // Control Flow ----------------------

    /**
     * Invoke the specified function for each selected element, passing the current datum (d),
     * the current index (i), and the current group (nodes), with this of the current DOM element (nodes[i]).
     * This method can be used to invoke arbitrary code for each selected element, and is useful for creating a context to access parent and child number simultaneously.
     *
     * @param func A function which is invoked for each selected element,
     *             being passed the current datum (d), the current index (i), and the current group (nodes), with this of the current DOM element (nodes[i]).
     */
    fun each(func: ValueFn<GElement, Datum, Unit>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Invoke the specified function exactly once, passing in this transition along with any optional arguments.
     * Returns this transition.
     *
     * @param func A function which is passed this transition as the first argument along with any optional arguments.
     * @param args List of optional arguments to be passed to the callback function.
     */
    fun call(func: (transition: Transition<GElement, Datum, PElement, PDatum>, args: Array<Any>) -> Any, args: Array<Any>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Return true if this transition contains no (non-null) elements.
     */
    fun empty(): Boolean

    /**
     * Return the first (non-null) element in this transition. If the transition is empty, returns null.
     */
    fun node(): GElement?

    /**
     * Return an array of all (non-null) elements in this transition.
     */
    fun nodes(): Array<GElement>

    /**
     * Returns the total number of elements in this transition.
     */
    fun size(): Number

    // Transition Configuration ----------------------

    /**
     * Returns the current value of the delay for the first (non-null) element in the transition.
     * This is generally useful only if you know that the transition contains exactly one element.
     */
    fun delay(): Number
    /**
     * For each selected element, sets the transition delay to the specified value in milliseconds.
     * If a delay is not specified, it defaults to zero.
     *
     * @param milliseconds Number of milliseconds for the delay.
     */
    fun delay(milliseconds: Number): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, sets the transition delay to the value in milliseconds returned by the
     * value function.
     *
     * @param milliseconds A value function which is evaluated for each selected element, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this of the current DOM element (nodes[i]). The return value is a number
     * specifying the delay in milliseconds.
     */
    fun delay(milliseconds: ValueFn<GElement, Datum, Number>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Returns the current value of the duration for the first (non-null) element in the transition.
     * This is generally useful only if you know that the transition contains exactly one element.
     */
    fun duration(): Number
    /**
     * For each selected element, sets the transition duration to the specified value in milliseconds.
     * If a duration is not specified, it defaults to 250ms.
     *
     * @param duration Number of milliseconds for the duration.
     */
    fun duration(milliseconds: Number): Transition<GElement, Datum, PElement, PDatum>
    /**
     * For each selected element, sets the transition duration to the value in milliseconds returned by the
     * value function.
     *
     * @param milliseconds A value function which is evaluated for each selected element, being passed the current datum (d),
     * the current index (i), and the current group (nodes), with this of the current DOM element (nodes[i]). The return value is a number
     * specifying the duration in milliseconds.
     */
    fun duration(milliseconds: ValueFn<GElement, Datum, Number>): Transition<GElement, Datum, PElement, PDatum>

    /**
     * Returns the current easing function for the first (non-null) element in the transition.
     * This is generally useful only if you know that the transition contains exactly one element.
     */
    fun ease(): (normalizedTime: Number) -> Number
    /**
     * Specifies the transition easing function for all selected elements. The value must be specified as a function.
     * The easing function is invoked for each frame of the animation, being passed the normalized time t in the range [0, 1];
     * it must then return the eased time tʹ which is typically also in the range [0, 1].
     * A good easing function should return 0 if t = 0 and 1 if t = 1. If an easing function is not specified,
     * it defaults to d3.easeCubic.
     *
     * @param easingFn An easing function which is passed the normalized time t in the range [0, 1];
     * it must then return the eased time tʹ which is typically also in the range [0, 1].
     * A good easing function should return 0 if t = 0 and 1 if t = 1.
     */
    fun ease(easingFn: (normalizedTime: Number) -> Number): Transition<GElement, Datum, PElement, PDatum>
}

/**
 * Returns a new transition with the specified name. If a name is not specified, null is used.
 * The new transition is only exclusive with other transitions of the same name.
 *
 * The generic "OldDatum" refers to the type of a previously-set datum of the selected HTML element in the Transition.
 *
 * @param name Name of the transition.
 */
external fun <OldDatum> transition(name: String? = definedExternally): Transition<HTMLElement, OldDatum, BaseType, Unit>;

/**
 * Returns a new transition from an existing transition.
 *
 * When using a transition instance, the returned transition has the same id and name as the specified transition.
 *
 * The generic "OldDatum" refers to the type of a previously-set datum of the selected HTML element in the Transition.
 *
 * @param transition A transition instance.
 */
external fun <OldDatum> transition(transition: Transition<BaseType, Any, BaseType, Any>): Transition<HTMLElement, OldDatum, BaseType, Unit>
