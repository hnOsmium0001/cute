package io.github.hnosmium0001.cute.core.render

import io.github.hnosmium0001.cute.core.Color
import io.github.hnosmium0001.cute.core.Point
import io.github.hnosmium0001.cute.core.Rect2D
import io.github.hnosmium0001.cute.core.Texture

/**
 * Repaint is an event that let a particular portion of the widgets, specified by the event requester, to update some
 * attributes of their draw commands. Those attributes includes color, alpha, ignore flag, etc..
 */
interface RepaintContext {
    fun findColoredTriangle(id: Int): ColoredTriangleRepainter
    fun modifyColoredTriangle(id: Int, consumer: ColoredTriangleRepainter.() -> Unit) {
        consumer.invoke(this.findColoredTriangle(id))
    }

    fun findTexturedTriangle(id: Int): TexturedTriangleRepainter
    fun modifyTexturedTriangle(id: Int, consumer: TexturedTriangleRepainter.() -> Unit) {
        consumer.invoke(this.findTexturedTriangle(id))
    }

    fun findColoredQuad(id: Int): ColoredQuadRepainter
    fun modifyColoredQuad(id: Int, consumer: ColoredQuadRepainter.() -> Unit) {
        consumer.invoke(this.findColoredQuad(id))
    }

    fun findTexturedQuad(id: Int): TexturedQuadRepainter
    fun modifyTexturedQuad(id: Int, consumer: TexturedQuadRepainter.() -> Unit) {
        consumer.invoke(this.findTexturedQuad(id))
    }
}

interface Repainter

interface TriangleRepainter : Repainter {
    var x1: Int
    var y1: Int

    var x2: Int
    var y2: Int

    var x3: Int
    var y3: Int

    var p1: Point
        get() = Point(x1, y1)
        set(value) {
            x1 = value.x
            y1 = value.y
        }
    var p2: Point
        get() = Point(x2, y2)
        set(value) {
            x2 = value.x
            y2 = value.y
        }
    var p3: Point
        get() = Point(x3, y3)
        set(value) {
            x3 = value.x
            y3 = value.y
        }
}

interface ColoredTriangleRepainter : TriangleRepainter {
    var p1Color: Color
    var p2Color: Color
    var p3Color: Color
}

interface TexturedTriangleRepainter : TriangleRepainter {
    var u1: Float
    var v1: Float

    var u2: Float
    var v2: Float

    var u3: Float
    var v3: Float
}

interface QuadRepainter : Repainter {
    var topLeft: Point
        get() = Point(x1, y1)
        set(value) {
            x1 = value.x
            y1 = value.y
        }
    var x1: Int
    var y1: Int

    var bottomRight: Point
        get() = Point(x2, y2)
        set(value) {
            x2 = value.x
            y2 = value.y
        }
    var x2: Int
    var y2: Int

    var width: Int
        get() = x2 - x1
        set(value) {
            x2 = x1 + value
        }
    var height: Int
        get() = y2 - y1
        set(value) {
            y2 = y1 + value
        }

    var rect: Rect2D
        get() = Rect2D.between(x1, y1, x2, y2)
        set(value) {
            x1 = value.x1
            y1 = value.y1
            x2 = value.x2
            y2 = value.y2
        }
}

interface ColoredQuadRepainter : QuadRepainter {
    var tlColor: Color
    var trColor: Color
    var blColor: Color
    var brColor: Color
}

interface TexturedQuadRepainter : QuadRepainter {
    val atlas: Texture

    var tlU: Float
    var tlV: Float

    var brU: Float
    var brV: Float

    fun setRegion(x: Int, y: Int, width: Int, height: Int) {
        tlU = x.toFloat() / atlas.width
        tlV = y.toFloat() / atlas.height
        brU = (x + width).toFloat() / atlas.width
        brV = (y + height).toFloat() / atlas.height
    }
}