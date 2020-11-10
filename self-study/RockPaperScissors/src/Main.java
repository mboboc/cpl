import java.util.Random;

public class Main {

    static Item getRandomItem() {
        var r = new Random().nextInt(2);

        switch (r) {
            case 0:
                return new Paper();
            case 1:
                return new Rock();
            case 2:
                return new Scissors();
            default:
                System.out.println("ERROR: Random");
        }
        return new Paper();
    }

    static void match(Item a, Item b) {
        System.out.println(a.toString() + " competes with " + b.toString() + " --> " + b.toString() + " " + a.compete(b));
    }

    public static void main(String[] args) {
        match(getRandomItem(), getRandomItem());
    }
}
