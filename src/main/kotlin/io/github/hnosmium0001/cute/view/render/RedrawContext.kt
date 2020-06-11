package io.github.hnosmium0001.cute.view.render

/**
 * Redraw is an event that completely rebuilds the draw commands buffer. All widgets in the tree will be iterated and
 * called to allocate their part of the draw commands.
 *
 * Redraw is generally expensive compared to other evemts such as [RepaintContext]. Prefer to use them if at all
 * possible.
 *
 * @see RepaintContext
 */
interface RedrawContext {
    // TODO
}