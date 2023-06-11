/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
package bd.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;


import bd.HardwareBD;

public class MonitorHardware implements Runnable {

    private boolean monitorando;
    private Thread th2;

    public MonitorHardware() {
        monitorando = true;
        th2 = new Thread(this);
    }

    public void iniciar() {
        th2.start();
    }

    public void parar() {
        monitorando = false;
        try {
            th2.join(2000);
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException no parar().");
        }
    }

    @Override
    public void run() {
        System.out.println("Iniciando Monitoramento de Hardware");
        String currentDirectory = System.getProperty("user.dir");
        File file = new File(currentDirectory + "\\src\\bd\\hardwares.txt");
        long lastModified = file.lastModified();
        
        while (monitorando) {
            System.out.println("A thread " + th2.getId() + " está Monitorando");

            if (th2.isInterrupted()) {
                System.out.println("Monitoramento Interrompido");
                return;
            }
            
            long newLastModified = file.lastModified();
            if (newLastModified > lastModified) {
                System.out.println("Arquivo modificado. Incrementando no banco de dados...");
                lastModified = newLastModified;
                incrementarNoBancoDeDados();
            }

            try {
                th2.sleep(1000);
            } catch (InterruptedException ex) {
                monitorando = false;
                System.out.println("InterruptedException no run()");
            }
        }
        System.out.println("Parando Monitoramento");
    }

    private void incrementarNoBancoDeDados() {
    // Configurar detalhes de conexão com o MongoDB usando a classe ConexaoMongoDB
    HardwareBD hardwareBD = new HardwareBD();
    String collectionName = "hardware";
    String currentDirectory = System.getProperty("user.dir");
    String filePath = currentDirectory + "\\src\\bd\\hardwares.txt";

    // Obter o objeto MongoCollection para a coleção desejada
    MongoCollection<Document> collection = hardwareBD.getCollection();

    // Ler o arquivo de texto e incrementar os dados no MongoDB
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            // Extrair os campos do registro
            String[] fields = line.split(",");

            // Criar um documento do MongoDB com os campos
            Document document = new Document()
                    .append("nome", fields[0])
                    .append("categoria", fields[1])
                    .append("estado", fields[2])
                    .append("quantidade", fields[3])
                    .append("preco", fields[4]);

            // Verificar se o documento já existe no banco de dados
            Document existingDocument = collection.find(document).first();

            if (existingDocument == null) {
                // O documento não existe, então inserir no banco de dados
                collection.insertOne(document);
            } else {
                // O documento já existe, então atualizar no banco de dados
                collection.replaceOne(existingDocument, document);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
