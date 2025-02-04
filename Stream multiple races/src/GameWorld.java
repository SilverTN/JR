import java.util.List;
import java.util.Map;

public class GameWorld{

    public static final Map<Coordinate, List<GameObject>> game =
            Map.ofEntries(
            Map.entry(new Coordinate(1, 1), List.of(new GameObject(200, Race.ELF), new GameObject(205, Race.GNOME))),
            Map.entry(new Coordinate(2, 1), List.of(new GameObject(400, Race.DWARF), new GameObject(4200, Race.ORK))),
            Map.entry(new Coordinate(3, 1), List.of(new GameObject(350, Race.DWARF), new GameObject(355, Race.TROLL))),
            Map.entry(new Coordinate(1, 2), List.of(new GameObject(2400, Race.HUMAN), new GameObject(325, Race.HUMAN))),
            Map.entry(new Coordinate(2, 2), List.of(new GameObject(5400, Race.HUMAN), new GameObject(2300, Race.ORK))),
            Map.entry(new Coordinate(3, 2), List.of(new GameObject(1350, Race.ELF), new GameObject(6050, Race.GNOME))),
            Map.entry(new Coordinate(1, 3), List.of(new GameObject(7400, Race.GNOME), new GameObject(300, Race.TROLL))),
            Map.entry(new Coordinate(2, 3), List.of(new GameObject(2356, Race.HUMAN), new GameObject(1600, Race.ORK))),
            Map.entry(new Coordinate(3, 3), List.of(new GameObject(1350, Race.ELF), new GameObject(4000, Race.DWARF)))
            );

}