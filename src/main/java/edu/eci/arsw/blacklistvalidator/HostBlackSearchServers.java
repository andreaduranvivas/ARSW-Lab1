package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;

public class HostBlackSearchServers extends Thread {
    private final HostBlacklistsDataSourceFacade skds;
    private final int startIndex;
    private final int endIndex;
    private final String ipaddress;
    private LinkedList<Integer> blackListOcurrences;

    private static final int BLACK_LIST_ALARM_COUNT = 5;

    public HostBlackSearchServers(HostBlacklistsDataSourceFacade skds, int startIndex, int endIndex, String ipaddress) {
        this.skds = skds;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ipaddress = ipaddress;
        this.blackListOcurrences = new LinkedList<>();
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            if (skds.isInBlackListServer(i, ipaddress)) {
                blackListOcurrences.add(i);
                if (blackListOcurrences.size() >= BLACK_LIST_ALARM_COUNT) {
                    break;
                }
            }
        }
    }

    public LinkedList<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }

    public int getCountOcurrences() {
        return blackListOcurrences.size();
    }
}