package com.github.fruitdrivendevelopers.util

import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2

class SwipeListener(private var swipeCallback: SwipeCallback) : GestureDetector.GestureListener {
    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        //exiting when fling is too slow
        if (Math.abs(velocityX) < 0.1f) {
            return false
        }
        if (Math.abs(velocityY) < 0.1f) {
            return false
        }

        val direction: Direction = if(Math.abs(velocityY) > Math.abs(velocityX)) {
            if (velocityY > 0) {
                Direction.DOWN
            } else {
                Direction.UP
            }
        } else {
            if (velocityX > 0) {
                Direction.RIGHT
            } else {
                Direction.LEFT
            }
        }

        swipeCallback.onSwipe(direction)
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean = false

    override fun zoom(initialDistance: Float, distance: Float): Boolean = false

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean = false

    override fun pinchStop() {}

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean = false

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean = false

    override fun longPress(x: Float, y: Float): Boolean = false

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean = false

    interface SwipeCallback{
        fun onSwipe(direction: Direction)
    }
}