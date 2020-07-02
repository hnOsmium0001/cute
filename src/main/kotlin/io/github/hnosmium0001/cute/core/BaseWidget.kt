package io.github.hnosmium0001.cute.core

import io.github.hnosmium0001.cute.core.input.UserInputEvent
import io.github.hnosmium0001.cute.core.render.RedrawContext
import io.github.hnosmium0001.cute.core.render.RepaintContext

abstract class BaseWidget : Widget, DefaultGeometryBehavior {
    override val rect: Rect2D = Rect2D()

    override var parent: Widget? = null

    override fun onReady() {}

    override fun onRemoved() {}

    override fun onInput(event: UserInputEvent): UserInputEvent.Result {
        return when (event) {
            is UserInputEvent.MousePressed -> onMousePressed(event)
            is UserInputEvent.MouseReleased -> onMouseReleased(event)
            is UserInputEvent.MouseMoved -> onMouseMoved(event)
            is UserInputEvent.ScrollWheel -> onMouseScrolled(event)
            is UserInputEvent.KeyPressed -> onKeyPressed(event)
            is UserInputEvent.KeyReleased -> onKeyReleased(event)
            is UserInputEvent.CharTyped -> onCharTyped(event)
        }
    }

    private fun onMousePressed(event: UserInputEvent.MousePressed): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onMouseReleased(event: UserInputEvent.MouseReleased): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onMouseMoved(event: UserInputEvent.MouseMoved): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onMouseScrolled(event: UserInputEvent.ScrollWheel): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onKeyPressed(event: UserInputEvent.KeyPressed): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onKeyReleased(event: UserInputEvent.KeyReleased): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    private fun onCharTyped(event: UserInputEvent.CharTyped): UserInputEvent.Result {
        return UserInputEvent.Result.IGNORED
    }

    override fun redraw(ctx: RedrawContext) {}
    override fun repaint(ctx: RepaintContext) {}
}