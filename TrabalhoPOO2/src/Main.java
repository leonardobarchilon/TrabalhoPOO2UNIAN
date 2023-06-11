/**
 *
 * @author Leonardo Barchilon - Aluno do último período de Ciência da Computação
 */
import java.text.ParseException;
import java.util.Scanner;

import GUI.Login;
import autenticar.Autenticacao;
import bd.ConexaoMongoDB;
import bd.DroparBanco;
import bd.FornecedorDataLoader;
import bd.HardwareDataLoader;
import bd.MongoDBDataLoader;
import bd.thread.MonitorFornecedor;
import bd.thread.MonitorHardware;
import bd.thread.MonitorUsuario;

/**
 * Esta classe contém o método principal que inicia a aplicação.
 */
public class Main {
    /**
     * O método principal da aplicação.
     * 
     * @param args os argumentos de linha de comando (não são utilizados)
     * @throws ParseException se ocorrer um erro ao analisar datas
     */
    public static void main(String[] args) throws ParseException {
        new DroparBanco();

        new MongoDBDataLoader();
        new FornecedorDataLoader();
        new HardwareDataLoader();
        new Login(new Autenticacao(new ConexaoMongoDB())).Logar();
        MonitorFornecedor monitorFornecedor = new MonitorFornecedor();
        monitorFornecedor.iniciar();
        MonitorHardware monitorHardware = new MonitorHardware();
        monitorHardware.iniciar();
        MonitorUsuario monitorUsuario = new MonitorUsuario();
        monitorUsuario.iniciar();

    }
}
