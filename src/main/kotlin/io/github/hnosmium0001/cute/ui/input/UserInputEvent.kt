package io.github.hnosmium0001.cute.ui.input

import io.github.hnosmium0001.cute.ui.Widget
import io.github.hnosmium0001.cute.util.toInt

inline class MouseButton(val id: Int)
inline class KeyCode(val id: Int)
inline class Scancode(val id: Int)

sealed class UserInputEvent {
    abstract fun isEligible(widget: Widget): Boolean

    class MousePressed(val mouseX: Int, val mouseY: Int, val button: MouseButton) : UserInputEvent() {
        override fun isEligible(widget: Widget): Boolean = widget.contains(mouseX, mouseY)
    }

    class MouseReleased(val mouseX: Int, val mouseY: Int, val button: MouseButton) : UserInputEvent() {
        override fun isEligible(widget: Widget): Boolean = widget.contains(mouseX, mouseY)
    }

    class MouseMoved(val mouseX: Int, val mouseY: Int) : UserInputEvent() {
        override fun isEligible(widget: Widget): Boolean = true
    }

    class ScrollWheel(val change: Double) : UserInputEvent() {
        // TODO
        override fun isEligible(widget: Widget): Boolean = true
    }

    class KeyPressed(val keyCode: KeyCode, val scancode: Scancode) : UserInputEvent() {
        // TODO
        override fun isEligible(widget: Widget): Boolean = true
    }

    class KeyReleased(val keyCode: KeyCode, val scancode: Scancode) : UserInputEvent() {
        // TODO
        override fun isEligible(widget: Widget): Boolean = true
    }

    class CharTyped(val keyCode: KeyCode, val char: Char) : UserInputEvent() {
        // TODO
        override fun isEligible(widget: Widget): Boolean = true
    }

    enum class Result(
        val stopped: Boolean,
        val consumed: Boolean
    ) {
        CAPTURED(true, true),
        USED(false, true),
        IGNORED(false, false),
        BLOCKED(true, false),
        ;

        val flag: Int = (stopped.toInt() shl 1) or (consumed.toInt())

        fun merge(that: Result): Result {
            return CAPTURED
        }
    }
}