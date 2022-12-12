/* 
Nome da dupla: Vinicius e Bernardo

Descrição da classe: É a Classe main, responsável por iniciar o programa e pedir as informações para os jogadores (Nome do jogo, cor da peça) 
e perguntar a jogada que ele deseja realizar.

*/
package Xadrez;
import java.util.Scanner;

public class Gerenciador {
    public static void main(String[] args) {
        Scanner ler_int =  new Scanner(System.in); //scanner para ler valores inteiros
        Scanner ler_string =  new Scanner(System.in);//scanner para ler Strings
        int menu =0;
        String jogo = "a";//Aqui será tratado no p3
        String j1 = "erro";
        String j2 = "erro";
        int corpecas =3;
        String corj1 = "Preto";
        String corj2 = "Preto";
        
            System.out.println("::: XADREZ :::"); //Menu de opções
            System.out.println("Selecione uma das opções abaixo:");
            System.out.println("1 - Iniciar novo jogo"); //Novo jogo
            System.out.println("2 - Carregar jogo"); //Carregar arquivo txt
            System.out.println("3 - Sair"); //Finalizar programa
            menu = ler_int.nextInt();
            
         switch (menu){
             case 1:{
                 System.out.println("Digite o nome do jogo (sem espaços):");
                 System.out.println("Exemplo: 'Jogo1'");
                 jogo = ler_string.nextLine();
                 //Salvar nome do jogo .txt
                 System.out.println("Digite o nome do Jogador 1:");
                 j1 = ler_string.nextLine();
                 
                 while(corpecas < 1 || corpecas > 2){
                    System.out.println("Escolha a cor das Peças"); //Jogador 1 sempre escolhe as peças
                    System.out.println("\t 1- Brancas");
                    System.out.println("\t 2- Pretas");
                    corpecas=ler_int.nextInt();

                    if (corpecas == 1) {
                        corj1 = "Branco";
                        corj2 =  "Preto";
                        
                    }else if(corpecas == 2){
                        corj1 =  "Preto";
                        corj2 = "Branco";
                        
                    }else{
                        System.out.println("Opção Invalída!");
                    }
                 }
                 //Salvar nome do jogador1
                 System.out.println("Digite o nome do Jogador 2:");
                 j2 = ler_string.nextLine();
                 
                 break;
             }case 2:{
                 System.out.println("Digite o nome do jogo:");
                 jogo = ler_string.nextLine();
                 break;
             }default:{
                 System.out.println("Volte sempre =D.");
                 System.exit(0);
                 break;
             }
             
         }
        
        if (menu == 1) { //caso a opção for 1 ele inicia o jogo
            Jogo j = new Jogo(j1, corj1, j2, corj2);
            j.comecarJogo();
        }else if(menu == 2){//será devidamente implementado no p3
            Jogo j = new Jogo(j1, corj1, j2, corj2);
        }

    ler_int.close();
    ler_string.close();
        
    }

}
