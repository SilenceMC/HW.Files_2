public class Main {

    public static void main(String[] args) {
        GameProgress newbieGamer = new GameProgress(100, 2, 1, 1.0);
        GameProgress mediumGamer = new GameProgress(70, 4, 35, 100.7);
        GameProgress hardGamer = new GameProgress(40, 7, 68, 359.2);

        newbieGamer.saveGame("newbieGamer");
        mediumGamer.saveGame("mediumGamer");
        hardGamer.saveGame("hardGamer");

        GameProgress.zipFiles();
    }

}
