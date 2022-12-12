/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: Esta classe deve guardar como atributo o nome do jogador e a cor que o jogador controla. 
A classe deve ter acesso às 16 peças que estão sob o seu controle, através desses atributos existem métodos para retornar o nome do jogador, a cor de suas peças e seu rei.

*/
package Xadrez;

public class Jogador {  
    private final String nome;
    private final boolean preto;
    private final Peca[] pecas;

    public Jogador(String name, boolean preto, Peca[] pecas) {
        this.nome = name;
        this.preto = preto;
        this.pecas = pecas;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isPreto() {
        return this.preto;
    }
    
    public Peca getRei() {
        if (this.preto) {
            return pecas[4];
        } else {
            return pecas[12];
        }
    }

    public Peca[] getPecas() {
        return this.pecas;
    }
}
