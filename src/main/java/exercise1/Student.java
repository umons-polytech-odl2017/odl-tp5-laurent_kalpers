package exercise1;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    String name;
    String registrationNumber;
    private Map <String,Integer> courses=new HashMap<>();

    public Student(String name, String registrationNumber,OptionalInt score) {
        if (name==null || registrationNumber==null)
        {
            throw new NullPointerException();
        }
        this.name = name;
        this.registrationNumber = registrationNumber;
    }

    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {
        if (course == null)
            throw new NullPointerException();
        else {
            if (score <= 20 && score >= 0) {
                courses.put(course, score);
            }
            else throw new IllegalArgumentException();
        }


    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public OptionalInt getScore(String course) {
        Integer nullableScore = courses.get(course);
        return nullableScore != null ? OptionalInt.of(nullableScore) : OptionalInt.empty();
    }

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {
        return courses.values().stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {
        return courses.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .findFirst();
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        return 0;
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        return courses.entrySet().stream()
            .filter(entry -> entry.getValue()<12)
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        return averageScore() >= 12 && failedCourses().size() < 3;
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() { return null; }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
