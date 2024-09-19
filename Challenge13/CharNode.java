public class CharNode {
    private char c;
    private CharNode nextNode;

    public CharNode(char c) {
        this.c = c;
    }

    public char getC() {
        return this.c;
    }

    public CharNode getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(CharNode nextChar) {
        this.nextNode = nextChar;
    }
}
