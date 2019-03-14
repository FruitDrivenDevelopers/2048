package com.github.fruitdrivendevelopers.screen

import com.badlogic.gdx.Game

class ScreenManager private constructor() {
    // Reference to game
    private var game: Game? = null

    companion object {
        private var instance: ScreenManager? = null

        fun getInstance(): ScreenManager {
            return instance ?: return ScreenManager()
        }
    }

    fun initialize(game: Game) {
        this.game = game
    }

    fun showScreen(screenEnum: ScreenEnum, vararg params: Any) {
        // Get current screen to dispose it
        val currentScreen = game!!.screen

        // Show new screen
        val newScreen = screenEnum.getScreen(params)
        newScreen.buildStage()
        game!!.screen = newScreen

        // Dispose previous screen
        currentScreen?.dispose()
    }
}