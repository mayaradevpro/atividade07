import java.util.Scanner;

class EditNode {
    String action;
    EditNode prev;
    EditNode next;

    public EditNode(String action) {
        this.action = action;
        this.prev = null;
        this.next = null;
    }
}

class TextEditorHistory {
    private EditNode current;
    private EditNode head;
    private EditNode redoHead;

    public TextEditorHistory() {
        this.head = new EditNode("");
        this.current = head;
        this.redoHead = null;
    }

    public void performAction(String newText) {
        EditNode newNode = new EditNode(newText);

        current.next = newNode;
        newNode.prev = current;

        current = newNode;

        redoHead = null;

        System.out.println("Ação realizada: " + newText);
    }

    public void undo() {
        if (current == head) {
            System.out.println("Nenhuma ação para desfazer.");
            return;
        }

        EditNode undoNode = new EditNode(current.action);
        undoNode.next = redoHead;
        redoHead = undoNode;

        current = current.prev;

        System.out.println("Desfazer ação. Texto atual: " + (current != null ? current.action : ""));
    }

    public void redo() {
        if (redoHead == null) {
            System.out.println("Nenhuma ação para refazer.");
            return;
        }

        EditNode redoNode = redoHead;
        redoHead = redoHead.next;

        current.next = redoNode;
        redoNode.prev = current;
        current = redoNode;

        System.out.println("Ação de refazer. Texto atual: " + current.action);
    }

    public void showCurrentText() {
        System.out.println("Texto atual: " + current.action);
    }
}

public class TextEditorDoubly {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditorHistory history = new TextEditorHistory();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionar texto");
            System.out.println("2. Desfazer última ação");
            System.out.println("3. Refazer última ação desfeita");
            System.out.println("4. Mostrar texto atual");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Insira o texto para adicionar: ");
                    String newText = scanner.nextLine();
                    history.performAction(newText);
                    break;
                case 2:
                    history.undo();
                    break;
                case 3:
                    history.redo();
                    break;
                case 4:
                    history.showCurrentText();
                    break;
                case 5:
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