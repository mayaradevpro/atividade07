import java.util.Scanner;

class Task {
    String description;
    boolean completed;
    Task next;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.next = null;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return description + (completed ? " (Completado)" : " (pendente)");
    }
}

class TaskList {
    private Task first;

    public TaskList() {
        this.first = null;
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        if (first == null) {
            first = newTask;
        } else {
            Task current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Tarefa adicionado: " + description);
    }

    public void removeTask(String description) {
        if (first == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        if (first.description.equals(description)) {
            first = first.next;
            System.out.println("Tarefa removida: " + description);
            return;
        }

        Task current = first;
        while (current.next != null && !current.next.description.equals(description)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Tarefa removida: " + description);
        } else {
            System.out.println("Tarefa não encontrada: " + description);
        }
    }

    public void markTaskAsCompleted(String description) {
        Task current = first;
        while (current != null) {
            if (current.description.equals(description)) {
                current.markAsCompleted();
                System.out.println("Tarefa completa: " + description);
                return;
            }
            current = current.next;
        }
        System.out.println("Tarefa não encontrada: " + description);
    }

    public void displayTasks() {
        if (first == null) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }

        Task current = first;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
}

public class TaskManager {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nGerenciador de tarefas");
            System.out.println("1. Adicionar tarefas");
            System.out.println("2. Remover tarefas");
            System.out.println("3. Marcar tarefa como concluída");
            System.out.println("4. Mostrar tarefas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Insira a descrição da tarefa:");
                    String description = scanner.nextLine();
                    list.addTask(description);
                    break;
                case 2:
                    System.out.print("Insira a descrição da tarefa para remover:");
                    String removeDescription = scanner.nextLine();
                    list.removeTask(removeDescription);
                    break;
                case 3:
                    System.out.print("Insira a descrição da tarefa para marcar como concluída:");
                    String completeDescription = scanner.nextLine();
                    list.markTaskAsCompleted(completeDescription);
                    break;
                case 4:
                    System.out.println("\nLista de Tarefa");
                    list.displayTasks();
                    break;
                case 5:
                    running = false;
                    System.out.println("Saindo do Gerenciador de Tarefas.");
                    break;
                default:
                    System.out.println("Escolha inválida. Tente outra");
            }
        }

        scanner.close();
    }
}