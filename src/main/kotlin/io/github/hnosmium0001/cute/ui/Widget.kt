package io.github.hnosmium0001.cute.ui

import io.github.hnosmium0001.cute.ui.render.RedrawContext
import io.github.hnosmium0001.cute.ui.render.RepaintContext
import io.github.hnosmium0001.cute.ui.input.UserInputEvent

interface Widget {
    var x1: Int
    var y1: Int
    var x2: Int
    var y2: Int

    var width: Int
    var height: Int

    val rect: Rect2D

    var root: RootWidget
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

interface ContainerWidget : Widget {
    fun addChild(child: Widget)
    fun removeChild(child: Widget)
    fun queueRemoveChild(child: Widget)
}