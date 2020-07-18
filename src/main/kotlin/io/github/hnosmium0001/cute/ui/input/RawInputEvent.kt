@file:Suppress("NAME_SHADOWING")

package io.github.hnosmium0001.cute.ui.input

import org.lwjgl.glfw.GLFW.*

inline class MouseButton(val id: Int)
inline class InputAction(val id: Int)
inline class KeyCode(val id: Int)
inline class Scancode(val id: Int)

inline class ModFlags(val id: Int) {
    val ctrlPressed: Boolean get() = (id and GLFW_MOD_CONTROL) == GLFW_MOD_CONTROL
    val shiftPressed: Boolean get() = (id and GLFW_MOD_SHIFT) == GLFW_MOD_SHIFT
    val altPressed: Boolean get() = (id and GLFW_MOD_ALT) == GLFW_MOD_ALT
    val capsLock: Boolean get() = (id and GLFW_MOD_CAPS_LOCK) == GLFW_MOD_CAPS_LOCK
    val numLock: Boolean get() = (id and GLFW_MOD_NUM_LOCK) == GLFW_MOD_NUM_LOCK
}

sealed class RawInputEvent {
    class MouseInput(val mouseX: Int, val mouseY: Int, val button: MouseButton, val action: InputAction, val mods: ModFlags) : RawInputEvent()
    class ScrollWheel(val change: Double) : RawInputEvent()
    class KeyPressed(val keyCode: KeyCode, val scancode: Scancode) : RawInputEvent()
    class KeyReleased(val keyCode: KeyCode, val scancode: Scancode) : RawInputEvent()
    class CharTyped(val keyCode: KeyCode, val char: Char) : RawInputEvent()
}

object RawInputHandler {
    fun onGLFWMouseCallback(button: Int, action: Int, mods: Int) {
        val button = MouseButton(button)
        val action = InputAction(action)
        val mods = ModFlags(mods)
    }

    fun onGLFWMouseScrollCallback(xOff: Double, yOff: Double) {
        // TODO
        
    }

    fun onGLFWMousePositionCallback(x: Double, y: Double) {
        // TODO
    }

    fun onGLFWKeyCallback(key: Int, scancode: Int, action: Int, mods: Int) {
        // TODO
    }

    fun onGLFWCharCallback(codepoint: Char) {
        // TODO
    }
}