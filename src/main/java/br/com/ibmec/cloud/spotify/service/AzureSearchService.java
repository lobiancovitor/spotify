package br.com.ibmec.cloud.spotify.service;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.search.documents.SearchClient;
import com.azure.search.documents.SearchClientBuilder;
import com.azure.search.documents.SearchDocument;
import com.azure.search.documents.indexes.models.IndexDocumentsBatch;
import com.azure.search.documents.models.SuggestResult;
import com.azure.search.documents.util.SuggestPagedIterable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AzureSearchService {
    private String endpoint = "https://spotify-search.search.windows.net";
    private String connectionKey = "mODGlwZXwyhOWOPW5tas14q47cNRmDFgG8O1KRU32kAzSeBUftKj";

    private String indexName = "musicas";

    public void index(UUID id, String nomeMusica, String banda) {

        //Cria o objeto para subir para o indice
        IndexDocumentsBatch<AzureSearchIndex> batch = new IndexDocumentsBatch<>();
        AzureSearchIndex index = new AzureSearchIndex();

        //configura o objeto para os valores od indice
        index.setId(id.toString());
        index.setNomeMusica(nomeMusica);
        index.setBanda(banda);

        batch.addUploadActions(Collections.singletonList(index));

        //Cria a conexão com o azure search
        SearchClient client = new SearchClientBuilder()
                .endpoint(endpoint)
                .indexName(indexName)
                .credential(new AzureKeyCredential(connectionKey))
                .buildClient();

        //Sobe o documento para o indice
        client.indexDocuments(batch);
    }

    public List<AzureSearchIndex> suggester(String search) {

        List<AzureSearchIndex> result = new ArrayList<>();

        //Cria a conexão com o azure search
        SearchClient client = new SearchClientBuilder()
                .endpoint(endpoint)
                .indexName(indexName)
                .credential(new AzureKeyCredential(connectionKey))
                .buildClient();

        SuggestPagedIterable suggestPagedIterable =  client.suggest(search, "suggester_musica");

        for (SuggestResult suggestResult: suggestPagedIterable) {
            SearchDocument searchDocument = suggestResult.getDocument(SearchDocument.class);

            for (Map.Entry<String, Object> keyValuePair: searchDocument.entrySet()) {
                result.add(client.getDocument(keyValuePair.getValue().toString(), AzureSearchIndex.class));
            }

        }

        return  result;
    }
}
