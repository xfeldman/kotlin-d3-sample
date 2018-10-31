import svg.*
import kotlinx.html.js.*
import react.*
import react.dom.*


interface MorphingShapeProps: RProps

class MorphingShapeState(var hovered: Boolean, var clicked: Boolean): RState

class MorphingShape: RComponentWithSVG<MorphingShapeProps, MorphingShapeState>() {

    override fun componentDidMount() {
        setState {
            clicked = false
            hovered = false
        }
    }

    override fun RBuilder.render() {
        svg {
            attrs {
                width = "100"
                height = "100"
                viewBox = "0 0 100 100"
            }
//            attrs["viewBox"] = "0 0 800 600"
            if (state.clicked) {
                rect {
                    attrs {
                        width = "80"
                        height = "80"
                        jsStyle {
                            stroke = "gray"
                            fill = if (state.hovered) "aliceblue" else "white"
                        }
                        onMouseOverFunction = { setState { hovered = true }}
                        onMouseOutFunction = { setState { hovered = false }}
                        onMouseDownFunction = {setState { clicked = true}}
                        onMouseUpFunction = {setState { clicked = false}}
                    }
                }
            } else {
                circle {
                    attrs {
                        cx = "40"
                        cy = "40"
                        r = "40"
                        jsStyle {
                            stroke = "gray"
                            fill = if (state.hovered) "aliceblue" else "white"
                        }
                        onMouseOverFunction = { setState { hovered = true }}
                        onMouseOutFunction = { setState { hovered = false }}
                        onMouseDownFunction = {setState { clicked = true}}
                        onMouseUpFunction = {setState { clicked = false}}
                    }
                }
            }
        }
    }
}


fun RBuilder.morphingShape() = child(MorphingShape::class) {
}

