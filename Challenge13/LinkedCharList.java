import java.util.ArrayList;


/**
 * Challenge 13 - LinkedCharList
 * 
 * This class creates a linked list
 * with various funcitons based on the
 * data provided. 
 * 
 * @author Brady Williams, L23
 *
 * @version April 14, 2024
 * 
 */


public class LinkedCharList implements LinkedCharListInterface {
    private CharNode headNode;

    public LinkedCharList() {
        this.headNode = null;
    }

    @Override
    public void constructCharList(char[] listChars) {
        if (listChars == null || listChars.length == 0) {
            return;
        }
        headNode = new CharNode(listChars[0]);
        CharNode tempNode = headNode;
        for (int i = 1; i < listChars.length; i++) {
            CharNode newNode = new CharNode(listChars[i]);
            tempNode.setNextNode(newNode);
            tempNode = newNode;
        }
    }

    @Override
    public void addChar(char c) {
        CharNode newNode = new CharNode(c);
        if (headNode == null) {
            headNode = newNode;
        } else {
            CharNode tempNode = headNode;
            while (tempNode.getNextNode() != null) {
                tempNode = tempNode.getNextNode();
            }
            tempNode.setNextNode(newNode);
        }
    }

    @Override
    public void insertCharAt(char c, int index) {
        CharNode newNode = new CharNode(c);
        if (index == 0 || headNode == null) {
            newNode.setNextNode(headNode);
            headNode = newNode;
        } else {
            CharNode tempNode = headNode;
            for (int i = 1; i < index && tempNode.getNextNode() != null; i++) {
                tempNode = tempNode.getNextNode();
            }
            newNode.setNextNode(tempNode.getNextNode());
            tempNode.setNextNode(newNode);
        }
    }

    @Override
    public char getCharAt(int index) {
        if (headNode == null) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, 0));
        }
        CharNode tempNode = headNode;
        for (int i = 1; i <= index; i++) {
            if (tempNode.getNextNode() == null) {
                throw new IndexOutOfBoundsException(String.format("Index %d out of bounds for length %d", index, i));
            }
            tempNode = tempNode.getNextNode();
        }
        return tempNode.getC();
    }

    @Override
    public LinkedCharList[] splitLinkedCharList(char regex) {
        ArrayList<LinkedCharList> resultList = new ArrayList<>();
        CharNode tempNode = headNode;
        CharNode startNode = null;
        boolean nonRegexFound = false;

        while (tempNode != null) {
            if (tempNode.getC() == regex) {
                if (startNode != null) {
                    LinkedCharList newList = new LinkedCharList();
                    newList.headNode = startNode;
                    CharNode prev = startNode;
                    while (prev.getNextNode() != tempNode) {
                        prev = prev.getNextNode();
                    }
                    prev.setNextNode(null);
                    resultList.add(newList);
                    startNode = null; 
                    nonRegexFound = false;
                } else {
                    resultList.add(new LinkedCharList());
                }
                if (nonRegexFound) {
                    resultList.add(new LinkedCharList());
                }
            } else {
                if (startNode == null) {
                    startNode = tempNode;  
                }
                nonRegexFound = true;
            }
            tempNode = tempNode.getNextNode();
        }

        if (startNode != null) {
            LinkedCharList newList = new LinkedCharList();
            newList.headNode = startNode;
            resultList.add(newList);
        }

        int counter = 0;
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).headNode == null) {
                counter++;
            }
        }

        if (counter == resultList.size()) {
            return null;
        } 
    
        LinkedCharList[] resultArray = new LinkedCharList[resultList.size()];
        return resultList.toArray(resultArray);
        
    }



}
