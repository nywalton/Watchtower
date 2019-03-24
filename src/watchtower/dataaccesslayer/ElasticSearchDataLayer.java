package watchtower.dataaccesslayer;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import watchtower.dataaccesslayer.contract.IData;
import watchtower.dataaccesslayer.contract.IDataInfo;
import watchtower.dataaccesslayer.contract.IDataLayer;

import java.io.IOException;
import java.util.Iterator;

public class ElasticSearchDataLayer<TDataInfo extends IDataInfo> implements IDataLayer {

    private static RestHighLevelClient client;

    // TODO add log4j and remove printlns
    public ElasticSearchDataLayer(TDataInfo info) {
        if (info == null) throw new IllegalArgumentException("info cannot be null");

        if (client == null)
        {
            System.out.println(info.getHostname());
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(info.getHostname(), info.getPort(), "http")));
        }
    }

    /**
     * Searches the data layer for an entity
     * @return Returns true if found in the data layer
     */
    @Override
    public boolean search(IData data) throws IOException {
        if (data == null) throw new IllegalArgumentException("data cannot be null");

        String id = Long.toString(data.getId());
        if (!checkIfIndexExists(data.getIndex(), id)) {
            System.out.println(String.format("Index %s does not exist", data.getIndex()));
            return false;
        }

        int counter = 0;
        SearchRequest searchRequest = new SearchRequest(data.getIndex());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchHits hits = response.getHits();

            if (hits.totalHits == 0) {
                System.out.println("No search results found.");
                return false;
            }

            Iterator<SearchHit> iterator = hits.iterator();
            while (iterator.hasNext()) {
                SearchHit searchHit = (SearchHit) iterator.next();
                System.out.println(String.format("Search hit [%d]: %s.", counter, searchHit.getSourceAsMap().get("emailAddress")));
                counter++;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return counter > 0;
    }

    /**
     * Inserts an entity into the data layer
     */
    @Override
    public void insert(IData data) throws IOException {
        if (data == null) throw new IllegalArgumentException("data cannot be null");

        try {
            IndexRequest request = new IndexRequest(data.getIndex(), "user");
            request.source(new Gson().toJson(data));
            IndexResponse response = client.index(request);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Verify if the given index exists by executing a simple get request using the index and an entity ID.
     * If the ID is not found, the request should still succeed if the index exists.
     * @param index The index name to verify
     * @param id The ID of an entity
     * @return True if the index exists
     */
    private boolean checkIfIndexExists(String index, String id) throws IOException {
        try {
            GetRequest request = new GetRequest(index);
            client.get(request.id(id));
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
