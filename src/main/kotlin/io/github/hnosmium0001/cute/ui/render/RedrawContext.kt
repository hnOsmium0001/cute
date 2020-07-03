package io.github.hnosmium0001.cute.ui.render

import io.github.hnosmium0001.cute.ui.Color
import io.github.hnosmium0001.cute.ui.Point
import io.github.hnosmium0001.cute.ui.RectangularSprite
import io.github.hnosmium0001.cute.ui.TriangularSprite

interface RedrawProvider {
    // "Ownership" of `RedrawContext` is transferred in these methods
    fun startRedraw(): RedrawContext
    fun finishRedraw(ctx: RedrawContext)
}

/**
 * Redraw is an event that completely rebuilds the draw commands buffer. All widgets in the tree will be iterated and
 * called to allocate their part of the draw commands.
 *
 * Redraw is generally expensive compared to other events such as [RepaintContext]. Prefer to use them if at all
 * possible.
 *
 * @see RepaintContext
 */
interface RedrawContext {
    fun drawLine(
        p1: Point,
        p2: Point,
        thickness: Float,
        color: Color
    ): Int

    fun drawTriangle(
        p1: Point,
        p1Color: Color,
        p2: Point,
        p2Color: Color,
        p3: Point,
        p3Color: Color
    ): Int

    fun drawTriangle(
        p1: Point,
        p2: Point,
        p3: Point,
        color: Color
    ) {
        drawTriangle(p1, color, p2, color, p3, color)
    }

    fun drawTriangle(
        p1: Point,
        p2: Point,
        p3: Point,
        sprite: TriangularSprite
    )

    fun drawQuad(
        tl: Point,
        br: Point,
        tlColor: Color,
        trColor: Color,
        blColor: Color,
        brColor: Color
    ): Int

    fun drawQuad(
        tl: Point,
        br: Point,
        color: Color
    ) {
        drawQuad(tl, br, tlColor = color, trColor = color, blColor = color, brColor = color)
    }

    fun drawQuad(
        p1: Point,
        p2: Point,
        sprite: RectangularSprite
    )
}