public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private int anio;
    private String genero;
    private boolean prestado;

    public Libro(int id, String titulo, String autor, int anio, String genero){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.genero = genero;
        this.prestado = false;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isPrestado() {
        return prestado;
    }

    @Override
    public String toString() {
        String estado = prestado ? "Prestado" : "Disponible";
        // Formato solo visible en consola
        return String.format("| %-4d | %-25s | %-20s | %-6d | %-12s| %-11s|",id, titulo, autor,anio, genero, estado);
    }
}
