import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cafs {

    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static void completeTask(ArrayList<Task> tasks, int index) {
        index--; // index starts from 0, unlike list
        if ( (index < tasks.size()) || (index > 0)) { // check if out of bounce
            Task t = tasks.get(index);
            if (t.getStatus()) { // check if already completed
                System.out.println("Task already completed!\n");
            } else {
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription() + "\n");
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                System.out.println("[" + currentTask.getStatusIcon() + "] " + count + ". " + currentTask.getDescription());
                count++;
            }
        }
        System.out.println("");
    }

    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("New: add a new task \nusage: new\n");
        System.out.println("");
    }

    private static void printWelcomeMessage() {
        String logo =   "        _..._                                        \n" +
                "    .-'_..._''.                                     \n" +
                "  .' .'      '.\\                                    \n" +
                " / .'                         _.._                  \n" +
                ". '                         .' .._|                 \n" +
                "| |                 __      | '                     \n" +
                "| |              .:--.'.  __| |__     _             \n" +
                ". '             / |   \\ ||__   __|  .' |            \n" +
                " \\ '.          .`\" __ | |   | |    .   | /          \n" +
                "  '. `._____.-'/ .'.''| |   | |  .'.'| |//          \n" +
                "    `-.______ / / /   | |_  | |.'.'.-'  /           \n" +
                "             `  \\ \\._,\\ '/  | |.'   \\_.'            \n" +
                "                 `--'  `\"   |_|           \n";

        System.out.println("\n" + logo + "\nCalendar Application for Students\n");
        System.out.println("type \"help\" for list of commands");
        System.out.println("____________________________________________________________\n\nCurrent time: ");
        getDateTime();
        System.out.println("____________________________________________________________");
    }

    public static void addTask(ArrayList<Task> tasks, String newTask) {
        Task t = new Task(newTask);
        tasks.add(t);
        System.out.println("added: " + newTask + "\n");
    }

    public static void main(String[] args) {

        printWelcomeMessage();

        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")){
            //System.out.println("LISA: "+ userCommand);

            String[] words = userCommand.split(" ");
            int length = words.length;

            if (length == 1) { //for user commands
                switch (words[0]) {
                    case "list":
                        printList(tasks);
                        break;
                    case "done":
                        System.out.println("What is the index of the completed task?");
                        int index = input.nextInt();
                        input.nextLine();
                        completeTask(tasks, index);
                        break;
                    case "new":
                        System.out.println("Please input your new task:");
                        String newTask = input.nextLine();
                        addTask(tasks, newTask);
                        break;
                    case "help":
                        printHelp();
                        break;
                    default:
                        System.out.println("Command not recognised \n");
                }
            } else {
                System.out.println("Command not recognised, please refer to the help guide.");
            }

            userCommand = input.nextLine();
        }

        System.out.println("LISA: Bye, hope to see you again!");
    }


}
