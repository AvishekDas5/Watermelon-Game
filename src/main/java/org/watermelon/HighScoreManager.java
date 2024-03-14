package org.watermelon;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;
import static java.util.Collections.reverseOrder;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "high_scores.txt";
    private static final int MAX_SCORES_TO_KEEP = 5;

    private static List<HighScore> highScores = new ArrayList<>();

    public static void loadHighScores() {
        try {
            File file = new File(HIGH_SCORE_FILE);
            int rank = 1;
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    HighScore newScore = new HighScore(name, score);

                    // Add new score to the list if there's space or if it's greater than the lowest score
                    if (highScores.size() < MAX_SCORES_TO_KEEP || newScore.compareTo(highScores.get(MAX_SCORES_TO_KEEP - 1)) > 0) {
                        highScores.add(newScore);

                        // Sort the list if needed
                        Collections.sort(highScores);

                        // Keep only the top 5 scores
                        highScores = highScores.subList(0, Math.min(highScores.size(), MAX_SCORES_TO_KEEP));
                    }
                }
                reader.close();

                // Display the top 5 scores
                Text highScore = getUIFactoryService().newText("", Color.WHITE, 16);
                highScore.setTranslateX(50);
                highScore.setTranslateY(500);
                highScore.setStroke(Color.BLACK);
                highScore.setStrokeWidth(1);

                addUINode(highScore);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Rank    HighScores    Score\n");
                for (HighScore item : highScores) {
                    stringBuilder.append(rank++).append("\t\t").append(item.getName()).append("   ").append(item.getScore()).append("\n");
                }
                highScore.setText(stringBuilder.toString());
            }
        } catch (IOException e) {
            getDialogService().showMessageBox(e.getMessage());
        }
    }

    public static int getHighScore(){
        return highScores.get(0).getScore();
    }

    public static void saveHighScore(String name, int score) {
        highScores.add(new HighScore(name, score));

        // Sort the high scores
        highScores.sort(reverseOrder());

        // Trim the list to keep only the top 5 scores
        highScores = highScores.subList(0, Math.min(highScores.size(), MAX_SCORES_TO_KEEP));

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE));
            for (HighScore highScore : highScores) {
                writer.write(highScore.getName() + "," + highScore.getScore());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            getDialogService().showMessageBox(e.getMessage());
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
