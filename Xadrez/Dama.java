/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: o desenho da dama é ‘D’, quando preta e ‘d’, quando branca. Para determinar se o movimento está correto, 
basta checar se ela se movimentou estritamente na horizontal ou vertical ( a variação das colunas ou linhas deve ser zero ) 
ou se movimentou na diagonal ( para determinar se um movimento é diagonal deve-se checar se o movimento horizontal é igual ao vertical ).

*/
package Xadrez;

public class Dama extends Peca{

    public Dama(boolean isPreto) {
        super(isPreto);
    }

    @Override
    public char desenho() {
        if (this.preto)
            return 'D';
        else
            return 'd';
    }

    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        //Se a peça se movimentou (não está na mesma posição)
        if ( !(colunaDestino == colunaOrigem && linhaDestino == linhaOrigem) ) {
            //Se a peça está se movimentando extritamente na vertical
            if (colunaDestino == colunaOrigem) 
                return true;
            //Se a peça está se movimentando extritamente na horizontal
            if (linhaDestino == linhaOrigem) 
                return true;
            //Se movimentou a mesma quantidade de colunas e linhas (diagonal)
            if ( Math.abs(linhaDestino - linhaOrigem) == Math.abs( (int)(colunaDestino - colunaOrigem) ) )
                return true;
        }
        return false;
    }         
}