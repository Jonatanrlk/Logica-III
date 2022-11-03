import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Guerrero extends Personaje
{
    // Atributos

    private int movimientos;
    private int eventos;
    private int hpFreezer;
    private int defensaFreezer;
    private int puntaje;
    List<String> historialEventos = null;

    public Guerrero(String nombre, int hp, int ataque, int defensa, int posicionX, int posicionY, int movimientos, int eventos, int hpFreezer, int defensaFreezer, int puntaje, ArrayList<String> historialEventos)
    {
        setNombre(nombre);
        setHp(hp);
        setAtaque(ataque);
        setDefensa(defensa);
        setPosicionX(posicionX);
        setPosicionY(posicionY);
        this.movimientos = movimientos;
        this.eventos = eventos;
        this.hpFreezer = hpFreezer;
        this.defensaFreezer = defensaFreezer;
        this.puntaje =puntaje;
        this.historialEventos = new ArrayList<String>();
    }

// Metodos Getter y Setter De la clase Guerrero

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public int getEventos() {
        return eventos;
    }

    public void setEventos(int eventos) {
        this.eventos = eventos;
    }

    public int getHpFreezer() {
        return hpFreezer;
    }

    public void setHpFreezer(int hpFreezer) {
        this.hpFreezer = hpFreezer;
    }

    public int getDefensaFreezer() {
        return defensaFreezer;
    }

    public void setDefensaFreezer(int defensaFreezer) {
        this.defensaFreezer = defensaFreezer;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public List<String> getHistorialEventos() {
        return historialEventos;
    }

    public void setHistorialEventos(List<String> historialEventos) {
        this.historialEventos = historialEventos;
    }

    //Puede a TODO: REVISAR ESTE METODO SI HAY UN ERROR
    public void mover() { // Controla el movimiento entre filas y columnas del personaje.
        if (getPosicionY()+1==5){ // se verifica si ya se acabó de recorrer las columnas para pasar a la siguiente fila.
            setPosicionX(getPosicionX()+1);
            setPosicionY(0);// se resetea la posición de las columnas
        }
        else{
            setPosicionY(getPosicionY()+1);// recorre las columnas
        }
        movimientos+=1; // acumulador de movimientos
    }

    public void recuperaHp() {
        int hprecuperado = ThreadLocalRandom.current().nextInt(1, 10 + 1); //Recupera hp aleatoriamente entre 1 y 10
        if(getNombre()=="Goku" && getHp()>=120){ // si tiene el hp completo no regenera nada
            setHp(getHp()+0);
        }
        if(getNombre()=="Vegeta" && getHp()>=150){ // si tiene el hp completo no regenera nada
            setHp(getHp()+0);
        }
        if(getNombre()=="Krilin" && getHp()>=90){ // si tiene el hp completo no regenera nada
            setHp(getHp()+0);
        }
        else{
            setHp(getHp()+hprecuperado);
            if(getNombre()=="Goku" && getHp()>120){ // si se pasa de 120, lo deja en 120
                setHp(120);
            }
            if(getNombre()=="Vegeta" && getHp()>150){ // si se pasa de 150, lo deja en 150
                setHp(150);
            }
            if(getNombre()=="Krilin" && getHp()>90){ // si se pasa de 90, lo deja en 90
                setHp(90);
            }
        }
        historialEventos.add("En la posicion ("+getPosicionX()+","+getPosicionY()+")"+" el personaje "+getNombre()+" ha recuperado "+hprecuperado+" puntos de HP.");
    }

    public void recibirAtaqueDeFreezer() {
        int ataque = ThreadLocalRandom.current().nextInt(15, 30 + 1);
        if(ataque-getDefensa() <  0){ // si la defensa es mayor que el ataque, no se quita HP
            setHp(getHp()+0);
            historialEventos.add("En la posicion ("+getPosicionX()+","+getPosicionY()+")"+" Freezer ataco al personaje "+getNombre()+" pero no le ha restado puntos de HP.");
        }
        else{
            setHp(getHp()-(ataque-getDefensa()));// restar HP al personaje (Puede restar cero de HP si la defensa es igual al ataque aleatorio de Freezer)
            historialEventos.add("En la posicion ("+getPosicionX()+","+getPosicionY()+")"+" el personaje "+getNombre()+" ha recibido un ataque de Freezer de  "+(ataque-getDefensa())+" puntos de HP.");
        }

    }

    public void atacarAFreezer() {
        int multiplicadora =  ThreadLocalRandom.current().nextInt(1, 4);//
        int ataque = ThreadLocalRandom.current().nextInt(1, getAtaque() * multiplicadora); // ataque entre 1 y el máximo ataque del personaje por un numero aleatorio.
        if(ataque-defensaFreezer<0){ // si la defensa de Freezer es mayor que el ataque del personaje, no se quita HP a Freezer
            hpFreezer-=0; //hpFreezer = hpFreezer - 0;
            historialEventos.add("En la posicion ("+getPosicionX()+","+getPosicionY()+")"+" el personaje "+getNombre()+" ha atacado a Freezer pero no le ha quitado puntos de HP.");
        }
        else{
            hpFreezer-=(ataque-defensaFreezer); // restar HP a freezer ( puede restar cero de HP si la defensa de freezer es igual al ataque aleatorio del personaje)
            puntaje+=(ataque-defensaFreezer); // suma los puntos que se le quitaron de hp a Freezer. ( puede sumar cero si la defensa de freezer es igual al ataque aleatorio del personaje
            historialEventos.add("En la posicion ("+getPosicionX()+","+getPosicionY()+")"+" el personaje "+getNombre()+" ha atacado a Freezer en "+(ataque-defensaFreezer)+" puntos de HP.");
        }
    }

    public void mostrarStatus() {
        System.out.println("\t\tHP: "+getHp()+" "+"\n \t\tPuntaje: "+puntaje+" "+"\n \t\tHPFreezer: "+hpFreezer);
        System.out.println("------------------------------");
    }

    public boolean juegoFinalizado() { // lo da por finalizado si esta en la posición final o si el personaje ya murio o si frezer ya murio
        if((getPosicionX()==4 & getPosicionY()==4)||getHp()<=0||hpFreezer<=0){
            return true;
        }
        else{
            return false;
        }
    }
}

