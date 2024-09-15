import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process {
    String processName;
    int processId;

    public Process(String processName, int processId) {
        this.processName = processName;
        this.processId = processId;
    }

    @Override
    public String toString() {
        return "Processo ID " + processId + ": " + processName;
    }
}

class ProcessQueue {
    private Queue<Process> queue;
    private int nextId;

    public ProcessQueue() {
        this.queue = new LinkedList<>();
        this.nextId = 1;
    }

    public void addProcess(String processName) {
        Process newProcess = new Process(processName, nextId++);
        queue.add(newProcess);
        System.out.println("Adicionado para fila: " + newProcess);
    }

    public void executeNextProcess() {
        if (queue.isEmpty()) {
            System.out.println("Nenhum processo na fila.");
            return;
        }
        Process process = queue.poll();
        System.out.println("Executando: " + process);
    }

    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("A fila de processos está vazia.");
            return;
        }
        System.out.println("Fila de processos atual:");
        for (Process process : queue) {
            System.out.println(process);
        }
    }
}

public class ProcessManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProcessQueue processQueue = new ProcessQueue();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar processo a fila:");
            System.out.println("2. Executar próximo passo");
            System.out.println("3. Mostrar fila de processo:");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Digite o nome do processo: ");
                    String processName = scanner.nextLine();
                    processQueue.addProcess(processName);
                    break;
                case 2:
                    processQueue.executeNextProcess();
                    break;
                case 3:
                    processQueue.showQueue();
                    break;
                case 4:
                    System.out.println("Preparando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção Inválida. Tente novamente");
                    break;
            }
        }
    }
}