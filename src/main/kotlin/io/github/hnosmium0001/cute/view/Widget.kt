package io.github.hnosmium0001.cute.view

import io.github.hnosmium0001.cute.view.render.RedrawContext
import io.github.hnosmium0001.cute.view.render.RepaintContext
import io.github.hnosmium0001.cute.view.event.UserInputEvent

interface Widget {
    var x1: Int
    var y1: Int
    var x2: Int
    var y2: Int

    var width: Int
    var height: Int

    val rect: Rect2D

    var parent: Widget?
    val children: Iterable<Widget>

    fun onReady()
    fun onRemoved()
    fun onInput(event: UserInputEvent): UserInputEvent.Result

    fun contains(point: Point): Boolean
    fun contains(x: Int, y: Int): Boolean

    fun redraw(ctx: RedrawContext)
    fun repaint(ctx: RepaintContext)
}

interface DefaultGeometryBehavior : Widget {
    override var x1: Int
        get() = rect.x1
        set(value) {
            rect.x1 = value
        }
    override var y1: Int
        get() = rect.y1
        set(value) {
            rect.y1 = value
        }
    override var x2: Int
        get() = x1 + width
        set(value) {
            rect.x2 = value
        }
    override var y2: Int
        get() = y1 + height
        set(value) {
            rect.y2 = value
        }

    override var width: Int
        get() = rect.width
        set(value) {
            rect.width = value
        }
    override var height: Int
        get() = rect.height
        set(value) {
            rect.height = value
        }

    override fun contains(point: Point): Boolean = rect.contains(point)
    override fun contains(x: Int, y: Int): Boolean = rect.contains(x, y)
}