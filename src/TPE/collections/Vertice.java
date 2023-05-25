package TPE.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Vertice<T> {
    private Integer id;
    private T valor;

    public Vertice(Integer id){
        this.id = id;
    }

    public Vertice(Integer id, T valor){
        this(id);
        this.valor = valor;
    }
    public Integer getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.id = id;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

}
