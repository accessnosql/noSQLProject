package persistence;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.CollectionEntity;

public class IncidenceMethods {

	public void incidenceCollection() {

	}

	public static void newIncidence(String dbName, ArangoDB arangoDB) {
		
		ArangoDAO arangoDAO = new ArangoDAO();
		// create collection
		final String collectionName = "incidences";
		try {
			final CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
			System.out.println("Collection created: " + myArangoCollection.getName());
		} catch (final ArangoDBException e) {
			System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
		}
	}
}
