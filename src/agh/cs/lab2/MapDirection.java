package agh.cs.lab2;

/**
 * Created by Kanes on 21.10.2016.
 */
public enum MapDirection {
    North, East, South, West;

    public MapDirection next(MapDirection dir){
        return MapDirection.values()[this.ordinal()+1%4];
    }
//    public MapDirection next(MapDirection dir){
//        switch(dir){
//            case North:
//                return East;
//            case East:
//                return South;
//            case West:
//                return North;
//            default:
//                return null;
//        }
//    }

}
