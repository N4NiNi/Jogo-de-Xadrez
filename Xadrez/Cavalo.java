/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: O desenho do cavalo é ‘C’, quando preto e ‘c’, quando branco. 
Para determinar se o movimento está correto, 
basta checar se ele movimentou duas casas na horizontal e uma na vertical; alternativamente, uma casa na horizontal e duas na vertical.

*/
package Xadrez;

public class Cavalo extends Peca{

    public Cavalo(boolean isPreto) {
        super(isPreto);
    }
  
    @Override
    public char desenho() {
        if (this.preto)
            return 'C';
        else
            return 'c';
    }


    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        //Se a peça se movimentou (não está na mesma posição)
        if ( !(colunaDestino == colunaOrigem && linhaDestino == linhaOrigem) ) {            
            //Se movimentando extritamente 1 casa na horizontal e 2 casas na vertical
            if ( Math.abs( (int)(colunaDestino - colunaOrigem) ) == 1 && Math.abs(linhaDestino - linhaOrigem) == 2 )
                return true;
            //Se movimenta extritamente 1 casa na vertical e 2 casas na horizontal
            if ( Math.abs( (int)(colunaDestino - colunaOrigem) ) == 2 && Math.abs(linhaDestino - linhaOrigem) == 1 )
                return true;
        } 
        return false;
    }
    
}