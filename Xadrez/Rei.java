/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        Esta classe representa a peça "rei" do xadrez. Para determinar se o movimento do rei está correto, basta checar
        se ele se movimentou estritamente uma casa na horizontal ou vertical ou se ele movimentou uma casa na 
        diagonal ( para determinar se um movimento é diagonal deve-se checar se o movimento horizontal é igual ao vertical ).
        O desenho do rei é 'R' se for preto e 'r'se for branco.
*/
package Xadrez;

public class Rei extends Peca{

    public Rei(boolean isPreto) {
        super(isPreto);
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if((colunaOrigem == colunaDestino) && (linhaOrigem+1 == linhaDestino)) {//vertical frente
            return true;
        }
        else if((colunaOrigem+1 == colunaDestino) && (linhaOrigem == linhaDestino)) {//horizontal direita
            return true;
        }
        else if((colunaOrigem-1 == colunaDestino) && (linhaOrigem == linhaDestino)) {//horizontal esquerda
            return true;
        }
        else if((colunaOrigem == colunaDestino) && (linhaOrigem-1 == linhaDestino)) {//vertical atras
            return true;
        }
        else if(linhaOrigem == linhaDestino-1 && colunaOrigem == colunaDestino+1) {//diagonal superior direita
            return true;
        }
        else if(linhaOrigem == linhaDestino+1 && colunaOrigem == colunaDestino-1) {//diagonal inferior esquerda
            return true;
        }
        else if(linhaOrigem == linhaDestino+1 && colunaOrigem == colunaDestino+1) {//diagonal inferiror direita
            return true;
        }
        else if(linhaOrigem == linhaDestino-1 && colunaOrigem == colunaDestino-1) {//diagonal superior esquerda
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public char desenho() {
        if (this.preto)
            return 'R';
        else
            return 'r';
    }
}
