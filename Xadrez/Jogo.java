/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: É esta classe que cria o tabuleiro, as peças, os jogadores e controla todo sobre o andamento do jogo, 
portanto ela tem atributos que determinam de quem é o turno e qual é o estado do jogo (em andamento, xeque, xeque-mate, etc)
e decide quando o turno e o estado são trocados. Esta classe também recebe a entrada do jogador através do método fazerJogada() 
e deve decidir se é uma jogada válida ou não, caso seja correta, deverá atualizar o tabuleiro e as peças aplicando a jogada.


*/
package Xadrez;

import java.util.Scanner;

public class Jogo {
    private Tabuleiro board;
    private Peca[] pecasPretas;
    private Peca[] pecasBrancas;
    private Jogador player1;
    private Jogador player2;
    private char status;
    private boolean turnoP1;
    private Jogador xeque;
    private Jogador vencedor;

    public Jogo(String nomeP1, String corP1, String nomeP2, String corP2) {
        boolean p1IsPreto = false;

        if (corP1.equalsIgnoreCase("preto"))
            p1IsPreto = true;
                
        if ( (!p1IsPreto && corP2.equalsIgnoreCase("preto")) || (p1IsPreto && corP2.equalsIgnoreCase("Branco")) ) {
            this.pecasPretas = new Peca[16];
            this.pecasBrancas = new Peca[16];
            this.criarPecas();
            this.criarTabuleiro();

            if (p1IsPreto) {
                this.player1 = new Jogador(nomeP1, p1IsPreto, this.pecasPretas);
                this.player2 = new Jogador(nomeP2, !p1IsPreto, this.pecasBrancas);
            } else {
                this.player1 = new Jogador(nomeP1, p1IsPreto, this.pecasBrancas);
                this.player2 = new Jogador(nomeP2, !p1IsPreto, this.pecasPretas);
            }

            this.emJogo();
            this.xeque = this.vencedor = null;

            // Se p1IsPreto for TRUE o jogador1 é Preto => O jogador1 NÃO começa
            // Se     ^     for FALSE o jogador1 é Branco => O jogador1 COMEÇA
            this.turnoP1 = !p1IsPreto; 
        } else {
            this.status = 'c';
            //Erro os jogadores pediram a mesma cor
        }
    }

    private void criarTabuleiro() {
        if (this.pecasBrancas != null && this.pecasPretas != null && this.board == null) {
            this.board = new Tabuleiro();
            this.board.iniciaTabuleiro(this.pecasPretas, this.pecasBrancas);
        }
    }

    private void criarPecas() {
        if (this.pecasBrancas != null && this.pecasPretas != null && this.pecasBrancas[0] == null && this.pecasPretas[0] == null) {
            //Peças pretas
            this.pecasPretas[0] = new Torre(true);
            this.pecasPretas[1] = new Cavalo(true);
            this.pecasPretas[2] = new Bispo(true);
            this.pecasPretas[3] = new Dama(true);
            this.pecasPretas[4] = new Rei(true);
            this.pecasPretas[5] = new Bispo(true);
            this.pecasPretas[6] = new Cavalo(true);
            this.pecasPretas[7] = new Torre(true);
            for (int i = 8; i < 16; i++)
                this.pecasPretas[i] = new Peao(true);
            //Peças brancas
            for (int i = 0; i < 8; i++)
                this.pecasBrancas[i] = new Peao(false);
            this.pecasBrancas[8] = new Torre(false);
            this.pecasBrancas[9] = new Cavalo(false);
            this.pecasBrancas[10] = new Bispo(false);
            this.pecasBrancas[11] = new Dama(false);
            this.pecasBrancas[12] = new Rei(false);
            this.pecasBrancas[13] = new Bispo(false);
            this.pecasBrancas[14] = new Cavalo(false);
            this.pecasBrancas[15] = new Torre(false);
        }
    }

