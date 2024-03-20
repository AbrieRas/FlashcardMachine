package org.example.Models;

/**
 * Represents a Flashcard object
 */
public class Flashcard {
    private final String question;
    private final String answer;
    private int retentionRating;

    /**
     * Creates a Flashcard instance, given input parameters
     *
     * @param question - flashcard question/front
     * @param answer - flashcard answer/back
     * @param retentionRating - rating based on retention difficulty (1 for hard, 7 for easy)
     */
    public Flashcard(String question, String answer, int retentionRating) {
        this.question = question;
        this.answer = answer;
        this.retentionRating = retentionRating;
    }

    /**
     * Provides flashcard question
     *
     * @return String - flashcard question/front
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Provides flashcard answer
     *
     * @return String - flashcard answer/back
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Provides flashcard retention difficulty
     *
     * @return String - flashcard retention difficulty
     */
    public int getRetentionRating() {
        return retentionRating;
    }

    /**
     * Sets flashcard specific retention difficulty within 1 to 7 range
     *
     * @param retentionRating - new flashcard retention difficulty
     */
    public void setRetentionRating(int retentionRating) {
        this.retentionRating = (retentionRating < 0) ? 0
                : Math.min(retentionRating, 7);
    }

    /**
     * Resets flashcard retention difficulty
     */
    public void resetRetentionRating() {
        this.retentionRating = 0;
    }
}
