import java.util.Scanner;

class UrlNode {
    String url;
    UrlNode next;

    public UrlNode(String url) {
        this.url = url;
        this.next = null;
    }

    @Override
    public String toString() {
        return url;
    }
}

class BrowserHistory {
    private UrlNode first;
    private UrlNode last;
    private int size;
    private final int maxSize;

    public BrowserHistory(int maxSize) {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.maxSize = maxSize;
    }

    public void visit(String url) {
        UrlNode newNode = new UrlNode(url);

        if (size == maxSize) {
            first = first.next;
            size--;
        }

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }

        size++;
        System.out.println("Visitado: " + url);
    }

    public void showHistory() {
        if (first == null) {
            System.out.println("Nenhum histórico de navegação.");
            return;
        }

        UrlNode current = first;
        System.out.println("Histórico de navegação:");
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void clearHistory() {
        first = null;
        last = null;
        size = 0;
        System.out.println("Histórico de navegação limpo.");
    }
}

public class BrowserSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BrowserHistory history = new BrowserHistory(5);

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Visite um novo URL");
            System.out.println("2. Mostrar histórico de navegação");
            System.out.println("3. Limpar histórico de navegação");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Insira URL para entrar:");
                    String url = scanner.nextLine();
                    history.visit(url);
                    break;
                case 2:
                    history.showHistory();
                    break;
                case 3:
                    history.clearHistory();
                    break;
                case 4:
                    System.out.println("Preparando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Tente novamente. Opção Inválida.");
                    break;
            }
        }
    }
}