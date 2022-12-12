/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: O desenho do bispo é ‘B’, quando preto e ‘b’, quando branco. 
Para determinar se o movimento está correto, 
basta checar se ele se movimentou na diagonal ( para determinar se um movimento é diagonal deve-se checar se o movimento horizontal é igual ao vertical ).

*/
package Xadrez;

public class Bispo extends Peca{

    public Bispo(boolean isPreto) {
        super(isPreto);
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        //Se a peça se movimentou (não está na mesma posição)
        if ( !(colunaDestino == colunaOrigem && linhaDestino == linhaOrigem) )
            //Se movimentou a mesma quantidade de colunas e linhas (diagonal)
            if ( Math.abs(linhaDestino - linhaOrigem) == Math.abs( (int)(colunaDestino - colunaOrigem) ) )
                return true;
        
        return false;
    }

    @Override
    public char desenho() {
        if (this.preto)
            return 'B';
        else
            return 'b';
    }
}
