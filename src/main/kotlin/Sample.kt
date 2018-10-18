import d3.selection.select
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.svg.SVGCircleElement
import org.w3c.dom.svg.SVGElement
import kotlin.js.Math
import kotlin.math.round

data class MyDatum(val number: Double)

fun main(args: Array<String>) {
//    sampleAnimation()
    sampleDataBinding()
}

fun sampleAnimation() {
    var sampleSVG = select<HTMLDivElement, MyDatum>("#container")
            .append<SVGElement>("svg")
            .attr("width", 100)
            .attr("height", 100)

    sampleSVG.append<SVGCircleElement>("circle")
            .style("stroke", "gray")
            .style("fill", "white")
            .attr("r", 40)
            .attr("cx", 50)
            .attr("cy", 50)
            .on("mouseover", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "aliceblue");})
            .on("mouseout", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "white");})
            .on("mousedown", ::animate)
}

fun animate(datum:MyDatum, index:Number, groups:Array<SVGCircleElement>) {
    select<SVGCircleElement, MyDatum>(groups[index.toInt()]).transition()
            .attr("r", 10)
            .transition()
            .delay(1000)
            .attr("r", 40)
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fun sampleDataBinding() {
    var dataset = ArrayList<MyDatum>()

    for(i in 0..4){
        dataset.add (MyDatum(round(Math.random()*2000)))
    }

    var sampleSVG = select<HTMLDivElement, MyDatum>("#container")
            .append<SVGElement>("svg")
            .attr("width", 400)
            .attr("height", 100)

    sampleSVG.selectAll<SVGCircleElement, MyDatum>("circle")
            .data(dataset.toTypedArray())
            .enter().append<SVGCircleElement>("circle")
            .style("stroke", "gray")
            .style("fill", "white")
            .attr("r", 40)
            .attr("cx") {_:MyDatum, i:Number, _:Array<SVGCircleElement> -> (i.toInt()*80 + 40)}
            .attr("cy", 40)
            .on("mouseover", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "aliceblue");})
            .on("mouseout", {_:MyDatum, index:Number, groups:Array<SVGCircleElement> ->
                select<SVGCircleElement, MyDatum>(groups[index.toInt()]).style("fill", "white");})
            .on("mousedown", ::animateFirstStep)
}

fun animateFirstStep(datum:MyDatum, index:Number, groups:Array<SVGCircleElement>){
    select<SVGCircleElement, MyDatum>(groups[index.toInt()])
            .transition()
            .delay(0)
            .duration(datum.number)
            .attr("r", 10)
            .on("end", ::animateSecondStep)
};

fun animateSecondStep(datum:MyDatum, index:Number, groups:Array<SVGCircleElement>){
    select<SVGCircleElement, MyDatum>(groups[index.toInt()])
            .transition()
            .duration(datum.number)
            .attr("r", 40)
};
