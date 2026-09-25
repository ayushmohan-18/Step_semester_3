package abstraction.class_problems;

abstract class Instrument {

    public Instrument() {
    }

    public String play() {
        return "Strumming the strings";
    }
}

class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return super.play();
    }
}

class Violin extends StringInstrument {

    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class warmup {

    public static void main(String[] args) {

        StringInstrument s = new StringInstrument();
        Violin v = new Violin();

        System.out.println(s.play());
        System.out.println(v.play());
    }
}