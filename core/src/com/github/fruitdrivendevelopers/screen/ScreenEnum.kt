package com.github.fruitdrivendevelopers.screen

enum class ScreenEnum {
    MAIN_MENU {
        override fun getScreen(params: Any) = MainMenuScreen()
    },
    GAME {
        override fun getScreen(params: Any) = GameScreen()
    };

    abstract fun getScreen(params: Any): AbstractScreen
}