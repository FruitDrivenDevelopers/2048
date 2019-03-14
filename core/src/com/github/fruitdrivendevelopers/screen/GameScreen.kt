package com.github.fruitdrivendevelopers.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.github.fruitdrivendevelopers.ui.Board
import com.github.fruitdrivendevelopers.util.Direction
import com.github.fruitdrivendevelopers.util.SwipeListener

class GameScreen : AbstractScreen(), SwipeListener.SwipeCallback, Board.ScoreChanged {
    private var board: Board = Board(this)
    private val style: Label.LabelStyle = Label.LabelStyle(BitmapFont().apply { data.scale(4f) }, Color.WHITE)
    private var label: Label = Label(SCORE_PREFIX, style)

    override fun show() {
        super.show()

        Gdx.input.inputProcessor = GestureDetector(SwipeListener(this))

        board.setFillParent(true)
        board.isTransform = true
        board.debug = true
    }

    override fun buildStage() {
        board.build()

        label = Label(SCORE_PREFIX, style)
        label.setPosition(width/2,height - label.height, Align.center)

        addActor(board)
        addActor(label)
    }

    override fun render(delta: Float) {
        super.render(delta)
        board.act(delta)
    }

    override fun onSwipe(direction: Direction) {
        board.onSwipe(direction)
    }

    override fun scoreChanged(score: Int) {
        label.setText(SCORE_PREFIX + score.toString())
        label.setPosition(width/2,height - label.height, Align.center)
    }

    companion object {
        const val SCORE_PREFIX = "Score: "
    }
}