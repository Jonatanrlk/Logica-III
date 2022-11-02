import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mapa
{
    private int orden;
    private int[][] mapa;

    public Mapa(int orden)
    {
        this.orden = orden;
        int elementos = orden*orden;
        List<Integer> porcentajes = new ArrayList<Integer>(); // Lista de porcentajes para ocurrir un evento. ( 25 posiciones )
        for (int a=0; a<16; a++){ // 64% de probabilidad de ocurrir un evento
            porcentajes.add(1);
        }
        for (int b=0; b<9; b++){ // 36% de probabilidad de no ocurrir nada
            porcentajes.add(0);
        }
        mapa = new int[orden][orden]; // se crea la matriz
        for (int i = 0; i < orden; i++) {       // Recorrer filas.
            for (int j = 0; j < orden; j++) {    // Recorrer columnas.
                elementos=elementos-1; // resta una probabilidad de las 25 a medida que las va llenando
                mapa[i][j]=porcentajes.remove(ThreadLocalRandom.current().nextInt(0, elementos + 1)); // remueve una probabilidad de la lista y la mete a la matriz
            }
        }
    }
    //METODOS PARA DISCUTIR E IR MIRANDO

    public void mostrarMapa()
    {
        for (int x=0; x < orden; x++)
        {
            System.out.print("|");
            for (int y=0; y < orden; y++) {
                System.out.print (mapa[x][y]);
                if (y!=mapa[x].length-1) System.out.print("\t\t");
            }
            System.out.println("|");
        }
    }

    public int getEvent(int posicionX, int posicionY)
    { //retorna el tipo de evento
        if(mapa[posicionX][posicionY]==0){
            return 0;
        }
        else
        {
            List<Integer> subeventos = new ArrayList<Integer>(); // Lista de porcentajes para ocurrir uno de los 3 subeventos
            for (int a=0; a<25; a++){ // 25% probabilidad de recuperar HP
                subeventos.add(1);
            }
            for (int b=0; b<40; b++){ // 40% probabilidad de recibir un ataque de Frezer
                subeventos.add(2);
            }
            for (int c=0; c<35; c++){ // 35% de probabilidad de atacar a Freezer
                subeventos.add(3);
            }
            int subevento= subeventos.get(ThreadLocalRandom.current().nextInt(0, 100)); // selecciona un evento aleatorio de los 3 posibles (1,2,3)
            return subevento;
        }
    }
}
