package finalproject.model.client;

public class NaturalPersonClient implements Client{
    private String name;
    private String cpf;

    public NaturalPersonClient(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getId() {
        return this.cpf;
    }
}
