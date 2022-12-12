/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        A classe Peça é uma classe pai para todas peças do Jogo, onde uma peça específica herda atributos
        e funções da classe. Esta classe possui 4 atributos sendo eles: “preto” uma variável do tipo boolean 
        para determinar a cor da peça, “vivo” também booleana para saber se a peça está viva no jogo, “linha” 
        e “coluna” do tipo int e char, respectivamente para localizar a posição da peça específica no tabuleiro. 
        Também contamos com 4 métodos, sendo dois deles abstratos: “desenho()” que retorna um char que representa 
        a peça e “checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino)” 
        que verifica se o movimento de uma determinada peça está correta. As outras duas são: isVivo(), 
        que retorna se tal peça ainda está viva no tabuleiro e morrer() que seta a variável vivo para falso.   
*/
package Xadrez;

public abstract class Peca {
    protected final boolean preto;
    protected boolean vivo;
    protected int linha;
    protected char coluna;

    public Peca(boolean isPreto) {
        this.preto = isPreto;
        this.vivo = true;
    }

    public abstract char desenho();
    public abstract boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);

    public boolean isVivo() {
        return this.vivo;
    }

    public void morrer() {
        this.vivo = false;
    }
}
