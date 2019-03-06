package com.github.fruitdrivendevelopers.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align

class Tile : Actor() {
    var background: Image = Image(Texture(Gdx.files.internal("tile.png")))
    var font: BitmapFont = BitmapFont()
    var value: Int = 0

    init {
        font.color = Color.WHITE
        font.data.scale(4f * 1)
        background.setOrigin(Align.center)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        if(value == 0) return

        val layout = GlyphLayout(font, value.toString())

        val fontX = x + (width - layout.width) / 2
        val fontY = y + (height + layout.height) / 2

        background.setPosition(x, y)
        background.draw(batch, parentAlpha)
        font.draw(batch, layout, fontX, fontY)
    }

    fun animatePop() {
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, 0.5f, Interpolation.circle),
                Actions.scaleTo(0.75f, 0.75f, 0.5f, Interpolation.circle),
                Actions.scaleTo(1f, 1f, 0.5f, Interpolation.circle))
        )
    }

    override fun setScale(scaleX: Float, scaleY: Float) {
        super.setScale(scaleX, scaleY)

        background.setScale(scaleX, scaleY)
    }
}