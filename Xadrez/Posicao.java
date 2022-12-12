/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        Esta classe tem um identificador de linha (de 1 à 8) e coluna (de ‘a’ à ‘h’), sua cor e se está ocupada 
        por uma peça ( se sim, devo saber qual ). Esta classe não possui nenhum método fora dos setters e getters, 
        mas estes são necessários, se uma peça se movimentar deve-se atualizar o atributo que identifica 
        a presença de peças, por exemplo.
        
        Mais especificamente:
        -    getLinha: retorna a linha da posição.
        -    getColuna: retorna a coluna da posição.
        -    isPreto: retorna a cor da peça que ocupa aquela posição.
        -    setPeca: Determina a peça que ocupa aquela posição.
        -    getPeca: Retorna a peça que ocupa determinada posição.
*/
package Xadrez;

public class Posicao {
    private boolean preto;
    private int linha;
    private char coluna;
    private Peca peca;

    public Posicao(int linha, char coluna, boolean preto, Peca peca) {
        this.linha = linha;
        this.coluna = coluna;
        this.peca = peca;
        this.preto = preto;
    }
   
    public int getLinha() {
        return this.linha;
    }

    public boolean isPreto() {
        return this.preto;
    }

    public char getColuna() {
        return this.coluna;
    }

    public Peca getPeca() {
        return this.peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
