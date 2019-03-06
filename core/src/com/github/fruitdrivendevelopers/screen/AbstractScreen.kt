package com.github.fruitdrivendevelopers.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport

abstract class AbstractScreen : Stage(FitViewport(540f, 960f, OrthographicCamera())), Screen {
    // Subclasses must load actors in this method
    abstract fun buildStage()

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT.or(GL20.GL_DEPTH_BUFFER_BIT))

        super.act(delta)
        super.draw()
    }

    override fun show() {
        Gdx.input.inputProcessor = this
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {}
    override fun pause() {}
    override fun resume() {}
}