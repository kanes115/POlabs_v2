package agh.cs.lab2;


public class Position {
    public final int x, y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Position(Position pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public String toString(){
        return "("+Integer.toString(x)+", "+Integer.toString(y)+")";
    }

    public boolean larger(Position pos){                //or equal
        if(this.x>=pos.x && this.y>=pos.y) return true;
        else return false;
    }

    public boolean smaller(Position pos){               //or equal
        if(pos.x>=this.x && pos.y>=this.y) return true;
        else return false;
    }

    public Position add(Position pos){
        return new Position(this.x+pos.x, this.y+pos.y);
    }

    public Position multiplyBy(int a){
        return new Position(x*a, y*a);
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
