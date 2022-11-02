import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Personaje
{
    // Atributos
    private String nombre;
    private int Hp;
    private int Ataque;
    private int Defensa;
    private int posicionX;
    private int posicionY;
    private int movimientos;
    private int eventos;
    private int hpFreezer;
    private int defensaFreezer;
    private int puntaje;
    List<String> historialEventos = null;

    public Personaje(String nombre, int hp, int ataque, int defensa, int posicionX, int posicionY, int movimientos, int eventos, int hpFreezer, int defensaFreezer, int puntaje, ArrayList<String> historialEventos)
    {
        this.nombre = nombre;
        Hp = hp;
        Ataque = ataque;
        Defensa = defensa;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.movimientos = movimientos;
        this.eventos = eventos;
        this.hpFreezer = hpFreezer;
        this.defensaFreezer = defensaFreezer;
        this.puntaje =puntaje;
        this.historialEventos = new ArrayList<String>();
    }

// Metodos Getter y Setter De la clase Personaje

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public int getAtaque() {
        return Ataque;
    }

    public void setAtaque(int ataque) {
        Ataque = ataque;
    }

    public int getDefensa() {
        return Defensa;
    }

    public void setDefensa(int defensa) {
        Defensa = defensa;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

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

    public void mover() { // Controla el movimiento entre filas y columnas del personaje.
        if (posicionY+1==5){ // se verifica si ya se acabó de recorrer las columnas para pasar a la siguiente fila.
            posicionX+=1;
            posicionY=0; // se resetea la posición de las columnas
        }
        else{
            posicionY+=1; // recorre las columnas
        }
        movimientos+=1; // acumulador de movimientos
    }

    public void recuperaHp() {
        int hprecuperado = ThreadLocalRandom.current().nextInt(1, 10 + 1); //Recupera hp aleatoriamente entre 1 y 10
        if(nombre=="Goku" && Hp>=120){ // si tiene el hp completo no regenera nada
            Hp+=0;
        }
        if(nombre=="Vegeta" && Hp>=150){ // si tiene el hp completo no regenera nada
            Hp+=0;
        }
        if(nombre=="Krilin" && Hp>=90){ // si tiene el hp completo no regenera nada
            Hp+=0;
        }
        else{
            Hp+= hprecuperado;
            if(nombre=="Goku" && Hp>120){ // si se pasa de 120, lo deja en 120
                Hp=120;
            }
            if(nombre=="Vegeta" && Hp>150){ // si se pasa de 150, lo deja en 150
                Hp=150;
            }
            if(nombre=="Krilin" && Hp>90){ // si se pasa de 90, lo deja en 90
                Hp=90;
            }
        }
        historialEventos.add("En la posicion ("+posicionX+","+posicionY+")"+" el personaje "+nombre+" ha recuperado "+hprecuperado+" puntos de HP.");
    }

    public void recibirAtaqueDeFreezer() {
        int ataque = ThreadLocalRandom.current().nextInt(15, 30 + 1);
        if(ataque-Defensa <  0){ // si la defensa es mayor que el ataque, no se quita HP
            Hp+=0;
            historialEventos.add("En la posicion ("+posicionX+","+posicionY+")"+" Freezer ataco al personaje "+nombre+" pero no le ha restado puntos de HP.");
        }
        else{
            Hp-=(ataque-Defensa); // restar HP al personaje (Puede restar cero de HP si la defensa es igual al ataque aleatorio de Freezer)
            historialEventos.add("En la posicion ("+posicionX+","+posicionY+")"+" el personaje "+nombre+" ha recibido un ataque de Freezer de  "+(ataque-Defensa)+" puntos de HP.");
        }

    }

    public void atacarAFreezer() {
        int multiplicadora =  ThreadLocalRandom.current().nextInt(1, 4);//
        int ataque = ThreadLocalRandom.current().nextInt(1, Ataque * multiplicadora); // ataque entre 1 y el máximo ataque del personaje por un numero aleatorio.
        if(ataque-defensaFreezer<0){ // si la defensa de Freezer es mayor que el ataque del personaje, no se quita HP a Freezer
            hpFreezer-=0; //hpFreezer = hpFreezer - 0;
            historialEventos.add("En la posicion ("+posicionX+","+posicionY+")"+" el personaje "+nombre+" ha atacado a Freezer pero no le ha quitado puntos de HP.");
        }
        else{
            hpFreezer-=(ataque-defensaFreezer); // restar HP a freezer ( puede restar cero de HP si la defensa de freezer es igual al ataque aleatorio del personaje)
            puntaje+=(ataque-defensaFreezer); // suma los puntos que se le quitaron de hp a Freezer. ( puede sumar cero si la defensa de freezer es igual al ataque aleatorio del personaje
            historialEventos.add("En la posicion ("+posicionX+","+posicionY+")"+" el personaje "+nombre+" ha atacado a Freezer en "+(ataque-defensaFreezer)+" puntos de HP.");
        }
    }

    public void mostrarStatus() {
        System.out.println("\t\tHP: "+Hp+" "+"\n \t\tPuntaje: "+puntaje+" "+"\n \t\tHPFreezer: "+hpFreezer);
        System.out.println("------------------------------");
    }

    public boolean juegoFinalizado() { // lo da por finalizado si esta en la posición final o si el personaje ya murio o si frezer ya murio
        if((posicionX==4 & posicionY==4)||Hp<=0||hpFreezer<=0){
            return true;
        }
        else{
            return false;
        }
    }
}

