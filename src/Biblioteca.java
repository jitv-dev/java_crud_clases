import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    // Arrays donde se guardan los libros en memoria
    private ArrayList<Libro> libros;
    private int contadorId;
    private Scanner teclado;

    // Constructor
    public Biblioteca(){
        libros = new ArrayList<>();
        contadorId = 1;
        teclado = new Scanner(System.in);
        cargaLibrosEjemplo();
    }

    // Carga de libros
    private void cargaLibrosEjemplo(){
        libros.add(new Libro(contadorId++, "Cien años de Soledad", "Gabriel Garcia Marquez", 1967, "Novela"));
        libros.add(new Libro(contadorId++, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Novela"));
        libros.add(new Libro(contadorId++, "1984", "George Orwell", 1949, "Ciencia Ficción"));
        libros.add(new Libro(contadorId++, "El Principito", "Antoine de Saint-Exupéry", 1943, "Fábula"));
        libros.add(new Libro(contadorId++, "Carmilla", "Sheridan Le Fanu", 1872, "Ficción Gótica"));
    }

    // Read - Leer libros
    public void listarLibros(){
        if (libros.isEmpty()){
            System.out.println("\n No tenemos libros registrados");
            return;
        }
        imprimirCabecera();
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
        imprimirPie();
        System.out.println("Total de libros: " + libros.size());
    }

    // Read - Buscar por ID
    public void buscarPorId(){
        System.out.println("\n Ingrese ID a buscar");
        int id = leerEntero();
        Libro libro = encontrarLibroPorId(id);
        if (libro != null){
            imprimirCabecera();
            System.out.println(libro);
            imprimirPie();
        } else {
            System.out.println("No existe el libro con el ID ingresado " + id);
        }
    }

    // Read - Buscar por Titulo
    public void buscarPorTitulo(){
        System.out.println("\n Ingrese titulo a buscar");
        String busqueda = teclado.nextLine().toLowerCase();
        boolean encontrado = false;
        imprimirCabecera();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(busqueda)){
                System.out.println(libro);
                encontrado = true;
            }
        }
        imprimirPie();
        if (!encontrado){
            System.out.println("No se encontro libro con el titulo: | " + busqueda + " |");
        }
    }

    // Create - Agregar libro
    public void agregarLibro(){
        System.out.println("Agregar libro nuevo");

        System.out.println("Titulo: ");
        String titulo = teclado.nextLine();
        System.out.println("Autor: ");
        String autor = teclado.nextLine();
        System.out.println("Año: ");
        int anio = leerEntero();
        System.out.println("Género: ");
        String genero = teclado.nextLine();

        Libro nuevo = new Libro(contadorId++, titulo, autor, anio, genero);
        libros.add(nuevo);
        System.out.println("Libro agregado con el ID: " + nuevo.getId());
    }

    // Metodos auxiliares
    private int leerEntero(){
        while (!teclado.hasNextInt()){
            System.out.println("Ingrese número válido");
            teclado.next();
        }
        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }

    private Libro encontrarLibroPorId(int id){
        for (Libro libro : libros) {
            if (libro.getId() == id){
                return libro;
            }
        }
        return null;
    }

    private void imprimirCabecera(){
        System.out.println("\n  +------+---------------------------+----------------------+--------+--------------+-------------+");
        System.out.println("  | ID   | TITULO                    | AUTOR                | ANIO   | GENERO       | ESTADO      |");
        System.out.println("  +------+---------------------------+----------------------+--------+--------------+-------------+");
    }

    private void imprimirPie() {
        System.out.println("  +------+---------------------------+----------------------+--------+--------------+-------------+");
    }
}
