package gromecode.lesson19.hw;

public class Demo {
    public static void main(String[] args) throws Exception {

        //тест создания файла
        System.out.println("ADD FILES*******************");
        File file1 = new File(10001, "fileEquals", "jpg", 20);
        System.out.println("Count chars of name in file1 = " + file1.getName().length());
        System.out.println(file1);

        //название больше 10 символов
//        File file2 = new File(10002, "file1More10Chars", "jpg", 12);
//        System.out.println(file2);

        //добавление storage
        //Поддерживаемые форматы
        Controller controller = new Controller();
        Storage storage1 = new Storage(101, new File[3], new String[]{"txt", "jpg", "png"}, "USA", 70);


        System.out.println();
        System.out.println("ADD FILES IN STORAGE*******************");
        //добавить файл
        try {
            controller.put(storage1, file1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        //добавить еще один файл с непддерживаемым форматом
        File file2 = new File(10002, "file2", "xls", 20);
        try {
            controller.put(storage1, file2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //добавить еще один файл, выйти за размеры хранилища
        File file3 = new File(10003, "file3", "txt", 100);
        try {
            System.out.println(storage1);
            controller.put(storage1, file3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //добавить еще один нормальный файл
        File file4 = new File(10004, "file4", "txt", 20);
        try {
            controller.put(storage1, file4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        //добавить файл с таким же ИД
        File file5 = new File(10001, "fileEquals", "png", 20);
        try {
            controller.put(storage1, file5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //добавить файл с другим ИД и таким же Именем
        File file6 = new File(10006, "fileEquals", "txt", 20);
        try {
            controller.put(storage1, file6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        //добавить лишний файл
        File file7 = new File(10007, "file7", "txt", 10);
        try {
            controller.put(storage1, file7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        System.out.println("DELETE FILES IN STORAGE*******************");


        //удалить не существующий файл
        try {
            controller.delete(storage1, file3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);


        //удалить файл с таким же ИД, но другим именем
        file7 = new File(10001, "file1", "jpg", 12);
        try {
            controller.delete(storage1, file7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        //удалить файл с другим ИД, но таким же именем
        File file8 = new File(10008, "fileEquals", "txt", 20);
        try {
            controller.delete(storage1, file8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        //удалить файл
        try {
            controller.delete(storage1, file4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        System.out.println("Size is " + storage1.getFilledSize());

        //вернуть файл обратно в хранилище
        try {
            controller.put(storage1, file4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);

        System.out.println();
        System.out.println("TRANSFER ALL FILES IN STORAGE TO*******************");


        Storage storage2 = new Storage(102, new File[5], new String[]{"txt", "xls", "jpg"}, "UK", 80);

        // тестирование перемещения всех файлов

        System.out.println(storage1);
        System.out.println(storage2);
        try {
            controller.transferAll(storage1, storage2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        System.out.println(storage2);

        System.out.println();

        //не дстаточно места во втором хранилище
        try {
            controller.put(storage2, file2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage2);

        //разный формат
        System.out.println();
        try {
            controller.transferAll(storage2, storage1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        System.out.println(storage2);

        //еще одно хранилище, куда все можно переместить
        //не достаточно пустых мест
        Storage storage3 = new Storage(103, new File[5], new String[]{"txt", "xls", "jpg", "png"}, "UA", 1000);
        try {
            controller.transferAll(storage1, storage3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage3);


        try {
            controller.transferAll(storage2, storage3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        System.out.println(storage2);
        System.out.println(storage3);
        System.out.println();

        try {
            controller.put(storage3, file8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //переместить не хватает памяти
        System.out.println(storage3.getFilledSize());
        try {
            controller.transferAll(storage3, storage2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //переместить в хранилище если не хватает размера
        Storage storage4 = new Storage(104, new File[4], new String[]{"txt", "xls", "jpg", "png"}, "UA2", 64);
        try {
            controller.transferAll(storage3, storage4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage3);
        System.out.println(storage4);

        System.out.println();
        System.out.println("TRANSFER FILE BY ID IN STORAGE TO*******************");
        //нет такого файла
        try {
            controller.transferFile(storage1, storage3, 10001);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            controller.transferFile(storage3, storage1, 101);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //формат не подходит
        try {
            controller.transferFile(storage3, storage1, 10002);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //не достаточо памяти
        try {
            controller.put(storage1,  file3);
            controller.transferFile(storage1, storage4,10003);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //все нормально
        try {
            controller.transferFile(storage3, storage1, 10006);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(storage1);
        System.out.println(storage2);
        System.out.println(storage3);
        System.out.println(storage4);

    }
}
