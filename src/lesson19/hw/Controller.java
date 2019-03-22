package lesson19.hw;

import java.util.Arrays;

public class Controller {

    public File put(Storage storage, File file) throws Exception {

        checkAddFile(storage, file);
        //add file
        for (int i = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i] == null) {
                storage.getFiles()[i] = file;
                System.out.println("File with id " + file.getId() + " add successfully in storage with id " + storage.getId());
                return storage.getFiles()[i];
            }
        }
        return null;
    }


    public void delete(Storage storage, File file) throws Exception {

        checkDeleteFile(storage, file);

        //delete file
        for (int i = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i] != null && storage.getFiles()[i].equals(file))
                storage.getFiles()[i] = null;
        }
        System.out.println("File with id " + file.getId() + " delete successfully in storage with id " + storage.getId());
    }


    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        checkTransferAllFiles(storageFrom, storageTo);

        for (int i = 0; i < storageFrom.getFiles().length; i++) {
            for (int j = 0; j < storageTo.getFiles().length; j++) {
                if (storageFrom.getFiles()[i] != null && storageTo.getFiles()[j] == null) {
                    storageTo.getFiles()[j] = storageFrom.getFiles()[i];
                    storageFrom.getFiles()[i] = null;
                    break;
                }
            }
        }

        System.out.println("Transfer all files in storage with id " + storageTo.getId() + " is done!");
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {

        File file = findFileById(storageFrom, id);
        checkDeleteFile(storageFrom, file);
        checkAddFile(storageTo, file);
        delete(storageFrom, file);
        put(storageTo, file);
        System.out.println("Transfer file with id " + id + " in storage with id " + storageTo.getId() + " is done!");

    }

    private void checkAddFile(Storage storage, File file) throws Exception {
        formatIsCorrect(storage, file);
        sameFile(storage, file);
        checkFreePlace(storage, file);
        if (storage.getFilledSize() + file.getSize() > storage.getStorageSize())
            throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because size in storage not enough. Last size is " + (storage.getStorageSize() - storage.getFilledSize()) + ". New file size is " + file.getSize());

    }

    private void checkDeleteFile(Storage storage, File file) throws Exception {
        for (File file1 : storage.getFiles()) {
            if (file1 != null && file1.equals(file))
                return;
        }
        throw new Exception("File with id " + file.getId() + " and name " + file.getName() + " has not been deleted in storage with id " + storage.getId() + ", because it is non there");
    }

    private void checkTransferAllFiles(Storage storageFrom, Storage storageTo) throws Exception {
        //найти файлы для перемещения
        //проверить возможность добавления файлов в другое хранилище
        //посчитать количество файлов для перемещения

        int countFilesToTransfer = 0;
        for (File file : storageFrom.getFiles()) {
            if (file != null) {
                formatIsCorrect(storageTo, file);
                sameFile(storageTo, file);
                countFilesToTransfer++;
            }
        }

        if (countFilesToTransfer == 0)
            throw new Exception("In storage with id " + storageFrom.getId() + " has not files for transfer");

        //проверить другое хранилище на наличие пустых мест
        //посчитать количество пустых мест
        int countFreePlaceToTransfer = 0;
        for (File file : storageTo.getFiles()) {
            if (file == null)
                countFreePlaceToTransfer++;
        }

        //проверить хватает ли пустых мест для перемещения
        if (countFreePlaceToTransfer < countFilesToTransfer)
            throw new Exception("Can not to transfer all files, because not enough free place in storage with id " + storageTo.getId());

        //проверить достатчноли места для перемещения
        if (storageTo.getStorageSize() - storageTo.getFilledSize() < storageFrom.getFilledSize())
            throw new Exception("Can not to transfer all files, because not enough size in storage with id " + storageTo.getId());

    }


    private void formatIsCorrect(Storage storage, File file) throws Exception {
        for (String format : storage.getFormatsSupported()) {
            if (file.getFormat().equals(format))
                return;
        }
        throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because this storage supported formats as " + Arrays.toString(storage.getFormatsSupported()) + ". This file have format " + file.getFormat());
    }

    private void sameFile(Storage storage, File file) throws Exception {
        for (File file1 : storage.getFiles()) {
            if (file1 != null && file1.getId() == file.getId())
                throw new Exception("File with id " + file.getId() + " already exists in in storage with id " + storage.getId());
        }
    }

    private void checkFreePlace(Storage storage, File file) throws Exception {
        for (File file1 : storage.getFiles()) {
            if (file1 == null) {
                return;
            }
        }
        throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because storage is full");
    }

    private File findFileById(Storage storage, long id) throws Exception {
        if (storage.getFiles() != null) {
            int i = 0;
            for (File file1 : storage.getFiles()) {
                if (file1 != null && file1.getId() == id)
                    return storage.getFiles()[i];
                i++;
            }
        }
        throw new Exception("File with id " + id + " not found in storage with id " + storage.getId());
    }
}
