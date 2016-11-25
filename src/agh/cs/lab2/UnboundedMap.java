package agh.cs.lab2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kanes on 10.11.2016.
 */
public class UnboundedMap extends AbstractWorldMap implements IWorldMap {
    protected Map<Position, HayStack> haystacks = new HashMap<Position, HayStack>();


    public UnboundedMap(List<HayStack> haystacks){
        for(HayStack hay : haystacks){
            this.haystacks.put(hay.getPosition(), hay);
        }
    }


    public boolean canMoveTo(Position position){
        if(super.isOccupied(position)) return false;
        if(haystacks.containsKey(position)) return false;
        return true;
    }


    public boolean isOccupied(Position position){
        return !canMoveTo(position);
    }

    public Object objectAt(Position position){
        if(super.objectAt(position) != null) return super.objectAt(position);

        return haystacks.get(position);
    }

    public Position getLowerLeft(){
        int leftiest = Integer.MAX_VALUE;
        int lowest = Integer.MAX_VALUE;



        for(Map.Entry<Position, Car> car : cars.entrySet()){
            if(car.getKey().getX()<leftiest) leftiest = car.getKey().getX();
            if(car.getKey().getY()<lowest) lowest = car.getKey().getY();
        }
        for(Map.Entry<Position, HayStack> haystack : haystacks.entrySet()){
            if(haystack.getKey().getX()<leftiest) leftiest = haystack.getKey().getX();
            if(haystack.getKey().getY()<lowest) lowest = haystack.getKey().getY();
        }

        return new Position(leftiest, lowest);
    }

    public Position getUpperRight(){
        int rightest = Integer.MIN_VALUE;
        int uppest = Integer.MIN_VALUE;

        for(Map.Entry<Position, Car> car : cars.entrySet()){
            if(car.getKey().getX() > rightest) rightest = car.getKey().getX();
            if(car.getKey().getY() > uppest) uppest = car.getKey().getY();
        }
        for(Map.Entry<Position, HayStack> haystack : haystacks.entrySet()){
            if(haystack.getKey().getX() > rightest) rightest = haystack.getKey().getX();
            if(haystack.getKey().getY() > uppest) uppest = haystack.getKey().getY();
        }

        return new Position(rightest, uppest);
    }

}
