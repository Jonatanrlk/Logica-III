import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String nombre="";
        char opcion;
        Personaje personaje = new Personaje(" " , 0, 0 , 0, 0, 0 , 0,0,0,0,0,null);
        // ELECCION DEL PERSONAJE
        do {
            System.out.println("\t\t\t\tElige tu personaje");

            System.out.println("\n \t\t\t\t| HP:120     |\n 1. GOKU \t\t| ATAQUE:22  | \n \t\t\t\t| DEFENSA:10 |");
            System.out.println("\n \t\t\t\t| HP:150     |\n 2. VEGETA \t\t| ATAQUE:15  |\n \t\t\t\t| DEFENSA:20 |");
            System.out.println("\n \t\t\t\t| HP:90      |\n 3. KRILLIN \t| ATAQUE:8   | \n \t\t\t\t| DEFENSA:8  |");
            System.out.println();
            System.out.print("\n \tOpcion => ");
            opcion = input.next().charAt(0);
        } while (opcion < '1' || opcion > '3');

        System.out.println("-------------------------------");
        System.out.println("\t\tINICIO DEL JUEGO ");
        System.out.println("-------------------------------");

        System.out.println("\t\t\t\tMAPA");
        System.out.println();
        // CREANDO (INSTANCIANDO OBJETO PERSONAJE)
        // Si elige la opcion 1 de arriba , entonces en el case '1'
        //creará un objeto y le asignará los datos de Goku
        //Y así mismo para los demas personajes
        switch (opcion) {
            case '1':
                personaje  = new Personaje("Goku", 120, 22, 10,0,0, 0,0,300,5,0,null);
                nombre="GOKU";
                break;
            case '2':
                personaje = new Personaje("Vegeta", 150, 15, 20,0,0, 0,0,300,5,0,null);
                nombre="VEGETA";
                break;
            case '3':
                personaje = new Personaje("Krilin", 90, 8, 8,0,0, 0,0,300,5,0,null);
                nombre="KRILLIN";
                break;
            default:
                break;
        }

        // En proceso , este es simplemente es un objeto
        //campo(o mapa de 5x5) es el campo donde van a estar los eventos
        Mapa campo = new Mapa(5);
        campo.mostrarMapa();
        System.out.println("-------------------------------");
        System.out.println();
        int evento;
        personaje.juegoFinalizado();
        while(!personaje.juegoFinalizado() && personaje.getMovimientos()<25){ //solo se toman 24 movimientos del juego, ya que en primer movimiento sería el de la posición (0,0)
            personaje.mover();
            System.out.println(nombre+" en Posicion ["+personaje.getPosicionX()+","+ personaje.getPosicionY()+"] \n");
            evento = campo.getEvent(personaje.getPosicionX(), personaje.getPosicionY());
            if(evento==0){
                System.out.println("\tEvento  <<No ha pasado nada!>>\n");
            }
            if(evento==1){
                personaje.recuperaHp();
                System.out.println("\tEvento <<Has recuperado HP!>>\n");
            }
            if(evento==2){
                personaje.recibirAtaqueDeFreezer();
                System.out.println("\tEvento <<Has recibido un ataque de Freezer!>>\n");
            }
            if(evento==3){
                personaje.atacarAFreezer();
                System.out.println("\tEvento <<Has atacado a Freezer!>>\n");
            }
            personaje.mostrarStatus();
        }
        if (personaje.getHp()<=0)
        {
            System.out.println("Oh no, has muerto en la posicion ("+personaje.getPosicionX()+","+ personaje.getPosicionY()+") !!!");
        }

        System.out.println("\t\tFIN DEL JUEGO");
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("\t\t\t\t\t\t\t\t--------RESUMEN--------");
        System.out.println();
        System.out.println(nombre+"\t>>HP FINAL: "+personaje.getHp()+"\n \t\t>>PUNTAJE: "+personaje.getPuntaje());
        System.out.println();
        for (int i=0; i<personaje.getHistorialEventos().size(); i++){
            System.out.println(personaje.historialEventos.get(i)+"\n");

        }


    }

}
