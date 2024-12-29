import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FolderFilesDestroyer{
    public static void main(String[] args){
        JOptionPane.showMessageDialog(null, "эта программа перезапишет все файлы в дирректории запуска(перезапишет на рандом)");
        int reply = JOptionPane.showConfirmDialog(
                null,
                "are you sure!?!",
                "Destroy files?",
                JOptionPane.YES_NO_OPTION
        );
        if (reply == JOptionPane.YES_OPTION) {
            destroyer();
            JOptionPane.showMessageDialog(null, "destroyed");
        }
    }
    public static void destroyer(){
        File folder = new File(".");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                byte[] fileBytes = getFileBytes(file);
                for (int i = 0; i < fileBytes.length; i++){
                    fileBytes[i] = (byte) i;
                }
                write(file, fileBytes);
            }
        }
    }
    public static byte[] getFileBytes(File file){
        byte[] buff = null;
        try(InputStream inputStream = new FileInputStream(file)) {
            buff = inputStream.readAllBytes();
        }catch (Exception ex){
            System.out.println("public static byte[] getFileBytes(File file){");
            System.out.println(ex);
        }
        return buff;
    }
    public static void write(File file, byte[] bytes){
        try(FileOutputStream fon = new FileOutputStream(file)){
            fon.write(bytes);
        }catch (Exception ex){
            System.out.println("public static void write(String fileName, byte[] bytes){");
            System.out.println(ex);
        }
    }
}