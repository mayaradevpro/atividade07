import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Customer {
    String name;
    int id;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ciente " + id + ": " + name;
    }
}

class BankQueue {
    private Queue<Customer> queue;
    private int nextId;

    public BankQueue() {
        this.queue = new LinkedList<>();
        this.nextId = 1;
    }

    public void addCustomer(String name) {
        Customer newCustomer = new Customer(name, nextId++);
        queue.add(newCustomer);
        System.out.println("Adionando a fila: " + newCustomer);
    }

    public void attendNextCustomer() {
        if (queue.isEmpty()) {
            System.out.println("Nenhum cliente na fila.");
            return;
        }
        Customer customer = queue.poll();
        System.out.println("Atendendo a: " + customer);
    }

    public void showQueue() {
        if (queue.isEmpty()) {
            System.out.println("A fila está vazia.");
            return;
        }
        System.out.println("Atender o próximo cliente");
        for (Customer customer : queue) {
            System.out.println(customer);
        }
    }
}

public class BankSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankQueue bankQueue = new BankQueue();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar o próximo cliente na fila");
            System.out.println("2. Atender o próximo cliente");
            System.out.println("3. Mostrar fila");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String name = scanner.nextLine();
                    bankQueue.addCustomer(name);
                    break;
                case 2:
                    bankQueue.attendNextCustomer();
                    break;
                case 3:
                    bankQueue.showQueue();
                    break;
                case 4:
                    System.out.println("Preparando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        }
    }
}