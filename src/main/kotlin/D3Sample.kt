import d3.selection.select
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.svg.SVGCircleElement
import org.w3c.dom.svg.SVGElement
import org.w3c.dom.svg.SVGGeometryElement
import org.w3c.dom.svg.SVGRectElement

data class MyDatum(val number: Double)

fun d3sample() {
    select<HTMLDivElement, MyDatum>("#d3Sample")
            .append<SVGElement>("svg")
            .attr("width", 100)
            .attr("height", 100)
    drawCircle()
}

fun animate(datum:MyDatum, index:Number, groups:Array<SVGCircleElement>) {
    select<SVGCircleElement, MyDatum>(groups[index.toInt()]).transition()
            .attr("r", 10)
            .transition()
            .delay(1000)
            .attr("r", 40)
}

fun drawCircle() {
    var svg = select<HTMLDivElement, MyDatum>("#d3Sample").select<SVGElement>("svg")
    svg.append<SVGCircleElement>("circle")
            .style("stroke", "gray")
            .style("fill", "white")
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

fun drawRect() {
    var svg = select<HTMLDivElement, MyDatum>("#d3Sample").select<SVGElement>("svg")
    svg.append<SVGRectElement>("rect")
            .style("stroke", "gray")
            .style("fill", "white")
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

fun replaceWithRect(datum: MyDatum, index:Number, groups:Array<out SVGGeometryElement>) {
    select<SVGCircleElement, MyDatum>("#myShape").remove()
    drawRect()
}

fun replaceWithCircle(datum: MyDatum, index:Number, groups:Array<out SVGGeometryElement>) {
    select<SVGCircleElement, MyDatum>("#myShape").remove()
    drawCircle()
}

