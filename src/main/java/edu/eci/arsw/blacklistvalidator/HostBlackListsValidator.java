/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camilo Fajardo y Andrea Duran
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT=5;
    private HostBlacklistsDataSourceFacade skds;
    int registeredServersCount;
    int serversPerThread;
    
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public LinkedList<Integer> checkHost(String ipaddress, int N){
        
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        int ocurrencesCount=0;
        int checkedListsCount=0;
        
        this.skds=HostBlacklistsDataSourceFacade.getInstance();
        this.registeredServersCount = skds.getRegisteredServersCount();
        this.serversPerThread = registeredServersCount / N;

        HostBlackSearchServers[] threads = threadGenerator(N, ipaddress);

        for (HostBlackSearchServers thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        for (HostBlackSearchServers thread : threads) {
            ocurrencesCount += thread.getCountOcurrences();
            blackListOcurrences.addAll(thread.getBlackListOcurrences());
        }
        
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{blackListOcurrences.size(), skds.getRegisteredServersCount()});
        
        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());

    /**
     *
     * @param N
     * @return
     */
    private HostBlackSearchServers[] threadGenerator(int N, String ipaddress){
        HostBlackSearchServers[] threads = new HostBlackSearchServers[N];
        int endIndex;
        int startIndex;

        for (int i = 0; i < N; i++) {
            startIndex = i * serversPerThread;

            if (i == N - 1) {
                endIndex = registeredServersCount;
            } else {
                endIndex = startIndex + serversPerThread;
            }

            threads[i] = new HostBlackSearchServers(this.skds, startIndex, endIndex, ipaddress);
            threads[i].start();
        }
        return threads;
    }

}
