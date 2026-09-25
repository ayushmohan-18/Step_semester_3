package abstraction.assignment_problems;

abstract class ArtPiece {

    private static int counter = 1;
    private final String pieceId;

    public ArtPiece() {
        pieceId = String.valueOf(counter++);
    }

    public String getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {

    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {

    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}

public class gallery {

    public static void main(String[] args) {

        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");

        System.out.println(p.describe());
        System.out.println(s.describe());

        System.out.println("Painting ID: " + p.getPieceId());
        System.out.println("Sculpture ID: " + s.getPieceId());
    }
}