/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        Esta classe representa a peça "peão" do xadrez. O peão pode se mover duas casas quando é seu primeiro 
        movimento e pode se mover na diagonal se for atacar outra peça, desta forma a classe peão deve ter 
        um atributo que determina se é o seu primeiro movimento. Sabendo disso, para determinar se o movimento 
        está correto, basta checar se ele movimentou estritamente 2 casas na vertical ( se for seu primeiro 
        movimento ) ou 1 casa na vertical e se for preto este movimento deve ser para baixo, se for branco deve 
        ser para cima. O desenho do peão é 'P' se for preto e 'p' se for branco.
*/
package Xadrez;

public class Peao extends Peca{
    private boolean firstmov; //sendo true Primeiro movimento

    public Peao(boolean isPreto) {
        super(isPreto);
        this.firstmov = true;   
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if(this.firstmov){
            //Se mover uma casa para cima (Peça branca)
            if (linhaDestino - linhaOrigem == 1) {
                if (Math.abs(colunaDestino - colunaOrigem) == 1 || colunaDestino == colunaOrigem) {
                    return !this.preto; //Se for preto é falso --- Se for branco é verdadeiro
                } else {
                    return false;
                }
            } 
            //Se mover duas casas para cima (Peça branca)
            else if (linhaDestino - linhaOrigem == 2) {
                if (colunaDestino == colunaOrigem) {
                    return !this.preto; //Se for preto é falso --- Se for branco é verdadeiro
                } else {
                    return false;
                }
            }

            //Se mover uma casa para baixo (Peça preta)
            if (linhaDestino - linhaOrigem == -1) {
                if (Math.abs(colunaDestino - colunaOrigem) == 1 || colunaDestino == colunaOrigem) {
                    return this.preto; //Se for preto é verdadeiro --- Se for branco é falso
                } else {
                    return false;
                }
            } 
            //Se mover duas casas para baixo (Peça preta)
            else if (linhaDestino - linhaOrigem == -2) {
                if (colunaDestino == colunaOrigem) {
                    return this.preto; //Se for preto é verdadeiro --- Se for branco é falso
                } else {
                    return false;
                }
            }
            
            //Se mover mais do que duas casas
            return false;

        } else {
            //Se mover uma casa para cima (Peça branca)
            if (linhaDestino - linhaOrigem == 1) {
                if (Math.abs(colunaDestino - colunaOrigem) == 1 || colunaDestino == colunaOrigem) {
                    return !this.preto; //Se for preto é falso --- Se for branco é verdadeiro
                } else {
                    return false;
                }
            }
            //Se mover uma casa para baixo (Peça preta)
            if (linhaDestino - linhaOrigem == -1) {
                if (Math.abs(colunaDestino - colunaOrigem) == 1 || colunaDestino == colunaOrigem) {
                    return this.preto; //Se for preto é verdadeiro --- Se for branco é falso
                } else {
                    return false;
                }
            }
            //Se mover mais do que uma casa
            return false;
        }
    }
    
    @Override
    public char desenho() {
        if (this.preto)
            return 'P';
        else
            return 'p';
    }
}
