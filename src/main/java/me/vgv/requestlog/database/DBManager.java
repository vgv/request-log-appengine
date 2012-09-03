package me.vgv.requestlog.database;

import com.google.appengine.api.datastore.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.vgv.requestlog.database.entity.Request;
import me.vgv.requestlog.database.entity.Response;

import java.util.*;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public class DBManager {

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public void save(Request request) {
		Gson gson = GsonUtils.createGson();

        Entity entity = new Entity("Request");

        entity.setProperty("key", request.getKey());
        entity.setProperty("method", request.getMethod());
        entity.setProperty("schema", request.getSchema());
        entity.setProperty("date", new Date());
        entity.setProperty("parameters", new Text(gson.toJson(request.getParameters())));

        datastore.put(entity);
    }

    public List<Response> find(String requestKey) {
		Gson gson = GsonUtils.createGson();

        Query query = new Query("Request");
        query.setFilter(new Query.FilterPredicate("key", Query.FilterOperator.EQUAL, requestKey));
        query.addSort("date", Query.SortDirection.DESCENDING);

        List<Response> result = new ArrayList<Response>();
        PreparedQuery preparedQuery = datastore.prepare(query);
        for (Entity entity : preparedQuery.asIterable(FetchOptions.Builder.withChunkSize(50).prefetchSize(50).limit(50))) {
            long id = entity.getKey().getId();
            String key = entity.getProperty("key").toString();
            Date date = (Date) entity.getProperty("date");
            String method = entity.getProperty("method").toString();
            String schema = entity.getProperty("schema").toString();
			Map<String, List<String>> parameters = (Map<String, List<String>>) gson.fromJson(
					((Text) entity.getProperty("parameters")).getValue(),
					new TypeToken<Map<String, List<String>>>(){}.getRawType());

            Response response = new Response(id, key, date, method, schema, parameters);
            result.add(response);
        }

        return result;
    }

    public void deleteAll(String key) {
		final int DELETE_COUNT = 100;

        Query query = new Query("Request");
        query.setFilter(new Query.FilterPredicate("key", Query.FilterOperator.EQUAL, key));
		query.setKeysOnly();
        PreparedQuery preparedQuery = datastore.prepare(query);

		List<Key> keys = new ArrayList<Key>(DELETE_COUNT);
		Iterator<Entity> entityIterator = preparedQuery.asIterator();
		while (entityIterator.hasNext()) {
			Entity entity = entityIterator.next();
			keys.add(entity.getKey());
			if (keys.size() >= DELETE_COUNT) {
				datastore.delete(keys);
				keys.clear();
			}
		}

		// удалим остатки
		if (keys.size() > 0) {
			datastore.delete(keys);
		}
    }
}
