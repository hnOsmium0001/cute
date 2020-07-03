package io.github.hnosmium0001.cute.ui

import io.github.hnosmium0001.cute.ui.input.UserInputEvent
import io.github.hnosmium0001.cute.ui.render.RedrawContext
import io.github.hnosmium0001.cute.ui.render.RepaintContext
import net.minecraft.client.Minecraft
import java.util.*
import kotlin.collections.ArrayList

class RootWidget : ContainerWidget, DefaultGeometryBehavior {
    override val rect: Rect2D = Rect2D(0, 0, -1, -1)

    override var root: RootWidget
        get() = this
        set(_) {
            throw IllegalStateException("Cannot set the root of a root widget.")
        }
    override var parent: Widget?
        get() = null
        set(_) {
            throw IllegalStateException("Cannot set parent of a root widget.")
        }
    override val children: List<Widget> = ArrayList()

    override fun addChild(child: Widget) {
        children as MutableList
        children.add(child)
        child.parent = this
        child.root = this
    }

    override fun removeChild(child: Widget) {
        children as MutableList
        children.remove(child)
    }

    override fun queueRemoveChild(child: Widget) {
        scheduleTask { removeChild(child) }
    }

    private val tasks: Queue<() -> Unit> = ArrayDeque()
    private val timedTasks: Queue<Triple<() -> Unit, Long, Long>> = ArrayDeque()

    fun scheduleTask(task: () -> Unit) {
        tasks.add(task)
    }

    fun scheduleTask(task: () -> Unit, delay: Long) {
        val currentTime = Minecraft.getInstance().world!!.gameTime
        timedTasks.add(Triple(task, currentTime, delay))
    }

    override fun onReady() {}
    override fun onRemoved() {}

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

    fun render() {
    }

    fun tick() {
        while (!tasks.isEmpty()) {
            tasks.remove().invoke()
        }
        while (!timedTasks.isEmpty()) {
            val task = timedTasks.element()
            val currentTime = Minecraft.getInstance().world!!.gameTime
            // 2nd is time started, 3rd is desired delay
            if (task.second - currentTime >= task.third) {
                task.first.invoke()
                timedTasks.remove()
            }
        }
    }

    override fun redraw(ctx: RedrawContext) {}
    override fun repaint(ctx: RepaintContext) {}

    fun onScreenResize(width: Int, height: Int) {
        rect.width = width
        rect.height = height
    }
}