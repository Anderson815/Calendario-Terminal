package pkg03.calendario;

import java.util.Date;

public class Cal {
    //Atributos
    private int rm[] = {3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3}; // relação do mes
    private int s; // dia da semana (1 - dom, 2 - seg, ..., 7 - sab) que começa o mes
    
    private int mes;
    private int ano;
    
    //Método principal
    public void interfaceGrafica(){
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
    private void inicializarS(){
        int anoConstrutor = 2017;
        int mesConstrutor = 0;
        int s = 1;
        
        if((this.getAno() == anoConstrutor && this.getMes() > mesConstrutor) || (this.getAno() > anoConstrutor)){
            for(int ano = anoConstrutor; ano <= this.getAno(); ano++){
                int mesFimLoop = 12;
                if(ano == this.getAno()){
                    mesFimLoop = this.getMes();
                }
                
                int fevereiro = (ano % 4 == 0)? 1: 0;
                this.getRm()[1] = fevereiro;
                
                for(int meses = 0; meses < mesFimLoop; meses++){
                    s += this.getRm()[meses];
                }
            }
        }
        s %= 7;
        if(s == 0)s = 7;
        this.setS(s);
    }
    
    //Método de visualização
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
        this.inicializarS();
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
