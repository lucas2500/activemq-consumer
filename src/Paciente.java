import java.io.Serializable;

public class Paciente implements Serializable {

    private String nome;
    private Integer id;
    private String msg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
