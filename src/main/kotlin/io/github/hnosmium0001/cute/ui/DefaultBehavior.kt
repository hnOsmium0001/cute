package io.github.hnosmium0001.cute.ui

interface DefaultGeometryBehavior : Widget {
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
    override fun contains(x: Int, y: Int): Boolean = rect.contains(x, y)
}