import java.util.Scanner;

class Card {
    String suit;
    String rank;
    Card prev;
    Card next;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return rank + " de " + suit;
    }
}

class Hand {
    private Card first;
    private Card last;

    public Hand() {
        this.first = null;
        this.last = null;
    }

    public void addCard(String suit, String rank) {
        Card newCard = new Card(suit, rank);
        if (first == null) {
            first = newCard;
            last = newCard;
        } else {
            last.next = newCard;
            newCard.prev = last;
            last = newCard;
        }
        System.out.println("Adicionando carta: " + newCard);
    }

    public void removeCard(String suit, String rank) {
        Card current = first;
        while (current != null) {
            if (current.suit.equals(suit) && current.rank.equals(rank)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    first = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    last = current.prev;
                }
                System.out.println("Removendo carta: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Carta não encontrada: " + rank + " de " + suit);
    }

    public void showHand() {
        if (first == null) {
            System.out.println("A mão está vazia.");
            return;
        }
        Card current = first;
        System.out.println("Mão atual:");
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void reorganizeCard(String suit, String rank) {
        Card current = first;
        while (current != null) {
            if (current.suit.equals(suit) && current.rank.equals(rank)) {
                if (current != last) {
                    if (current.prev != null) {
                        current.prev.next = current.next;
                    }
                    if (current.next != null) {
                        current.next.prev = current.prev;
                    }
                    if (current == first) {
                        first = current.next;
                    }
                    current.next = null;
                    current.prev = last;
                    last.next = current;
                    last = current;
                }
                System.out.println("Carta reorganizado: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Carta não encontrada: " + rank + " de " + suit);
    }
}

public class CardGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hand hand = new Hand();

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("1. Adicionado carta");
            System.out.println("2. Remover carta");
            System.out.println("3. Mostrar mão");
            System.out.println("4. Reogarnizando cartas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Digite o naipe de cartas: ");
                    String suitAdd = scanner.nextLine();
                    System.out.print("Insira a classificação da carta: ");
                    String rankAdd = scanner.nextLine();
                    hand.addCard(suitAdd, rankAdd);
                    break;
                case 2:
                    System.out.print("Insira o naipe de cartas para remover: ");
                    String suitRemove = scanner.nextLine();
                    System.out.print("Insira a classificação da carta a ser removida: ");
                    String rankRemove = scanner.nextLine();
                    hand.removeCard(suitRemove, rankRemove);
                    break;
                case 3:
                    hand.showHand();
                    break;
                case 4:
                    System.out.print("Insira o naipe de cartas para reorganizar: ");
                    String suitReorg = scanner.nextLine();
                    System.out.print("Insira a classificação da carta a ser reorganizada: ");
                    String rankReorg = scanner.nextLine();
                    hand.reorganizeCard(suitReorg, rankReorg);
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