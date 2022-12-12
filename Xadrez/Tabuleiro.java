/*
    Nome da dupla: Vinicius e Bernardo

    Descrição da classe:
        Esta classe represente o tabuleiro do xadrez, seu único atributo é uma matriz de posições que forma o tabuleiro. 
        O tabuleiro deve verificar se um movimento está dentro de seus limites e, em conjunto com os métodos das peças, 
        determina se um movimento é válido e se há xeque e com base nisso verificar se existe um possível xeque-mate, 
        movendo todas as peças aliadas para verificar se existe uma possibilidade de fuga do rei, sendo esses movimentos 
        revertidos depois de testados pela método reverterMovimento(). Deve também se atualizar (atualizaTabuleiro()) 
        depois que uma jogada é feita e poder imprimir o estado de todas as posições através do método imprimeTabuleiro().

        Mais especificamente:
        -    iniciaTabuleiro: Método responsável por iniciar Tabuleiro com as 32 peças, colocando nas posições iniciais.
        -    isXeque: Esse método faz uma série de movimentos em comparação com a posição do rei sobre todas as peças inimigas, 
        verificando se ela pode ser eliminada, sendo assim declarando um xeque.
        -    validaXeque: Faz uma varredura de possíveis movimentos das peças aliadas e do próprio rei para verificar se é possível 
        sair do estado xeque, caso não, quer dizer que é um xeque-mate.
        -    atualizaTabuleiro: Responsável por atualizar a posição de uma peça após um movimento válido.
        -    reverterMovimento: Utilizado para reverter o movimento após a verificação de possíveis movimentos para sair ou colocar 
        o rei em xeque, uma vez que o movimento é feito para teste.
        -    isPosPecaPreto: Esse método pega as informações de linha e coluna passada para verificar se aquela posição possui alguma 
        peça, se sim retorna a cor dela em boolean (true para preta e false para branca).
        -    validaMovimento: Responsável por validar o movimento de uma peça, verificando se tal movimento é possível para determinada peça.
        -    imprimeTabuleiro: Esse método imprime todo o tabuleiro sendo “O” representando a cor preta e “-” a cor branca e as peças
        são representadas pelo seu desenho.
*/
package Xadrez;

public class Tabuleiro {
    private Posicao[][] pos;
   
    public Tabuleiro() {
        this.pos = new Posicao[8][8];
    }
    
