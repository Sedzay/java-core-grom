package lesson19.hw;

import java.util.Arrays;

public class Controller {

    public void put(Storage storage, File file) throws Exception {
        if (storage == null || file == null) {
            System.err.println("Storage or file is null");
            return;
        }

        checkAddFile(storage, file);
        //add file
        for (int i = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i] == null) {
                storage.getFiles()[i] = file;
                break;
            }
        }
        System.out.println("File with id " + file.getId() + " add successfully in storage with id " + storage.getId());
    }


    public void delete(Storage storage, File file) throws Exception {
        if (storage == null || file == null) {
            System.err.println("Storage or file is null");
            return;
        }

        checkDeleteFile(storage, file);

        //delete file
        for (int i = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i] != null && storage.getFiles()[i].equals(file))
                storage.getFiles()[i] = null;
        }
        System.out.println("File with id " + file.getId() + " delete successfully in storage with id " + storage.getId());
    }


    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        //проверить хранилища пустые или нет
        if (storageFrom == null || storageTo == null) {
            System.err.println("Storage From or Storage To does not exist");
            return;
        }

        checkTransferAllFiles(storageFrom, storageTo);

        for (int i = 0; i < storageFrom.getFiles().length; i++) {
            for (int j = 0; j < storageTo.getFiles().length; j++) {
                if(storageFrom.getFiles()[i] != null && storageTo.getFiles()[j] == null) {
                    storageTo.getFiles()[j] = storageFrom.getFiles()[i];
                    storageFrom.getFiles()[i] = null;
                    break;
                }
            }
        }

        System.out.println("Transfer all files in storage with id " + storageTo.getId() + " is done!");

    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        if (storageFrom == null || storageTo == null) {
            System.err.println("Storage From or Storage To does not exist");
            return;
        }
        File file = findFileById(storageFrom, id);
        if(file == null) {
            System.err.println("File with id " + id + " not found");
            return;
        }
        checkDeleteFile(storageFrom, file);
        checkAddFile(storageTo, file);
        delete(storageFrom, file);
        put(storageTo, file);
        System.out.println("Transfer file with id " + id + " in storage with id " + storageTo.getId() + " is done!");

    }

    private void checkAddFile(Storage storage, File file) throws Exception {
        if (!formatIsCorrect(storage, file)) {
            //System.err.println("Format is not correct");
            throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because this storage supported formats as " + Arrays.toString(storage.getFormatsSupported()) + ". This file have format " + file.getFormat());
        }

        if (storage.getFiles() != null) {

            if (sameFile(storage, file)) {
                //System.err.println("Same file already exist");
                throw new Exception("File with id " + file.getId() + " already exists in in storage with id " + storage.getId());
            }

            boolean checkFreePlace = false;
            for (File file1 : storage.getFiles()) {
                if (file1 == null) {
                    checkFreePlace = true;
                }
            }
            if (!checkFreePlace)
                throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because storage is full");
        }

        if (storage.getFilledSize() + file.getSize() > storage.getStorageSize()) {
            //System.err.println("Size over");
            throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because size in storage not enough. Last size is " + (storage.getStorageSize() - storage.getFilledSize()) + ". New file size is " + file.getSize());
        }
    }

    private void checkDeleteFile(Storage storage, File file) throws Exception {
        if (storage.getFiles() != null) {
            if (!sameFile(storage, file)) {
                //System.err.println("Wrong file");
                throw new Exception("File with id " + file.getId() + " and name " + file.getName() + " has not been deleted in storage with id " + storage.getId() + ", because it is non there");
            }
            return;
        }
    }

    private void checkTransferAllFiles(Storage storageFrom, Storage storageTo) throws Exception {
        //проверить нет ли такого же файла в другом хранилище
        //посчитать количество файлов для перемещения
        if (storageTo.getFiles() != null && storageFrom.getFiles() != null) {
            int countFilesToTransfer = 0;
            int countFreePlaceToTransfer = 0;
            for (File file : storageTo.getFiles()) {
                if (file != null)
                    checkDeleteFile(storageFrom, file);
                else
                    countFreePlaceToTransfer++;
            }

            //проверить возможность добавления файлов
            //посчитать количество пустых мест

            for (File file : storageFrom.getFiles()) {
                if (file != null) {
                    checkAddFile(storageTo, file);
                    countFilesToTransfer++;
                }
            }
            //проверить хватает ли пустых мест для перемещения
            if (countFreePlaceToTransfer < countFilesToTransfer)
                throw new Exception("Can not to transfer all files, because not enough free place in storage with id " + storageTo.getId());

            //проверить достатчноли места для перемещения
            if (storageTo.getStorageSize() - storageTo.getFilledSize() < storageFrom.getFilledSize())
                throw new Exception("Can not to transfer all files, because not enough size in storage with id " + storageTo.getId());
        }
    }

    private boolean sameFile(Storage storage, File file) {
        boolean validation = false;
        for (File file1 : storage.getFiles()) {
            if (file1 != null && file1.equals(file))
                validation = true;
        }

        return validation;
    }

    private boolean formatIsCorrect(Storage storage, File file) {
        boolean validation = false;
        for (String format : storage.getFormatsSupported()) {
            if (file.getFormat() == format)
                validation = true;
        }
        return validation;
    }

    private File findFileById(Storage storage, long id) {
        File file = null;
        if (storage.getFiles() != null) {
            int i = 0;
            for (File file1 : storage.getFiles()) {
                if (file1 != null && file1.getId() == id)
                    file = storage.getFiles()[i];
                i++;
            }
        }
        return file;
    }
}
