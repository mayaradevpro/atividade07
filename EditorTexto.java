import java.util.Scanner;

class TextEditNode {
    String action;
    String previousText;
    TextEditNode next;

    public TextEditNode(String action, String previousText) {
        this.action = action;
        this.previousText = previousText;
        this.next = null;
    }
}

class TextEditorHistory {
    private TextEditNode top;
    private String currentText;

    public TextEditorHistory() {
        this.top = null;
        this.currentText = "";
    }

    public void performAction(String newText) {
        TextEditNode newNode = new TextEditNode(newText, currentText);
        newNode.next = top;
        top = newNode;
        currentText = newText;
        System.out.println("Ação executada: " + newText);
    }

    public void undo() {
        if (top == null) {
            System.out.println("Nenhuma ação para desfazer.");
            return;
        }

        currentText = top.previousText;
        System.out.println("Ação desfeita. Texto atual: " + currentText);
        top = top.next;
    }

    public void showCurrentText() {
        System.out.println("Texto atual: " + currentText);
    }

    public String getCurrentText() {
        return currentText;
    }
}

public class TextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditorHistory history = new TextEditorHistory();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Adicionar texto");
            System.out.println("2. Desfazer última ação");
            System.out.println("3. Mostrar texto atual");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Insira o texto para adicionar:");
                    String newText = scanner.nextLine();
                    history.performAction(history.getCurrentText() + newText);
                    break;
                case 2:
                    history.undo();
                    break;
                case 3:
                    history.showCurrentText();
                    break;
                case 4:
                    System.out.println("Preparando...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, por favor tente novamente.");
                    break;
            }
        }
    }
}