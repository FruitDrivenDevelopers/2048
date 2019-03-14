package com.github.fruitdrivendevelopers.util

import com.github.fruitdrivendevelopers.screen.ScreenManager
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.github.fruitdrivendevelopers.screen.ScreenEnum
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton

object UIFactory {
    fun createButton(texture: Texture): ImageButton {
        return ImageButton(TextureRegionDrawable(TextureRegion(texture)))
    }

    fun createListener(dstScreen: ScreenEnum, vararg params: Any) =
            object : InputListener() {
                override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    ScreenManager.getInstance().showScreen(dstScreen, params)
                    return false
                }
            }
}