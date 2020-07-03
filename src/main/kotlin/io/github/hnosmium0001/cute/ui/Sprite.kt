package io.github.hnosmium0001.cute.ui

import net.minecraft.util.ResourceLocation

class Texture(
    val id: ResourceLocation,
    val width: Int,
    val height: Int
)

interface Sprite {
    val atlas: Texture
}

class TriangularSprite(
    override val atlas: Texture,
    val p1U: Float,
    val p1V: Float,
    val p2U: Float,
    val p2V: Float,
    val p3U: Float,
    val p3V: Float
) : Sprite

class RectangularSprite(
    override val atlas: Texture,
    val tlU: Float,
    val tlV: Float,
    val brU: Float,
    val brV: Float
) : Sprite {
    constructor(
        atlas: Texture,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) : this(
        atlas,
        tlU = x.toFloat() / atlas.width,
        tlV = y.toFloat() / atlas.height,
        brU = (x + width).toFloat() / atlas.width,
        brV = (y + height).toFloat() / atlas.height
    )
}