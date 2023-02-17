package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        printData(tasksData);
        System.out.println();
        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        ArrayList<Task> filteredList = filterTaskList("task", tasksData);
        printData(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        return (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
    }

    private static ArrayList<Task> filterTaskList(String filterString, ArrayList<Task> tasksData) {
        return (ArrayList<Task>) tasksData.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
    }

    public static void printData(ArrayList<Task> tasksData) {
        printTaskStream(tasksData.stream());
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        printTaskStream(tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription())));
    }

    public static void printTaskStream(Stream<Task> taskStream) {
        taskStream
                .forEach((task) -> {
                    System.out.println(task);
                });
    }
}
