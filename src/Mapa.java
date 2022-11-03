import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mapa
{
    private int orden;
    private ArrayList<Integer> porcentajes;
    private int[][] mapa;

    public Mapa(int orden)
    {
        this.orden = orden;
        porcentajes = new ArrayList<Integer>();
        mapa = new int[orden][orden];
        Probabilides();
        iniciarJuego();
    }

    public void iniciarJuego()
    {
        int elementos = orden*orden;

        for (int i = 0; i < orden; i++) // Recorrer filas.
        {
            for (int j = 0; j < orden; j++)
            {    // Recorrer columnas.
                elementos-=1; // resta una probabilidad de las 25 a medida que las va llenando
                mapa[i][j] = porcentajes.remove(ThreadLocalRandom.current().nextInt(0, elementos + 1));// remueve una probabilidad de la lista y la mete a la matriz
            }
        }


    }
    private void Probabilides()
    {
         // Lista de porcentajes para ocurrir un evento. ( 25 posiciones )
        System.out.println();
        for (int i = 0; i < 25; i++)// 64% de probabilidad de ocurrir un evento y 36% de probabilidad de no ocurrir nada
        {
            if (i < 9)
            {
                porcentajes.add(0);
            }else
            {
                porcentajes.add(1);
            }
        }

    }


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

    public List<Integer> Eventos()
    {
        List<Integer> subeventos;
        subeventos = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            if (i < 25)// 25% probabilidad de recuperar HP
            {
                subeventos.add(1);
            } else if (i < 40) // 40% probabilidad de recibir un ataque de Frezer
            {
                subeventos.add(2);
            } else {
                subeventos.add(3);// 35% de probabilidad de atacar a Freezer
            }

        }

        return subeventos;
    }

    public int getEvent(int posicionX, int posicionY) //retorna el tipo de evento
    {
        if (mapa[posicionX][posicionY] == 0)
        {
            return 0;
        }

        return Eventos().get(ThreadLocalRandom.current().nextInt(0, 100));// selecciona un evento aleatorio de los 3 posibles (1,2,3)
    }
}
