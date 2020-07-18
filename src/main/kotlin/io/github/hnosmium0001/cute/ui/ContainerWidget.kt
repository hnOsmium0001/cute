package io.github.hnosmium0001.cute.ui

import io.github.hnosmium0001.cute.ui.input.InputEvent

abstract class ContainerWidget : Widget() {
    override fun onInput(event: InputEvent): InputEvent.Result {
        var result = InputEvent.Result.IGNORED
        for (child in children) {
            if (event.isEligible(child)) {
                result = result.merge(child.onInput(event))
                if (result.stopped) {
                    break
                }
            }
        }
        return result
    }

    abstract fun addChild(child: Widget)
    abstract fun removeChild(child: Widget)
    abstract fun queueRemoveChild(child: Widget)
}