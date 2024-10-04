import Metodos.informacoes;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String resposta = "";
        informacoes pesquisa = new informacoes();

        while (!resposta.equalsIgnoreCase("sair")) {
            try {
                System.out.println("Digite o CEP a ser buscado ou 'sair'");
                resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("sair")) {
                    System.out.println("Sistema encerrado com sucesso!");
                    break;
                }
                if (resposta.split("-").length != 2) {
                    if (resposta.length() != 8) {
                        System.out.println("Quantidade de caracteres inválida!");
                        break;
                    }
                }
                pesquisa.requisicao(resposta);

            } catch (IllegalArgumentException e) {
                System.out.println("CEP inválido!");
            }
        }
    }
}
