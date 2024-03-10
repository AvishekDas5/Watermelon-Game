package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ScoreManager {

    private static IntegerProperty gameScore = new SimpleIntegerProperty(0);

    public static IntegerProperty getGameScoreProperty() {
        return gameScore;
    }

    public static int getGameScore() {
        return gameScore.get();
    }

    public static void setGameScore(int score) {
        gameScore.set(score);
    }

    public static void addToGameScore(int value) {
        gameScore.set(gameScore.get() + value);
    }

    public static void resetGameScore() {
        gameScore.set(0);
    }
}
