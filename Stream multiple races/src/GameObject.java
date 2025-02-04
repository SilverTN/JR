public class GameObject {
    private Integer gold;
    private Race race;

    public GameObject(Integer gold, Race race) {
        this.gold = gold;
        this.race = race;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Integer getGold() {
        return gold;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "\nGameObject {gold=" + gold + ", race='" + race + "}";
    }
}
