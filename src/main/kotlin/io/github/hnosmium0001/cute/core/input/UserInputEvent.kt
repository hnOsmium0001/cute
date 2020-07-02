package io.github.hnosmium0001.cute.core.input

import io.github.hnosmium0001.cute.util.toInt

inline class MouseButton(val id: Int)
inline class KeyCode(val id: Int)
inline class Scancode(val id: Int)

sealed class UserInputEvent {
    class MousePressed(val mouseX: Int, val mouseY: Int, val button: MouseButton) : UserInputEvent()
    class MouseReleased(val mouseX: Int, val mouseY: Int, val button: MouseButton) : UserInputEvent()
    class MouseMoved(val mouseX: Int, val mouseY: Int) : UserInputEvent()
    class ScrollWheel(val change: Double) : UserInputEvent()

    class KeyPressed(val keyCode: KeyCode, val scancode: Scancode) : UserInputEvent()
    class KeyReleased(val keyCode: KeyCode, val scancode: Scancode) : UserInputEvent()
    class CharTyped(val keyCode: KeyCode, val char: Char) : UserInputEvent()

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
    }
}