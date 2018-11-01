import d3.selection.BaseType
import d3.selection.Selection
import d3.selection.select
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.svg.*
import kotlin.coroutines.experimental.*
import kotlinx.coroutines.experimental.launch
import org.w3c.dom.HTMLElement

data class MyDatum(val number: Double)

fun d3sample() {
    select<HTMLDivElement, MyDatum>("#d3Sample")
            .append<SVGElement>("svg")
            .attr("width", 100)
            .attr("height", 100)
    drawCircle(true)
}

fun drawCircle(visible: Boolean):Selection<out SVGCircleElement, MyDatum, HTMLElement, Any> {
    var svg = select<HTMLDivElement, MyDatum>("#d3Sample").select<SVGElement>("svg")
    return svg.append<SVGCircleElement>("circle")
            .style("stroke", "gray")
            .style("fill", "white")
            .style("opacity", if (visible) 1 else 0)
            .attr("id", "myShape")
            .attr("r", 40)
            .attr("cx", 40)
            .attr("cy", 40)
            .on("mouseover", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "aliceblue");})
            .on("mouseout", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "white");})
            .on("mousedown", ::replaceWithRect)
            .on("mouseup", ::replaceWithCircle)
}

fun drawRect(visible:Boolean):Selection<out SVGRectElement, MyDatum, HTMLElement, Any> {
    var svg = select<HTMLDivElement, MyDatum>("#d3Sample").select<SVGElement>("svg")
    return svg.append<SVGRectElement>("rect")
            .style("stroke", "gray")
            .style("fill", "white")
            .style("opacity", if (visible) 1 else 0)
            .attr("id", "myShape")
            .attr("width", 80)
            .attr("height", 80)
            .on("mouseover", { _: MyDatum, index:Number, groups:Array<SVGRectElement> ->
                select<SVGRectElement, MyDatum>(groups[index.toInt()]).style("fill", "aliceblue");})
            .on("mouseout", { _: MyDatum, index:Number, groups:Array<SVGRectElement> ->
                select<SVGRectElement, MyDatum>(groups[index.toInt()]).style("fill", "white");})
            .on("mousedown", ::replaceWithRect)
            .on("mouseup", ::replaceWithCircle)
}

fun replaceWithRect(datum: MyDatum, index:Number, groups:Array<out BaseType>) {
    val shape = select<SVGCircleElement, MyDatum>("#myShape")
    launch {
        animateDown(shape)
        shape.remove()
        val rect = drawRect(false)
        animateUp(rect)
    }
}

fun replaceWithCircle(datum: MyDatum, index:Number, groups:Array<out BaseType>) {
    val shape = select<SVGCircleElement, MyDatum>("#myShape")
    launch {
        animateDown(shape)
        shape.remove()
        val rect = drawCircle(false)
        animateUp(rect)
    }
}

suspend fun animateDown(selection: Selection<out BaseType, MyDatum, HTMLElement, Any>):Selection<out BaseType, MyDatum, HTMLElement, Any> =
    suspendCoroutine { cont ->
        selection
                .style("opacity", 1)
                .transition()
                .delay(0)
                .duration(200)
                .style("opacity", 0)
                .on("end") {datum:MyDatum, index:Number, groups:Array<out BaseType> ->
                    cont.resume(selection)
                }
    }

suspend fun animateUp(selection: Selection<out BaseType, MyDatum, HTMLElement, Any>):Selection<out BaseType, MyDatum, HTMLElement, Any> =
    suspendCoroutine { cont ->
        selection
                .style("opacity", 0)
                .transition()
                .delay(0)
                .duration(200)
                .style("opacity", 1)
                .on("end") {datum:MyDatum, index:Number, groups:Array<out BaseType> ->
                    cont.resume(selection)
                }
    }
