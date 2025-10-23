package model;

public class Produto {
    private String id;
    private String nome;
    private double preco;
    private String categoria;

    public Produto(String id, String nome, double preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(String nome, double preco, String categoria) {
        this(null, nome, preco, categoria);
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return nome + " - R$" + preco + " (" + categoria + ")";
    }
}
