package io.github.hnosmium0001.cute.view

interface Widget {
    var x1: Int
    var x2: Int
    var y1: Int
    var y2: Int

    var width: Int
    var height: Int

    val rect: Rect2D

    fun contains(point: Point): Boolean

    fun redraw()
}

interface DefaultBehaviorMixin : Widget {
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
}