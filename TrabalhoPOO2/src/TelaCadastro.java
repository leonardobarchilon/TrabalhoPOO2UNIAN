import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCadastro extends JFrame {
    JLabel lblNome, lblCpf, lblEmail;
    JTextField txtNome, txtCpf, txtEmail;
    JButton btnEnviar;

    public TelaCadastro(){
        setTitle("Cadastro");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();

        lblCpf = new JLabel("CPF:");
        try{
            txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException ex){
            Logger.getLogger(TelaCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
