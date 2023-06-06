import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Usuario {

    private String nome;
    private String cpf;

    private String email;

    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void gravarUsuario(){
        try{
            UsuarioBd.insereUsuario(this);
        } catch (SQLException ex){
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Usuario(" + "nome=" + nome + ", cpf=" + cpf + ", email=" + email + ')';
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
