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
            System.out.println("No tenemos libros registrados");
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
        System.out.println("Ingrese ID a buscar");
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
        System.out.println("Ingrese titulo a buscar");
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

    // Update - Actualizar libro
    public void actualizarLibro(){
        System.out.println("Ingrese ID a actualizar: ");
        int id = leerEntero();
        Libro libro = encontrarLibroPorId(id);

        if (libro == null) {
            System.out.printf("No existe un libro con ID: " + id);
            return;
        }

        // Mostrar libro actual
        imprimirCabecera();
        System.out.println(libro);
        imprimirPie();

        // Sub-menu de actualizacion
        System.out.println("Qué desea actualizar?");
        System.out.println("1. Titulo");
        System.out.println("2. Autor");
        System.out.println("3. Año");
        System.out.println("4. Género");
        System.out.println("5. Todos los campos");
        System.out.println("Ingrese su opción: ");
        int opcion = leerEntero();

        switch (opcion){
            case 1:
                System.out.println("Nuevo titulo");
                libro.setTitulo(teclado.nextLine());
                break;
            case 2:
                System.out.println("Nuevo Autor");
                libro.setAutor(teclado.nextLine());
                break;
            case 3:
                System.out.println("Nuevo Año");
                libro.setAnio(leerEntero());
                break;
            case 4:
                System.out.println("Nuevo Género");
                libro.setGenero(teclado.nextLine());
                break;
            case 5:
                System.out.println("Nuevo titulo"); libro.setTitulo(teclado.nextLine());
                System.out.println("Nuevo Autor"); libro.setAutor(teclado.nextLine());
                System.out.println("Nuevo Año"); libro.setAnio(leerEntero());
                System.out.println("Nuevo Género"); libro.setGenero(teclado.nextLine());
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }
        System.out.println("Libro actualizado correctamente");
    }

    // Delete - Eliminar un libro
    public void eliminarLibro(){
        System.out.println("Ingrese el ID del libro a eliminar");
        int id = leerEntero();
        Libro libro = encontrarLibroPorId(id);

        if (libro == null) {
            System.out.printf("No existe un libro con ID: " + id);
            return;
        }

        // Mostrar libro antes de eliminar
        imprimirCabecera();
        System.out.println(libro);
        imprimirPie();

        // Pedir confirmacion
        System.out.println("Está seguro de eliminar este libro? Si/No");
        String confirmacion = teclado.nextLine();

        if (confirmacion.equalsIgnoreCase("si")){
            System.out.println("Libro eliminado correctamente");
            libros.remove(libro);
        } else {
            System.out.println("Eliminación cancelada");
        }
    }

    public void prestarLibro(){
        System.out.println("Ingrese ID del libro a prestar: ");
        int id = leerEntero();
        Libro libro = encontrarLibroPorId(id);

        if (libro == null) {
            System.out.println("No existe un libro con ID: " + id);
        } else if (libro.isPrestado()) {
            System.out.println("El libro ya está prestado");
        } else {
            System.out.println("Libro: " + libro.getTitulo() + " marcado como prestado");
            libro.setPrestado(true);
        }
    }

    public void devolverLibro(){
        System.out.println("Ingrese ID del libro a devolver: ");
        int id = leerEntero();
        Libro libro = encontrarLibroPorId(id);

        if (libro == null) {
            System.out.println("No existe un libro con ID: " + id);
        } else if (!libro.isPrestado()) {
            System.out.println("El libro ya está disponible");
        } else {
            System.out.println("Libro: " + libro.getTitulo() + " devuelto");
            libro.setPrestado(false);
        }
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
        System.out.println("+------+---------------------------+----------------------+--------+--------------+-------------+");
        System.out.println("| ID   | TITULO                    | AUTOR                | ANIO   | GENERO       | ESTADO      |");
        System.out.println("+------+---------------------------+----------------------+--------+--------------+-------------+");
    }

    private void imprimirPie() {
        System.out.println("+------+---------------------------+----------------------+--------+--------------+-------------+");
    }
}
