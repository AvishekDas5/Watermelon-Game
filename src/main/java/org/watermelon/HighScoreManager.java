package org.watermelon;

import com.almasb.fxgl.dsl.FXGL;

import java.io.*;
import java.util.*;

abstract class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "high_scores.txt";
    private static List<HighScore> highScores = new ArrayList<>();

    public static void loadHighScores() {
        try {
            File file = new File(HIGH_SCORE_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    highScores.add(new HighScore(name, score));
                }
                reader.close();
            }
        } catch (IOException e) {
            FXGL.getDialogService().showMessageBox(e.getMessage());
        }
    }

    public static void saveHighScore(String name, int score) {
        highScores.add(new HighScore(name, score));
        highScores.sort(Collections.reverseOrder());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE));
            for (HighScore highScore : highScores) {
                writer.write(highScore.getName() + "," + highScore.getScore());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            FXGL.getDialogService().showMessageBox(e.getMessage());
        }
    }

    private static class HighScore implements Comparable<HighScore> {
        private final String name;
        private final int score;

        public HighScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(HighScore other) {
            return Integer.compare(other.score, this.score);
        }
    }
}
