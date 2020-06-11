package io.github.hnosmium0001.cute.view

class Point(
    var x: Int,
    var y: Int
)

class Rect2D(
    var x1: Int,
    var y1: Int,
    var width: Int,
    var height: Int
) {
    var x2: Int
        get() = x1 + width
        set(value) {
            x1 = value - width
        }
    var y2: Int
        get() = y1 + height
        set(value) {
            y1 = value - height
        }

    fun contains(point: Point): Boolean {
        return point.x >= x1 &&
            point.y >= y1 &&
            point.x <= x2 &&
            point.y <= y2
    }

    companion object {
        fun at(x1: Int, y1: Int, width: Int, height: Int) = Rect2D(x1, y1, width, height)

        fun between(x1: Int, y1: Int, x2: Int, y2: Int) = Rect2D(x1, y1, x2 - x1, y2 - y1)
        fun between(p1: Point, p2: Point) = between(p1.x, p1.y, p2.x, p2.y)
    }
}