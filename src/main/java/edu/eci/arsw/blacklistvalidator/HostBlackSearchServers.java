package edu.eci.arsw.blacklistvalidator;

public class HostBlackSearchServers extends Thread {

    private HostBlackSearchServers validator;
    private int ipAdress;

    public HostBlackSearchServers(HostBlackSearchServers validator){
        this.validator = validator;
    }

    public int ocurrencesCount(){
        return validator.checkHost();
    }

    @Override
    public void run(){
        System
    }
}