    public void iniciaTabuleiro(Peca[] pecasPretas, Peca[] pecasBrancas){
        if (this.pos[0][0] == null) {
            boolean preto = false;
            for (int i = 7, countP = 0, countB = 0; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    if (i == 7 || i == 6) {
                        pos[i][j] = new Posicao(i+1, (char)(j+97), preto, pecasPretas[countP]);
                        pecasPretas[countP].linha = i+1;
                        pecasPretas[countP].coluna = (char)( j + 97 );
                        countP++;
                    } else if (i == 1 || i == 0) {
                        pos[i][j] = new Posicao(i+1, (char)(j+97), preto, pecasBrancas[countB]);
                        pecasBrancas[countB].linha = i+1;
                        pecasBrancas[countB].coluna = (char)( j + 97 );
                        countB++;
                    } else {
                        pos[i][j] = new Posicao(i+1, (char)(j+97), preto, null);
                    }
                    if (j != 7)
                        preto = !preto;
                }
            }
        }
    }
    
    public int validaXeque(Peca rei, Peca[] inim, Peca[] alia) {
        Peca daXeque = null;
        for (int i = 0; i < 16; i++) {
            if (inim[i].vivo) {
                if (this.validaMovimento(rei.linha, rei.coluna, inim[i].linha, inim[i].coluna)) {
                    daXeque = inim[i]; 
                    break;
                }
            }
        }
        
        if (daXeque == null)
            return 0;
        
        //Sair desse for sem dar retorno signifca que nenhuma peça aliada pode fazer um movimento que elimina o xeque e não gere outro
        for (int aliado = 0; aliado < 16; aliado++) {
            if (alia[aliado].vivo) {
                int linha = alia[aliado].linha;
                char coluna = alia[aliado].coluna;

                //Checa se o rei consegue fugir
                if (alia[aliado].getClass() == Rei.class) {
                    for (int i = 0; i < 3; i ++) {
                        for (int j = 0; j < 3; j++) {
                            if (validaMovimento(linha - 1 + i, (char)(coluna - 1 + j), linha, coluna)) {
                                Peca morta = atualizaTabuleiro(linha, linha - 1 + i, coluna, (char)(coluna - 1 + j));
                                if (isXeque(rei, inim)) {
                                    reverterMovimento(linha, linha - 1 + i, coluna, (char)(coluna - 1 + j), morta);
                                    continue;
                                }
                                reverterMovimento(linha, linha - 1 + i, coluna, (char)(coluna - 1 + j), morta);
                                return 1;
                            }
                        }
                    }
                }

                //Checa se a peça aliadda consegue bloquear o ataque e não gera outro xeque
                if (daXeque.getClass() != Cavalo.class && daXeque.getClass() != Peao.class && alia[aliado].getClass() != Rei.class) {
                    int linDestino = rei.linha-1;
                    int colDestino = rei.coluna-97;
                    int linOrigem = daXeque.linha-1;
                    int colOrigem = daXeque.coluna-97;
                    //se estiver movendo na horizontal
                    if (linOrigem == linDestino) {
                        if (colOrigem > colDestino) {
                            for (int i = colOrigem+1; i < colDestino; i++)
                                if (validaMovimento(rei.linha, (char)(i+97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, rei.linha, coluna, (char)(i+97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, rei.linha, coluna, (char)(i+97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, rei.linha, coluna, (char)(i+97), morta);
                                    return 1;
                                }
                        } else {
                            for (int i = colDestino+1; i < colOrigem; i++)
                                if (validaMovimento(rei.linha, (char)(i+97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, rei.linha, coluna, (char)(i+97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, rei.linha, coluna, (char)(i+97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, rei.linha, coluna, (char)(i+97), morta);
                                    return 1;
                                }
                        }
                    }
                    //se estiver movendo na vertical 
                    else if (colDestino == colOrigem) {
                        if (linDestino > linOrigem) {
                            for (int i = linOrigem+1; i < linDestino; i++)
                                if (validaMovimento(i+1, rei.coluna, linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, i+1, coluna, rei.coluna);
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, i+1, coluna, rei.coluna, morta);
                                            continue;
                                        }
                                        reverterMovimento(linha, i+1, coluna, rei.coluna, morta);
                                        return 1;
                                    }
                        } else {
                            for (int i = linDestino+1; i < linOrigem; i++)
                                if (validaMovimento(i+1, rei.coluna, linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, i+1, coluna, rei.coluna);
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, i+1, coluna, rei.coluna, morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, i+1, coluna, rei.coluna, morta);
                                    return 1;
                                } 
                        }
                    } 
                    //se estiver movendo na diagonal
                    else {
                        //se estiver movendo /'
                        if (linDestino > linOrigem && colDestino > colOrigem) {
                            for (int i = 1; i < linDestino-linOrigem; i++)
                                if (validaMovimento(linOrigem + i + 1, (char)(colOrigem + i + 97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, linOrigem + i + 1, coluna, (char)(colOrigem + i + 97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, linOrigem + i + 1, coluna, (char)(colOrigem + i + 97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, linOrigem + i + 1, coluna, (char)(colOrigem + i + 97), morta);
                                    return 1;
                                }
                        } 
                        //se estiver movendo '\
                        else if (linDestino > linOrigem && colDestino < colOrigem) {
                            for (int i = 1; i < linDestino-linOrigem; i++)
                                if (validaMovimento(linOrigem + i + 1, (char)(colOrigem - i + 97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, linOrigem + i + 1, coluna, (char)(colOrigem - i + 97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, linOrigem + i + 1, coluna, (char)(colOrigem - i + 97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, linOrigem + i + 1, coluna, (char)(colOrigem - i + 97), morta);
                                    return 1;
                                }
                        } 
                        //se estiver movendo \.
                        else if (linDestino < linOrigem && colDestino > colOrigem) {
                            for (int i = 1; i < linOrigem-linDestino; i++)
                                if (validaMovimento(linOrigem - i + 1, (char)(colOrigem + i + 97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, linOrigem - i + 1, coluna, (char)(colOrigem + i + 97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, linOrigem - i + 1, coluna, (char)(colOrigem + i + 97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, linOrigem - i + 1, coluna, (char)(colOrigem + i + 97), morta);
                                    return 1;
                                }
                        } 
                        //se estiver movendo ./ 
                        else if (linDestino < linOrigem && colDestino < colOrigem) {
                            for (int i = 1; i < linOrigem-linDestino; i++)
                                if (validaMovimento(linOrigem - i + 1, (char)(colOrigem - i + 97), linha, coluna)) {
                                    Peca morta = atualizaTabuleiro(linha, linOrigem - i + 1, coluna, (char)(colOrigem - i + 97));
                                    if (isXeque(rei, inim)) {
                                        reverterMovimento(linha, linOrigem - i + 1, coluna, (char)(colOrigem - i + 97), morta);
                                        continue;
                                    }
                                    reverterMovimento(linha, linOrigem - i + 1, coluna, (char)(colOrigem - i + 97), morta);
                                    return 1;
                                }
                        }
                    }
                }

                //Checa se a peça aliada consegue comer a peça que ataca e não gera outro xeque
                if (validaMovimento(daXeque.linha, daXeque.coluna, linha, coluna)) {
                    Peca morta = atualizaTabuleiro(linha, daXeque.linha, coluna, daXeque.coluna);
                    if (isXeque(rei, inim)) {
                        reverterMovimento(linha, daXeque.linha, coluna, daXeque.coluna, morta);
                        continue;
                    }
                    reverterMovimento(linha, daXeque.linha, coluna, daXeque.coluna, morta);
                    return 1;
                }
            }
        }
        return 2;
    }

    private boolean isXeque(Peca rei, Peca[] inim) {
        for (int i = 0; i < 16; i++) {
            if (inim[i].vivo) {
                if (this.validaMovimento(rei.linha, rei.coluna, inim[i].linha, inim[i].coluna))
                    return true;
            }
        }
        return false;    
    }

    public Peca atualizaTabuleiro(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino) {
        int linOrigem = linhaOrigem-1;
        int linDestino = linhaDestino-1;
        int colOrigem = colunaOrigem-97;
        int colDestino = colunaDestino-97;

        Peca movimenta = pos[linOrigem][colOrigem].getPeca();
        Peca morta = pos[linDestino][colDestino].getPeca();
        pos[linOrigem][colOrigem].setPeca(null);
        pos[linDestino][colDestino].setPeca(movimenta);
        movimenta.linha = linhaDestino;
        movimenta.coluna = colunaDestino;

        if (morta != null)
            morta.morrer();
        return morta;
    }

    public void reverterMovimento(int linhaOrigem, int linhaDestino, char colunaOrigem, char colunaDestino, Peca morta) {
        this.atualizaTabuleiro(linhaDestino, linhaOrigem, colunaDestino, colunaOrigem);
        this.pos[linhaDestino-1][colunaDestino-97].setPeca(morta); 
        if (morta != null)
            morta.vivo = true;
    }

    public boolean isPosPecaPreto(int linha, char coluna) {
        if (linha >= 1 && linha <=8 && coluna >= 'a' && coluna <= 'h') {
            Peca peca = pos[linha-1][(char)(coluna-97)].getPeca();
            if (peca != null) {
                return peca.preto;
            }
            return false;
        } else {
            return false;
        }
    }
    
    public boolean validaMovimento(int linhaDestino, char colunaDestino, int linhaOrigem, char colunaOrigem){
        //Se as posições não são iguais
        if ( !(colunaDestino == colunaOrigem && linhaDestino == linhaOrigem) ) {
            //Se ambas as linhas estão dentro do tabuleiro 
            if (linhaDestino >= 1 && linhaDestino <= 8 && linhaOrigem >= 1 && linhaOrigem <= 8) {
                //Se ambas as colunas estão dentro do tabuleiro
                if (colunaDestino >= 'a' && colunaDestino <= 'h' && colunaOrigem >= 'a' && colunaOrigem <= 'h') {
                    //Converte a notação de Xadres para a posição no vetor
                    int linOrigem = linhaOrigem-1;
                    int colOrigem = colunaOrigem-97; 
                    //Peça na posição de origem
                    Peca peca = pos[linOrigem][colOrigem].getPeca();
                    
                    //Checa se existe uma peça na posição de origem
                    if (peca != null) {
                        boolean resultado = peca.checaMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino);
                        if (resultado) {
                            //Converte a notação de Xadres para a posição no vetor
                            int linDestino = linhaDestino-1;
                            int colDestino = colunaDestino-97;
                            //Peça na posição de destino
                            Peca pecaDest = pos[linDestino][colDestino].getPeca();

                            //Se a peça for um cavalo
                            if (peca.getClass() == Cavalo.class) {
                                if (pecaDest == null || pecaDest.preto != peca.preto) 
                                    return true;
                                else   
                                    return false;
                            }
                            //Se for um peão
                            else if (peca.getClass() == Peao.class) {
                                if (Math.abs(colunaDestino - colunaOrigem) == 1) {
                                    if (pecaDest != null && pecaDest.preto != peca.preto) 
                                        return true;
                                    else 
                                        return false;
                                } else if (pecaDest != null) {
                                    return false;
                                } else if (Math.abs(linhaDestino - linhaOrigem) == 2) {
                                    if (peca.preto) {
                                        if (pos[linOrigem-1][colOrigem].getPeca() == null)
                                            return true;
                                        else
                                            return false;
                                    } else {
                                        if (pos[linOrigem+1][colOrigem].getPeca() == null)
                                            return true;
                                        else
                                            return false;
                                    }
                                } else {
                                    return true;
                                } 
                            }
                            //Se for qualquer outra peça
                            else {
                                //Se estiver movendo na horizontal
                                if (linDestino == linOrigem) {
                                    if (colDestino > colOrigem) {
                                        for (int i = colOrigem+1; i < colDestino; i++)
                                            if (pos[linOrigem][i].getPeca() != null)
                                                return false;
                                    } else {
                                        for (int i = colDestino+1; i < colOrigem; i++)
                                            if (pos[linOrigem][i].getPeca() != null)
                                                return false;
                                    }
                                }
                                //Se estiver movendo na vertical 
                                else if (colDestino == colOrigem) {
                                    if (linDestino > linOrigem) {
                                        for (int i = linOrigem+1; i < linDestino; i++)
                                            if (pos[i][colOrigem].getPeca() != null)
                                                return false;
                                    } else {
                                        for (int i = linDestino+1; i < linOrigem; i++)
                                            if (pos[i][colOrigem].getPeca() != null)
                                                return false;
                                    }
                                } 
                                //Se estiver movendo na diagonal
                                else {
                                    //Se estiver movendo /'
                                    if (linDestino > linOrigem && colDestino > colOrigem) {
                                        for (int i = 1; i < linDestino-linOrigem; i++)
                                            if (pos[linOrigem + i][colOrigem + i].getPeca() != null)
                                                return false;
                                    } 
                                    //Se estiver movendo '\
                                    else if (linDestino > linOrigem && colDestino < colOrigem) {
                                        for (int i = 1; i < linDestino-linOrigem; i++)
                                            if (pos[linOrigem + i][colOrigem - i].getPeca() != null)
                                                return false;
                                    } 
                                    //Se estiver movendo \.
                                    else if (linDestino < linOrigem && colDestino > colOrigem) {
                                        for (int i = 1; i < linOrigem-linDestino; i++)
                                            if (pos[linOrigem - i][colOrigem + i].getPeca() != null)
                                                return false;
                                    } 
                                    //Se estiver movendo ./ 
                                    else if (linDestino < linOrigem && colDestino < colOrigem) {
                                        for (int i = 1; i < linOrigem-linDestino; i++)
                                            if (pos[linOrigem - i][colOrigem - i].getPeca() != null)
                                                return false;
                                    }
                                }

                                //Chegando aqui sabemos que não há nenhuma peça entre a origem e o destino
                                //Depois checa se o destino está vazio ou é uma peça inimiga
                                if (pecaDest == null || pecaDest.preto != peca.preto)
                                        return true;
                                    else
                                        return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
     
    public void imprimeTabuleiro(){
        System.out.print("   ");
        for (int x = 0; x < 8; x++) {
            System.out.printf("%c ", x + 'a');
        }
        System.out.println("\n   ---------------");
        
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    System.out.print( (1 + i) + "| ");
                }
                if (pos[i][j].getPeca() != null) {
                    System.out.print(pos[i][j].getPeca().desenho() + " ");
                } else if (pos[i][j].isPreto()) {
                    System.out.printf("O ");
                } else
                    System.out.printf("- ");

                if (j == 7) {
                    System.out.println("|" + (1 + i));
                }
            }
        }
        
        System.out.println("   ---------------");
        System.out.print("   ");
        for (int x = 0; x < 8; x++) {
            System.out.printf("%c ", x + 'a');
        }
        System.out.println();
    }
    
}
