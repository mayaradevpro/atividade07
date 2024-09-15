import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class PrintJob {
    String documentName;
    int jobId;

    public PrintJob(String documentName, int jobId) {
        this.documentName = documentName;
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "Impressão na fila " + jobId + ": " + documentName;
    }
}

class PrintQueue {
    private Queue<PrintJob> queue;
    private int nextId;

    public PrintQueue() {
        this.queue = new LinkedList<>();
        this.nextId = 1;
    }

    public void addJob(String documentName) {
        PrintJob newJob = new PrintJob(documentName, nextId++);
        queue.add(newJob);
        System.out.println("Adicionado a fila: " + newJob);
    }

    public void processNextJob() {
        if (queue.isEmpty()) {
            System.out.println("Nenhum trabalho de impressão na fila.");
            return;
        }
        PrintJob job = queue.poll();
        System.out.println("Processando: " + job);
    }

    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("A fila de impressão está vazia.");
            return;
        }
        System.out.println("Fila de impressão atual:");
        for (PrintJob job : queue) {
            System.out.println(job);
        }
    }
}

public class PrintManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintQueue printQueue = new PrintQueue();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar trabalho de impressão à fila");
            System.out.println("2. Processar próximo trabalho de impressão");
            System.out.println("3. Mostrar fila de impressão");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Insira o nome do documento: ");
                    String documentName = scanner.nextLine();
                    printQueue.addJob(documentName);
                    break;
                case 2:
                    printQueue.processNextJob();
                    break;
                case 3:
                    printQueue.showQueue();
                    break;
                case 4:
                    System.out.println("Preparando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção Inválida. Tente novamente.");
                    break;
            }
        }
    }
}