package watchtower.dataaccesslayer.contract;

public interface IDataInfo {

    /**
     * Returns the hostname of the data provider
     * @return Returns the hostname of the data provider
     */
    String getHostname();

    /**
     * Returns the port of the data provider
     * @return Returns the port of the data provider
     */
    int getPort();
}
