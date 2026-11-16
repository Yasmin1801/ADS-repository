// O NoSuchElementException ocorre quando tentamos ler um dado (como usando o Scanner),
// mas não há mais nenhum elemento ou linha disponível para ser lido no fluxo de entrada.
// Capturamos essa exceção para evitar que o programa feche de forma inesperada.
import java.util.NoSuchElementException;
import java.util.Scanner;

public class codigopi {


    static String lerLinha(Scanner entrada, String mensagem) {
        System.out.print(mensagem + " (V para voltar): ");
        String texto = entrada.nextLine().trim();
        if (texto.equalsIgnoreCase("v")) {
            return null;
        }
        return texto;
    }

    static final int LIMITE_CARACTERES = 30;
    static final String MSG_LIMITE = "[ERRO] Entrada muito longa! O limite é de 30 caracteres.";

    static Integer lerInteiro(Scanner entrada, String mensagem, int min, int max) {
        while (true) {
            String texto = lerLinha(entrada, mensagem);
            if (texto == null) {
                return null;
            }
            if (texto.length() > LIMITE_CARACTERES) {
                System.out.println(MSG_LIMITE);
                continue;
            }
            if (temSimboloMatematico(texto)) {
                System.out.println(MSG_SIMBOLOS);
                continue;
            }
            if (!texto.matches("\\d+")) {
                System.out.println("[ERRO] Valor inválido! Digite apenas números inteiros, sem símbolos.");
                continue;
            }
            try {
                int valor = Integer.parseInt(texto);
                if (valor < min || valor > max) {
                    if (max == Integer.MAX_VALUE) {
                        System.out.println("[ERRO] O valor deve ser no mínimo " + min + "!");
                    } else {
                        System.out.println("[ERRO] O valor deve estar entre " + min + " e " + max + "!");
                    }
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Valor inválido! Digite um número inteiro.");
            }
        }
    }

    static Double lerDecimal(Scanner entrada, String mensagem, double min, double max, boolean minInclusivo, int casasMax) {
        while (true) {
            String texto = lerLinha(entrada, mensagem);
            if (texto == null) {
                return null;
            }
            if (texto.length() > LIMITE_CARACTERES) {
                System.out.println(MSG_LIMITE);
                continue;
            }
            if (temSimboloMatematico(texto)) {
                System.out.println(MSG_SIMBOLOS);
                continue;
            }
            if (!texto.matches("\\d+([.,]\\d+)?")) {
                System.out.println("[ERRO] Valor inválido! Digite apenas números (ex: 50.50), sem símbolos.");
                continue;
            }
            int posSeparador = Math.max(texto.indexOf('.'), texto.indexOf(','));
            if (posSeparador >= 0 && texto.length() - posSeparador - 1 > casasMax) {
                System.out.println("[ERRO] Use no máximo " + casasMax + " casas decimais!");
                continue;
            }
            try {
                double valor = Double.parseDouble(texto.replace(',', '.'));
                boolean valido = !Double.isNaN(valor) && valor <= max && (minInclusivo ? valor >= min : valor > min);
                if (valido) {
                    return valor;
                }
                System.out.println("[ERRO] Valor fora do limite permitido (" + (minInclusivo ? "de " : "maior que ") + min + " até " + max + ")!");
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Valor inválido! Digite um número decimal.");
            }
        }
    }

    static final String MSG_SIMBOLOS = "[ERRO] Símbolos matemáticos (+ - * / = < > ^ %) não são permitidos!";

    static boolean temSimboloMatematico(String texto) {
        return texto.matches(".*[+\\-*/=<>^%].*");
    }

    static void voltarAoMenu() {
        System.out.println("\n[VOLTAR] Operação cancelada. Retornando ao menu...");
    }

    // ---------- PROGRAMA PRINCIPAL ----------
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int tamanhoMaximo = 10;
        String[] nomes = new String[tamanhoMaximo];
        int[] qtdsAtuais = new int[tamanhoMaximo];
        int[] qtdsMinimas = new int[tamanhoMaximo];
        double[] precosBase = new double[tamanhoMaximo];
        double[] taxasImposto = new double[tamanhoMaximo];
        int[] vendasMes = new int[tamanhoMaximo];

        int totalCadastrados = 0;
        int opcao = -1;

        System.out.println("=== SISTEMA DE GESTÃO DE ESTOQUE TRIBUTÁVEL ===");

        do {
            try {
                System.out.println("\n================== MENU ==================");
                System.out.println("1 - Cadastrar novo produto ou peça");
                System.out.println("2 - Listar produtos e verificar alertas");
                System.out.println("3 - Registrar vendas e calcular estimativa");
                System.out.println("4 - Atualizar / Repor estoque de um produto");
                System.out.println("5 - Editar dados de um produto");
                System.out.println("0 - Sair do sistema");
                System.out.println("(Em qualquer pergunta, digite V para voltar ao menu)");
                System.out.println("==========================================");
                System.out.print("Escolha uma opção: ");

                String linhaMenu = entrada.nextLine().trim();
                if (linhaMenu.length() > LIMITE_CARACTERES) {
                    System.out.println("\n" + MSG_LIMITE);
                    opcao = -1;
                    continue;
                }
                if (temSimboloMatematico(linhaMenu)) {
                    System.out.println("\n" + MSG_SIMBOLOS);
                    opcao = -1;
                    continue;
                }
                if (!linhaMenu.matches("\\d+")) {
                    System.out.println("\n[ERRO] Entrada inválida! Digite apenas números inteiros.");
                    opcao = -1;
                    continue;
                }
                try {
                    opcao = Integer.parseInt(linhaMenu);
                } catch (NumberFormatException e) {
                    System.out.println("\n[ERRO] Entrada inválida! Digite apenas números inteiros.");
                    opcao = -1;
                    continue;
                }

                switch (opcao) {
                    case 1:
                        if (totalCadastrados < tamanhoMaximo) {
                            String nome = lerLinha(entrada, "Digite o nome do jogo ou peça");
                            if (nome == null) {
                                voltarAoMenu();
                                break;
                            }

                            if (nome.isEmpty()) {
                                System.out.println("\n[ERRO] O nome do produto não pode estar vazio!");
                                break;
                            }

                            if (temSimboloMatematico(nome)) {
                                System.out.println("\n" + MSG_SIMBOLOS);
                                break;
                            }

                            if (nome.length() > 30) {
                                System.out.println("\n[ERRO] O nome é muito longo! O limite é de 30 caracteres.");
                                break;
                            }

                            boolean jaExiste = false;
                            for (int i = 0; i < totalCadastrados; i++) {
                                if (nomes[i].equalsIgnoreCase(nome)) {
                                    jaExiste = true;
                                    break;
                                }
                            }

                            if (jaExiste) {
                                System.out.println("\n[ERRO] Este produto ou peça já está cadastrado no sistema!");
                                break;
                            }

                            Integer qtdAtual = lerInteiro(entrada, "Digite a quantidade atual em estoque", 0, Integer.MAX_VALUE);
                            if (qtdAtual == null) {
                                voltarAoMenu();
                                break;
                            }

                            Integer qtdMinima = lerInteiro(entrada, "Digite a quantidade mínima permitida (maior que 1)", 2, Integer.MAX_VALUE);
                            if (qtdMinima == null) {
                                voltarAoMenu();
                                break;
                            }

                            Double preco = lerDecimal(entrada, "Digite o preço base unitário em R$ (Ex: 50.50)", 0, 100000.0, false, 2);
                            if (preco == null) {
                                voltarAoMenu();
                                break;
                            }

                            Double taxa = lerDecimal(entrada, "Digite a taxa de imposto personalizada em %", 0, 100.0, true, 4);
                            if (taxa == null) {
                                voltarAoMenu();
                                break;
                            }

                            nomes[totalCadastrados] = nome;
                            qtdsAtuais[totalCadastrados] = qtdAtual;
                            qtdsMinimas[totalCadastrados] = qtdMinima;
                            precosBase[totalCadastrados] = preco;
                            taxasImposto[totalCadastrados] = taxa;
                            vendasMes[totalCadastrados] = 0;

                            double valorImposto = preco * (taxa / 100.0);
                            double precoFinal = preco + valorImposto;

                            System.out.println("\n[SUCESSO] Produto cadastrado com sucesso!");
                            System.out.printf("Preço Base: R$ %.2f | Imposto: R$ %.2f | Preço Final: R$ %.2f\n", preco, valorImposto, precoFinal);

                            if (qtdAtual <= qtdMinima) {
                                System.out.println("[ALERTA] O produto '" + nome + "' está com estoque baixo!");
                            }

                            totalCadastrados++;
                        } else {
                            System.out.println("\n[ERRO] O limite máximo de cadastros (10) foi atingido!");
                        }
                        break;

                    case 2:
                        if (totalCadastrados == 0) {
                            System.out.println("\n[AVISO] Nenhum produto cadastrado até o momento.");
                        } else {
                            System.out.println("\n--- LISTA DE PRODUTOS E TRIBUTAÇÕES ---");
                            for (int i = 0; i < totalCadastrados; i++) {
                                double valorImposto = precosBase[i] * (taxasImposto[i] / 100.0);
                                double precoFinal = precosBase[i] + valorImposto;

                                System.out.println("----------------------------------------");
                                System.out.println("Código/Posição: " + (i + 1));
                                System.out.println("Nome: " + nomes[i]);
                                System.out.println("Quantidade Atual: " + qtdsAtuais[i]);
                                System.out.println("Quantidade Mínima: " + qtdsMinimas[i]);
                                System.out.printf("Preço Base: R$ %.2f\n", precosBase[i]);
                                System.out.printf("Taxa de Imposto: %.1f%%\n", taxasImposto[i]);
                                System.out.printf("Preço Final (com tributo): R$ %.2f\n", precoFinal);

                                if (qtdsAtuais[i] <= qtdsMinimas[i]) {
                                    System.out.println("Status: [ALERTA] Estoque baixo!");
                                } else {
                                    System.out.println("Status: Normal");
                                }
                            }
                            System.out.println("----------------------------------------");
                        }
                        break;

                    case 3:
                        if (totalCadastrados == 0) {
                            System.out.println("\n[AVISO] Cadastre produtos antes de registrar vendas.");
                        } else {
                            System.out.println("\n--- REGISTRAR VENDAS E ESTIMATIVA ---");
                            for (int i = 0; i < totalCadastrados; i++) {
                                System.out.println((i + 1) + " - " + nomes[i] + " (Estoque: " + qtdsAtuais[i] + ")");
                            }

                            Integer codigo = lerInteiro(entrada, "Digite o código do produto que deseja analisar", 1, totalCadastrados);
                            if (codigo == null) {
                                voltarAoMenu();
                                break;
                            }
                            int indice = codigo - 1;

                            Integer vendas = lerInteiro(entrada, "Digite a quantidade vendida", 0, Integer.MAX_VALUE);
                            if (vendas == null) {
                                voltarAoMenu();
                                break;
                            }

                            if (vendas > qtdsAtuais[indice]) {
                                System.out.println("[ERRO] Quantidade de vendas superior ao estoque atual (" + qtdsAtuais[indice] + ")!");
                                break;
                            }
                            vendasMes[indice] += vendas;
                            qtdsAtuais[indice] -= vendas;

                            System.out.println("\n[SUCESSO] Venda registrada! Novo estoque de '" + nomes[indice] + "': " + qtdsAtuais[indice] + " unidades.");

                            if (vendas > 0) {
                                double vendaDiaria = (double) vendas / 30.0;
                                double diasRestantes = (double) qtdsAtuais[indice] / vendaDiaria;

                                if (diasRestantes < 1.0) {
                                    System.out.println("[ESTIMATIVA] O estoque restante vai acabar em menos de 1 dia.");
                                } else {
                                    System.out.printf("[ESTIMATIVA] O estoque restante vai acabar em aproximadamente %d dias.\n", (int) Math.round(diasRestantes));
                                }
                            } else {
                                System.out.println("\n[AVISO] Nenhuma venda registrada para este item.");
                            }
                        }
                        break;

                    case 4: // Atualizar / Repor Estoque
                        if (totalCadastrados == 0) {
                            System.out.println("\n[AVISO] Nenhum produto cadastrado.");
                        } else {
                            System.out.println("\n--- ATUALIZAR / REPOR ESTOQUE ---");
                            for (int i = 0; i < totalCadastrados; i++) {
                                System.out.println((i + 1) + " - " + nomes[i] + " (Estoque atual: " + qtdsAtuais[i] + ")");
                            }

                            Integer codigo = lerInteiro(entrada, "Digite o código do produto para atualizar o estoque", 1, totalCadastrados);
                            if (codigo == null) {
                                voltarAoMenu();
                                break;
                            }
                            int indice = codigo - 1;

                            System.out.println("1 - Adicionar unidades (Reposição)");
                            System.out.println("2 - Remover unidades (Baixa)");
                            Integer opEstoque = lerInteiro(entrada, "Escolha a operação", 1, 2);
                            if (opEstoque == null) {
                                voltarAoMenu();
                                break;
                            }

                            Integer qtdMod = lerInteiro(entrada, "Digite a quantidade", 0, Integer.MAX_VALUE);
                            if (qtdMod == null) {
                                voltarAoMenu();
                                break;
                            }

                            if (opEstoque == 1) {
                                qtdsAtuais[indice] += qtdMod;
                                System.out.println("[SUCESSO] Estoque atualizado! Novo total: " + qtdsAtuais[indice]);
                            } else {
                                if (qtdMod > qtdsAtuais[indice]) {
                                    System.out.println("[ERRO] Não é possível remover mais do que o estoque atual!");
                                } else {
                                    qtdsAtuais[indice] -= qtdMod;
                                    System.out.println("[SUCESSO] Estoque atualizado! Novo total: " + qtdsAtuais[indice]);
                                }
                            }
                        }
                        break;

                    case 5:
                        if (totalCadastrados == 0) {
                            System.out.println("\n[AVISO] Nenhum produto cadastrado.");
                        } else {
                            System.out.println("\n--- EDITAR PRODUTO ---");
                            for (int i = 0; i < totalCadastrados; i++) {
                                System.out.println((i + 1) + " - " + nomes[i]);
                            }

                            Integer codigo = lerInteiro(entrada, "Digite o código do produto que deseja editar", 1, totalCadastrados);
                            if (codigo == null) {
                                voltarAoMenu();
                                break;
                            }
                            int indice = codigo - 1;

                            String novoNome = lerLinha(entrada, "Novo nome (atual: " + nomes[indice] + "; Enter para manter)");
                            if (novoNome == null) {
                                voltarAoMenu();
                                break;
                            }

                            if (temSimboloMatematico(novoNome)) {
                                System.out.println("\n" + MSG_SIMBOLOS);
                                break;
                            }

                            if (novoNome.length() > LIMITE_CARACTERES) {
                                System.out.println("\n" + MSG_LIMITE);
                                break;
                            }

                            Integer novaMin = lerInteiro(entrada, "Nova quantidade mínima (atual: " + qtdsMinimas[indice] + ", maior que 1)", 2, Integer.MAX_VALUE);
                            if (novaMin == null) {
                                voltarAoMenu();
                                break;
                            }

                            Double novoPreco = lerDecimal(entrada, "Novo preço base (atual: " + precosBase[indice] + ")", 0, 100000.0, false, 2);
                            if (novoPreco == null) {
                                voltarAoMenu();
                                break;
                            }

                            Double novaTaxa = lerDecimal(entrada, "Nova taxa de imposto em % (atual: " + taxasImposto[indice] + ")", 0, 100.0, true, 4);
                            if (novaTaxa == null) {
                                voltarAoMenu();
                                break;
                            }

                            // Só aplica as mudanças depois de todas as respostas
                            if (!novoNome.isEmpty()) {
                                if (novoNome.length() <= 30) {
                                    nomes[indice] = novoNome;
                                } else {
                                    System.out.println("[AVISO] Nome muito longo, mantido o anterior.");
                                }
                            }
                            qtdsMinimas[indice] = novaMin;
                            precosBase[indice] = novoPreco;
                            taxasImposto[indice] = novaTaxa;

                            System.out.println("\n[SUCESSO] Produto atualizado com sucesso!");
                        }
                        break;

                    case 0:
                        System.out.println("\nEncerrando o sistema. Até logo!");
                        break;

                    default:
                        System.out.println("\n[ERRO] Opção inválida! Escolha uma opção válida do menu.");
                        break;
                }

            } catch (NoSuchElementException e) {
                System.out.println("\nEntrada encerrada. Saindo do sistema.");
                opcao = 0;
            } catch (Exception e) {
                System.out.println("\n[ERRO CRÍTICO] Ocorreu uma exceção inesperada: " + e.getMessage());
            }

        } while (opcao != 0);

        entrada.close();
    }
}