package com.github.fruitdrivendevelopers.screen


enum class ScreenEnum {
    MAIN_MENU {
        override fun getScreen(params: Any) : AbstractScreen {
            return MainMenuScreen()
        }
    },
    GAME {
        override fun getScreen(params: Any) : AbstractScreen {
            return GameScreen()
        }
    };

    abstract fun getScreen(params: Any): AbstractScreen
}