package lesson18.exercise1;

public class Task {

    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage();
        PictureStorage pictureStorage = new PictureStorage();
        CodeStorage codeStorage = new CodeStorage();
        String[] fileName = {"test1", "test2"};
        String[] pictureName = {"test1", "test2","test3"};
        String[] codeName = {"test1"};

        fileStorage.setFiles(fileName);
        pictureStorage.setFiles(pictureName);
        codeStorage.setFiles(codeName);

        System.out.println("Start printing name...");
        printer(fileStorage);
        printer(pictureStorage);
        printer(codeStorage);

        System.out.println("Start printing name...");
        printer(fileStorage);
        printer(pictureStorage);
        printer(codeStorage);

        System.out.println("Done");

    }


    public static void printer(Storage storage) {
        String[] names = storage.getFiles();

        try {
            System.out.println("5th name is " + names[4]);
        }catch (Exception e) {
            System.out.println("5h name can not be found");
            //System.out.println(e.getMessage());

        }


    }

}
