package pkg03.calendario;

import java.util.Date;
import java.util.Scanner;

public class Cal {
    //Atributos
    private int rm[] = {3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2, 3}; // relação do mes
    private int s; // dia da semana (1 - dom, 2 - seg, ..., 7 - sab) que começa o mes
    
    private int mes;
    private int ano;
            
    //Método principal
    public void avancarMes(){
        int anoReferencia = this.getAno();
        int mesReferencia = this.getMes();
        int diaReferencia = this.getS();
        
        if(this.getMes() < 11)this.setMes(this.getMes() + 1);
        else{
            this.setMes(0);
            this.setAno(this.getAno() + 1);
        }
        this.atualizarS(anoReferencia, mesReferencia, diaReferencia);
    } 
    
    //Método principal
    public void voltarMes(){
        int anoReferencia = this.getAno();
        int mesReferencia = this.getMes();
        int diaReferencia = this.getS();
        
        if(this.getMes() > 0)this.setMes(this.getMes() - 1);
        else{ 
            this.setMes(11);
            this.setAno(this.getAno() -1);
        }
        
        this.atualizarS(anoReferencia, mesReferencia, diaReferencia);
    }
    
    //Método principal
    public void pesquisarMes(int m, int a){
        int anoReferenica = this.getAno();
        int mesReferencia = this.getMes();
        int diaReferencia = this.getS();
        
        this.setAno(a);
        this.setMes(m);
        
        this.atualizarS(anoReferenica, mesReferencia, diaReferencia);
    }
    
    //Método Auxiliar
    private void atualizarS(int anoReferencia, int mesReferencia, int diaSReferencia){        
        int dsa = diaSReferencia; //dia da semana atual
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
            else{ 
                comecoMes = 11;
                anoReferencia--;
            }
            
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
    
    public void setMes(int m){
        this.mes = m;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public void setAno(int a){
        this.ano = a;
        
        // verificação de ano bissexto
        int fevereiro = (this.getAno()%4 == 0)? 1: 0;
        this.getRm()[1] = fevereiro;
    }
    
    public int getAno(){
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
