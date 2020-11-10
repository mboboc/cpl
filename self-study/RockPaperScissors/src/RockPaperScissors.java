enum Outcome { WIN, LOSE, DRAW };

interface Item {
    Outcome compete(Item item);
    Outcome eval(Paper paper);
    Outcome eval(Rock rock);
    Outcome eval(Scissors scissors);
    String toString();
}

class Paper implements Item {

    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Rock rock) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Scissors scissors) {
        return Outcome.LOSE;
    }

    @Override
    public String toString() {
        return "Paper";
    }
}

class Rock implements Item {

    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Rock rock) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Scissors scissors) {
        return Outcome.WIN;
    }

    @Override
    public String toString() {
        return "Rock";
    }
}

class Scissors implements Item {
    @Override
    public Outcome compete(Item item) {
        return item.eval(this);
    }

    @Override
    public Outcome eval(Paper paper) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Rock rock) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Scissors scissors) {
        return Outcome.DRAW;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}