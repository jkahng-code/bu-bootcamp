import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    // Tracks how many lines were skipped because they were not valid integers
    private static int invalidLineCount = 0;

    public static void main(String[] args) {
        // Step 1: read scores from file
        String inputFile = "scores.txt";
        ArrayList<Integer> scores = readScores(inputFile);

        if (scores.isEmpty()) {
            System.out.println("No valid scores were found in " + inputFile + ". Nothing to report.");
            return;
        }

        // Step 2: calculate statistics
        double avg = calculateAverage(scores);

        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }

        // Step 3: write and print report
        writeReport(scores, avg, highest, lowest, "report.txt");
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.trim();

                if (trimmed.isEmpty()) {
                    continue;
                }

                try {
                    int value = Integer.parseInt(trimmed);
                    scores.add(value);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: skipping invalid entry on line " + lineNumber + ": \"" + trimmed + "\"");
                    invalidLineCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: could not find file \"" + filename + "\".");
        } catch (IOException e) {
            System.out.println("Error: problem reading file \"" + filename + "\": " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            StringBuilder report = new StringBuilder();

            report.append("=== Grade Analysis Report ===\n");
            report.append(String.format("Total scores processed: %d%n", scores.size()));
            report.append(String.format("Invalid lines skipped:  %d%n", invalidLineCount));
            report.append("\n");
            report.append(String.format("Average score: %.2f%n", avg));
            report.append(String.format("Highest score: %d%n", high));
            report.append(String.format("Lowest score:  %d%n", low));
            report.append("\n");
            report.append("Grade distribution:\n");
            report.append(String.format("  A (90-100):   %d%n", countA));
            report.append(String.format("  B (80-89):    %d%n", countB));
            report.append(String.format("  C (70-79):    %d%n", countC));
            report.append(String.format("  D (60-69):    %d%n", countD));
            report.append(String.format("  F (below 60): %d%n", countF));

            String reportText = report.toString();

            writer.write(reportText);
            System.out.print(reportText);

        } catch (IOException e) {
            System.out.println("Error: could not write report to \"" + outputFile + "\": " + e.getMessage());
        }
    }
}