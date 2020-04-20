/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03.calendario;

import static java.lang.Thread.sleep;
import java.util.Scanner;

/**
 *
 * @author ander
 */
public class Calendario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cal cal = new Cal();
        boolean continuar = true;
        
        do{
            Calendario.exibirMes(cal);
            continuar = Calendario.opcoes(cal);
        }while(continuar);
    }
    
    public static void exibirMes(Cal cal){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        Calendario.interfaceCabecalhoMesAno(cal.getMes(), cal.getAno());
        Calendario.interfaceCabecalhoSemana();
        Calendario.interfaceCalendario(cal);
    }
        
    // Métodos Auxiliares Nível 1
    private static void interfaceCabecalhoMesAno(int m, int a){
        String meses[] = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO",
                        "JULHO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};        
        int margem = (34 - (meses[m].length() + 8))/2;
                
        for(int contadorLinha = 0; contadorLinha <= 3; contadorLinha++){
            for(int contadorColuna = 0; contadorColuna <= 35; contadorColuna++){
                if((contadorLinha == 0 || contadorLinha == 3) && (contadorColuna != 0 && contadorColuna != 35)){
                    System.out.print("_");
                }else if(contadorLinha != 0 && (contadorColuna == 0 || contadorColuna == 35)){
                    System.out.print("|");
                }
                else{
                    if(contadorLinha == 2 && contadorColuna == margem + 1){
                        System.out.print(meses[m] + " de " + a);
                        contadorColuna += meses[m].length() + 7;
                    }else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }       
    }
    
    // Métodos Auxiliares Nível 1
    private static void interfaceCabecalhoSemana(){
        String semana[] = {"DO", "SE", "TE", "QA", "QI", "SE", "SA"};
        int contadorColuna = 0;
        int indice = 0;
        
        do{
            if(contadorColuna % 5 == 2){
                System.out.print(semana[indice]);
                contadorColuna++;
                indice++;
            }else if(contadorColuna % 5 == 0){
                System.out.print("|");
            }else{
                System.out.print(" ");
            }
            contadorColuna++; 
        }while(contadorColuna <= 35);
        System.out.println("");
    }
    
    // Métodos Auxiliares Nível 1
    private static void interfaceCalendario(Cal cal){
        
        int dia = 1;
        int semanas_do_mes = Calendario.semanas_do_mes(cal);
        boolean intervalo;
        
        
        for(int contadorLinha = 0; contadorLinha <= semanas_do_mes * 3 ; contadorLinha++){
            for(int contadorColuna = 0; contadorColuna <= 35; contadorColuna++){
                if(contadorLinha % 3 == 0 && contadorColuna % 5 != 0){
                    System.out.print("_");
                }else if(contadorColuna % 5 == 0){
                    System.out.print("|");
                }else{
                    intervalo = ((contadorLinha == 2 && contadorColuna >= (cal.getS() * 5) - 3)
                                    || (contadorLinha > 2 && dia <= 28 + cal.getRm()[cal.getMes()]));
                    if(intervalo && contadorLinha % 3 == 2 && contadorColuna % 5 == 2){
                        String d = (dia < 10)? "0" + Integer.toString(dia): Integer.toString(dia);
                        System.out.print(d);
                        contadorColuna++;
                        dia++;
                    }else{
                        System.out.print(" ");
                    }
                }
            }
            System.out.print("\n");
        }    
    }
    
    //Método Auxiliar nível 2
    private static int semanas_do_mes(Cal cal){
        int semanas_mes = 5;
        
        if(cal.getS() == 1 && cal.getRm()[cal.getMes()] == 0){
            semanas_mes = 4;
        }else if((cal.getS() == 6 || cal.getS() == 7) && cal.getRm()[cal.getMes()] == 3){
            semanas_mes = 6;
        }else if(cal.getS() == 7 && cal.getRm()[cal.getMes()] == 2){
            semanas_mes = 6;
        }else{
            semanas_mes = 5;
        }
        
        return semanas_mes;
    }
    
    //Método InterfaceGráfica
    public static boolean opcoes(Cal cal){
        Scanner leitor  = new Scanner(System.in);
        String op;
        
        System.out.println("\n\n");
        System.out.println("|--- LISTA DE AÇÕES ---|");
        System.out.println("| a - Avançar um mês   |");
        System.out.println("| v - Voltar um mês    |");
        System.out.println("| p - Pesquisar um mês |");
        System.out.println("| s - Sair             |");
        System.out.println("|______________________|");
        System.out.print("O que você deseja fazer?: ");
        op = leitor.next();
        
        return Calendario.opcao(cal, op);
    }
    
    // Métodos Auxiliares Nível 1
    private static boolean opcao(Cal cal, String op){
        boolean continuar = true;
        Scanner leitor = new Scanner(System.in);
        Thread tempo = new Thread();
        
        switch(op){
            case "a":
                cal.avancarMes();
                break;
            case "v":
                cal.voltarMes();
                break;
            case "p":
                System.out.println("--- Pesquisar Mês ---");
                int ano, mes;
                
                System.out.print("ANO: ");
                ano = leitor.nextInt();
                System.out.print("MÊS: ");
                mes = leitor.nextInt();
                
                if(mes < 1 || mes > 12){
                    System.out.println("\n!!! MÊS INVÁLIDO !!!");
                    System.out.print("Aperte qualquer tecla e enter para continuar ");
                    leitor.next();
                }else cal.pesquisarMes(mes - 1, ano);  
                
                break;
            case "s":
                continuar = false;
                break;
            default:
                System.out.println("\n!!! OPÇÃO INVÁLIDA !!!");
                System.out.print("Aperte qualquer tecla e enter para continuar ");
                leitor.next();
        }
        return continuar;
    }
}
