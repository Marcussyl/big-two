public class BigTwoGUITester {
    private static Deck deck = new Deck();
    public static void main(String[] args){

        BigTwo game = new BigTwo();
//        game.start(deck);
        BigTwoGUI ui = new BigTwoGUI(game);


        ui.repaint();
    }
}
