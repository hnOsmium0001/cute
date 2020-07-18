package io.github.hnosmium0001.cute.ui

import io.github.hnosmium0001.cute.ui.input.InputEvent
import io.github.hnosmium0001.cute.ui.input.RawInputEvent
import io.github.hnosmium0001.cute.ui.render.RedrawContext
import io.github.hnosmium0001.cute.ui.render.RepaintContext

abstract class Widget {
    val rect = Rect2D()

    var x1: Int
        get() = rect.x1
        set(value) {
            rect.x1 = value
        }
    var y1: Int
        get() = rect.y1
        set(value) {
            rect.y1 = value
        }
    var x2: Int
        get() = x1 + width
        set(value) {
            rect.x2 = value
        }
    var y2: Int
        get() = y1 + height
        set(value) {
            rect.y2 = value
        }

    var width: Int
        get() = rect.width
        set(value) {
            rect.width = value
        }
    var height: Int
        get() = rect.height
        set(value) {
            rect.height = value
        }

    fun contains(point: Point): Boolean = rect.contains(point)
    fun contains(x: Int, y: Int): Boolean = rect.contains(x, y)

    abstract var root: RootWidget
    abstract var parent: Widget?
    abstract val children: Iterable<Widget>

    open fun onReady() {}
    open fun onRemoved() {}

    open fun onInput(event: InputEvent): InputEvent.Result {
        return when (event) {
            is InputEvent.MousePressed -> onMousePressed(event)
            is InputEvent.MouseReleased -> onMouseReleased(event)
            is InputEvent.MouseMoved -> onMouseMoved(event)
            is InputEvent.ScrollWheel -> onMouseScrolled(event)
            is InputEvent.KeyPressed -> onKeyPressed(event)
            is InputEvent.KeyReleased -> onKeyReleased(event)
            is InputEvent.CharTyped -> onCharTyped(event)
        }
    }

    protected open fun onMousePressed(event: InputEvent.MousePressed): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onMouseReleased(event: InputEvent.MouseReleased): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onMouseMoved(event: InputEvent.MouseMoved): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onMouseScrolled(event: InputEvent.ScrollWheel): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onKeyPressed(event: InputEvent.KeyPressed): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onKeyReleased(event: InputEvent.KeyReleased): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    protected open fun onCharTyped(event: InputEvent.CharTyped): InputEvent.Result {
        return InputEvent.Result.IGNORED
    }

    open fun onRawInput(event: RawInputEvent) {}

    open fun redraw(ctx: RedrawContext) {}
    open fun repaint(ctx: RepaintContext) {}

    protected var processInputs: Boolean
        get() = root.rawReceivers.contains(this)
        set(value) {
            if (value) {
                root.rawReceivers.add(this)
            } else {
                root.rawReceivers.remove(this)
            }
        }
}