    public boolean fazerJogada(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino) {
        //Se o tabuleiro não estiver criado
        if (this.board == null)
            return false;

        //Se for o turno do P1
        if (this.turnoP1) {
            //Se a peça na origem for de cor diferente do P1
            if (this.player1.isPreto() != this.board.isPosPecaPreto(linhaOrigem, colunaOrigem))
                return false;
        } 
        //Se for o turno do P2
        else {
            //Se a peça na origem for de cor diferente do P2
            if (this.player2.isPreto() != this.board.isPosPecaPreto(linhaOrigem, colunaOrigem))
                return false;
        }

        //Se o movimento for inválido
        if (!this.board.validaMovimento(linhaDestino, colunaDestino, linhaOrigem, colunaOrigem))
            return false;
            
        //Cheaca se o movimento coloca o próprio jogador em cheque
        int resultado;

        if (this.turnoP1) {
            Peca morta = this.board.atualizaTabuleiro(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino);
            resultado = this.board.validaXeque(player1.getRei(), player2.getPecas(), player1.getPecas());
            if (resultado == 1 || resultado == 2) {
                this.board.reverterMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, morta);
                return false;
            }

            resultado = this.board.validaXeque(player2.getRei(), player1.getPecas(), player2.getPecas());
            if (resultado == 1)
                this.xeque(player2);
            else if (resultado == 2){
                this.xequeMate(player1);
                return true;
            } else {
                this.emJogo();
            }
        } else {
            Peca morta = this.board.atualizaTabuleiro(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino);
            resultado = this.board.validaXeque(player2.getRei(), player1.getPecas(), player2.getPecas());
            if (resultado == 1 || resultado == 2) {
                this.board.reverterMovimento(linhaOrigem, linhaDestino, colunaOrigem, colunaDestino, morta);
                return false;
            }

            resultado = this.board.validaXeque(player1.getRei(), player2.getPecas(), player1.getPecas());
            if (resultado == 1)
                this.xeque(player1);
            else if (resultado == 2) {
                this.xequeMate(player2);
                return true;
            } else {
                this.emJogo();
            }
        }

        this.trocaTurno();
        return true;
    }

    private void emJogo() {
        this.xeque = null; //setar xeque como nulo
        this.status = 'j'; //em jogo
    }

    public void print() {
        this.board.imprimeTabuleiro(); //imprime tabuleiro
    }

    public boolean getTurno() {
        return this.turnoP1; //retorna de quem é a vez
    }

    private void trocaTurno() {
        this.turnoP1 = !this.turnoP1; // realiza a troca do turno
    }

    private void xeque(Jogador player) { //seta o jogador em estado de xeque
        this.xeque = player;
        this.status = 'x';
    }

    private void xequeMate(Jogador vencedor) { //O status do jogo fica em xeque mate, ou seja o jogo acabou.
        this.vencedor = vencedor;
        this.status = 'X';
    }

    public char getStatus() {//retorna o estado atual da partida
        return this.status;
    }

    public String getNomPlayer1() {//retorna o nome do jogador 1
        return player1.getNome();
    }

    public boolean getCorPlayer1() {//retorna a cor das peças do jogador 1
        return this.player1.isPreto();
    }

    public boolean getCorPlayer2() {//retorna a cor das peças do jogador 2
        return this.player2.isPreto();
    }

    public String getNomPlayer2() {//retorna o nome do jogador 2
        return player2.getNome();
    }
    
    public void comecarJogo() { //inicia o jogo e a cada jogada é printado o estado do jogo, de quem é a vez e qual movimento ele deseja realizar
        if (this.board != null) {
            System.out.println("O jogo começou!");
            Scanner scan = new Scanner(System.in);

            this.print();

            //Enquanto não estiver em xeque-mate
            while (this.status != 'X') {
                if (turnoP1) 
                    System.out.println("O turno é do jogador1 (" + this.player1.getNome() + ")");
                else 
                    System.out.println("O turno é do jogador2 (" + this.player2.getNome() + ")");
                
                //do-while o movimento for inválido
                boolean isMovValido;
                do {
                    String mov;

                    int linha; char coluna; 
                    System.out.print("Digite a posição de origem(colunaLinha, ex: a1): ");
                    mov = scan.nextLine();
                    coluna = mov.charAt(0);
                    linha = Character.getNumericValue(mov.charAt(1));

                    int linhaDest; char colunaDest;
                    System.out.print("Digite a posição de destino(colunaLinha, ex: a1): ");
                    mov = scan.nextLine();
                    colunaDest = mov.charAt(0);
                    linhaDest = Character.getNumericValue(mov.charAt(1));

                    isMovValido = this.fazerJogada(linha, linhaDest, coluna, colunaDest);

                    if (isMovValido == false)
                        System.out.println("Movimento inválido!");
                } while (isMovValido == false);

                this.print();

                if (this.status == 'x') {
                    System.out.println("O jogador " + this.xeque.getNome() + " está em xeque!");
                } else if (this.status == 'X') {
                    System.out.println("Xeque-mate! O jogador " + this.vencedor.getNome() + " venceu!");
                }
            }

            scan.close();
        }
    }
}
