package watchtower.dataaccesslayer;

import watchtower.dataaccesslayer.contract.IDataInfo;

public class ElasticSearchDataInfo implements IDataInfo {

    private String hostname;
    private int port;

    /**
     * Constructor that accepts a hostname and a port
     */
    public ElasticSearchDataInfo(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Returns the hostname
     * @return Returns the hostname
     */
    @Override
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the port
     * @return Returns the port
     */
    @Override
    public int getPort() {
        return port;
    }
}
