import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void zipFiles() {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/Users/archr/Games/savegames/GameProgress.zip"))) {
            File[] files = new File("/Users/archr/Games/savegames").listFiles();
            for (File file : files) {
                if (file.getName().contains(".dat")) {
                    System.out.println(file.getName());
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    // считываем содержимое файла в массив byte
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    // добавляем содержимое к архиву
                    zout.write(buffer);
                    //удаляем исходный файл
                    file.delete();
                    // закрываем текущую запись для новой записи
                    zout.closeEntry();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveGame(String fileName) {
        try (ObjectOutputStream saveGameProgress = new ObjectOutputStream(new FileOutputStream("/Users/archr/Games/savegames/" + fileName + ".dat"))) {
            saveGameProgress.writeObject(GameProgress.this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}