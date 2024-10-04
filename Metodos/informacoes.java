package Metodos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class informacoes {
    private String cept;
    private String logadouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public informacoes (informacoesOMDB minhaInformacoesOMDB) {
        this.cept = minhaInformacoesOMDB.cep();
        this.logadouro = minhaInformacoesOMDB.logradouro();
        this.complemento = minhaInformacoesOMDB.complemento();
        this.unidade = minhaInformacoesOMDB.unidade();
        this.bairro = minhaInformacoesOMDB.bairro();
        this.localidade = minhaInformacoesOMDB.localidade();
        this.uf = minhaInformacoesOMDB.uf();
        this.estado = minhaInformacoesOMDB.estado();
        this.regiao = minhaInformacoesOMDB.regiao();
        this.ibge = minhaInformacoesOMDB.ibge();
        this.gia = minhaInformacoesOMDB.gia();
        this.ddd = minhaInformacoesOMDB.ddd();
        this.siafi = minhaInformacoesOMDB.siafi();
    }

    public informacoes() {

    }

    public void requisicao (String cep) throws IOException, InterruptedException {
        String apiCep = "https://viacep.com.br/ws/" + cep + "/json/";

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCep)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().contains("erro")) {
            System.out.println("Nenhum CEP encontrado, verifique se foi digitado corretamente e tente novamente.");
        } else {
            String json = response.body();

            informacoesOMDB minhaInformacoesOMDB = gson.fromJson(json, informacoesOMDB.class);
            informacoes minhasInformacoes = new informacoes(minhaInformacoesOMDB);
            System.out.println(minhasInformacoes);

            FileWriter escritor = new FileWriter("CEPInformacoes.txt");
            escritor.write(String.valueOf(minhasInformacoes));
            escritor.close();
        }
    }

    @Override
    public String toString() {
        return "Informações {" + "\n" +
                "  cep='" + cept + "'\n" +
                "  longadouro='" + logadouro + "'\n" +
                "  complemento='" + complemento + "'\n" +
                "  unidade='" + unidade + "'\n" +
                "  bairro='" + bairro + "'\n" +
                "  localidade='" + localidade + "'\n" +
                "  uf='" + uf + "'\n" +
                "  estado='" + estado + "'\n" +
                "  regiao='" + regiao + "'\n" +
                "  ibge='" + ibge + "'\n" +
                "  gia='" + gia + "'\n" +
                "  ddd='" + ddd + "'\n" +
                "  siafi='" + siafi + "'\n" +
                "}";
    }
}
