package com.github.fruitdrivendevelopers.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.github.fruitdrivendevelopers.util.UIFactory


class MainMenuScreen : AbstractScreen() {
    //todo zamienić na xml xD
    //d9bb43 -> a65454
    private val txtrBg: Texture = Texture(Gdx.files.internal("img/menu_background.png"))
    private val txtrPlay: Texture = Texture(Gdx.files.internal("img/btn_play.png"))
    private val txtrExit: Texture = Texture(Gdx.files.internal("img/btn_exit.png"))

    override fun buildStage() {
        // Adding actors
        val bg = Image(txtrBg)
        addActor(bg)

        val btnPlay = UIFactory.createButton(txtrPlay)
        btnPlay.setPosition(width / 2, height / 2, Align.center)
        addActor(btnPlay)

        val btnExit = UIFactory.createButton(txtrExit)
        btnExit.setPosition(width / 2, 60f, Align.center)
        addActor(btnExit)

        // Setting listeners
        btnPlay.addListener(UIFactory.createListener(ScreenEnum.GAME))

        btnExit.addListener(
                object : InputListener() {
                    override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                        Gdx.app.exit()
                        return false
                    }
                })
    }

    override fun dispose() {
        super.dispose()
        txtrBg.dispose()
        txtrPlay.dispose()
        txtrExit.dispose()
    }
}