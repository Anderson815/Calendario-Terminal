package pkg03.calendario;

import java.util.Date;
import java.util.Scanner;

public class Cal {
    //Atributos
    private int rm[] = {3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3}; // relação do mes
    private int s; // dia da semana (1 - dom, 2 - seg, ..., 7 - sab) que começa o mes
    
    private int mes;
    private int ano;
    
    //Método InterfaceGrafica
    public void exibirMes(){
        this.interfaceCabecalhoMesAno(this.getMes(), this.getAno());
        this.interfaceCabecalhoSemana();
        this.interfaceCalendario();
    }
        
    // Métodos Auxiliares Nível 1
    private void interfaceCabecalhoMesAno(int m, int a){
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
    private void interfaceCabecalhoSemana(){
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
    private void interfaceCalendario(){
        
        int dia = 1;
        int semanas_do_mes = this.semanas_do_mes();
        boolean intervalo;
        
        
        for(int contadorLinha = 0; contadorLinha <= semanas_do_mes * 3 ; contadorLinha++){
            for(int contadorColuna = 0; contadorColuna <= 35; contadorColuna++){
                if(contadorLinha % 3 == 0 && contadorColuna % 5 != 0){
                    System.out.print("_");
                }else if(contadorColuna % 5 == 0){
                    System.out.print("|");
                }else{
                    intervalo = ((contadorLinha == 2 && contadorColuna >= (this.getS() * 5) - 3)
                                    || (contadorLinha > 2 && dia <= 28 + this.getRm()[this.getMes()]));
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
    private int semanas_do_mes(){
        int semanas_mes = 5;
        
        if(this.getS() == 1 && this.getRm()[this.getMes()] == 0){
            semanas_mes = 4;
        }else if((this.getS() == 6 || this.getS() == 7) && this.getRm()[this.getMes()] == 3){
            semanas_mes = 6;
        }else if(this.getS() == 7 && this.getRm()[this.getMes()] == 2){
            semanas_mes = 6;
        }else{
            semanas_mes = 5;
        }
        
        return semanas_mes;
    }
    
    //Método InterfaceGráfica
    public void opcoes(){
        Scanner leitor  = new Scanner(System.in);
        String op;
        
        System.out.println("\n\n");
        System.out.println("|--- LISTA DE AÇÕES ---|");
        System.out.println("| > - Avançar um mês   |");
        System.out.println("| < - Voltar um mês    |");
        System.out.println("| p - Pesquisar um mês |");
        System.out.println("| s - Sair             |");
        System.out.println("|______________________|");
        System.out.print("O que você deseja fazer?: ");
        op = leitor.next();
        
        this.opcao(op);
    }
    
    // Métodos Auxiliares Nível 1
    private void opcao(String op){
        switch(op){
            case ">":
                this.avancarMes();
                break;
            case "<":
                break;
            case "p":
                break;
            case "s":
                break;
            default:
                System.out.println("!!! OPÇÃO INVÁLIDA !!!");
        }
    }
    
    //Método principal
    public void avancarMes(){
        this.setMes(this.getMes() + 1);
    } 
    
    //Método principal
    public void voltarMes(){
        this.setMes(this.getMes() - 1);
    }
    
    //Método principal
    public void pesquisarMes(int m, int a){
        this.setAno(a);
        this.setMes(m);
    }
    
    //Método Auxiliar
    private void atualizarS(int anoReferencia, int mesReferencia, int diaSReferencia){        
        int dsa = diaSReferencia; //dia da semana artual
        int comecoMes = mesReferencia;
        
        if((this.getAno() == anoReferencia && this.getMes() > mesReferencia) || this.getAno() > anoReferencia){
            for(int ano = anoReferencia; ano <= this.getAno(); ano++){
                
                int mesFimLoop = (ano == this.getAno())?this.getMes():12;
                int fevereiro = (ano % 4 == 0)? 1: 0;
                this.getRm()[1] = fevereiro;
                
                for(int meses = comecoMes; meses < mesFimLoop; meses++){
                    dsa += this.getRm()[meses];
                }
                comecoMes = 0;
            }
            
            dsa %= 7;
            if(dsa == 0)dsa = 7;
            this.setS(dsa);
            
        }else if((this.getAno() == anoReferencia && this.getMes() < mesReferencia) || this.getAno() < anoReferencia){
            int rm = 0;
            if(comecoMes > 0)comecoMes = comecoMes - 1;
            else comecoMes = 11;
            
            for(int ano = anoReferencia; ano >= this.getAno(); ano--){
                
                int mesFimLoop = (ano == this.getAno())? this.getMes(): 0;
                int fevereiro = (ano % 4 == 0)? 1: 0;
                this.getRm()[1] = fevereiro;
                
                for(int meses = comecoMes; meses >= mesFimLoop; meses--){
                    rm += this.getRm()[meses];
                }
                comecoMes = 11;
            }
            
            dsa = (diaSReferencia + 7 - (rm % 7)) % 7;
            if(dsa == 0) dsa = 7;
            this.setS(dsa);
        }
        
    }
    
    //Método de status
    public void estado(){
        System.out.println("Descrição");
        System.out.println("Ano: " + this.getAno());
        System.out.println("Mês: " + this.getMes());
        System.out.println("S: " + this.getS());
        System.out.println("Relação de mês!");
        for(int r: this.getRm()){
            System.out.println(r);
        }
        
        
    }
    
    //Métodos especiais
    public Cal(){
        Date calendario = new Date();
        this.setMes(calendario.getMonth());
        this.setAno(calendario.getYear() + 1900);        
        this.atualizarS(2020, 2, 1);
    }
    
    private void setMes(int m){
        this.mes = m;
    }
    
    private int getMes(){
        return this.mes;
    }
    
    private void setAno(int a){
        this.ano = a;
        
        // verificação de ano bissexto
        int fevereiro = (this.getAno()%4 == 0)? 1: 0;
        this.getRm()[1] = fevereiro;
    }
    
    private int getAno(){
        return this.ano;
    }

    public int[] getRm() {
        return rm;
    }

    public void setRm(int[] rm) {
        this.rm = rm;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }  
}
