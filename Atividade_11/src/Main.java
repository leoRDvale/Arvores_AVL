import java.util.*;

public class Main {
    public static void main(String[] args) {

        //Sorteie aleatoriamente 100 números de -500 a 500 e insira na ávore AVL. Verifique se a árvore produzida continua
        // sendo uma árvore AVL. Imprima, para cada nó o seu valor e o fator de balancoamento. Remova 20 números, verifique se
        // a árvore resultante continua sendo uma árvore AVL. Imprima, para cada nó o seu valor e o fator de balancoamento

        
                ArvoreAvl tree = new ArvoreAvl();
                Random rand = new Random();
                Set<Integer> numbers = new HashSet<>();

                while (numbers.size() < 100) {
                    numbers.add(rand.nextInt(1001) - 500);
                }

                
                for (int n : numbers) {
                    tree.insert(n);
                }

                System.out.println("Após inserções:");
                System.out.println("Ainda é uma árvore AVL? " + tree.eAVL()+"\n\n\n");
                tree.imprimeArvore();

                // Remover 20 números aleatórios dos inseridos
                List<Integer> numList = new ArrayList<>(numbers);
                Collections.shuffle(numList);
                for (int i = 0; i < 20; i++) {
                    tree.delete(numList.get(i));
                }

                System.out.println("\n\n\n***********************\nApós remoções:\n***********************");
                System.out.println("Ainda é uma árvore AVL? " + tree.eAVL()+"\n\n\n");
                tree.imprimeArvore();
            }
        }
        
