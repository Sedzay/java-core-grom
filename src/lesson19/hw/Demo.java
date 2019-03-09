package lesson19.hw;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class Demo {
    public static void main(String[] args) throws Exception {

        //тест создания файла
        System.out.println("ADD FILES*******************");
        File file1 = new File(10001, "fileEquals", "jpg", 12);
        System.out.println("Count chars of name in file1 = " + file1.getName().length());
        System.out.println(file1);

        //название больше 10 символов
//        File file2 = new File(10002, "file1More10Chars", "jpg", 12);
//        System.out.println(file2);

        //добавление storage
        //Поддерживаемые форматы
        String[] supportedFormates = {"txt", "jpg", "png"};
        Storage storage1 = new Storage(101, null, supportedFormates, "USA", 100);

        System.out.println();
        System.out.println("ADD FILES IN STORAGE*******************");
        //добавить файл
        Controller controller = new Controller();
        controller.put(storage1, file1);


        //добавить еще один файл с непддерживаемым форматом
        File file2 = new File(10002, "file2", "xls", 12);
        controller.put(storage1, file2);


        //добавить еще один файл, выйти за размеры хранилища
        File file3 = new File(10003, "file3", "txt", 100);
        controller.put(storage1, file3);

        //добавить еще один нормальный файл
        File file4 = new File(10004, "file4", "png", 20);
        controller.put(storage1, file4);

        //добавить файл с таким же ИД и Именем
        File file5 = new File(10001, "fileEquals", "png", 20);
        controller.put(storage1, file5);

        //добавить файл с другим ИД и таким же Именем
        File file6 = new File(10006, "fileEquals", "txt", 20);
        controller.put(storage1, file6);

        System.out.println();
        System.out.println(storage1);

        System.out.println();
        System.out.println("DELETE FILES IN STORAGE*******************");

        //удалить не существующий файл
        controller.delete(storage1, file3);
        System.out.println(storage1);

        //удалить файл с таким же ИД, но другим именем
        File file7 = new File(10001, "file1", "jpg", 12);
        controller.delete(storage1, file7);
        System.out.println(storage1);

        //удалить файл с другим ИД, но таким же именем
        File file8 = new File(10008, "fileEquals", "txt", 20);
        controller.delete(storage1, file8);
        System.out.println(storage1);

        //удалить файл
        controller.delete(storage1, file4);
        System.out.println(storage1);
        System.out.println(storage1.getFilledSize());

        System.out.println();
        System.out.println("TRANSFER ALL FILES IN STORAGE TO*******************");

        Storage storage2 = null;
        controller.transferAll(storage1, storage2);
        controller.transferAll(storage2, storage1);

        storage2 = new Storage(102, null, new String[]{"txt", "xls"}, "UK", 1000);

        // тестирование перемещения всех файлов
        //не хватает места для перемещения
        controller.transferAll(storage1,storage2);
        System.out.println();
        controller.put(storage2, file2);
        controller.put(storage2,file8);
        System.out.println();
        //разный формат
        controller.transferAll(storage2,storage1);
        System.out.println();
        System.out.println(storage1);
        System.out.println(storage2);
        System.out.println();

        //еще одно хранилище, куда все можно переместить
        Storage storage3 = new Storage(103, null, new String[]{"txt", "xls", "jpg", "png"},"UA", 1000);
        controller.transferAll(storage1,storage3);
        controller.transferAll(storage2,storage3);
        System.out.println(storage1);
        System.out.println(storage2);
        System.out.println(storage3);
        System.out.println();

        System.out.println();
        System.out.println("TRANSFER FILE BY ID IN STORAGE TO*******************");
        //нет такого файла
        controller.transferFile(storage1,storage3,10001);
        controller.transferFile(storage3,storage1,101);
        //формат не подходит
        controller.transferFile(storage3,storage1,10002);
        //все нормально
        controller.transferFile(storage3,storage1,10001);
    }

}
