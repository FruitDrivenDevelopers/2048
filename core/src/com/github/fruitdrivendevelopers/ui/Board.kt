package com.github.fruitdrivendevelopers.ui

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.github.fruitdrivendevelopers.util.Direction

class Board(private var scoreChanged: ScoreChanged): Table() {
    private var score: Int = 0
    private val gameRows: Int = 4
    private val gameColumns: Int = 4
    private var array: Array<Array<Tile>> = Array(gameRows) {
        Array(gameColumns) {
            Tile()
        }
    }

    fun build() {
        for (row in 0 until gameRows) {
            row()
            for (col in 0 until gameColumns) {
                add(array[row][col]).size(100f).pad(15f)
            }
        }

        generateRandomTile()
        generateRandomTile()
    }

    override fun act(delta: Float) {
        array.forEach { it.forEach { it.act(delta) } }
        super.act(delta)
    }

    fun onSwipe(direction: Direction) {
        val moved = when(direction) {
            Direction.UP -> handleSwipeUp()
            Direction.DOWN -> handleSwipeDown()
            Direction.RIGHT -> handleSwipeRight()
            Direction.LEFT -> handleSwipeLeft()
        }

        if (moved) {
            generateRandomTile()
        }
    }

    private fun generateRandomTile() {
        val freeTiles: MutableList<Tile> = mutableListOf()

        array.forEach { arrayOfTiles ->
            arrayOfTiles.forEach { tile ->
                if (tile.value == 0) {
                    freeTiles += tile
                }
            }
        }

        val tile: Tile = freeTiles.random()
        tile.value = 2
        tile.animatePop()
    }

    private fun handleSwipeUp(): Boolean {
        var moved = false

        for (col in 0 until gameColumns) {
            for (row in 0 until gameRows) {
                if (transportTile(row, col, Direction.UP)) {
                    moved = true
                }
            }
        }
        return moved
    }

    //todo merge wywoływany kilka razy
    private fun handleSwipeLeft(): Boolean {
        var moved = false

        for (row in 0 until gameRows) {
            for (col in 0 until gameColumns) {
                if (transportTile(row, col, Direction.LEFT)) {
                    moved = true
                }
            }
        }

        return moved
    }

    private fun handleSwipeDown(): Boolean {
        var moved = false

        for (col in gameColumns-1 downTo 0) {
            for (row in gameRows-1 downTo 0) {
                if (transportTile(row, col, Direction.DOWN)) {
                    moved = true
                }
            }
        }
        return moved
    }

    private fun handleSwipeRight(): Boolean {
        var moved = false

        for (col in gameColumns-1 downTo 0) {
            for (row in gameRows-1 downTo 0) {
                if (transportTile(row, col, Direction.RIGHT)) {
                    moved = true
                }
            }
        }
        return moved
    }

    private fun transportTile(fromX: Int, fromY: Int, direction: Direction): Boolean {
        var moved = false

        if (array[fromX][fromY].value == 0) {
            return false
        }

        if (direction == Direction.RIGHT) {
            //transport tile
            var nextCol: Int = fromY
            while (nextCol != gameColumns - 1 && array[fromX][nextCol + 1].value == 0) {
                nextCol++
            }

            if (nextCol != fromY) {
                array[fromX][nextCol].value = array[fromX][fromY].value
                array[fromX][fromY].value = 0

                moved = true
            }

            //merge tiles
            if (mergeTiles(fromX, nextCol, direction)) {
                moved = true
            }
        } else if (direction == Direction.LEFT) {
            //transport tile
            var nextCol: Int = fromY
            while (nextCol != 0 && array[fromX][nextCol - 1].value == 0) {
                nextCol--
            }
            if (nextCol != fromY) {
                array[fromX][nextCol].value = array[fromX][fromY].value
                array[fromX][fromY].value = 0
                moved = true
            }

            //merge tiles
            if (mergeTiles(fromX, nextCol, direction)) {
                moved = true
            }
        } else if (direction == Direction.UP) {
            //transport tile
            var nextRow: Int = fromX
            while (nextRow != 0 && array[nextRow - 1][fromY].value == 0) {
                nextRow--
            }
            if (nextRow != fromX) {
                array[nextRow][fromY].value = array[fromX][fromY].value
                array[fromX][fromY].value = 0
                moved = true
            }

            //merge tiles
            if (mergeTiles(nextRow, fromY, direction)) {
                moved = true
            }
        } else if (direction == Direction.DOWN) {
            //transport tile
            var nextRow: Int = fromX
            while (nextRow != gameRows - 1 && array[nextRow + 1][fromY].value == 0) {
                nextRow++
            }
            if (nextRow != fromX) {
                array[nextRow][fromY].value = array[fromX][fromY].value
                array[fromX][fromY].value = 0
                moved = true
            }

            //merge tiles
            if (mergeTiles(nextRow, fromY, direction)) {
                moved = true
            }
        }

        return moved
    }

    private fun mergeTiles(fromX: Int, fromY: Int, direction: Direction): Boolean {
        var toX: Int = fromX
        var toY: Int = fromY

        when (direction) {
            Direction.UP -> {
                if (fromX == 0) return false
                toX--
            }
            Direction.RIGHT -> {
                if (fromY == gameColumns -1) return false
                toY++
            }
            Direction.LEFT -> {
                if (fromY == 0) return false
                toY--
            }
            Direction.DOWN -> {
                if (fromX == gameRows-1) return false
                toX++
            }
        }

        if (array[fromX][fromY].value == array[toX][toY].value) {

            score += array[toX][toY].value
            scoreChanged.scoreChanged(score)

            array[toX][toY].value *= 2
            array[fromX][fromY].value = 0

            return true
        }
        return false
    }

    interface ScoreChanged {
        fun scoreChanged(score: Int)
    }
}