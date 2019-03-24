package watchtower.dataaccesslayer.contract;

import java.io.IOException;

public interface IDataLayer<TDataInfo extends IDataInfo, TData extends IData>{

    /**
     * Searches the data layer for an entity
     * @return Returns true if found in the data layer
     */
    boolean search(TData data) throws IOException;

    /**
     * Inserts an entity into the data layer
     */
    void insert(TData data) throws IOException;
}
