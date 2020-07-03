package io.github.hnosmium0001.cute.ui

import io.github.hnosmium0001.cute.ui.input.UserInputEvent

abstract class BaseContainerWidget : BaseWidget(), ContainerWidget {
    override fun onInput(event: UserInputEvent): UserInputEvent.Result {
        var result = UserInputEvent.Result.IGNORED
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

    override fun addChild(child: Widget) {

    }
}