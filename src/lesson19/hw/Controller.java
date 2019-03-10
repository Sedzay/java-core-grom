package lesson19.hw;

import java.util.Arrays;

public class Controller {

    public void put(Storage storage, File file) throws Exception {
        if (storage == null) {
            System.err.println("Storage is null");
            return;
        }

        //add file
        if (!checkAddFile(storage, file)) {
            System.err.println("Adding file did not happen");
            return;
        }
        File[] files = storage.getFiles();
        if (files == null) {
            files = new File[]{file};
        } else {
            files = Arrays.copyOf(files, files.length + 1);
            files[files.length - 1] = file;
        }
        storage.setFiles(files);
        System.out.println("File with id " + file.getId() + " add successfully in storage with id " + storage.getId());

    }


    public void delete(Storage storage, File file) throws Exception {
        if (storage == null) {
            System.err.println("Storage is null");
            return;
        }

        if (!checkDeleteFile(storage, file)) {
            System.err.println("Deleting file did not happen");
            return;
        }


        //delete file
        for (int i = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i].equals(file))
                storage.getFiles()[i] = null;
        }
        File[] files = new File[storage.getFiles().length - 1];
        for (int i = 0, j = 0; i < storage.getFiles().length; i++) {
            if (storage.getFiles()[i] == null)
                continue;
            files[j] = storage.getFiles()[i];
            j++;
        }
        storage.setFiles(files);
        System.out.println("File with id " + file.getId() + " delete successfully in storage with id " + storage.getId());

    }


    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        //проверить хранилища пустые или нет
        if (storageFrom == null || storageTo == null) {
            System.err.println("Storage From or Storage To does not exist");
            return;
        }
        //проверить файлы хранилища Из
        for (File file : storageFrom.getFiles()) {
            if (!checkAddFile(storageTo, file) || !checkDeleteFile(storageFrom, file)) {
                System.err.println("Transfer all files did not happen");
                return;
            }
        }
        for (File file : storageFrom.getFiles()) {
            transferFile(storageFrom, storageTo, file.getId());
        }
        System.out.println("Transfer all files in storage with id " + storageTo.getId() + " is done!");

    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        if (storageFrom == null || storageTo == null) {
            System.err.println("Storage From or Storage To does not exist");
            return;
        }
        File file = findFileById(storageFrom, id);
        if (checkDeleteFile(storageFrom, file) && checkAddFile(storageTo, file)) {
            delete(storageFrom, file);
            put(storageTo, file);
            System.out.println("Transfer file with id " + id + " in storage with id " + storageTo.getId() + " is done!");
        }
        else
            System.err.println("File transfer did not happen");
    }


    private boolean checkAddFile(Storage storage, File file) throws Exception {
        if (file == null)
            return false;

        if (!formatIsCorrect(storage, file)) {
            System.err.println("Format is not correct");
            throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because this storage supported formats as " + Arrays.toString(storage.getFormatsSupported()) + ". This file have format " + file.getFormat());
        }

        if (sameFile(storage, file)) {
            System.err.println("Same file already exist");
            throw new Exception("File with id " + file.getId() + " already exists in in storage with id " + storage.getId());
        }

        if (storage.getFilledSize() + file.getSize() > storage.getStorageSize()) {
            System.err.println("Size over");
            throw new Exception("File with id " + file.getId() + " has not been added in storage with id " + storage.getId() + ", because size in storage not enough. Last size is " + (storage.getStorageSize() - storage.getFilledSize()) + ". New file size is " + file.getSize());
        }
        return true;
    }

    private boolean checkDeleteFile(Storage storage, File file) throws Exception {
        if (file == null) {
            System.err.println("file is null");
            return false;
        }

        if (!sameFile(storage, file)) {
            System.err.println("Wrong file");
            throw new Exception("File with id " + file.getId() + " and name " + file.getName() + " has not been deleted in storage with id " + storage.getId() + ", because it is non there");
        }
        return true;
    }

    private boolean sameFile(Storage storage, File file) {
        boolean validation = false;
        if (storage.getFiles() != null) {
            for (File file1 : storage.getFiles()) {
                if (file1.equals(file))
                    validation = true;
            }
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
                if (file1.getId() == id)
                    file = storage.getFiles()[i];
                i++;
            }
        }
        return file;
    }
}
