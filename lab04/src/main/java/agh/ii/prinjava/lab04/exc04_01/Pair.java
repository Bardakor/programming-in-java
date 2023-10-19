package agh.ii.prinjava.lab04.exc04_01;

public class Pair<F, S> implements Cloneable {

    // add one contructor wih two parameters ( F first, S second)
    // add the accessors (getters) for both fields and mutators (setters) for both
    // fields
    // add the toString() method, equals() and hashCode() methods
    // add the clone() method

    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pair))
            return false;
        Pair<F, S> p = (Pair<F, S>) o;
        return p.first.equals(first) && p.second.equals(second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() ^ second.hashCode();
    }

    @Override
    public Pair<F, S> clone() {
        try {
            return (Pair<F, S>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
