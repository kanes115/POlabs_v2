package agh.cs.lab2;

import java.util.*;

/**
 * Created by Kanes on 10.11.2016.
 */
public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    private MapVisualizer mapV = new MapVisualizer();
    protected Map<Position, Car> cars = new HashMap<Position, Car>();
    protected List<Car> carsList = new LinkedList<>();


    public boolean add(Car car){
        if(!isOccupied(car.getPosition())) {
            cars.put(car.getPosition(), car);
            carsList.add(car);
            car.addListener(this);
            return true;
        }
        throw new IllegalArgumentException("can't add car");

    }

    public void run(MoveDirection[] directions){
       int turn = 0;

        for(int i = 0; i < directions.length; i++){
            carsList.get(turn).move(directions[i]);
            turn += 1;
            turn = turn % cars.size();
            System.out.println(toString());
        }
    }

    public Object objectAt(Position position){
        return cars.get(position);
    }

    public boolean isOccupied(Position position){
        if(cars.containsKey(position)) return true;
        return false;
    }

    public String toString(){
        return mapV.dump(this, getLowerLeft(), getUpperRight());
    }

    public void positionChanged(Position oldP, Position newP){
        Car updatedCar = cars.remove(oldP);
        cars.put(newP, updatedCar);
    }

    public abstract Position getLowerLeft();

    public abstract Position getUpperRight();
}
