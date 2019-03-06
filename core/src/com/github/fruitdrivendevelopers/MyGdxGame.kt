package com.github.fruitdrivendevelopers

import com.badlogic.gdx.Game
import com.github.fruitdrivendevelopers.screen.ScreenEnum
import com.github.fruitdrivendevelopers.screen.ScreenManager

class MyGdxGame : Game {

    constructor() : super()

    override fun create() {
        ScreenManager.getInstance().initialize(this)
        ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU)
    }
}
