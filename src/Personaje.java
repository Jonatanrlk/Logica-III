import java.util.List;

public class Personaje
{
    private String nombre;
    private int ataque;
    private int defensa;
    private int hp;
    private int posicionX;
    private int posicionY;

    public Personaje(String nombre, int ataque, int defensa, int hp, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.hp = hp;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
}
