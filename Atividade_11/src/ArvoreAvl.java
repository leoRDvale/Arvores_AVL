import javax.swing.*;

public class ArvoreAvl {
    class No {
        int chave, altura;
        No esquerda, direita;

        No(int d) {
            chave = d;
            altura = 1;
        }

        int fatorbalancoamento() {
            return altura(esquerda) - altura(direita);
        }
    }

    No raiz;


    int altura(No N) {
        return N == null ? 0 : N.altura;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;
        x.direita = y;
        y.esquerda = T2;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        return x;
    }

    No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;
        y.esquerda = x;
        x.direita = T2;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        return y;
    }

    int getBalanco(No N) {
        return N == null ? 0 : altura(N.esquerda) - altura(N.direita);
    }

    No insert(No no, int chave) {
        if (no == null)
            return new No(chave);
        if (chave < no.chave)
            no.esquerda = insert(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = insert(no.direita, chave);
        else
            return no; // Duplicados não são permitidos

        no.altura = 1 + max(altura(no.esquerda), altura(no.direita));
        int balanco = getBalanco(no);

        // balancoamento
        if (balanco > 1 && chave < no.esquerda.chave)
            return rotacaoDireita(no);
        if (balanco < -1 && chave > no.direita.chave)
            return rotacaoEsquerda(no);
        if (balanco > 1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (balanco < -1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    No minValueno(No no) {
        No current = no;
        while (current.esquerda != null)
            current = current.esquerda;
        return current;
    }

    No delete(No raiz, int chave) {
        if (raiz == null)
            return raiz;
        if (chave < raiz.chave)
            raiz.esquerda = delete(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = delete(raiz.direita, chave);
        else {
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                No temp = (raiz.esquerda != null) ? raiz.esquerda : raiz.direita;
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;
            } else {
                No temp = minValueno(raiz.direita);
                raiz.chave = temp.chave;
                raiz.direita = delete(raiz.direita, temp.chave);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        int balanco = getBalanco(raiz);

        // balancoamento
        if (balanco > 1 && getBalanco(raiz.esquerda) >= 0)
            return rotacaoDireita(raiz);
        if (balanco > 1 && getBalanco(raiz.esquerda) < 0) {
            raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
            return rotacaoDireita(raiz);
        }
        if (balanco < -1 && getBalanco(raiz.direita) <= 0)
            return rotacaoEsquerda(raiz);
        if (balanco < -1 && getBalanco(raiz.direita) > 0) {
            raiz.direita = rotacaoDireita(raiz.direita);
            return rotacaoEsquerda(raiz);
        }

        return raiz;
    }

    // Impressão em ordem com fator de balancoamento
    void printInOrder(No no) {
        if (no != null) {
            printInOrder(no.esquerda);
            System.out.println("Valor: " + no.chave + ", Fator de balancoamento: " + no.fatorbalancoamento());
            printInOrder(no.direita);
        }
    }

    boolean eAVL(No no) {
        if (no == null)
            return true;
        int balanco = getBalanco(no);
        if (Math.abs(balanco) > 1)
            return false;
        return eAVL(no.esquerda) && eAVL(no.direita);
    }


    public void insert(int chave) {
        raiz = insert(raiz, chave);
    }

    public void delete(int chave) {
        raiz = delete(raiz, chave);
    }

    public void imprimeArvore() {
        printInOrder(raiz);
    }

    public boolean eAVL() {
        return eAVL(raiz);
    }
}

