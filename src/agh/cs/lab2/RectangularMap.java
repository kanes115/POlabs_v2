package agh.cs.lab2;

/**
 * Created by Kanes on 04.11.2016.
 */
public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    public final int WEIGHT, HEIGHT;
    Position upperRight, lowerLeft;


    public RectangularMap(int weight, int height){
        WEIGHT = weight;
        HEIGHT = height;
        upperRight = new Position(weight - 1, height -1);
        lowerLeft = new Position(0, 0);
    }

    public boolean canMoveTo(Position position){
        if(position.smaller(upperRight) && position.larger(lowerLeft)) return true;
        return false;
    }

//    public String toString(){
//        return mapV.dump(this, lowerLeft, upperRight);
//    }

    public Position getLowerLeft(){
        return lowerLeft;
    }

    public Position getUpperRight(){
        return upperRight;
    }
}
