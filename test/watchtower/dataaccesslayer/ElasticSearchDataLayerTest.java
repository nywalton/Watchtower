package watchtower.dataaccesslayer;

import org.junit.Assert;
import watchtower.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ElasticSearchDataLayerTest {

    private ElasticSearchDataLayer<ElasticSearchDataInfo> dataLayer;
    private final ElasticSearchDataInfo elasticSearchDataInfo = new ElasticSearchDataInfo("localhost", 9200);


    @Before
    public void setUp() throws Exception {
        dataLayer = new ElasticSearchDataLayer<ElasticSearchDataInfo>(elasticSearchDataInfo);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws IOException {
        User testUser = new User(0, "user@test.com");
        dataLayer.insert(testUser);
    }

    @Test
    public void search() throws IOException {
        User testUser = new User(0, "user@test.com");
        Assert.assertTrue(dataLayer.search(testUser));
    }
}