/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        Esta classe representa a peça "torre" do xadrez. o desenho da torre é ‘T’, quando preta e ‘t’, quando branca. 
        Para determinar se o movimento está correto, basta checar se ele se movimentou estritamente na horizontal 
        ou vertical ( ou seja, a variação das colunas ou linhas deve ser zero ).
*/
package Xadrez;

public class Torre extends Peca{

    public Torre(boolean isPreto) {
        super(isPreto);
    }
    
    @Override
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        if(colunaDestino == colunaOrigem && linhaOrigem != linhaDestino) {//andar vertical
            return true;
        } 
        else if(colunaDestino != colunaOrigem && linhaOrigem == linhaDestino) {//andar horizontal
            return true;
        } 
        else {
            return false;
        }
    }
        
    @Override
    public char desenho() {
        if (this.preto)
            return 'T';
        else 
            return 't';
    }       
}
