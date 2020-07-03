package io.github.hnosmium0001.cute.signal

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap

class Signal<Callback>(
    val name: String,
    private val dispatcherConstructor: (Collection<Callback>) -> Callback
) {
    var dispatcher: Callback = dispatcherConstructor.invoke(ImmutableList.of())
        private set

    private var subscribers: MutableMap<Int, Callback> = ImmutableMap.of()
    private var nextID = 0

    private fun ensureMutable() {
        if (subscribers == ImmutableMap.of<Int, Callback>()) {
            subscribers = Int2ObjectOpenHashMap()
            dispatcher = dispatcherConstructor.invoke(subscribers.values)
        }
    }

    private fun nextID() = nextID++

    fun connect(callback: Callback): Int {
        ensureMutable()
        return nextID().also { subscribers[it] = callback }
    }

    fun disconnect(id: Int): Callback? {
        ensureMutable()
        return subscribers.remove(id)
    }
}